package com.amine.cusomerms.respositories;

import com.amine.cusomerms.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRespository extends JpaRepository<Product, Long> {
}
