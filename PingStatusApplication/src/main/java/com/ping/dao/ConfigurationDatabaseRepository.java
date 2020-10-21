package com.ping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ping.dao.model.ConfigurationEntity;

/**
 * Class to process configuration Database operations
 * 
 * @author Satya Sravanthi Dhanekula
 *
 */
public interface ConfigurationDatabaseRepository extends JpaRepository<ConfigurationEntity, Long> {

}
