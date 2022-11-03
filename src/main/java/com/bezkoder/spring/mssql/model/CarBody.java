package com.bezkoder.spring.mssql.model;

import java.io.Serializable;

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
@Table(name = "carBody")
public class CarBody implements Serializable {
	
	@EmbeddedId
	private CarId id;
	
	@Column(name = "carBodyMsg")
	private String carBodyMsg;
	
	@Column(name = "carBodyTrunk")
	private String carBodyTrunk;
	
	@MapsId
	@OneToOne
	@JsonBackReference
	private Car car;
}