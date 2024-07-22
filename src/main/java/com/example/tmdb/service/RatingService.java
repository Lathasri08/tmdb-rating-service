package com.example.tmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tmdb.repo.RatingRepository;
import com.example.tmdb.exception.NotFoundException;
import com.example.tmdb.model.Rating;

@Service
public class RatingService {
	
	@Autowired
	RatingRepository ratingRepository;
	
	public double updateRating(String name, double stars) {
		
		Rating rating = ratingRepository.findByName(name);
		
		if(rating==null) {
			
			rating = new Rating();
			rating.setAvgRating(stars);
			rating.setName(name);
			rating.setCount(1);
			
		}else {
		
			int count = rating.getCount();
			double newAverage = (rating.getAvgRating()*count +stars) / (count+1);
			rating.setAvgRating(newAverage);
			rating.setCount(++count);
		}
		
		ratingRepository.save(rating);
		return rating.getAvgRating();
	}
	
	public Rating fetchRating(String name) {
		
		Rating rating = ratingRepository.findByName(name);
		
		if(rating==null) {
			throw new NotFoundException("Movie not found with name: "+name);
		}
		
		return rating;
	}
}
