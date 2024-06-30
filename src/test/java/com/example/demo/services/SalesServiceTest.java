package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Salesperson;
import com.example.demo.entities.iSale;
import com.example.demo.exceptions.SaleAlreadyExistsException;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SalesRepository;
import com.example.demo.repositories.SalespersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SalesServiceTest {

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SalespersonRepository salespersonRepository;

    @InjectMocks
    private SalesService salesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_SaleExists() {
        // Arrange
        iSale sale = new iSale(); // Assuming iSale is an interface, you'll need a concrete implementation or mock
                                  // it
        sale.setId(1L);
        when(salesRepository.findById(1L)).thenReturn(Optional.of(sale));

        // Act
        iSale result = salesService.findById(1L);

        // Assert
        assertEquals(sale.getId(), result.getId());
    }

    @Test
    public void testFindById_SaleDoesNotExist() {
        // Arrange
        when(salesRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        iSale result = salesService.findById(1L);

        // Assert
        assertNull(result);
    }

    @Test
    public void testSaveAllSaleAlreadyExistsException() {
        // Arrange
        iSale sale = new iSale();
        sale.setItem("Item1");
        sale.setAmount(100.0);
        Customer customer = new Customer();
        customer.setId(1L);
        sale.setCustomer(customer);
        Salesperson salesperson = new Salesperson();
        salesperson.setId(1L);
        sale.setSalesperson(salesperson);

        // Act
        when(salesRepository.findByItemAndAmountAndCustomer_IdAndSalesperson_Id(any(), any(), any(), any()))
                .thenReturn(Optional.of(sale));

        List<iSale> sales = new ArrayList<>();
        sales.add(sale);

        // Assert
        assertThrows(SaleAlreadyExistsException.class, () -> salesService.saveAll(sales));
    }
}