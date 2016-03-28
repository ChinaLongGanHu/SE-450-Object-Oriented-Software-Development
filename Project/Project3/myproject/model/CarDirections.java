package myproject.model;

import java.util.List;

public interface CarDirections {
	public void setNextNSRoad(CarDirections road);

	public void setNextEWRoad(CarDirections road);

	public CarDirections getNextNSRoad();

	public CarDirections getNextEWRoad();

	public double getRoadEnd();

	public void acceptCar(Car car, double frontPosition);

	public List<Car> getCars();

	public double distanceUntilStop(Car car, double fromPosition);

	public CarDirections getNextRoad(Car c);

	public void setNextRoad(CarDirections r);
}
