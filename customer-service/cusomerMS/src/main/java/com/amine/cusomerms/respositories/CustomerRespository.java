package com.amine.cusomerms.respositories;

import com.amine.cusomerms.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRespository extends JpaRepository<Customer, Long> {
}
