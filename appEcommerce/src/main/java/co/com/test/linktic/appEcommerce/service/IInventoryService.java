package co.com.test.linktic.appEcommerce.service;


import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import org.springframework.http.ResponseEntity;


public interface IInventoryService {

	ResponseEntity<ResponseDTO>  getInventoryDetails(Integer productId, String token);

}
