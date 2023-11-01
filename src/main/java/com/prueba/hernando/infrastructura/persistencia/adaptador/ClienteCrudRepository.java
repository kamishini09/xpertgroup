package com.prueba.hernando.infrastructura.persistencia.adaptador;

import com.prueba.hernando.infrastructura.persistencia.entidad.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ClienteCrudRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("SELECT c FROM ClienteEntity c WHERE CONCAT(c.nombre,' ',c.apellidoPaterno,' ',c.apellidoMaterno) = :nombreCompleto")
    Optional<ClienteEntity> buscarPorNombreCompleto(@Param("nombreCompleto") String nombreCompleto);




}
