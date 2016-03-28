package myproject.model;

import java.util.ArrayList;
import java.util.List;

public final class RoadDirectionNorthSouth extends Road implements
		CarDirectionInterface {
	private List<Car> myCars = new ArrayList<Car>();
	private CarDirectionInterface nextNorthSouth;
	private CarDirectionInterface nextRoad;
	private double roadClosed;

	RoadDirectionNorthSouth(double roadEnd) {
		if (roadEnd < 0.0)
			throw new IllegalArgumentException();
		else
			roadClosed = roadEnd;
	}

	@Override
	public List<Car> getCars() {
		return myCars;
	}

	@Override
	public void setNextNorthSouthRoad(CarDirectionInterface road) {
		nextNorthSouth = road;
		nextRoad = road;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null)
			throw new IllegalArgumentException();
		myCars.remove(car);
		if (frontPosition > getRoadClosed())
			getNextRoad(car).acceptCar(car, frontPosition - getRoadClosed());
		else {
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			myCars.add(car);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getvarTimeStep(), car);
		}
	}

	@Override
	public CarDirectionInterface getNextRoad(Car c) {
		return nextNorthSouth;
	}

	@Override
	public void setNextRoad(CarDirectionInterface r) {
		nextRoad = r;

	}

	@Override
	public void setNextEastWestRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

	@Override
	public CarDirectionInterface getNextNorthSouthRoad() {
		// TODO Auto-generated method stub
		return nextNorthSouth;
	}

	@Override
	public CarDirectionInterface getNextEastWestRoad() {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

	@Override
	public double getRoadClosed() {
		// TODO Auto-generated method stub
		return roadClosed;
	}

	@Override
	public double distanceToStop(Car car, double fromPosition) {
		// TODO Auto-generated method stub
		double obstacle = Inventory.distanceToCarBack(car, fromPosition,
				myCars, this);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadClosed() - fromPosition)
					+ getNextRoad(car).distanceToStop(car, 0);
		return obstacle;
	}

}
