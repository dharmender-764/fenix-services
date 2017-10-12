package com.fenix.ecom.returns.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fenix.ecom.returns.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
