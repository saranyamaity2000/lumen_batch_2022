package com.example.demo.repo;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.MobilePlan;

public interface PlanRepositry  extends JpaRepository<MobilePlan, Integer> {
	Optional<MobilePlan> findByPlanId(int id) ;
	
	List<MobilePlan> findByPlanName(String planName) ; 
	
	@Query(value = "select * from lumen_mobile_plans where cost>:updatedCost", nativeQuery = true)
	List<MobilePlan> getCostGreaterThan(@Param("updatedCost") double updatedCost);
	
	/*
	 * Not a native query , its JPQL , understand interm of java! 
	 */
	@Query(value = "update MobilePlan set validity=:updatedValidity where planName=:updatedPlanName")
	@Modifying
	@Transactional
	int updateValidityByPlanName(@Param("updatedPlanName") String planName, @Param("updatedValidity") String validity) ;
}
