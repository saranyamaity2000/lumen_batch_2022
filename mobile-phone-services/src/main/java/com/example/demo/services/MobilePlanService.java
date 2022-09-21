package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MobilePlan;
import com.example.demo.repo.PlanRepositry;

@Service
public class MobilePlanService {
    private PlanRepositry repo ; 
	
	@Autowired
	public MobilePlanService(PlanRepositry repo) {
		super() ; 
		this.repo = repo ; 
	}
	
	public List<MobilePlan> findAll(){
		return this.repo.findAll() ; 
	}
	
	/*
	 * add to the database if not present
	 */
	public MobilePlan add(MobilePlan entity) {
		MobilePlan addedObj = entity ; 
		this.repo.findById(entity.getPlanId()).ifPresent(e -> {throw new RuntimeException("Element Already Present");});
		this.repo.save(entity) ; 
		return addedObj ; 
	}
	
	public MobilePlan findByPlanId(int id) { 
		String msg = new StringBuilder("Element with id ").append(id).append(" Not present!").toString() ; 
		return this.repo.findById(id).orElseThrow(() -> new RuntimeException("ERROR-202 || " + msg)) ; 
	}
	
	public MobilePlan update(MobilePlan entity) {
		return this.repo.save(entity) ; 
	}
	
	public void deleteById(int id) {
		this.findByPlanId(id) ; // to get runtime error!
		this.repo.deleteById(id); 
	}
	
	public List<MobilePlan> findByPlanName(String planName){
		return this.repo.findByPlanName(planName) ; 
	}
	
	public List<MobilePlan> findByCostGreatherThan(double cost){
		return this.repo.getCostGreaterThan(cost) ; 
	}
	
	public String updateValidityByPlanName(String planName, String validity) {
		Integer updateCount =  this.repo.updateValidityByPlanName(planName, validity) ; 
		return updateCount.toString() + " " + "rows updated!" ; 
	}
}
