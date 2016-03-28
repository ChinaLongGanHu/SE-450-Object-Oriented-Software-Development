package myproject.model;


import java.util.ArrayList;
import java.util.List;

public class CarFailure implements CarDirections {
	private List<Car> _cars = new ArrayList<Car>();
	private double _roadEnd;
	private CarDirections _nextRoad;
	private CarDirections _nextEWRoad;
	private CarDirections _nextNSRoad;

	CarFailure(double roadEnd) {
		if (roadEnd < 0.0)
			throw new IllegalArgumentException("Road is < 0");
		else
			_roadEnd = roadEnd;
		_nextRoad = this;
		_nextEWRoad = this;
		_nextNSRoad = this;
	}

	@Override
	public void setNextNSRoad(CarDirections road) {
		throw new IllegalArgumentException("Something went bad");

	}

	@Override
	public void setNextEWRoad(CarDirections road) {
		throw new IllegalArgumentException("Something went bad");

	}

	@Override
	public CarDirections getNextNSRoad() {
		return this;
	}

	@Override
	public CarDirections getNextEWRoad() {
		return this;
	}

	@Override
	public double getRoadEnd() {
		return _roadEnd;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null)
			throw new IllegalArgumentException("null car");
		_cars.remove(car);
	}

	@Override
	public List<Car> getCars() {
		return _cars;
	}

	@Override
	public double distanceUntilStop(Car car, double fromPosition) {
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public CarDirections getNextRoad(Car c) {
		return this;
	}

	@Override
	public void setNextRoad(CarDirections r) {
		throw new IllegalArgumentException("Something went bad");

	}

}
