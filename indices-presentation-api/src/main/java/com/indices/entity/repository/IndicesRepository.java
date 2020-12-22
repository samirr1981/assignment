package com.indices.entity.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indices.entity.model.Instrument;

@Repository
public interface IndicesRepository extends JpaRepository<Instrument, Long>{

	
	@Query("SELECT c FROM Instrument c WHERE (:instrument_identifier is null or c.instrument = :instrument_identifier) and c.instrument_timestamp  >=  :instrument_endtime")
	@Transactional
	List<Instrument> findAllByInstrument(@Param("instrument_identifier") String instrument_identifier, @Param("instrument_endtime") LocalDateTime instrument_endtime);
		
}
