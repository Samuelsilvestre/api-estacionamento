package com.api.apiestacionamento.service;

import org.springframework.stereotype.Service;

import com.api.apiestacionamento.repository.ParkingRepository;

@Service
public class ParkingService {
    
    final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }
}
