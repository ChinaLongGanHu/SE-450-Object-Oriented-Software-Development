package myproject.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A light has a boolean state.
 */
public class Light implements Agent, CarDirections 
{
	
	private LightStateControl lightState;
	private List<Car> cars = new ArrayList<Car>();
	private CarDirections nextNSRoad;
	private CarDirections nextEWRoad;
	private CarDirections nextRoad;
	private double roadEnd;
	double greenLightTime;
	double yellowLightTime;
	
	Light(double roadEnd) 
	{
		if (Math.random() <= 0.5)
			lightState = new LightTurnsGreen();
		else
			lightState = new LightTurnsRed();

		if (roadEnd < 0.0)
			throw new IllegalArgumentException("Road can not be <= 0");
		else
			this.roadEnd = roadEnd;

		greenLightTime = MP.getGreenTime();
		yellowLightTime = MP.getYellowTime();

	}

	private boolean state;
	
	public boolean getState() {
		return state;
	}
	
	public Color getColor() {
		return lightState.getColor();
	}
	
	@Override
	public void setNextNSRoad(CarDirections road) {
		// TODO Auto-generated method stub
		nextNSRoad = road;
	}

	@Override
	public void setNextEWRoad(CarDirections road) {
		// TODO Auto-generated method stub
		nextEWRoad = road;
	}

	@Override
	public CarDirections getNextNSRoad() {
		// TODO Auto-generated method stub
		return nextNSRoad;
	}

	@Override
	public CarDirections getNextEWRoad() {
		// TODO Auto-generated method stub
		return nextEWRoad;
	}

	@Override
	public double getRoadEnd() {
		// TODO Auto-generated method stub
		return roadEnd;
	}

	@Override
	public void acceptCar(Car car, double frontPosition) {
		// TODO Auto-generated method stub
		if (car == null) {
			throw new IllegalArgumentException("Null Car");
		}
		cars.remove(car);
		if (frontPosition > getRoadEnd()) {
			getNextRoad(car).acceptCar(car, frontPosition - getRoadEnd());
		} else {
			car.setCurrentRoad(this);
			car.setFrontPosition(frontPosition);
			cars.add(car);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getTimeStep(), car);
		}
	}

	@Override
	public List<Car> getCars() {
		// TODO Auto-generated method stub
		return cars;
	}

	@Override
	public double distanceUntilStop(Car car, double fromPosition) {
		// TODO Auto-generated method stub
		double obstacleDist = lightState.obstacleDistance(car, fromPosition,
				this);

		double obstacle = Inventory.distanceToCarBack(car, fromPosition, cars,
				(CarDirections) this);
		obstacle = Math.min(obstacle, obstacleDist);
		if (obstacle == Double.POSITIVE_INFINITY)
			obstacle = (getRoadEnd() - fromPosition + getNextRoad(car)
					.distanceUntilStop(car, 0));

		return obstacle;
	}

	@Override
	public CarDirections getNextRoad(Car c) {
		// TODO Auto-generated method stub
		if (c.getNSCar())
			return getNextNSRoad();
		else
			return getNextEWRoad();
	}

	@Override
	public void setNextRoad(CarDirections r) {
		// TODO Auto-generated method stub
		nextRoad = r;
	}

	public void removeCar(Car d) 
	{
		if (d == null) 
		{
			throw new IllegalArgumentException("Null Car");
		}
		cars.remove(d);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
				lightState = lightState.next();
				CarServerEdge.getServer().enqueue(
						CarServerEdge.getServer().currentTime()
								+ lightState.time(greenLightTime, yellowLightTime),
						this);
	}
	
}

