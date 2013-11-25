package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Samochod;

public interface SamochodManager {
	
	void addSamochod(Samochod samochod);
	void deleteSamochod(Long id);
	void updateSamochod(Samochod samochod);
	Samochod findSamochodById(Long id);
	Samochod findSamochodByModel(String model);
	List<Samochod> getAllSamochod();

}
