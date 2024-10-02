/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuario_service.controller;

import com.usuario_service.entities.User;
import com.usuario_service.dto.AutomovilDTO;
import com.usuario_service.dto.MotocicletaDTO;
import com.usuario_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;


	/*obtiene todos los usuarios*/
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	/*obtiene un usuario por su id*/
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User userFound = userService.getUserById(id);
		if (userFound == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userFound);
	}

	/*guarda un usuario*/
	@PostMapping()
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User userToSave = userService.save(user);
		return ResponseEntity.ok(userToSave);
	}

	/* 
    este metodo esta usando restTemplate por debajo para hacer las peticiones
    de datos a los otros microservicios
    se comunica con el microservicio automoviles y trae los autos de un usuario
    segun su id
    path: localhost:8001/user/automoviles/usuarioId
	 */
	@CircuitBreaker(name = "automovilCB", fallbackMethod = "fallBackGetAutomoviles")
	@GetMapping("/automoviles/{usuarioId}")
	public ResponseEntity<List<AutomovilDTO>> listarAutomoviles(@PathVariable("usuarioId") int id) {
		User userFound = userService.getUserById(id);
		if (userFound == null) {
			return ResponseEntity.notFound().build();
		}
		List<AutomovilDTO> automoviles = userService.getAutomoviles(id);
		return ResponseEntity.ok(automoviles);
	}

	public ResponseEntity<List<AutomovilDTO>> fallBackGetAutomoviles(@PathVariable("usuarioId") int id,
			RuntimeException excepcion) {
		return new ResponseEntity("El usuario : " + id + " tiene los carros en el taller", HttpStatus.OK);
	}

	/*éste metodo hace lo mismo que  listarAutomoviles pero 
    comunicandose con otro microservicio
	 */
	@CircuitBreaker(name = "motocicletaCB", fallbackMethod = "fallBackGetMotocicletas")
	@GetMapping("/motocicletas/{usuarioId}")
	public ResponseEntity<List<MotocicletaDTO>> listarMotocicletas(@PathVariable("usuarioId") int id) {
		User userFound = userService.getUserById(id);
		if (userFound == null) {
			return ResponseEntity.notFound().build();
		}
		List<MotocicletaDTO> motocicletas = userService.getMotocicleta(id);
		return ResponseEntity.ok(motocicletas);
	}
	
	public ResponseEntity<List<MotocicletaDTO>> fallBackGetMotocicletas(@PathVariable("usuarioId") int id,
			RuntimeException excepcion) {
		return new ResponseEntity("Usuario : " + id + " el servicio esta acualmente en mantenimiento ", HttpStatus.OK);
	}

	/* guarda un auto en un usuario ya existente 
    path: localhost:8001/user/automovil/usuarioId
	 */
	@PostMapping("/automovil/{usuarioId}")
	public ResponseEntity<AutomovilDTO> saveAutomovil(@PathVariable("usuarioId") int usuarioId, @RequestBody AutomovilDTO automovil) {
		AutomovilDTO autoSave = userService.saveAutomovil(usuarioId, automovil);
		return ResponseEntity.ok(autoSave);
	}

	/* guarda una moto en un usuario ya existente */
	@PostMapping("/motocicleta/{usuarioId}")
	public ResponseEntity<MotocicletaDTO> saveMotocicleta(@PathVariable("usuarioId") int usuarioId, @RequestBody MotocicletaDTO motocicleta) {
		MotocicletaDTO motocicletaSave = userService.saveMotocicleta(usuarioId, motocicleta);
		return ResponseEntity.ok(motocicletaSave);
	}

	/* usando feign client le pide al microservicio de automoviles y de motocicletas
    los vehiculos de un usuario segun su id
	 */
		@CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetAllVehicles")
	@GetMapping("/vehicles/{usuarioId}")
	public ResponseEntity<?> getAllVehicles(@PathVariable("usuarioId") int usuarioId) {
		return ResponseEntity.ok(userService.getUserAndVehicles(usuarioId));
	}
	
	public ResponseEntity<?> fallBackGetAllVehicles(@PathVariable("usuarioId") int id,
			RuntimeException excepcion) {
		return new ResponseEntity("Usuario : " + id + "los servicios no estan actualmente en linea", HttpStatus.OK);
	}
}
