package myproject.model;

import java.util.List;

public interface CarFunctionalities 
{
	public void setNextNSRoad(CarFunctionalities road);

	public void setNextEWRoad(CarFunctionalities road);

	public CarFunctionalities getNextNSRoad();

	public CarFunctionalities getNextEWRoad();

	public double getRoadEnd();

	public void acceptCar(Car car, double frontPosition);

	public List<Car> getCars();

	public double distanceUntilStop(Car car, double fromPosition);

	public CarFunctionalities getNextRoad(Car c);

	public void setNextRoad(CarFunctionalities r);
}
