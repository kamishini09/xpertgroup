package com.prueba.hernando.infrastructura.persistencia.entidad;

import com.prueba.hernando.dominio.modelo.Customer;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity()
@Table(name = "Client")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    private String sexo;
    private String correo;
    private Integer telefono;
    private Date fecha_nacimiento;

    public static ClienteEntity CustomerToClienteEntity(Customer customer){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombre(customer.getName());
        clienteEntity.setApellidoPaterno(customer.getLast_name_father());
        clienteEntity.setApellidoMaterno(customer.getLast_name_mother());
        clienteEntity.setSexo(customer.getSex());
        clienteEntity.setCorreo(customer.getEmail());
        clienteEntity.setTelefono(customer.getPhone());
        clienteEntity.setFecha_nacimiento(customer.getDate_birth());
        return clienteEntity;
    }
}
