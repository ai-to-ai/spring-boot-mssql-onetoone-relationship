package com.bezkoder.spring.mssql.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Embeddable
public class CarId implements Serializable {
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "carId")
	private UUID carId;  // (auto generated UUID)

	@CreatedDate
	@Column(name = "carDate")
	private Date carDate;

	public CarId() throws ParseException {
		this.carId = UUID.randomUUID();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
		Date date = new Date();
		
		String temp = formatter.format(date);
		this.carDate = formatter.parse(temp);
	}

	public CarId(UUID carId, Date carDate) {
		this.carId = carId;
		this.carDate = carDate;
	}

	public UUID getCarId() {
		return carId;
	}

	public Date getCarDate() {
		return carDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CarId)) return false;
		CarId that = (CarId) o;
		return Objects.equals(getCarId(), that.getCarId()) &&
				Objects.equals(getCarDate(), that.getCarDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCarId(), getCarDate());
	}
}