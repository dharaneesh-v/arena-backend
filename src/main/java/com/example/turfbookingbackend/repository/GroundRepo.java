package com.example.turfbookingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.turfbookingbackend.model.Ground;

public interface GroundRepo extends JpaRepository<Ground,Long>{
    
}
