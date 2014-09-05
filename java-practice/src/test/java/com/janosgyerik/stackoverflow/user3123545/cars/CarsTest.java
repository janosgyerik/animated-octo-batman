package com.janosgyerik.stackoverflow.user3123545.cars;

import java.util.Collection;

interface Car {
	boolean isEngineOn();
	boolean isDriverBeltOn();
	void warnDriver(String message);
}

interface CarValidator {
	void validateCars(Collection<Car> cars);
}

public class CarsTest {
}
