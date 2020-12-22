package com.indices.configuration;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadConfiguration {
	
	@Value("${threadpool.corepoolsize}")
	private int corePoolSize;
	
	@Value("${threadpool.maxpoolsize}")
	private int maxPoolSize;
	
	@Value("${threadpool.queuecapacity}")
	private int queueCapicity;
	
	
	@Bean("statExecutor")
	public Executor asyncServiceExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapicity);
		executor.setThreadNamePrefix("TicksStat-");
		executor.initialize();
		return executor;
	}
	
	@Bean("statSubExecutor")
	public Executor asyncAddTicksServiceExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapicity);
		executor.setThreadNamePrefix("TicksStat-");
		executor.initialize();
		return executor;
	}

}
