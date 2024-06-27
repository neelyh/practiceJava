package com.example.demo.controllers;

import com.example.demo.entities.iSale;
import com.example.demo.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// where is the sales service repo
// docs show these as private a lot - security risk possibly?
@RestController("/api")
@RequestMapping(value = "/api")
public class SalesController {

    @Autowired
    SalesService salesService;

// feel like this should be GetMapping according to spring boot docs
// also /findById, find WHAT by ID
// what if sale isn't found, add error throws
    @ResponseBody
    @RequestMapping(path = "/findById", method = RequestMethod.GET)
    public iSale findById(@RequestParam("id") Long id){
        return salesService.findById(id);
    }

    @PostMapping("/sales")
    public ResponseEntity<?> createSales(@RequestBody List<iSale> sales){
        for(iSale sale: sales){
            if(sale.getItem()== null || sale.getItem().isEmpty() || sale.getAmount()== null || sale.getCustomer() == null || sale.getSalesperson() == null){
                return new ResponseEntity<>("Missing information in sale.", HttpStatus.BAD_REQUEST);
            }
            if(salesService.existsById(sale.getId())){
                return new ResponseEntity<>("Sale already exists in the db.", HttpStatus.CONFLICT)
            }
        }
        salesService.saveAll(sales)
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//     Sale newSale(@RequestBody Sale newSale) {
//     return repository.save(newSale);
//   }
}
