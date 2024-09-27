/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motocicleta_service.controller;

import com.motocicleta_service.entities.Motocicleta;
import com.motocicleta_service.service.MotocicletaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rafae
 */
@RestController
@RequestMapping("/api/motocicleta")
public class MotocicletaController {
    
    @Autowired
    private MotocicletaService motocicletaService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Motocicleta>> getAllMotocicletaes(){
        List<Motocicleta> motocicletas = motocicletaService.getAll();
        if(motocicletas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motocicletas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Motocicleta> getMotocicletaById(@PathVariable("id") int id){
        Motocicleta motoFound = motocicletaService.getMotocicletaById(id);
        if(motoFound == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(motoFound);
    }
    
    @PostMapping()
    public ResponseEntity<Motocicleta> saveMotocicleta(@RequestBody Motocicleta motocicleta){
        Motocicleta motocicletaToSave = motocicletaService.saveMotocicleta(motocicleta);
        return ResponseEntity.ok(motocicletaToSave);
    }
    
    /*pertenece a un ejemplo de prueba con resttemplate*/
    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<List<Motocicleta>> getMotocicletaByUsuarioId(@PathVariable("usuarioId") int usuarioId){
       
        List<Motocicleta> userMotocicleta = motocicletaService.findAllMotocicletasByUsuarioId(usuarioId);
        
        if(userMotocicleta.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userMotocicleta);
    }
    
    @GetMapping("/all-motocicletas-user/{usuarioId}")
    public ResponseEntity<?> findAllMotocicletasByUsuarioId(@PathVariable("usuarioId") int usuarioId){
        List<Motocicleta> motocicletasUser = motocicletaService.findAllMotocicletasByUsuarioId(usuarioId);
        if(motocicletasUser.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motocicletasUser);
    }
}
