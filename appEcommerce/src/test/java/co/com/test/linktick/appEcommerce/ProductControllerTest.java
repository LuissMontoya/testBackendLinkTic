package co.com.test.linktick.appEcommerce;

import co.com.test.linktic.appEcommerce.AppEcommerceApplication;
import co.com.test.linktic.appEcommerce.DTO.ProductDTO;
import co.com.test.linktic.appEcommerce.repositories.CategoryRepository;
import co.com.test.linktic.appEcommerce.service.impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;


@SpringBootTest(classes = AppEcommerceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductServiceImpl productsService;

    private ProductDTO productDTO;

    @Test
    public void Cuando_se_llama_a_productos_el_estado_es_200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/product/search?id=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void crearProductoDeberiaAlmacenarCorrectamenteYRetornarProducto() throws Exception {
        ProductDTO productoDTO = new ProductDTO();
        productoDTO.setName("Silla 1");
        productoDTO.setDescription("Silla ergonómica");
        productoDTO.setPrice(430.000);
        productoDTO.setStock(45);
        productoDTO.setCategory_id(1);

        MvcResult resultadoProductoCreado = mvc.perform(
                        MockMvcRequestBuilders.post("http://localhost:8080/api/product/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode", is(201)))
                .andExpect(jsonPath("$.message", is("CREATED")))
                .andExpect(jsonPath("$.objectResponse.id", notNullValue()))
                .andExpect(jsonPath("$.objectResponse.name", is("Silla 1")))
                .andExpect(jsonPath("$.objectResponse.description", is("Silla ergonómica")))
                .andExpect(jsonPath("$.objectResponse.price", is(430.0)))
                .andExpect(jsonPath("$.objectResponse.stock", is(45)))
                .andExpect(jsonPath("$.objectResponse.category.id", is(1)))
                .andExpect(jsonPath("$.objectResponse.category.name", is("Computadoras")))
                .andExpect(jsonPath("$.objectResponse.category.status", is("ACTIVO")))
                .andReturn();
    }

    @Test
    public void actualizarProductoDeberiaModificarYRetornarProductoActualizado() throws Exception {

        ProductDTO productoInicial = new ProductDTO();
        productoInicial.setName("Silla Original");
        productoInicial.setDescription("Silla básica");
        productoInicial.setPrice(300.000);
        productoInicial.setStock(10);
        productoInicial.setCategory_id(1);

        MvcResult resultadoCreado = mvc.perform(MockMvcRequestBuilders.post("/api/product/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoInicial)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = resultadoCreado.getResponse().getContentAsString();
        JsonNode root = objectMapper.readTree(response);
        int idCreado = root.path("objectResponse").path("id").asInt();

        ProductDTO productoActualizado = new ProductDTO();
        productoActualizado.setId(idCreado);
        productoActualizado.setName("Silla Actualizada");
        productoActualizado.setDescription("Silla ergonómica pro");
        productoActualizado.setPrice(500.000);
        productoActualizado.setStock(20);
        productoActualizado.setCategory_id(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/product/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is(200)))
                .andExpect(jsonPath("$.message", is("OK")))
                .andExpect(jsonPath("$.objectResponse.id", is(idCreado)))
                .andExpect(jsonPath("$.objectResponse.name", is("Silla Actualizada")))
                .andExpect(jsonPath("$.objectResponse.description", is("Silla ergonómica pro")))
                .andExpect(jsonPath("$.objectResponse.price", is(500.0)))
                .andExpect(jsonPath("$.objectResponse.stock", is(20)))
                .andExpect(jsonPath("$.objectResponse.category.id", is(1)));
    }

    @Test
    public void actualizarProductoNoExistenteDeberiaRetornarErrorNotFound() throws Exception {
        ProductDTO productoInexistente = new ProductDTO();
        productoInexistente.setId(9999);
        productoInexistente.setName("Producto Fantasma");
        productoInexistente.setDescription("No debería existir");
        productoInexistente.setPrice(100.0);
        productoInexistente.setStock(0);
        productoInexistente.setCategory_id(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/product/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productoInexistente)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.objectResponse", containsString("producto no fue encontrado")));
    }

    @Test
    public void errorInternoAlActualizarProductoDeberiaRetornarError() throws Exception {
        ProductDTO producto = new ProductDTO();
        producto.setId(null);
        producto.setName(null);
        producto.setDescription("Sin nombre");
        producto.setPrice(200.0);
        producto.setStock(5);
        producto.setCategory_id(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/product/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.statusCode",is(400)))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void erroAlConsultarProductoDeberiaRetornarMsgNOT_FOUND() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/product/search?id=999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message", is("NOT_FOUND")));
    }


}
