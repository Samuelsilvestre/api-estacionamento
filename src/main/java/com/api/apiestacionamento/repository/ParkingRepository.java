package com.api.apiestacionamento.repository;

import java.rmi.server.UID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.apiestacionamento.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, UID> {
    
}
