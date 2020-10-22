package com.ping.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class to process file
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Component
public class LogProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LogProcessor.class);

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Value("${timeoutfile.path}")
	private String path;

	@Value("${timeoutfile.fileName}")
	private String fileName;

	/**
	 * Method to process the timeout requests
	 * 
	 * @param pingTimeOutEntities
	 */
	public void processTimeOutRequests(Map<String, Long> pingTimeOutEntities) {
		if (pingTimeOutEntities != null && !pingTimeOutEntities.isEmpty()) {

			String file = path + "\\" + fileName;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Writing the requests to file:::" + file);
			}

			try {
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for (Entry<String, Long> entry : pingTimeOutEntities.entrySet()) {
					bufferedWriter.write(formatter.format(new Date()) + "-" + entry.getKey() + "-" + entry.getValue());
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
			} catch (Exception e) {
				LOGGER.error("Exception while writing the timeout entries to the file ");
			}
		}
	}

}
