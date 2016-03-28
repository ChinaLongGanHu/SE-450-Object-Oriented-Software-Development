package myproject.model;

import java.util.List;

/**
 * A road holds cars.
 */
public abstract class RoadPortion implements CarFunctionalities {

	public abstract List<Car> getCars();

	@Override
	public abstract void setNextNSRoad(CarFunctionalities road);

	@Override
	public abstract void setNextEWRoad(CarFunctionalities road);

	@Override
	public abstract CarFunctionalities getNextNSRoad();

	@Override
	public abstract CarFunctionalities getNextEWRoad();

	@Override
	public abstract double getRoadEnd();

	@Override
	public abstract void acceptCar(Car car, double frontPosition);

	@Override
	public abstract double distanceUntilStop(Car car, double fromPosition);

	@Override
	public abstract CarFunctionalities getNextRoad(Car c);

	@Override
	public abstract void setNextRoad(CarFunctionalities r);
}
