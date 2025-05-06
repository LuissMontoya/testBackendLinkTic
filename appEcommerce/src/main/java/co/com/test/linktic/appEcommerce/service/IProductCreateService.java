package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ProductDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IProductCreateService {
    ResponseEntity<ResponseDTO> saveProduct(ProductDTO productDTO);
}
