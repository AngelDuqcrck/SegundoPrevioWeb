package com.segundo.previo.spweb.shared.dto;

import java.util.Date;


import lombok.Data;

@Data
public class TicketDTO {
    private Integer id;

    private String titulo;

    
    private String descripcion;

    private Date fechaRegistro;

    private String estado;

    private Integer userId;
}
