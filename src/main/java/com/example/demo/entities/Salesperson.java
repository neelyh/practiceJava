package com.example.demo.entities;

import com.example.demo.entities.iSale;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Salesperson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "salesperson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<iSale> sales;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
