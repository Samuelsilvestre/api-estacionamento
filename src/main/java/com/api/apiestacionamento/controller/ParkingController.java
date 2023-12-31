package com.api.apiestacionamento.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiestacionamento.dto.ParkingDto;
import com.api.apiestacionamento.model.Parking;
import com.api.apiestacionamento.service.ParkingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/parking")
public class ParkingController {
    
    final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;

    }

    @PostMapping("/post")
    public ResponseEntity<Object> create(@RequestBody @Valid ParkingDto dto) {
        if (service.existName(dto.getName())) {
            return ResponseEntity.badRequest().body("no ja esta cadastrado");

        }

        var model = new Parking();
        BeanUtils.copyProperties(dto, model);
        model.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.ok().body(service.save(model));
        
    }
}
