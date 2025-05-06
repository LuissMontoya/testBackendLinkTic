package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.DTO.UsersDTO;
import org.springframework.http.ResponseEntity;

public interface IUserCreateService {
    ResponseEntity<ResponseDTO> saveUser(UsersDTO userDTO);
}
