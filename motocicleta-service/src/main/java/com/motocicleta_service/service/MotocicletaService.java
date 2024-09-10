/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motocicleta_service.service;

import com.motocicleta_service.entities.Motocicleta;
import com.motocicleta_service.repository.IMotocicletaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafae
 */
@Service
public class MotocicletaService {
    
    @Autowired
    private IMotocicletaRepository motocicletaRepository;
    
    public List<Motocicleta> getAll(){
        return (List<Motocicleta>) motocicletaRepository.findAll();
    }
    
    public Motocicleta getMotocicletaById(int id){
       return motocicletaRepository.findById(id).orElse(null);
    }
    
    public Motocicleta saveMotocicleta(Motocicleta motocicleta){
        Motocicleta motoSave = motocicletaRepository.save(motocicleta);
        return motoSave;
    }
    
    public List<Motocicleta> findAllMotocicletasByUsuarioId(int usuarioId) {
        return motocicletaRepository.findAllMotocicletaByUsuarioId(usuarioId);
    }
}
