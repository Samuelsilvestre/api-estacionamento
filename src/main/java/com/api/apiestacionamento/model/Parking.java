package com.api.apiestacionamento.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;

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
    private BigInteger id;

    @Column(name = "name", length = 25, nullable = false, unique = true)
    private String name;

    @Column(name = "type", nullable = false, length = 25)
    private String type;
    
    @Column(name = "address", length = 50, nullable = false)
    private String address;

    private LocalDateTime date;


}
