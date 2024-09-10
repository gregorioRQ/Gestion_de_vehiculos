/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuario_service.service;

import com.usuario_service.entities.User;
import com.usuario_service.feingclients.IAutomovilFeignClient;
import com.usuario_service.feingclients.IMotocicletaFeignClient;
import com.usuario_service.dto.AutomovilDTO;
import com.usuario_service.response.ErrorDTO;
import com.usuario_service.dto.MotocicletaDTO;
import com.usuario_service.response.UsuarioConVehiculoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usuario_service.repository.IUserRepository;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author rafae
 */
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IAutomovilFeignClient automovilFeignClient;

    @Autowired
    private IMotocicletaFeignClient motocicletaFeignClient;

    /*
    getAutomoviles y getMotocicletas
    estos metodos son usados con otra forma de comunicar microservicio llamado restTemplate
     */
    public List<AutomovilDTO> getAutomoviles(int usuarioId) {
        List<AutomovilDTO> automoviles = restTemplate.getForObject("http://localhost:8002/automovil/user/" + usuarioId, List.class);
        return automoviles;
    }

    public List<MotocicletaDTO> getMotocicleta(int usuarioId) {
        List<MotocicletaDTO> motocicletas = restTemplate.getForObject("http://localhost:8003/motocicleta/user/" + usuarioId, List.class);
        return motocicletas;
    }

    public AutomovilDTO saveAutomovil(int usuarioId, AutomovilDTO automovil) {
        automovil.setUsuarioId(usuarioId);
        AutomovilDTO automovilSave = automovilFeignClient.save(automovil);
        return automovilSave;
    }

    public MotocicletaDTO saveMotocicleta(int usuarioId, MotocicletaDTO motocicleta) {
        motocicleta.setUsuarioId(usuarioId);
        MotocicletaDTO motocicletaSave = motocicletaFeignClient.save(motocicleta);
        return motocicletaSave;
    }

    public  Object getUserAndVehicles(int usuarioId){
        User userFound = userRepo.findById(usuarioId).orElse(null);     
        if(userFound == null){           
            return new ErrorDTO("Mensaje Error El usuario no ha sido encontrado");
        }      
        /* en esta parte se le pide al microservicio automovil por los datos de un usuario segun su id*/
        List<AutomovilDTO> automovilesUser = automovilFeignClient.getAutomoviles(usuarioId);
        if(automovilesUser.isEmpty()){
            return new ErrorDTO("Mensaje el usuario no tiene automoviles");
        }
        List<MotocicletaDTO> motocicletasUser = motocicletaFeignClient.getMotocicletas(usuarioId);
        if(motocicletasUser.isEmpty()){
            return new ErrorDTO("Mensaje: este usuario no tiene motocicletas");         
        }      
        return UsuarioConVehiculoDTO.builder()
                .user(userFound)
                .automoviles(automovilesUser)
                .motocicletas(motocicletasUser)
                .build();
    }
    
    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public User save(User us) {
        User nuser = userRepo.save(us);
        return nuser;
    }
    
}
