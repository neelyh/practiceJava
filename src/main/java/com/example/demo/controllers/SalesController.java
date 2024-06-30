package com.example.demo.controllers;

import com.example.demo.entities.iSale;
import com.example.demo.exceptions.MissingInformationException;
import com.example.demo.exceptions.SaleAlreadyExistsException;
import com.example.demo.repositories.SalesRepository;
import com.example.demo.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// this should be https????

// docs show these as private classes a lot - security risk possibly?
// why have api twice, I removed it
// Also would be cool to have a router that routes all /sales /customers /salesPerson for the API to be quick as application grows
@RestController
@RequestMapping("/api")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesRepository salesRepository;

    // feel like this should be GetMapping according to spring boot docs
    // also /findById, find WHAT by ID, this should be more specific, could be /sales/findById if we did routing
    // what if sale isn't found, should add error throws
    @ResponseBody
    @GetMapping("/findById")
    public iSale findById(@RequestParam("id") Long id) {
        return salesService.findById(id);
    }

    @PostMapping("/sales")
    public ResponseEntity<?> createSales(@RequestBody List<iSale> sales) {
        try {
            salesService.saveAll(sales);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SaleAlreadyExistsException | MissingInformationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/salesForSalesperson")
    public ResponseEntity<List<iSale>> getSalesForSalesperson(
            @RequestParam("salespersonId") Long salespersonId,
            @RequestParam(value = "customerId", required = false) Long customerId) {

        List<iSale> sales;
        if (customerId != null) {
            sales = salesRepository.findBySalesperson_IdAndCustomer_Id(salespersonId, customerId);
        } else {
            sales = salesRepository.findBySalesperson_Id(salespersonId);
        }

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

}