package com.example.demo.repositories;

import com.example.demo.entities.iSale;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// I changed this to a JPA repo as thats what the docs had
// I added db logic here too as the service seems to deal with all business logic so separating concerns
public interface SalesRepository extends JpaRepository<iSale, Long> {
  Optional<iSale> findByItemAndAmountAndCustomer_IdAndSalesperson_Id(String item, Double amount, Long customerId, Long salespersonId);

  List<iSale> findBySalesperson_Id(Long salespersonId);

  List<iSale> findBySalesperson_IdAndCustomer_Id(Long salespersonId, Long customerId);
}