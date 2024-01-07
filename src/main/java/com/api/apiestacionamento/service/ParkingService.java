package com.api.apiestacionamento.service;
import java.math.BigInteger;
import java.util.List;
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
    
    public List<Parking> listParking( ) {
        return repository.findAll();
        
    }

    public boolean hasName(String name) {
        return repository.existsByName(name);

    }


    @Transactional
    public Object save(Parking parking) {
        return repository.save(parking);

    }
    
    public Optional<Parking> getOne(BigInteger id) {
        return repository.findById(id);

    }
    
    @Transactional
    public void deleteRegister(Parking parking) {
        repository.delete(parking);
        
    }
}
