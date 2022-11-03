package com.bezkoder.spring.mssql.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "carPayment")
public class CarPayment implements Serializable {
	
	@EmbeddedId
	private CarId id;
		
	@Column(name = "carPayId", unique = true)
	private String carPayId;
	
	@Column(name = "carPayMsg")
	private String carPayMsg;
	
	@Column(name = "carPayEmail")
	private String carPayEmail;
	
	@MapsId
	@OneToOne
	@JsonBackReference
	private Car car;
	
}