package com.example.demo.services;

import com.example.demo.entities.iSale;
import com.example.demo.repositories.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SalesServiceTest {

    @Mock
    private SalesRepository salesRepository;

    @InjectMocks
    private SalesService salesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_SaleExists() {
        // Arrange
        iSale sale = new iSale(); // Assuming iSale is an interface, you'll need a concrete implementation or mock it
        sale.setId(1L); // Ensure iSale has a setId method
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
}