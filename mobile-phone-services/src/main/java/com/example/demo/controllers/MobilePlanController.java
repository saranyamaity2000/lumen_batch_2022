package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.MobilePlan;
import com.example.demo.services.MobilePlanService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="/api/v1/plans")
@AllArgsConstructor
public class MobilePlanController {
	private MobilePlanService service ; 
	
	@GetMapping
	public List<MobilePlan> getAll(){
		 return this.service.findAll() ; 
	}
	
	@GetMapping("/search/id/{id}")
    public MobilePlan findByPlanId(@PathVariable("id") int id) {
        return this.service.findByPlanId(id);
    }
	
	@GetMapping("/search/name/{name}")
    public List<MobilePlan> findByPlanName(@PathVariable("name") String name) {
        return this.service.findByPlanName(name);
    }
	
	@GetMapping("/search/cost-more-than/{cost}")
    public List<MobilePlan> findByCostGreatherThan(@PathVariable("cost") double cost) {
        return this.service.findByCostGreatherThan(cost);
    }
	
	@PostMapping("/add")
	public ResponseEntity<MobilePlan> add(@RequestBody MobilePlan entity){
		MobilePlan addedObj = this.service.add(entity) ; 
		System.out.println(addedObj);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.path("/api/v1/search/{id}")
				.buildAndExpand(entity.getPlanId())
				.toUri(); 
		
		return ResponseEntity.created(location).body(addedObj) ; 
	}
	
	@PutMapping("/update")
    public ResponseEntity<MobilePlan> update(@RequestBody MobilePlan entity){
        MobilePlan addedObj = this.service.update(entity);
        return ResponseEntity.ok().body(addedObj);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        this.service.deleteById(id) ; 
        return ResponseEntity.status(HttpStatus.OK).body("Deleted one record");
    }
    /*
     * Here!!!
     */
    @PutMapping("/update/{planName}/{validity}")
    public ResponseEntity<String> update(@PathVariable("planName") String planName, @PathVariable("validity") String validity){
        String result = this.service.updateValidityByPlanName(planName , validity) ; 
        return ResponseEntity.ok().body(result);
    }
}
