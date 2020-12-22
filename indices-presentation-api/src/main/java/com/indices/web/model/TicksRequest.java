package com.indices.web.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TicksRequest {
	@NotEmpty(message = "Instrument Label Required.")
	public String instrument;
	@NotNull(message = "Price Required.")
	public Double price;
	@NotNull(message = "Timestamp Required")
	public Long timestamp;
	public String getInstrument() {
		return instrument;
	}
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
