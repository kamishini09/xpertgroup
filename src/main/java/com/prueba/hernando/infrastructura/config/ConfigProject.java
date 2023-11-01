package com.prueba.hernando.infrastructura.config;

import com.prueba.hernando.aplicacion.SaveCustomer;
import com.prueba.hernando.aplicacion.ListCustomer;
import com.prueba.hernando.infrastructura.persistencia.adaptador.ClienteCrudRepository;
import com.prueba.hernando.infrastructura.persistencia.adaptador.ClienteServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigProject {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Bean
    public ClienteServiceRepository clienteServiceRepository(){
        return new ClienteServiceRepository(clienteCrudRepository);
    }

    @Bean
    public SaveCustomer saveCustomer(){
        return new SaveCustomer(clienteServiceRepository());
    }

    @Bean
    public ListCustomer listCustomer(){
        return new ListCustomer(clienteServiceRepository());
    }


}
