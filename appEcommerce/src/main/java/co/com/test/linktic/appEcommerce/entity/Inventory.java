package co.com.test.linktic.appEcommerce.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

	 @Id
	 @Column(name = "id_inventory")
	 private int id;
	 
	 @Column(name = "product_id")
	 private int productId;
	 
	 @Column(name = "quantity")
	 private int quantity;

}
