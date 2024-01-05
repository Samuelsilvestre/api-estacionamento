package com.api.apiestacionamento.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apiestacionamento.dto.ParkingDto;
import com.api.apiestacionamento.model.Parking;
import com.api.apiestacionamento.service.ParkingService;

import jakarta.validation.Valid;

@RequestMapping("api/parking")
@RestController
public class ParkingController {
    
    private ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;

    }

    @PostMapping("/post")
    public ResponseEntity<Object> save(@RequestBody @Valid ParkingDto dto) {

        if (service.hasName(dto.getName())) {
            return ResponseEntity.badRequest().body("nome de estacionamento ja esta cadastrado.");

        }

        Parking model = new Parking();
        BeanUtils.copyProperties(dto, model);
        model.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.ok().body(service.save(model));

    }

    @GetMapping("/get")
    public ResponseEntity<List<Parking>> listParking() {
        return ResponseEntity.ok().body(service.getAll());


    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOne(@PathVariable BigInteger id) {
        Optional<Parking> optional = service.getOne(id);
        
        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());

        }

        return ResponseEntity.notFound().build();
        
    }
}
