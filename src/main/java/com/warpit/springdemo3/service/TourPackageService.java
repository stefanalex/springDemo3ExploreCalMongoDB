package com.warpit.springdemo3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warpit.springdemo3.domain.TourPackage;
import com.warpit.springdemo3.repo.TourPackageRepository;



@Service
public class TourPackageService {
	
	@Autowired
	private TourPackageRepository tourPackageRepository;
	
	/**
	 * Create a tour Package
	 * @param code
	 * @param name
	 * @return
	 */
	public TourPackage createTourPackage(String code,String name) {
		return tourPackageRepository.findById(name).orElse(tourPackageRepository.save(new TourPackage(code,name)));
		
	}
	
	/**
	 * Lookup all tour packages
	 * @return
	 */
	public Iterable<TourPackage> lookup(){
		return tourPackageRepository.findAll();
		
	}
	
	/**
	 * get total number of total package
	 * @return
	 */
	public long total() {return tourPackageRepository.count();}

	
	
}
