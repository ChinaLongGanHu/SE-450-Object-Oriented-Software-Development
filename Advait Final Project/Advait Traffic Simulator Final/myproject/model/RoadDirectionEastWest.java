package myproject.model;

import java.util.ArrayList;
import java.util.List;

public final class RoadDirectionEastWest extends Road implements
		CarDirectionInterface {
	private List<Car> simCars = new ArrayList<Car>();
	private CarDirectionInterface nextEastWestRoad;
	private CarDirectionInterface nextRoad;
	private double roadClosed;

	RoadDirectionEastWest(double roadClosed) {
		if (roadClosed < 0.0) {
			throw new IllegalArgumentException();
		} else {
			this.roadClosed = roadClosed;
		}

	}

	@Override
	public List<Car> getCars() {
		return simCars;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		if (car == null)
			throw new IllegalArgumentException();
		simCars.remove(car);
		if (frontPosition > getRoadClosed())
			getNextRoad(car).acceptCar(car, frontPosition - getRoadClosed());
		else {
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			simCars.add(car);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getvarTimeStep(), car);
		}

	}

	@Override
	public CarDirectionInterface getNextRoad(Car c) {
		return nextEastWestRoad;
	}

	@Override
	public void setNextRoad(CarDirectionInterface r) {
		nextRoad = r;

	}

	@Override
	public void setNextNorthSouthRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

	@Override
	public void setNextEastWestRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		nextEastWestRoad = road;
		nextRoad = road;
	}

	@Override
	public CarDirectionInterface getNextNorthSouthRoad() {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException();
	}

	@Override
	public CarDirectionInterface getNextEastWestRoad() {
		// TODO Auto-generated method stub
		return nextEastWestRoad;
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
				simCars, (CarDirectionInterface) this);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadClosed() - fromPosition)
					+ getNextRoad(car).distanceToStop(car, 0);
		return obstacle;
	}
}
