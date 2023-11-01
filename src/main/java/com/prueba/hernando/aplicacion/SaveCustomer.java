package com.prueba.hernando.aplicacion;

import com.prueba.hernando.aplicacion.service.SaveCustomerService;
import com.prueba.hernando.dominio.modelo.Customer;
import com.prueba.hernando.dominio.puertos.CustomerRepository;
import com.prueba.hernando.infrastructura.modelo.CustomerController;
import com.prueba.hernando.util.StandarErrorMessage;
import com.prueba.hernando.util.ValidationCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class SaveCustomer implements SaveCustomerService {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CustomerRepository customerRepository;

    public SaveCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Integer saveCustomer(CustomerController customerController) {
        Customer customer = Customer.CustomerControllerToCustomer(customerController);
        validation(customer);
        boolean responseCustomesExist = customerRepository.getCustomerByName(
                customer.getName().toLowerCase(Locale.ROOT),customer.getLast_name_father().toLowerCase(Locale.ROOT),
                customer.getLast_name_mother().toLowerCase(Locale.ROOT));
        LOGGER.info("Existe cliente: "+responseCustomesExist);
        if(responseCustomesExist){
            throw new StandarErrorMessage("El cliente: "
                    +customer.getName()+" "+customer.getLast_name_father()+" "+customer.getLast_name_mother()
                    +", ya esta registrado.");
        }else {
            return customerRepository.saveCustomer(customer);
        }
    }

    private void validation(Customer customer){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator= validatorFactory.getValidator();
        Set<ConstraintViolation<Customer>> violations=validator.validate(customer);

        if(!violations.isEmpty()){
            List<String> violationMessages = new ArrayList<>();
            for(ConstraintViolation<Customer> violation:violations){
                violationMessages.add(violation.getMessage());
            }
            throw new ValidationCustomer(violationMessages);
        }

    }
}
