package com.bezkoder.spring.mssql.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class Car implements Serializable {
	
	@EmbeddedId
	private CarId id;

	@Column(name = "carPayId")
	private String carPayId;
	
	@Column(name = "carType")
	private String carType;
	
	@Column(name = "carWheels")
	private String carWheels;

	@OneToOne(mappedBy = "car")
	@JsonManagedReference
	private CarPayment carPayment;

	@OneToOne(mappedBy = "car")
	@JsonManagedReference
	private CarBody carBody;
	
	//getters/setters
	
	//hashcode and equals methods
}