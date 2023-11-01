package com.prueba.hernando.aplicacion;

import com.prueba.hernando.aplicacion.service.ListCustomerService;
import com.prueba.hernando.dominio.modelo.Customer;
import com.prueba.hernando.dominio.puertos.CustomerRepository;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import com.prueba.hernando.util.StandarErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class ListCustomer implements ListCustomerService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CustomerRepository customerRepository;

    public ListCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerController customerById(Integer identificador) {
        Optional<Customer> optionalCustomer = customerRepository.getCustomerByID(identificador);
        LOGGER.info("Informacion cliente consultado: "+optionalCustomer);

        if(optionalCustomer.isPresent()){
         return   CustomerController.CustomerToCustomerController(optionalCustomer.get());
        }else {
            throw new StandarErrorMessage("El identificador: "+identificador+" no esta en la base de datos");
        }

    }
}
