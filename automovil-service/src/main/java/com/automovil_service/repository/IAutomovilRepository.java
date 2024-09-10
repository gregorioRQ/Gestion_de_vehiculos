/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.automovil_service.repository;

import com.automovil_service.entities.Automovil;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafae
 */
@Repository
public interface IAutomovilRepository extends CrudRepository <Automovil, Integer>{
    List<Automovil> findAllAutomovilByUsuarioId(int usuarioId);
}
