package com.prueba.hernando.infrastructura.persistencia.adaptador;

import com.prueba.hernando.dominio.modelo.Customer;
import com.prueba.hernando.dominio.puertos.CustomerRepository;
import com.prueba.hernando.infrastructura.persistencia.entidad.ClienteEntity;
import com.prueba.hernando.util.StandarErrorMessage;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClienteServiceRepository implements CustomerRepository {
    private final ClienteCrudRepository clienteCrudRepository;

    public ClienteServiceRepository(ClienteCrudRepository clienteCrudRepository) {
        this.clienteCrudRepository = clienteCrudRepository;
    }

    @Override
    public Integer saveCustomer(Customer customer) {
        try{
            ClienteEntity cliente = ClienteEntity.CustomerToClienteEntity(customer);
            ClienteEntity clienteBD = clienteCrudRepository.save(cliente);
            return clienteBD.getId();
        }catch (Exception ex){
            Pattern pattern = Pattern.compile("ERROR:(.*)");
            Matcher matcher = pattern.matcher(ex.getMessage());
            if(matcher.find()){
                String mensajeError = matcher.group(1).trim();
                throw new StandarErrorMessage("Error al crear registro: "+mensajeError);
            }else{
                throw new StandarErrorMessage("Error al crear registro: No se pudo extraer el mensaje de error.");
            }
        }
    }

    @Override
    public Boolean getCustomerByName(String name, String apellido1, String apellido2) {
        String nombre = name +" "+ apellido1 +" "+apellido2;
        return clienteCrudRepository.buscarPorNombreCompleto(nombre).isPresent();
    }

    @Override
    public Optional<Customer> getCustomerByID(Integer id) {
        Optional<ClienteEntity> clienteOptional = clienteCrudRepository.findById(id);
        if (!clienteOptional.isEmpty()) {
            ClienteEntity clienteEntity = clienteOptional.get();
            Customer customer = Customer.ClienteEntityToCustomer(clienteEntity);
            return Optional.of(customer);
        } else {
            return Optional.empty();
        }

    }


}
