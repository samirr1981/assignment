package com.indices.service;

import java.util.concurrent.CompletableFuture;

import com.indices.web.model.TicksRequest;
import com.indices.web.model.TicksStatResponse;

public interface InstrumentService {

	CompletableFuture<Void> addInstrumentTicks(TicksRequest request);
	CompletableFuture<TicksStatResponse> getTickStatByInstrument(String identifier);
	

}