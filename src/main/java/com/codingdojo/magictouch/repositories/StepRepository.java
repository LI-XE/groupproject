package com.codingdojo.magictouch.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.magictouch.models.Step;

@Repository
public interface StepRepository extends CrudRepository<Step, Long> {
	List<Step>findALL();

}
