package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.entity.Users;
import org.springframework.http.ResponseEntity;

public interface IUserQueryService {
    ResponseEntity<ResponseDTO> getAll();
    ResponseEntity<ResponseDTO> findUserById(Integer id);
    Users findByEmail(String email);
}
