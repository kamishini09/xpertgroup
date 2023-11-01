package com.prueba.hernando.infrastructura.controllador;

import com.prueba.hernando.aplicacion.service.ListCustomerService;
import com.prueba.hernando.aplicacion.service.SaveCustomerService;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/customer")
public class Controllador {


    private static final Logger LOGGER = LogManager.getLogger();

    private final SaveCustomerService saveCustomerService;
    private final ListCustomerService listCustomerService;

        public Controllador(SaveCustomerService saveCustomerService, ListCustomerService listCustomerService) {
        this.saveCustomerService = saveCustomerService;
            this.listCustomerService = listCustomerService;
        }

    @PostMapping("/save")
    public ResponseEntity<Integer> saveCustomer(@Valid @RequestBody CustomerController customerController) {
        LOGGER.info("Inicia peticion, cliente: "+customerController);
        return new ResponseEntity<>(saveCustomerService.saveCustomer(customerController), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?>searchCustomer(@PathVariable Integer id){
        return new ResponseEntity<>(listCustomerService.customerById(id),HttpStatus.OK);

    }
}
