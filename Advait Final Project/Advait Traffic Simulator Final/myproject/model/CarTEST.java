package myproject.model;

import junit.framework.TestCase;

public class CarTEST extends TestCase {
	double length = MP.getvarCarLength();
	double velocity = MP.getvarMaximumVelocity();
	double breakDistance = MP.getvarCarBreakDistance();
	double stopDistance = MP.getvarCarStopDistance();

	public void testCarFields() {

		Car car = Inventory.newCar();
		CarDirectionInterface road = Inventory.newEWRoad();

		car.setCurrentRoad(road);
		assertSame(car.getCurrentRoad(), road);

		double frontPosition = car.getFrontPosition();
		double backPosition = car.getBackPosition();
		double length = car.getLength();

		assertSame((int) frontPosition - (int) length, (int) backPosition);

		assertTrue(car.getMaxVelocity() >= MP.getvarMaximumVelocityMin());
		assertTrue(car.getMaxVelocity() <= MP.getvarMaximumVelocityMax());

		assertTrue(car.getBrakeDistance() >= MP.getvarCarBreakDistanceMin());
		assertTrue(car.getBrakeDistance() <= MP.getvarCarBreakDistanceMax());

		assertTrue(car.getStopDistance() >= .5);
		assertTrue(car.getStopDistance() <= 5);

		Car car2 = Inventory.newCar();

		assertNotSame(car.getMaxVelocity(), car2.getMaxVelocity());
	}

	public void testConstructor() {

		Car c1 = new Car(length, velocity, breakDistance, stopDistance);
		assertEquals(length, c1.getLength());
		assertEquals(velocity, c1.getMaxVelocity());
		assertEquals(breakDistance, c1.getBrakeDistance());
		assertEquals(stopDistance, c1.getStopDistance());

		Car c2 = Inventory.newCar();
		assertNotSame(c1, c2);
		assertSame(c1, c1);
		assertSame(c2, c2);

		Car c3 = Inventory.newCar();
		assertNotSame(c2, c3);
	}

	public void testConstructorExceptions() {
		Car c1;
		try {
			c1 = new Car(0, velocity, breakDistance, stopDistance);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			c1 = new Car(length, 0, breakDistance, stopDistance);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			c1 = new Car(length, velocity, 0, stopDistance);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			c1 = new Car(length, velocity, breakDistance, 0);
			fail();
		} catch (IllegalArgumentException e) {

		}
	}

	public void testCar() {
		Car car = Inventory.newCar();
		CarDirectionInterface road = Inventory.newEWRoad();

		car.setCurrentRoad(road);

		double frontPosition = car.getFrontPosition();
		double length = car.getLength();
		double backPostion = car.getBackPosition();

		assertEquals(frontPosition - length, backPostion);

	}

}
