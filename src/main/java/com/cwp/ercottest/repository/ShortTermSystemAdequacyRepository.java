package com.cwp.ercottest.repository;

import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ShortTermSystemAdequacyRepository extends CrudRepository<ShortTermSystemAdequacy, Timestamp> {
}
