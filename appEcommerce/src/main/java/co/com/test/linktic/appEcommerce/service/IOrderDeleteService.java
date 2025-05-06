package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IOrderDeleteService {
    ResponseEntity<ResponseDTO> delete(Integer id);
}
