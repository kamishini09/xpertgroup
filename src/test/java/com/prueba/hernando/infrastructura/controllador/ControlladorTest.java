package com.prueba.hernando.infrastructura.controllador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.prueba.hernando.aplicacion.service.ListCustomerService;
import com.prueba.hernando.aplicacion.service.SaveCustomerService;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ControlladorTest {

    private Controllador controlador;
    private SaveCustomerService saveCustomerService;
    private ListCustomerService listCustomerService;

    @BeforeEach
    public void setUp() {
        saveCustomerService = mock(SaveCustomerService.class);
        controlador = new Controllador(saveCustomerService,listCustomerService);
    }

    @Test
    public void testSaveCustomer() {

        CustomerController customerController = mock(CustomerController.class);
        when(saveCustomerService.saveCustomer(customerController)).thenReturn(1);

        ResponseEntity<Integer> responseEntity = controlador.saveCustomer(customerController);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().intValue());
    }

}