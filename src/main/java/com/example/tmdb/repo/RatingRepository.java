package com.example.tmdb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tmdb.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	
	Rating findByName(String name);
	
	List<Rating> findAllByAvgRatingBetween(double min, double max);
}

