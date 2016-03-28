package myproject.model;

import java.util.ArrayList;
import java.util.List;

public class CarShowCase implements CarDirectionInterface, Agent {

	private List<Car> cars = new ArrayList<Car>();
	private CarDirectionInterface nextEastWestRoad;
	private CarDirectionInterface nextNorthSouthRoad;
	private CarDirectionInterface nextRoad;
	private double roadEnd;
	private double generationRate;

	CarShowCase(double roadEnd) {
		if (roadEnd < 0.0) {
			throw new IllegalArgumentException("Oppps !! Something went wrong");
		} else {
			this.roadEnd = roadEnd;
		}
		generationRate = MP.getCarGenerationDelay();
	}

	@Override
	public void run() {
		Car car = Inventory.newCar();
		car.setCurrentRoad(nextRoad);
		car.setNSCar(getNSCarDirection());
		if (nextRoad.distanceToStop(car, 1) >= 1) {
			nextRoad.acceptCar(car, 1);
		}
		CarServerEdge.getServer().enqueue(
				CarServerEdge.getServer().currentTime() + generationRate, this);
		generationRate = MP.getvarEntryRate();
	}

	public boolean getNSCarDirection() {
		if (this.nextEastWestRoad == null)
			return true;
		else
			return false;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		cars.remove(car);
		nextRoad.acceptCar(car, frontPosition);

	}

	@Override
	public List<Car> getCars() {
		return cars;
	}

	@Override
	public CarDirectionInterface getNextRoad(Car c) {
		if (c.getNSCar()) {
			return getNextNorthSouthRoad();
		} else {
			return getNextEastWestRoad();
		}
	}

	@Override
	public void setNextRoad(CarDirectionInterface r) {
		nextRoad = r;
	}

	@Override
	public void setNextNorthSouthRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		nextNorthSouthRoad = road;
		nextRoad = road;
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
		return nextNorthSouthRoad;
	}

	@Override
	public CarDirectionInterface getNextEastWestRoad() {
		// TODO Auto-generated method stub
		return nextEastWestRoad;
	}

	@Override
	public double getRoadClosed() {
		// TODO Auto-generated method stub
		return roadEnd;
	}

	@Override
	public double distanceToStop(Car car, double fromPosition) {
		// TODO Auto-generated method stub
		throw new IllegalArgumentException(
				"Oppps !! Something really went wrong");
	}

}
