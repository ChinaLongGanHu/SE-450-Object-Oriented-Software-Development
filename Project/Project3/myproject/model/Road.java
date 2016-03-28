package myproject.model;

import java.util.List;

/**
 * A road holds cars.
 */
public abstract class Road implements CarDirections
{
	Road() { } // Created only by this package

	public abstract List<Car> getCars();

	@Override
	public abstract void setNextNSRoad(CarDirections road);

	@Override
	public abstract void setNextEWRoad(CarDirections road);

	@Override
	public abstract CarDirections getNextNSRoad();

	@Override
	public abstract CarDirections getNextEWRoad();

	@Override
	public abstract double getRoadEnd();

	@Override
	public abstract void acceptCar(Car car, double frontPosition);

	@Override
	public abstract double distanceUntilStop(Car car, double fromPosition);

	@Override
	public abstract CarDirections getNextRoad(Car c);

	@Override
	public abstract void setNextRoad(CarDirections r);
}
