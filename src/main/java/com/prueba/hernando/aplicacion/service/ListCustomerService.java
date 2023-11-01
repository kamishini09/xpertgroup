package com.prueba.hernando.aplicacion.service;

import com.prueba.hernando.infrastructura.modelo.CustomerController;

public interface ListCustomerService {
    CustomerController customerById(Integer identificador);
}
