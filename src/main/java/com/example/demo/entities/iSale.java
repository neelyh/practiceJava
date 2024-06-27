package com.example.demo.entities;

import com.example.demo.repositories.SalesRepository;
import javax.persistence.*;

@javax.persistence.Entity
public class iSale {
    @Autowired
    SalesRepository salesRepository;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long id;

    public String item = "";
    public Double amount;

    @ManyToOne
    // this was wrong - mapping to same thing twice
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "salesperson_id", nullable = false)
    private Salesperson salesperson;

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void saveAll(List<iSale> sales){
        salesRepository.saveAll(sales);
    }
}
