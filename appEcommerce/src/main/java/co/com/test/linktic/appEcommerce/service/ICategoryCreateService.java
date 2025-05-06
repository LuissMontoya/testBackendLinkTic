package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.CategoryDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICategoryCreateService {
    ResponseEntity<ResponseDTO> saveCategory(CategoryDTO categoryDTO);
}
