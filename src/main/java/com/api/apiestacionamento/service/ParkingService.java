package com.api.apiestacionamento.service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;
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

    public List<Parking> getAll() {
        return repository.findAll();

    }

    @Transactional
    public Object save(Parking parking) {
        return repository.save(parking);

    }
    
    public Optional<Parking> getOne(BigInteger id) {
        return repository.findById(id);
        
    }
}
