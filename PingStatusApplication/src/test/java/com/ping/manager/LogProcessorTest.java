package com.ping.manager;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class LogProcessorTest {

	LogProcessor logProcessor = new LogProcessor();

	@Test
	void testprocessTimeOutRequests() {
		Map<String, Long > pingTimeOutEntities = new HashMap<String, Long>();
		logProcessor.processTimeOutRequests(pingTimeOutEntities);
	}

}
