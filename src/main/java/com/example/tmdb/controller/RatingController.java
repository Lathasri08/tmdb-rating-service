package com.example.tmdb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tmdb.model.Rating;
import com.example.tmdb.model.RatingRequest;
import com.example.tmdb.service.RatingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rating")
@Slf4j
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@GetMapping("/{name}")
	public ResponseEntity<Rating> getRating(@PathVariable String name){
		
		Rating rating = ratingService.fetchRating(name);
		log.info("Returning rating of the movie with name: "+ name);
		return ResponseEntity.ok(rating);
	}
	
	@PutMapping
	public Map<String, Double> updateMapping(@RequestBody RatingRequest request) {
		
		double avgRating = ratingService.updateRating(request.getName(), request.getRating());
		log.info("Updating movie with name: "+ request.getName());
		return Map.of("average",avgRating);
	}
}
