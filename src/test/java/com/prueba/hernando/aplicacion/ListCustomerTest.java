package com.prueba.hernando.aplicacion;

import com.prueba.hernando.dominio.modelo.Customer;
import com.prueba.hernando.dominio.puertos.CustomerRepository;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import com.prueba.hernando.util.StandarErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ListCustomerTest {
    private ListCustomer listCustomer;

    @Mock
    private CustomerRepository customerRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listCustomer = new ListCustomer(customerRepository);
    }


    @Test
    public void testCustomerById_ExistCustomer() {


        Customer simulatedCustomer = new Customer(
                1,"juan", "perez", "garcia", "M",
                "juan.perez@example.com", 123456789, new Date());
        Mockito.when(customerRepository.getCustomerByID(1)).thenReturn(Optional.of(simulatedCustomer));
        ListCustomer listCustomer = new ListCustomer(customerRepository);

        CustomerController customerController = listCustomer.customerById(1);

        assertEquals("juan", customerController.getNombre());
        assertEquals("perez", customerController.getApellido_paterno());
        assertEquals("garcia", customerController.getApellido_materno());
    }

    @Test
    public void testCustomerById_NonexistentCustomer() {

        Mockito.when(customerRepository.getCustomerByID(2)).thenReturn(Optional.empty());
        ListCustomer listCustomer = new ListCustomer(customerRepository);

        StandarErrorMessage exception = assertThrows(StandarErrorMessage.class, () -> {
            listCustomer.customerById(2);
        });

        assertEquals("El identificador: 2 no esta en la base de datos", exception.getMessage());
    }

}