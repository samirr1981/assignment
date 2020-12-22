package com.indices.web;


import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indices.service.InstrumentService;
import com.indices.web.model.TicksRequest;
import com.indices.web.model.TicksStatResponse;

@RestController
@RequestMapping(value="api/v1")
public class IndicesController {
	
	@Autowired
	private InstrumentService instrumentService;
	
	@GetMapping(value="/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TicksStatResponse>  getAllTickStat() throws Exception {
		CompletableFuture<TicksStatResponse> ticksStat = instrumentService.getTickStatByInstrument(null);
		CompletableFuture.allOf(ticksStat).join();
		return ResponseEntity.status(HttpStatus.OK).body(ticksStat.get());	}

	@GetMapping(value="/statistics/{instrument_identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TicksStatResponse> getTickStatByInstrument(@PathVariable("instrument_identifier") String instrumentIdentifier) throws Exception {
		CompletableFuture<TicksStatResponse> ticksStat = instrumentService.getTickStatByInstrument(instrumentIdentifier);
		CompletableFuture.allOf(ticksStat).join();
		return ResponseEntity.status(HttpStatus.OK).body(ticksStat.get());
	}
	
	@PostMapping(value="/ticks", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addInstrumentTicks(
			@Valid @RequestBody TicksRequest request){
		CompletableFuture<Void> addTask = instrumentService.addInstrumentTicks(request);
		CompletableFuture.allOf(addTask).join();
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

}
