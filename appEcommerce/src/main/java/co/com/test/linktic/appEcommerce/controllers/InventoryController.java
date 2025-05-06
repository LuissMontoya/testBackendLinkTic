package co.com.test.linktic.appEcommerce.controllers;

import co.com.test.linktic.appEcommerce.DTO.ResponseDTO;
import co.com.test.linktic.appEcommerce.service.impl.InventoryServiceImpl;
import co.com.test.linktic.appEcommerce.utils.Constants;
import co.com.test.linktic.appEcommerce.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT })
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ResponseDTO> getInventory(@PathVariable Integer productId, @RequestHeader("Authorization") String authorization) {
        String token = authorization.substring(7);
        return inventoryServiceImpl.getInventoryDetails(productId, token);
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ResponseDTO> updateInventory(@PathVariable Integer productId,
                                                  @RequestParam int quantity) {
        ResponseDTO response;
        try {
            inventoryServiceImpl.updateQuantity(productId, quantity);

            response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(), Constants.CANTIDAD_ACTUALIZADA);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
