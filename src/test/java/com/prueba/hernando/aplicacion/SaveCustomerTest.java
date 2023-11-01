package com.prueba.hernando.aplicacion;


import com.prueba.hernando.dominio.puertos.CustomerRepository;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import com.prueba.hernando.util.StandarErrorMessage;
import com.prueba.hernando.util.ValidationCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SaveCustomerTest {

    private SaveCustomer saveCustomer;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        saveCustomer = new SaveCustomer(customerRepository);
    }

    @Test
    public void testSaveCustomerSucceeds() {
        Mockito.when(customerRepository.getCustomerByName(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(false);
        Mockito.when(customerRepository.saveCustomer(Mockito.any())).thenReturn(1);

        Integer id = saveCustomer.saveCustomer(new CustomerController("Juan", "Pérez", "García", "M", "juan.perez@example.com", 123456789, new Date()));

        assertEquals(1, id);
    }

    @Test
    public void testSaveCustomer_CustomerExists() {

        CustomerController customerController = new CustomerController(
                "prueba","prueba","prueba",
                "M","prueba@gmail.com",22345678,new Date());
        Mockito.when(customerRepository.getCustomerByName(Mockito.anyString() , Mockito.anyString() , Mockito.anyString())).thenReturn(true);


        assertThrows(StandarErrorMessage.class, () -> saveCustomer.saveCustomer(customerController));
    }

    @Test
    public void testSaveCustomer_ValidationFailed() {
        Mockito.when(customerRepository.getCustomerByName(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        try {
            saveCustomer.saveCustomer(new CustomerController("juan", "perez", "garcia", "M", "juan.perez@example.com", 123456789, new Date()));
            fail("No se esperaba que el método no lanzara una excepción");
        } catch (StandarErrorMessage e) {
            assertEquals("El cliente: juan perez garcia, ya esta registrado.", e.getMessage());
        }
    }

    @Test
    public void testSaveCustomerThrowsValidationException() {
        CustomerController customerController = new CustomerController("Juan", "Pérez", "García", "Masculino", "", 123456789, new Date());

        try {
            saveCustomer.saveCustomer(customerController);
            fail("No se esperaba que el método no lanzara una excepción");
        } catch (ValidationCustomer e) {
            assertEquals("El Sexo debe ser M o F", e.getMessage());
        }
    }

}