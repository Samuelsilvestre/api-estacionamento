package com.api.apiestacionamento.service;

import org.springframework.stereotype.Service;

import com.api.apiestacionamento.model.Parking;
import com.api.apiestacionamento.repository.ParkingRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingService {
    
    final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;

    }
    
    @Transactional
    public Parking save(Parking parking) {
        return repository.save(parking);

    }

    public boolean existName(String name) {
        return repository.existsByName(name);

    }
}
