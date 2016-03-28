package myproject.model;

import java.util.ArrayList;
import java.util.List;

public class CarShowCase implements CarDirections, Agent {

	private List<Car> _cars = new ArrayList<Car>();
	private CarDirections nextEWRoad;
	private CarDirections nextNSRoad;
	private CarDirections nextRoad;
	private double roadEnd;
	private double generationRate;

	CarShowCase(double roadEnd) 
	{
		if (roadEnd < 0.0)
		{
			throw new IllegalArgumentException("");
		}
		else
		{
			this.roadEnd = roadEnd;
		}
			
		generationRate = MP.getCarGenerationDelay();
	}

	public boolean getNSCarDirection() {
		if (this.nextEWRoad == null)
			return true;
		else
			return false;
	}

	@Override
	public void setNextNSRoad(CarDirections road) {
		nextNSRoad = road;
		nextRoad = road;

	}

	@Override
	public void setNextEWRoad(CarDirections road) {
		nextEWRoad = road;
		nextRoad = road;

	}

	@Override
	public CarDirections getNextNSRoad() {
		return nextNSRoad;
	}

	@Override
	public CarDirections getNextEWRoad() {
		return nextEWRoad;
	}

	@Override
	public double getRoadEnd() {
		return roadEnd;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		_cars.remove(car);
		nextRoad.acceptCar(car, frontPosition);

	}

	@Override
	public List<Car> getCars() {
		return _cars;
	}

	@Override
	public double distanceUntilStop(Car car, double fromPosition) {
		throw new IllegalArgumentException("Sink distance called!");
	}

	@Override
	public CarDirections getNextRoad(Car c) {
		if (c.getNSCar())
			return getNextNSRoad();
		else
			return getNextEWRoad();
	}

	@Override
	public void setNextRoad(CarDirections r) {
		nextRoad = r;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
				Car car = Inventory.newCar();
				car.setCurrentRoad(nextRoad);
				car.setNSCar(getNSCarDirection());
				if (nextRoad.distanceUntilStop(car, 1) >= 1) {
					nextRoad.acceptCar(car, 1);
				}
				CarServerEdge.getServer().enqueue(
						CarServerEdge.getServer().currentTime() + generationRate,
						this);
				generationRate = MP.getCarEntryRate();
	}

}
