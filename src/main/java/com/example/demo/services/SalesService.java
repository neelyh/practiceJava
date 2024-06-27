package com.example.demo.services;

import com.example.demo.entities.iSale;
import com.example.demo.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    @Autowired
    SalesRepository parentEntityRepository;

    public iSale findById(Long id) {
        return parentEntityRepository.findById(id).orElse(null);
    }
}
