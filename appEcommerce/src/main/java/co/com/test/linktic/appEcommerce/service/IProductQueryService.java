package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IProductQueryService {
    ResponseEntity<ResponseDTO> getAll();
    ResponseEntity<ResponseDTO> getAllPaginate(int page, int size);
    ResponseEntity<ResponseDTO> findProductById(Integer id);
}