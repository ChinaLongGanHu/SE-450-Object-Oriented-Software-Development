package myproject.model;

import java.util.ArrayList;
import java.util.List;

public final class RoadDirectionEastWest extends Road implements CarDirections {
	private List<Car> _cars = new ArrayList<Car>();
	private CarDirections _nextEWRoad;
	private CarDirections _nextRoad;
	private double _roadEnd;

	RoadDirectionEastWest(double roadEnd) {
		if (roadEnd < 0.0)
			throw new IllegalArgumentException();
		else
			_roadEnd = roadEnd;
	}

	@Override
	public List<Car> getCars() {
		return _cars;
	}

	@Override
	public void setNextNSRoad(CarDirections road) {
		throw new IllegalArgumentException();

	}

	@Override
	public void setNextEWRoad(CarDirections road) {
		_nextEWRoad = road;
		_nextRoad = road;
	}

	@Override
	public CarDirections getNextNSRoad() {
		throw new IllegalArgumentException();
	}

	@Override
	public CarDirections getNextEWRoad() {
		return _nextEWRoad;
	}

	@Override
	public double getRoadEnd() {
		return _roadEnd;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null)
			throw new IllegalArgumentException();
		_cars.remove(car);
		if (frontPosition > getRoadEnd())
			getNextRoad(car).acceptCar(car, frontPosition - getRoadEnd());
		else {
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			_cars.add(car);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getTimeStep(), car);
		}

	}

	@Override
	public double distanceUntilStop(Car car, double fromPosition) {
		double obstacle = Inventory.distanceToCarBack(car, fromPosition, _cars,
				(CarDirections) this);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadEnd() - fromPosition)
					+ getNextRoad(car).distanceUntilStop(car, 0);
		return obstacle;
	}

	@Override
	public CarDirections getNextRoad(Car c) {
		return _nextEWRoad;
	}

	@Override
	public void setNextRoad(CarDirections r) {
		_nextRoad = r;

	}
}
