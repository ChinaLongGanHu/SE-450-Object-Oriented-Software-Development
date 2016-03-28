package myproject.model;

import java.util.ArrayList;
import java.util.List;

public final class RoadDirectionNorthSouth extends Road implements CarDirections {
	private List<Car> cars = new ArrayList<Car>();
	private CarDirections nextNSRoad;
	private CarDirections nextRoad;
	private double roadEnd;

	RoadDirectionNorthSouth(double roadEnd) {
		if (roadEnd < 0.0)
			throw new IllegalArgumentException();
		else
			this.roadEnd = roadEnd;
	}

	@Override
	public List<Car> getCars() {
		return cars;
	}

	@Override
	public void setNextNSRoad(CarDirections road) {
		nextNSRoad = road;
		nextRoad = road;
	}

	@Override
	public void setNextEWRoad(CarDirections road) {
		throw new IllegalArgumentException();
	}

	@Override
	public CarDirections getNextNSRoad() {
		return nextNSRoad;
	}

	@Override
	public CarDirections getNextEWRoad() {
		throw new IllegalArgumentException();
	}

	@Override
	public double getRoadEnd() {
		return roadEnd;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null)
			throw new IllegalArgumentException();
		cars.remove(car);
		if (frontPosition > getRoadEnd())
			getNextRoad(car).acceptCar(car, frontPosition - getRoadEnd());
		else {
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			cars.add(car);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getTimeStep(), car);
		}
	}

	@Override
	public double distanceUntilStop(Car car, double fromPosition) {
		double obstacle = Inventory.distanceToCarBack(car, fromPosition, cars,
				this);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadEnd() - fromPosition)
					+ getNextRoad(car).distanceUntilStop(car, 0);
		return obstacle;
	}

	@Override
	public CarDirections getNextRoad(Car c) {
		return nextNSRoad;
	}

	@Override
	public void setNextRoad(CarDirections r) {
		nextRoad = r;

	}

}
