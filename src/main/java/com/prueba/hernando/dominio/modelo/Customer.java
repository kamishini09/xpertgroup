package com.prueba.hernando.dominio.modelo;

import com.prueba.hernando.infrastructura.modelo.CustomerController;
import com.prueba.hernando.infrastructura.persistencia.entidad.ClienteEntity;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Locale;


public class Customer {
    private Integer identificador;
    @Valid
    @NotBlank(message = "El nombre no puede estar vacio")
    @NotNull(message = "El nombre no puede estar vacio")
    private String name;
    @Valid
    @NotBlank(message = "El apellido del padre no puede estar vacio")
    @NotNull(message = "El apellido del padre no puede estar vacio")
    private String last_name_father;
    @Valid
    @NotBlank(message = "El apellido de la madre no puede estar vacio")
    @NotNull(message = "El apellido de la madre no puede estar vacio")
    private String last_name_mother;
    @Pattern(regexp = "^[MF]$", message = "El Sexo debe ser M o F")
    private String sex;
    private String email;
    @NotNull(message = "El telefono no puede estar vacío")
    @DecimalMin(value = "1000000", message = "El telefono debe contener al menos 7 dígitos")
    @DecimalMax(value = "9999999999", message = "El telefono no puede exceder los 10 dígitos")
    private Integer phone;
    private Date date_birth;

    public static Customer CustomerControllerToCustomer(CustomerController customerController){
        return new Customer(
                customerController.getNombre().toLowerCase(Locale.ROOT),
                customerController.getApellido_paterno().toLowerCase(Locale.ROOT),
                customerController.getApellido_materno().toLowerCase(Locale.ROOT),
                customerController.getSexo(),
                customerController.getCorreo(),
                customerController.getTelefono(),
                customerController.getFecha_nacimiento()  );
    }
    public static Customer ClienteEntityToCustomer(ClienteEntity clienteEntity){
        return new Customer(
                clienteEntity.getId(),
                clienteEntity.getNombre(),
                clienteEntity.getApellidoPaterno(),
                clienteEntity.getApellidoMaterno(),
                clienteEntity.getSexo(),
                clienteEntity.getCorreo(),
                clienteEntity.getTelefono(),
                clienteEntity.getFecha_nacimiento()
        );
    }

    public Customer(Integer identificador, String name, String last_name_father, String last_name_mother, String sex, String email, int phone, Date date_birth) {
        this.identificador = identificador;
        this.name = name;
        this.last_name_father = last_name_father;
        this.last_name_mother = last_name_mother;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.date_birth = date_birth;
    }

    public Customer(String name, String last_name_father, String last_name_mother, String sex, String email, int phone, Date date_birth) {
        this.name = name;
        this.last_name_father = last_name_father;
        this.last_name_mother = last_name_mother;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.date_birth = date_birth;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name_mother() {
        return last_name_mother;
    }

    public void setLast_name_mother(String last_name_mother) {
        this.last_name_mother = last_name_mother;
    }

    public String getLast_name_father() {
        return last_name_father;
    }

    public void setLast_name_father(String last_name_father) {
        this.last_name_father = last_name_father;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(Date date_birth) {
        this.date_birth = date_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
