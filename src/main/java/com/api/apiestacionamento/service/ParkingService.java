package com.api.apiestacionamento.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.apiestacionamento.model.Parking;
import com.api.apiestacionamento.repository.ParkingRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingService {
    
    private ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;

    }

    public boolean hasName(String name) {
        return repository.existsByName(name);

    }

    @Transactional
    public Object save(Parking parking) {
        return repository.save(parking);

    }
}
