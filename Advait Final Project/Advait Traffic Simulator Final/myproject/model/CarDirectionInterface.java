package myproject.model;

import java.util.List;

public interface CarDirectionInterface {
	public void setNextNorthSouthRoad(CarDirectionInterface road);

	public void setNextEastWestRoad(CarDirectionInterface road);

	public CarDirectionInterface getNextNorthSouthRoad();

	public CarDirectionInterface getNextEastWestRoad();

	public double getRoadClosed();

	public void acceptCar(Car car, double frontPosition);

	public List<Car> getCars();

	public double distanceToStop(Car car, double fromPosition);

	public CarDirectionInterface getNextRoad(Car c);

	public void setNextRoad(CarDirectionInterface r);
}
