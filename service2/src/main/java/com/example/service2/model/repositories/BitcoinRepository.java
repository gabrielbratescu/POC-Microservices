package com.example.service2.model.repositories;

import com.example.service2.model.beans.Bitcoin;
import io.micrometer.observation.annotation.Observed;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

@Observed
public interface BitcoinRepository extends CrudRepository<Bitcoin, Long> {


    List<Bitcoin> findByCreatedAtGreaterThan(Date date);



}
