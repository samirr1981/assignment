package com.indices.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.indices.entity.mapper.InstrumentTicksMapper;
import com.indices.entity.model.Instrument;
import com.indices.entity.repository.IndicesRepository;
import com.indices.exception.InvalidInstrumentException;
import com.indices.exception.NoDataAvailableException;
import com.indices.web.model.TicksRequest;
import com.indices.web.model.TicksStatResponse;

@Service
public class InstrumentServiceImpl implements InstrumentService {

	@Autowired
	private IndicesRepository indicesRepository;
	
	@Autowired
	private InstrumentTicksMapper instrumentTicksMapper;
	
	private final static int VARIENT = 1;
	
	@Override
	@Async("statExecutor")
	public CompletableFuture<Void> addInstrumentTicks(TicksRequest request) {
		validateTicks(request.getTimestamp());

		try {
			indicesRepository.save(instrumentTicksMapper.mapToInstrument(request));
		}
		catch(InvalidInstrumentException e) {
			throw new InvalidInstrumentException(e.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Plesae try after sometime");
		}
		return CompletableFuture.completedFuture(null);
	}
	
	private void validateTicks(Long timestamp) {
		LocalDateTime end = Instant.ofEpochMilli(timestamp)
		        .atZone(ZoneId.systemDefault())
		        .toLocalDateTime();
		LocalDateTime start = LocalDateTime.now().minusMinutes(VARIENT);

		if ( end.isBefore(start)) {
		  throw new InvalidInstrumentException("Invalid Ticks Timestamp");
		}
	}

	@Override
	@Async("statExecutor")
	public CompletableFuture<TicksStatResponse> getTickStatByInstrument(String identifier){
		TicksStatResponse response = new TicksStatResponse();
		List<Instrument> instrumentsList = indicesRepository.findAllByInstrument(identifier, LocalDateTime.now().minusMinutes(VARIENT));
		if(instrumentsList == null || instrumentsList.isEmpty()) {
			throw new NoDataAvailableException("No Data Available");
		}
		try {
		CompletableFuture<Double> avg = getAverage(instrumentsList);
		CompletableFuture<BigDecimal> max = getMaxPrice(instrumentsList);
		CompletableFuture<BigDecimal> min = getMinPrice(instrumentsList);
		
		CompletableFuture.allOf(avg, max, min).join();
		
		response.setAvg(avg.get());
		response.setMax(Double.parseDouble(max.get().toString()));
		response.setMin(Double.parseDouble(min.get().toString()));
		response.setCount(new Long(instrumentsList.size()));
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Please try after sometime");
		}
		return CompletableFuture.completedFuture(response);
	}
	
	@Async("statSubExecutor")
	private CompletableFuture<BigDecimal> getMinPrice(List<Instrument> instList) {
		return CompletableFuture.completedFuture(instList.parallelStream().min(Comparator.comparing(Instrument::getPrice)).get().getPrice());
	}
	
	@Async("statSubExecutor")
	private CompletableFuture<BigDecimal> getMaxPrice(List<Instrument> instList) {
		return CompletableFuture.completedFuture(instList.parallelStream().max(Comparator.comparing(Instrument::getPrice)).get().getPrice());
	}
	
	@Async("statSubExecutor")
	private CompletableFuture<Double> getAverage(List<Instrument> instList) {
		return CompletableFuture.completedFuture(instList.parallelStream().mapToDouble(i -> Double.parseDouble(i.getPrice().toString())).average().getAsDouble());
	}

}
