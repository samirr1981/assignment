package com.indices.entity.mapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.indices.entity.model.Instrument;
import com.indices.web.model.TicksRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstrumentTicksMapper {
	
	public Instrument mapToInstrument(TicksRequest request) {
		Instrument instrument = new Instrument();
		instrument.setInstrument(request.getInstrument());
		instrument.setPrice(new BigDecimal(request.getPrice()));
		instrument.setInstrument_timestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(request.getTimestamp()), ZoneId.systemDefault()));
		return instrument;
	}

}
