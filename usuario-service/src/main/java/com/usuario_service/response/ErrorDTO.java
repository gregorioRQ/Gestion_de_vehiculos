/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuario_service.response;

import lombok.Data;

/**
 *
 * @author rafae
 */
@Data
public class ErrorDTO {
    private String mensaje;

    public ErrorDTO(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
