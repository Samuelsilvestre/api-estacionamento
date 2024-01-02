package com.api.apiestacionamento.controller;

import java.util.List;
import java.util.Optional;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.coyote.http2.Http2OutputBuffer;
import org.aspectj.apache.bcel.generic.RET;
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

    @GetMapping("/get/all")
    public ResponseEntity<List<Parking>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("get/one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable BigInteger id) {
        Optional<Parking> model = service.getId(id);

        if (!model.isPresent()) {
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok().body(model.get());

}

@DeleteMapping("delete/{id}")
public ResponseEntity<Object> delete(@PathVariable BigInteger id) {
    Optional<Parking> model = service.getId(id);

    if (!model.isPresent()) {
        return ResponseEntity.notFound().build();

    }
    
    service.destroy(model.get());
    return ResponseEntity.ok().body("deletado");
    
}

}
