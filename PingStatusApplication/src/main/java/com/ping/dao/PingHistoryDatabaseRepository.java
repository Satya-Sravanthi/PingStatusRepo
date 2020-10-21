package com.ping.dao;

import org.springframework.data.repository.CrudRepository;

import com.ping.dao.model.PingHistoryEntity;

/**
 * Class to process Database operations
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */

public interface PingHistoryDatabaseRepository extends CrudRepository<PingHistoryEntity, Long> {

}
