package com.prueba.hernando.dominio.puertos;

import com.prueba.hernando.dominio.modelo.Customer;
import java.util.Optional;

public interface CustomerRepository {
    Integer saveCustomer(Customer customer);
    Boolean getCustomerByName(String name, String apellido1, String apellido2);
    Optional<Customer> getCustomerByID(Integer id);

}
