package com.bezkoder.spring.mssql.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.mssql.model.Car;
import com.bezkoder.spring.mssql.model.CarBody;
import com.bezkoder.spring.mssql.model.CarId;
import com.bezkoder.spring.mssql.repository.CarBodyRepository;
import com.bezkoder.spring.mssql.repository.CarRepository;
import com.bezkoder.spring.mssql.request.CreateRequest;
import com.bezkoder.spring.mssql.request.GetRequest;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CarController {

	@Autowired
	CarRepository carRepository;

	@Autowired
	CarBodyRepository carBodyRepository;

	@PostMapping("/cars")
	public ResponseEntity<Car> getCarByPK(@RequestBody GetRequest carRequest) {
		try {

			// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
			// Date carDate = formatter.parse(carRequest.getCarDate());
			Date carDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(carRequest.getCarDate());
			
			UUID carId = UUID.fromString(carRequest.getCarId());

			Car car = carRepository.fetchCars(carId, carDate);

			if (car == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(car, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cars/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") String carPayId) {

		Car car = carRepository.fetchCarsByCarPayId(carPayId);

		if (car != null) {
			return new ResponseEntity<>(car, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/cars_list")
	public ResponseEntity<List<Car>> getCars() {

		List<Car> cars = carRepository.findAll();

		if (!cars.isEmpty()) {
			return new ResponseEntity<>(cars, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/carbodys_list")
	public ResponseEntity<List<CarBody>> getCarBodys() {

		List<CarBody> carBodys = carBodyRepository.findAll();

		if (!carBodys.isEmpty()) {
			return new ResponseEntity<>(carBodys, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/car")
	public ResponseEntity<List<Car>> createCar(@RequestBody CreateRequest createRequest) {
		try {
			
		   CarId carId = new CarId();

		   CarBody carBody = new CarBody();
			carBody.setId(carId);
			carBody.setCarBodyMsg(createRequest.getCarBodyMsg());
			carBody.setCarBodyTrunk(createRequest.getCarBodyTrunk());

		   Car car = new Car();
		   car.setId(carId);
		   car.setCarPayId(createRequest.getCarPayId());
		   car.setCarType(createRequest.getCarType());
		   car.setCarWheels(createRequest.getCarWheels());

		   carBody.setCar(car);
			car.setCarBody(carBody);

		   // car.setCarBody(carBody);
			// // car.setCarPayment(null);

			// // car.setCarBody(carBody);
			carRepository.save(car);
			carBodyRepository.save(carBody);
			// carBodyRepository.save(carBody);

			return new ResponseEntity<>(carRepository.findAll(), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
