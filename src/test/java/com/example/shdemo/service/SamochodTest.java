package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Samochod;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SamochodTest {

	@Autowired
	SamochodManager samochodManager;

	private final String MARKA_1 = "VW";
	private final String MODEL_1 = "Polo";
	private final int YOP_1 = 2004;
	
	private final String MARKA_2 = "Ford";
	private final String MODEL_2 = "Fiesta";
	private final int YOP_2 = 2012;
	
	private final String MARKA_3 = "Skoda";
	private final String MODEL_3 = "Fabia";
	private final int YOP_3 = 2007;



	@Test
	public void addClientCheck() {

		List<Samochod> pobraneSamochody = samochodManager.getAllSamochod();

		for (Samochod s : pobraneSamochody) {
			if (s.getMarka().equals(MODEL_1)) {
				samochodManager.deleteSamochod(s.getId());
			}
		}

		Samochod samochod = new Samochod();
		samochod.setMarka(MARKA_1);
		samochod.setYop(YOP_1);
		samochod.setModel(MODEL_1);
		

		samochodManager.addSamochod(samochod);

		Person retrievedClient = sellingManager.findClientByPin(PIN_1);

		assertEquals(NAME_1, retrievedClient.getFirstName());
		assertEquals(PIN_1, retrievedClient.getPin());
		// ... check other properties here
	}

	@Test
	public void addCarCheck() {

		Car car = new Car();
		car.setMake(MAKE_1);
		car.setModel(MODEL_1);
		// ... other properties here

		Long carId = sellingManager.addNewCar(car);

		Car retrievedCar = sellingManager.findCarById(carId);
		assertEquals(MAKE_1, retrievedCar.getMake());
		assertEquals(MODEL_1, retrievedCar.getModel());
		// ... check other properties here

	}

	@Test
	public void sellCarCheck() {

		Person person = new Person();
		person.setFirstName(NAME_2);
		person.setPin(PIN_2);

		sellingManager.addClient(person);

		Person retrievedPerson = sellingManager.findClientByPin(PIN_2);

		Car car = new Car();
		car.setMake(MAKE_2);
		car.setModel(MODEL_2);

		Long carId = sellingManager.addNewCar(car);

		sellingManager.sellCar(retrievedPerson.getId(), carId);

		List<Car> ownedCars = sellingManager.getOwnedCars(retrievedPerson);

		assertEquals(1, ownedCars.size());
		assertEquals(MAKE_2, ownedCars.get(0).getMake());
		assertEquals(MODEL_2, ownedCars.get(0).getModel());
	}

	// @Test -
	public void disposeCarCheck() {
		// Do it yourself
	}

}
