/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.automovil_service.controller;

import com.automovil_service.entities.Automovil;
import com.automovil_service.service.AutomovilService;
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
@RequestMapping("/api/automovil")
public class AutomovilController {

    @Autowired
    private AutomovilService automovilService;

    @GetMapping("/all")
    public ResponseEntity<List<Automovil>> getAllAutomoviles() {
        List<Automovil> automoviles = automovilService.getAll();
        if (automoviles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(automoviles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovil> getAutomovilById(@PathVariable("id") int id) {
        Automovil automovilFound = automovilService.getAutomovilById(id);
        if (automovilFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(automovilFound);
    }

    @PostMapping()
    public ResponseEntity<Automovil> saveAutomovil(@RequestBody Automovil automovil) {
        Automovil automovilToSave = automovilService.saveAutomovil(automovil);
        return ResponseEntity.ok(automovilToSave);
    }

    /*este metodo fue creado para probar el uso de resttemplate*/
    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<List<Automovil>> listarAutomovilesPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
        List<Automovil> userAutomovil = automovilService.findAllAutomovilesByUsuarioId(usuarioId);
        if (userAutomovil.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userAutomovil);
    }

    /*este metodo usa feign client*/
    @GetMapping("/all-automoviles-user/{usuarioId}")
    public ResponseEntity<?> findAllAutomovilesByUsuarioId(@PathVariable("usuarioId") int usuarioId
    ) {
        List<Automovil> userAutomovil = automovilService.findAllAutomovilesByUsuarioId(usuarioId);
        if (userAutomovil.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userAutomovil);
    }
}
