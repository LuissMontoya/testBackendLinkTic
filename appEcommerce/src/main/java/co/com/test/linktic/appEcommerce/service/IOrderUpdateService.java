package co.com.test.linktic.appEcommerce.service;

import co.com.test.linktic.appEcommerce.DTO.OrderDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IOrderUpdateService {
    ResponseEntity<ResponseDTO> updateOrder(OrderDTO orderDTO);
}
