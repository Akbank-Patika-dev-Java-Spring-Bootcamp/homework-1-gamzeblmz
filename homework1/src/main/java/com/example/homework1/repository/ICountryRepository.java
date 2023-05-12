package com.example.homework1.repository;

import com.example.homework1.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends  JpaRepository<Country,Long>{

}
