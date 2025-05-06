package co.com.test.linktic.appEcommerce.service;


import co.com.test.linktic.appEcommerce.DTO.InventoryDTO;
import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.entity.Inventory;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface IInventoryService {

	ResponseEntity<ResponseDTO>  getInventoryDetails(Integer productId);

}
