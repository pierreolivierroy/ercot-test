package com.cwp.ercottest.repository;

import com.cwp.ercottest.model.ShortTermSystemAdequacy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface ShortTermSystemAdequacyRepository extends CrudRepository<ShortTermSystemAdequacy, Timestamp> {
    Optional<ShortTermSystemAdequacy> findTopByOrderByOriginalDateTimeDesc();
}
