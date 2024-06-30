package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Salesperson;
import com.example.demo.entities.iSale;
import com.example.demo.exceptions.MissingInformationException;
import com.example.demo.exceptions.SaleAlreadyExistsException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SalesRepository;
import com.example.demo.repositories.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    public iSale findById(Long id) {
        return salesRepository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return salesRepository.existsById(id);
    }

    public void saveAll(List<iSale> sales){

        for (iSale sale : sales) {
            if (sale.getItem() == null || sale.getItem().isEmpty()) {
                throw new MissingInformationException("Item is missing.");
            }
            if (sale.getAmount() == null) {
                throw new MissingInformationException("Amount is missing.");
            }
            if (sale.getCustomer() == null || sale.getCustomer().getId() == null) {
                throw new MissingInformationException("Customer ID is missing.");
            }
            if (sale.getSalesperson() == null || sale.getSalesperson().getId() == null) {
                throw new MissingInformationException("Salesperson ID is missing.");
            }

            // For the purpose of the exercise and simplicity of the data model, this is the best way for me to check if a sale already exists, but in production I'm sure there's a better way and we should think about customers actually wanting another order of the same thing
            Optional<iSale> existingSale = salesRepository.findByItemAndAmountAndCustomer_IdAndSalesperson_Id(
                sale.getItem(),
                sale.getAmount(),
                sale.getCustomer().getId(),
                sale.getSalesperson().getId()
            );

            if (existingSale.isPresent()) {
                throw new SaleAlreadyExistsException("Sale already exists.");
            }

            // Due to having trouble with unwanted persistence operations I need to get the "linked" entities if I don't want to create new ones
            // To stick to the exercise I won't be creating new customers/salespeople
            Optional<Customer> customer = customerRepository.findById(sale.getCustomer().getId());
            customer.ifPresent(sale::setCustomer);

            Optional<Salesperson> salesperson = salespersonRepository.findById(sale.getSalesperson().getId());
            salesperson.ifPresent(sale::setSalesperson);

            salesRepository.save(sale);
        }
    }
}