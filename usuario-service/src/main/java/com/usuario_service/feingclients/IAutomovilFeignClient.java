/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuario_service.feingclients;

import com.usuario_service.dto.AutomovilDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * esta interfaz define los metodos que el cliente usara para solicitar info al microservicio automovil-service
 * los metodos se implementan en la capa de servicio
 * @author rafae
 */
@FeignClient(name = "automovil-service", url="http://localhost:8002/automovil")
public interface IAutomovilFeignClient {

    @PostMapping()
    public AutomovilDTO  save(@RequestBody AutomovilDTO automovil);
    
    /*esta es una ruta que usa feignclient para comunicarse con el otro 
    microservicio, para que funcione el otro microservicio ah de tener
    un metodo con la misma ruta, asi éste micro podra hacer la solicitud de datos de forma correcta
    */
    @GetMapping("/all-automoviles-user/{usuarioId}")
     List<AutomovilDTO> getAutomoviles(@PathVariable("usuarioId") int usuarioId);
}
