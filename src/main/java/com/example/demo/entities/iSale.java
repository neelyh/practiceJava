package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class iSale {
    // @Autowired shouldn't have dependency
    // SalesRepository salesRepository;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    // encapsulation reasons I've changed these to private
    private String item = "";
    private Double amount;

    // this was wrong originally - mapping to same thing twice
    @ManyToOne    
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Salesperson getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(Salesperson salesperson) {
        this.salesperson = salesperson;
    }
}
