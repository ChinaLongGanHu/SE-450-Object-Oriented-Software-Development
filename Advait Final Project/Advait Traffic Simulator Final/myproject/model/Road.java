package myproject.model;

import java.util.List;

/**
 * A road holds cars.
 */
public abstract class Road implements CarDirectionInterface {

	public abstract List<Car> getCars();

	@Override
	public abstract void setNextNorthSouthRoad(CarDirectionInterface road);

	@Override
	public abstract void setNextEastWestRoad(CarDirectionInterface road);

	@Override
	public abstract CarDirectionInterface getNextNorthSouthRoad();

	@Override
	public abstract CarDirectionInterface getNextEastWestRoad();

	@Override
	public abstract double getRoadClosed();

	@Override
	public abstract void acceptCar(Car car, double frontPosition);

	@Override
	public abstract double distanceToStop(Car car, double fromPosition);

	@Override
	public abstract CarDirectionInterface getNextRoad(Car c);

	@Override
	public abstract void setNextRoad(CarDirectionInterface r);
}
