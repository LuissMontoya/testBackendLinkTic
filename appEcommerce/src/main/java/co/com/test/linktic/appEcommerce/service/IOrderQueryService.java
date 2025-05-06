package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IOrderQueryService {
    ResponseEntity<ResponseDTO> getAll();
    ResponseEntity<ResponseDTO> findOrderById(Integer id);
}
