package co.com.test.linktic.appEcommerce.controllers;

import co.com.test.linktic.appEcommerce.service.IProductCreateService;
import co.com.test.linktic.appEcommerce.service.IProductDeleteService;
import co.com.test.linktic.appEcommerce.service.IProductQueryService;
import co.com.test.linktic.appEcommerce.service.IProductUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.test.linktic.appEcommerce.DTO.ProductDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.utils.Constants;
import co.com.test.linktic.appEcommerce.utils.RespErrorMessage;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT })
@RequiredArgsConstructor
public class ProductController {

	private final IProductCreateService createService;
	private final IProductUpdateService updateService;
	private final IProductQueryService findService;
	private final IProductDeleteService deleteService;

	@ApiResponses(value = {
			@ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTO Product) {
		return this.createService.saveProduct(Product);
	}

	@GetMapping("/search")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	public ResponseEntity<ResponseDTO> findProductById(@RequestParam("id") Integer id) {
		return this.findService.findProductById(id);
	}

	@GetMapping("/getAll")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	public ResponseEntity<ResponseDTO> getAll() {
		return this.findService.getAll();
	}

	@GetMapping("/getByPage")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	public ResponseEntity<ResponseDTO> getAllPaginate(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		return this.findService.getAllPaginate(page, size);
	}


	@PutMapping("/update")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
			@ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
		if (result.hasErrors()) {
			String errores = result.getFieldErrors().stream()
					.map(e -> e.getField() + ": " + e.getDefaultMessage())
					.collect(Collectors.joining(", "));

			return ResponseEntity.badRequest().body(new ResponseDTO(400, errores, null));
		}
		return this.updateService.updateProduct(productDTO);
	}
	
	 @DeleteMapping("/delete")
	    @ApiResponses(value = {
	            @ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
	            @ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
	            @ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
	            @ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
	    public ResponseEntity<ResponseDTO> delete(@RequestParam("id") Integer id) {
	    	return this.deleteService.delete(id);
	    	}


}
