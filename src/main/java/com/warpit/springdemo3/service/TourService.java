package com.warpit.springdemo3.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warpit.springdemo3.domain.Tour;
import com.warpit.springdemo3.domain.TourPackage;
import com.warpit.springdemo3.repo.TourPackageRepository;
import com.warpit.springdemo3.repo.TourRepository;

@Service
public class TourService {
	
	@Autowired
	private TourRepository tourRepository;
	@Autowired
    private TourPackageRepository tourPackageRepository;

	 
	  

	    /**
	     * Create a new Tour Object and persist it to the Database
	     *
	     * @param title Title of the tour
	     * @param tourPackageName tour Package of the tour
	     * @param details Extra details about the tour
	     * @return Tour
	     */
	    public Tour createTour(String title, String tourPackageName, Map<String, String> details) {
	        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() ->
	                new RuntimeException("Tour package does not exist: " + tourPackageName));
	        return tourRepository.save(new Tour(title, tourPackage, details));
	    }
	    /**
	     * Calculate the number of Tours in the Database.
	     *
	     * @return the total.
	     */
	    public long total() {
	        return tourRepository.count();
	    }
	
	
}
