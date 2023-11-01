package com.prueba.hernando.infrastructura.modelo;

import com.prueba.hernando.dominio.modelo.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class CustomerController {
    private Integer id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String sexo;
    private String correo;
    private Integer telefono;
    private Date fecha_nacimiento;

    public CustomerController(String nombre, String apellido_paterno, String apellido_materno, String sexo,
                              String correo, Integer telefono, Date fecha_nacimiento) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public CustomerController(Integer id, String nombre, String apellido_paterno, String apellido_materno,
                              String sexo, String correo, Integer telefono, Date fecha_nacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
    }



    public static CustomerController CustomerToCustomerController(Customer customer){
        return new CustomerController(
                customer.getIdentificador(),
                customer.getName(),
                customer.getLast_name_father(),
                customer.getLast_name_mother(),
                customer.getSex(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getDate_birth());
    }
}
