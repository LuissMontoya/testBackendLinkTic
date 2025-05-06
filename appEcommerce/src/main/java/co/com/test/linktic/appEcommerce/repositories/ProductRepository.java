package co.com.test.linktic.appEcommerce.repositories;

import org.springframework.stereotype.Repository;

import co.com.test.linktic.appEcommerce.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

    List<Product> findAllByOrderByIdDesc();
}