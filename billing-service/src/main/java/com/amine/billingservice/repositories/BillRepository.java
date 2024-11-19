package com.amine.billingservice.repositories;

import com.amine.billingservice.entites.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {
}
