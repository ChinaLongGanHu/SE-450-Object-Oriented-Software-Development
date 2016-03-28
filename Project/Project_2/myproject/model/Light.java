package myproject.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import myproject.ui.UI;

/**
 * A light has a boolean state.
 */
public class Light implements Agent, CarFunctionalities
{
	private UI uiVar;
	private LightState lightState;
	private List<Car> cars = new ArrayList<Car>();
	private CarFunctionalities nextNSRoad;
	private CarFunctionalities nextEWRoad;
	private CarFunctionalities nextRoad;
	private double roadEndVar;
	double greenLightTime;
	double yellowLightTime;
	
	// Created only by this package
	Light(double roadEnd) 
	{
		
		
		if (Math.random() <= 0.5)
		{
			lightState = new LightGreen();
		}
		else
		{
			lightState = new LightRed();
		}
			
		if (roadEnd < 0.0)
		{
			throw new IllegalArgumentException("Road can not be <= 0");
		}
		else
		{
			roadEndVar = roadEnd;
		}

		greenLightTime = MP.getGreenTime();
		yellowLightTime = MP.getYellowTime();

	}

	private boolean state;

	public String getState() 
	{
		return lightState.getState();
	}
	
	public Color getColor() 
	{
		return lightState.getColor();
	}
	
	public void run(double time) 
	{
		
		if (time%40==0) 
		{
			state = !state;
		}
		
		lightState = lightState.next();
		TimeServerLinked.getServer().enqueue(TimeServerLinked.getServer().currentTime()
						+ lightState.time(greenLightTime, yellowLightTime),this);
	}

	@Override
	public void setNextNSRoad(CarFunctionalities road) 
	{
		// TODO Auto-generated method stub
		nextNSRoad = road;
	}

	@Override
	public void setNextEWRoad(CarFunctionalities road) 
	{
		// TODO Auto-generated method stub
		nextEWRoad = road;
	}

	@Override
	public CarFunctionalities getNextNSRoad() {
		// TODO Auto-generated method stub
		return nextNSRoad;
	}

	@Override
	public CarFunctionalities getNextEWRoad() {
		// TODO Auto-generated method stub
		return nextEWRoad;
	}

	@Override
	public double getRoadEnd() {
		// TODO Auto-generated method stub
		return roadEndVar;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) 
	{
		// TODO Auto-generated method stub
		if (car == null) 
		{
			//throw new IllegalArgumentException("Null Car will not be accepted");
			uiVar.displayError("Null Car will not be accepted");
		}
		
		cars.remove(car);
		
		if (frontPosition > getRoadEnd()) 
		{
			getNextRoad(car).acceptCar(car, frontPosition - getRoadEnd());
		} 
		else 
		{
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			cars.add(car);
			TimeServerLinked.getServer().enqueue(
					TimeServerLinked.getServer().currentTime()
							+ MP.getTimeStep(), car);
		}
		
	}

	public void removeCar(Car car) 
	{
		if (car == null) 
		{
			//throw new IllegalArgumentException("Null Car will not be accepted");
			uiVar.displayError("Null Car will not be accepted");
		}
		cars.remove(car);
	}
	
	@Override
	public List<Car> getCars() 
	{
		// TODO Auto-generated method stub
		return cars;
	}

	@Override
	public double distanceUntilStop(Car car, double frontPosition) 
	{
		// TODO Auto-generated method stub
		double obstacleDist = lightState.obstacleDistance(car, frontPosition,
				this);

		double obstacle = GoDownFactory.distanceToCarBack(car, frontPosition, cars,
				(CarFunctionalities) this);
		obstacle = Math.min(obstacle, obstacleDist);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadEnd() - frontPosition + getNextRoad(car)
					.distanceUntilStop(car, 0));

		return obstacle;
	}

	@Override
	public CarFunctionalities getNextRoad(Car c) 
	{
		// TODO Auto-generated method stub
		if (c.getNSCar())
			return getNextNSRoad();
		else
			return getNextEWRoad();
	}

	@Override
	public void setNextRoad(CarFunctionalities r) 
	{
		// TODO Auto-generated method stub
		nextRoad = r;
	}
}

