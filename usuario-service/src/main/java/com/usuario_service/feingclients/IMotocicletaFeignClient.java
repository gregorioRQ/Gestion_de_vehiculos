/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usuario_service.feingclients;

import com.usuario_service.dto.MotocicletaDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author rafae
 */
@FeignClient(name="motocicleta-service"/*, url="http://localhost:8003/motocicleta"*/)
public interface IMotocicletaFeignClient {
    @PostMapping()
    public MotocicletaDTO save(@RequestBody MotocicletaDTO motocicleta);
    
    @GetMapping("/all-motocicletas-user/{usuarioId}")
    List<MotocicletaDTO> getMotocicletas(@PathVariable("usuarioId") int usuarioId);
}
