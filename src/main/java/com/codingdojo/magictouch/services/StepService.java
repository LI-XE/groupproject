package com.codingdojo.magictouch.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.magictouch.models.Step;
import com.codingdojo.magictouch.repositories.StepRepository;

@Service
public class StepService {
	@Autowired
	private StepRepository sRepo;
	
	
	public List<Step> allStep(){
		return this.sRepo.findAll();
	}
	
	public Step newStep(Step step) {
		return this.sRepo.save(step);
	}
	
	

}
