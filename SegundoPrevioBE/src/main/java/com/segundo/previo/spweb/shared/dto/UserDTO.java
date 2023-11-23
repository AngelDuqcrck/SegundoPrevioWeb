package com.segundo.previo.spweb.shared.dto;

import lombok.Data;
@Data

public class UserDTO {
    private Integer id;
    
    private String nombre;
  
    private String apellido;

    private String telefono;

    private String email;
}