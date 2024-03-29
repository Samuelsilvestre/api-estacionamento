package com.api.apiestacionamento.controller;
import java.util.List;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<Parking>> getAll() {
        return ResponseEntity.ok().body(service.listParking());
    }  
   
  
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getOne(@PathVariable BigInteger id) {
        Optional<Parking> optional = service.getOne(id);
        
        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());

        }

        return ResponseEntity.notFound().build();

    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable BigInteger id) {
        Optional<Parking> optional = service.getOne(id);

        if (optional.isPresent()) {
            service.deleteRegister(optional.get());
            return ResponseEntity.ok().body(optional.get());

        }

        return ResponseEntity.notFound().build();

    }

    
    @PutMapping("/put/{id}")
    public ResponseEntity<Object> update(@PathVariable BigInteger id, @RequestBody @Valid ParkingDto dto) {
        Optional<Parking> optional = service.getOne(id);
        Parking model = new Parking();

        if (optional.isPresent()) {
            BeanUtils.copyProperties(dto, model);
            model.setId(id);
            model.setDate(optional.get().getDate());
            return ResponseEntity.ok().body(service.save(model));

        }

        return ResponseEntity.notFound().build();

    }

}
    


