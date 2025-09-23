package com.example;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exception.CustomerNotFoundException;
import com.example.exception.DuplicateEmailException;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepositoryFacade;
import com.example.service.CustomerServiceImpl;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepositoryFacade customerRepositoryFacade;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Customer> customers = List.of(
                new Customer(1L, "John Doe", "john@example.com"),
                new Customer(2L, "Jane Doe", "jane@example.com")
        );
        when(customerRepositoryFacade.findAll()).thenReturn(customers);

        List<Customer> result = customerService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindById_found() {
        Customer customer = new Customer(1L, "John Doe", "john@example.com");
        when(customerRepositoryFacade.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findById(1L);
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testFindById_notFound() {
        when(customerRepositoryFacade.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(1L));
    }

    @Test
    void testCreate_success() {
        CustomerDTO dto = new CustomerDTO("Alice", "alice@example.com");
        Customer savedCustomer = new Customer(1L, "Alice", "alice@example.com");

        when(customerMapper.toEntity(dto)).thenReturn(savedCustomer);
        when(customerRepositoryFacade.save(any(Customer.class))).thenReturn(savedCustomer);

        Customer result = customerService.create(dto);

        assertNotNull(result.getId());
        assertEquals(dto.getEmail(), result.getEmail());
    }

    @Test
    void testCreate_duplicateEmail() {
        CustomerDTO dto = new CustomerDTO("Alice", "alice@example.com");

        when(customerMapper.toEntity(dto)).thenReturn(new Customer(null, "Alice", "alice@example.com"));
        when(customerRepositoryFacade.save(any(Customer.class))).thenThrow(PersistenceException.class);

        assertThrows(DuplicateEmailException.class, () -> customerService.create(dto));
    }

    @Test
    void testUpdate_success() {
        Long id = 1L;
        Customer existing = new Customer(id, "Old Name", "old@example.com");
        CustomerDTO updatedDto = new CustomerDTO("New Name", "new@example.com");
        Customer updatedCustomer = new Customer(id, "New Name", "new@example.com");

        when(customerRepositoryFacade.findById(id)).thenReturn(Optional.of(existing));
        when(customerRepositoryFacade.update(existing)).thenReturn(updatedCustomer);

        Customer result = customerService.update(id, updatedDto);

        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
    }

    @Test
    void testUpdate_duplicateEmail() {
        Long id = 1L;
        Customer existing = new Customer(id, "Old Name", "old@example.com");
        CustomerDTO updatedDto = new CustomerDTO("New Name", "new@example.com");

        when(customerRepositoryFacade.findById(id)).thenReturn(Optional.of(existing));
        when(customerRepositoryFacade.update(existing)).thenThrow(PersistenceException.class);

        assertThrows(DuplicateEmailException.class, () -> customerService.update(id, updatedDto));
    }

    @Test
    void testDelete_success() {
        Long id = 1L;
        Customer customer = new Customer(id, "Delete", "delete@example.com");

        when(customerRepositoryFacade.findById(id)).thenReturn(Optional.of(customer));
        doNothing().when(customerRepositoryFacade).delete(customer);

        customerService.delete(id);
        assertTrue(true);
    }

    @Test
    void testDelete_notFound() {
        Long id = 999L;
        when(customerRepositoryFacade.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(id));
    }
}
