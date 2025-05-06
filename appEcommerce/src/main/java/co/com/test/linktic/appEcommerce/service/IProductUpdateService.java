package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ProductDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IProductUpdateService {
    ResponseEntity<ResponseDTO> updateProduct(ProductDTO productDTO);
}
