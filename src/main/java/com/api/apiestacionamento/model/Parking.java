package com.api.apiestacionamento.model;

import java.io.Serializable;
import java.rmi.server.UID;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "tb_parking")
@Data
public class Parking implements Serializable {
    
    private static final long serialVersion = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UID id;

    @Column(name = "name", length = 25, nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false, length = 25)
    @Pattern(regexp = "aero porto|porto|shoping|condominio")
    private String type;

    private LocalDateTime date;

}
