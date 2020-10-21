package com.ping.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ping.dao.model.PingEntity;

/**
 * Class to process Database operations
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */

public interface PingDatabaseRepository extends JpaRepository<PingEntity, Long> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Ping p SET p.PING_TIMESTAMP  = ? WHERE p.CLIENT_ID = ?", nativeQuery = true)
	int updateLogEntry(Long timestamp, String clientId);
	
	@Query(value="select * from Ping p where p.client_id = ?", nativeQuery=true)
	PingEntity findByClientId(String clientId);
}
