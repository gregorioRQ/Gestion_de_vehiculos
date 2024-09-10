/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.automovil_service.service;

import com.automovil_service.entities.Automovil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.automovil_service.repository.IAutomovilRepository;

/**
 *
 * @author rafae
 */
@Service
public class AutomovilService {
    
    @Autowired
    private IAutomovilRepository automovilRepo;
    
    public List<Automovil> getAll(){
        return (List<Automovil>) automovilRepo.findAll();
    }
    
    public Automovil getAutomovilById(int id){
       return automovilRepo.findById(id).orElse(null);
    }
    
    public Automovil saveAutomovil(Automovil automovil){
        Automovil automovilSave = automovilRepo.save(automovil);
        return automovilSave;
    }
    
    public List<Automovil> findAllAutomovilesByUsuarioId(int usuarioId){
        return automovilRepo.findAllAutomovilByUsuarioId(usuarioId);
    }
}
