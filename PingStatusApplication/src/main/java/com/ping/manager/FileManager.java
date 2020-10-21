package com.ping.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ping.dao.model.PingEntity;

/**
 * Class to process file
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
@Component
public class FileManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileManager.class);

	@Value("${timeoutfile.path}")
	private String path;

	@Value("${timeoutfile.fileName}")
	private String fileName;

	/**
	 * Method to process the timeout requests
	 * @param clientLogEntries
	 */
	public void processTimeOutRequests(List<PingEntity> clientLogEntries) {
		if (clientLogEntries != null && !clientLogEntries.isEmpty()) {

			String file = path + "\\" + fileName;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Writing the requests to file:::" + file);
			}

			try {
				FileWriter fw = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(fw);
				for (PingEntity entry : clientLogEntries) {
					out.write(entry.getClientId() + "-" + entry.getPingTimeStamp());
					out.newLine();
				}
				out.close();
			} catch (Exception e) {
				LOGGER.error("Exception while writing the timeout entries to the file ");
			}
		}
	}

}
