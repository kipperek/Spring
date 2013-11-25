package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.shdemo.domain.Samochod;


public class SamochodManagerImpl implements SamochodManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addSamochod(Samochod samochod) {
		samochod.setId(null);
		sessionFactory.getCurrentSession().persist(samochod);
	}

	@Override
	public void deleteSamochod(Long id) {
		Samochod samochod = findSamochodById(id);
		
		sessionFactory.getCurrentSession().delete(samochod);
		
	}

	@Override
	public void updateSamochod(Samochod samochod) {
		sessionFactory.getCurrentSession().update(samochod);
	}

	@Override
	public Samochod findSamochodById(Long id) {
		return (Samochod) sessionFactory.getCurrentSession().get(Samochod.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Samochod> getAllSamochod() {
		return sessionFactory.getCurrentSession().getNamedQuery("samochod.all").list();
	}

	@Override
	public Samochod findSamochodByModel(String model) {
		return (Samochod) sessionFactory.getCurrentSession().getNamedQuery("samochod.byModel").setString("model", model).uniqueResult();
	}

}
