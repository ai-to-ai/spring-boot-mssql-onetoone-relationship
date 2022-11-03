package com.bezkoder.spring.mssql.repository;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.mssql.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

	// @Query(value = "SELECT * FROM cars, addresses WHERE user_id = ?1 And address_type = ?2" ,nativeQuery=true)
    // Car findByUserIdAndType(Long userId, int addressType);

	@Query("select c from Car c, CarBody cb Where c.id.carId = cb.id.carId AND c.id.carDate = cb.id.carDate AND cb.id.carId = :carId AND cb.id.carDate = :carDate")
	Car fetchCars(@Param("carId") UUID carId, @Param("carDate") Date carDate);
	
	@Query("SELECT c from Car c, CarBody cb WHERE c.carPayment.carPayId = :carPayId")
	Car fetchCarsByCarPayId(@Param("carPayId") String carPayId);

}