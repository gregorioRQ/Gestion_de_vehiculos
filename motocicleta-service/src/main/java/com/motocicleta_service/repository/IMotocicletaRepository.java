/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motocicleta_service.repository;

import com.motocicleta_service.entities.Motocicleta;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rafae
 */
@Repository
public interface IMotocicletaRepository extends CrudRepository <Motocicleta, Integer>{
  
    List<Motocicleta> findAllMotocicletaByUsuarioId(int usuarioId);
}
