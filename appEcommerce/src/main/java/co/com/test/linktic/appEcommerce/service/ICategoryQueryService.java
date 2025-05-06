package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICategoryQueryService {
    ResponseEntity<ResponseDTO> getAll();
    ResponseEntity<ResponseDTO> findCategoryById(Integer id);
}
