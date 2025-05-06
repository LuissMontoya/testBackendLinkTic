package co.com.test.linktic.appEcommerce.repositories;

import co.com.test.linktic.appEcommerce.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Integer>{
	
	public Optional<Inventory> findInventoryById(Integer id);
    List<Inventory> findAllByOrderByIdDesc();
    Optional<Inventory> findByProductId(Integer productId);
}