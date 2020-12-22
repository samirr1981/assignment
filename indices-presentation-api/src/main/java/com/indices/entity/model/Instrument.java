package com.indices.entity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.indices.entity.converter.DateTimeConverter;

@Entity
@Table(name="INSTRUMENT_TICKS_DETAILS")
public class Instrument {
	@Id
	@GeneratedValue
	private Long id;
	
	private String instrument;
	
	private BigDecimal price;
	
	@Convert(converter = DateTimeConverter.class)
	private LocalDateTime instrument_timestamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getInstrument_timestamp() {
		return instrument_timestamp;
	}

	public void setInstrument_timestamp(LocalDateTime instrument_timestamp) {
		this.instrument_timestamp = instrument_timestamp;
	}
	
	

}
