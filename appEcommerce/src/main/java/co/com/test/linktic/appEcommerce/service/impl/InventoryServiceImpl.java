package co.com.test.linktic.appEcommerce.service.impl;

import co.com.test.linktic.appEcommerce.DTO.ProductDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.entity.Product;
import co.com.test.linktic.appEcommerce.repositories.InventoryRepository;
import co.com.test.linktic.appEcommerce.repositories.ProductRepository;
import co.com.test.linktic.appEcommerce.service.IInventoryService;
import co.com.test.linktic.appEcommerce.utils.Constants;
import co.com.test.linktic.appEcommerce.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements IInventoryService {

	private final InventoryRepository inventoryRepository;
	private final ProductRepository productRepository;
	private final RestTemplate restTemplate;

	@Value("${products.service.url}")
	private String productServiceUrl;

	@Override
	public ResponseEntity<ResponseDTO> getInventoryDetails(Integer productId, String token) {
		log.info("getInventoryDetails - productId: {}", productId);
		ResponseDTO response;

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + token);


			RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET,
					URI.create(productServiceUrl + productId));

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ResponseDTO> productResponseEntity = restTemplate.exchange(
					requestEntity, ResponseDTO.class);

			ResponseDTO productResponse = productResponseEntity.getBody();
			log.info("Respuesta del servicio de productos: {}", productResponse);

			List<?> rawList = (List<?>) productResponse.getObjectResponse();

			if (rawList == null || rawList.isEmpty()) {
				throw new RuntimeException(Constants.PRODUCTO_NO_ENCONTRADO);
			}

			Map<?, ?> productMap = (Map<?, ?>) rawList.get(0);

			ProductDTO product = new ProductDTO();
			product.setId((Integer) productMap.get("id"));
			product.setName((String) productMap.get("name"));
			product.setDescription((String) productMap.get("description"));
			product.setPrice((Double) productMap.get("price"));
			product.setStock((Integer) productMap.get("stock"));

			Product product_end = productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException(Constants.PRODUCTO_NO_ENCONTRADO));

			Map<String, Object> responseBody = new HashMap<>();
			responseBody.put("producto", product);
			responseBody.put("cantidad_disponible", product_end.getStock());

			response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(), responseBody);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (HttpClientErrorException.NotFound e) {
			log.error("Product not found: {}", e.getMessage());
			response = Utils.mapearRespuesta(Constants.PRODUCTO_NO_ENCONTRADO, HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			log.error("Error: {}", e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
	}

	@Override
	public void updateQuantity(int productId, int quantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException(Constants.PRODUCTO_NO_ENCONTRADO));
		product.setStock(product.getStock() - quantity);
		productRepository.save(product);

		emitEventChange(product);
	}

	private void emitEventChange(Product product) {
		log.info("Inventario actualizado para producto ID " + product.getId() +
				", nueva cantidad: " + product.getStock());
	}


}
