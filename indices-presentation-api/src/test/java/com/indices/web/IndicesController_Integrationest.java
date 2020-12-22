package com.indices.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.time.Instant;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indices.web.model.TicksRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
		)
@TestPropertySource(locations = {"classpath:application-test.properties"})
@AutoConfigureMockMvc
@SqlGroup({
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sqls/before.sql"),
	@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sqls/after.sql")
})
public class IndicesController_Integrationest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	
	@Test
	public void addInstrumentTicks_Success() throws Exception {
		
		MvcResult result = this.mockMvc.perform(
					post("/api/v1/ticks")
					.contentType(MediaType.APPLICATION_JSON)
					.content(createJSONRequest()))
					.andExpect(status().is2xxSuccessful())
					.andReturn();
		}
	
	@Test
	public void getTickStatByInstrument_Success() throws Exception {
		MvcResult result = this.mockMvc.perform(
								get("/api/v1/statistics/IBM.M")
									.contentType(MediaType.APPLICATION_JSON))
									.andExpect(status().is2xxSuccessful())
									.andReturn();
				
	}
	
	@Test
	public void getAllTickStat_Success() throws Exception {
		MvcResult result = this.mockMvc.perform(
				get("/api/v1/statistics")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().is2xxSuccessful())
					.andReturn();
	}
	
	private String createJSONRequest(){
		String jsonRequest = null;
		TicksRequest ticksRequest = new TicksRequest();
		ticksRequest.setInstrument("IBM.M");
		ticksRequest.setPrice(22.7);
		ticksRequest.setTimestamp(Instant.now().toEpochMilli());
		ObjectMapper obj = new ObjectMapper();
        try {
        		jsonRequest = obj.writeValueAsString(ticksRequest);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		return jsonRequest;
	}

}
