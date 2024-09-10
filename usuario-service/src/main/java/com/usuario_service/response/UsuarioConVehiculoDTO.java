/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuario_service.response;

import com.usuario_service.dto.AutomovilDTO;
import com.usuario_service.dto.MotocicletaDTO;
import com.usuario_service.entities.User;
import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author rafae
 */
@Data
@Builder
public class UsuarioConVehiculoDTO {

    private User user;
    private List<AutomovilDTO> automoviles;
    private List<MotocicletaDTO> motocicletas;

    public UsuarioConVehiculoDTO() {
    }

    public UsuarioConVehiculoDTO(User user, List<AutomovilDTO> automoviles, List<MotocicletaDTO> motocicletas) {
        this.user = user;
        this.automoviles = automoviles;
        this.motocicletas = motocicletas;
    }

    
}
