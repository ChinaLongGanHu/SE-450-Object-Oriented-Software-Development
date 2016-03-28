package myproject.model;

import java.util.ArrayList;
import java.util.List;

public class CarFailure implements CarDirectionInterface {
	private List<Car> cars = new ArrayList<Car>();
	private double roadEnd;
	private CarDirectionInterface nextRoad;
	private CarDirectionInterface nextEWRoad;
	private CarDirectionInterface nextNSRoad;

	CarFailure(double roadEnd) {
		if (roadEnd < 0.0) {
			throw new IllegalArgumentException("Road can not be negative");
		} else {
			this.roadEnd = roadEnd;
		}

		nextRoad = this;
		nextEWRoad = this;
		nextNSRoad = this;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null) {
			throw new IllegalArgumentException("Car can not be null");
		}

		cars.remove(car);
	}

	@Override
	public List<Car> getCars() {
		return cars;
	}

	@Override
	public CarDirectionInterface getNextRoad(Car c) {
		return this;
	}

	@Override
	public void setNextRoad(CarDirectionInterface r) {
		throw new IllegalArgumentException("Oppps !! Road is going to fail");

	}

	@Override
	public void setNextNorthSouthRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Oppps !! Road is going to fail");
	}

	@Override
	public void setNextEastWestRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException("Oppps !! Road is going to fail");
	}

	@Override
	public CarDirectionInterface getNextNorthSouthRoad() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public CarDirectionInterface getNextEastWestRoad() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public double getRoadClosed() {
		// TODO Auto-generated method stub
		return roadEnd;
	}

	@Override
	public double distanceToStop(Car car, double fromPosition) {
		// TODO Auto-generated method stub
		return Double.POSITIVE_INFINITY;
	}

}
