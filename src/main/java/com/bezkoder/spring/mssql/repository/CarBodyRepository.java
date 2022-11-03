package com.bezkoder.spring.mssql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.mssql.model.CarBody;

public interface CarBodyRepository extends JpaRepository<CarBody, Long> {


}