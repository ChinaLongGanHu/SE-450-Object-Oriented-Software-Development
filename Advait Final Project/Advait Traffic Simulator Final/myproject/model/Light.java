package myproject.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A light has a boolean state.
 */
public class Light implements Agent, CarDirectionInterface {

	private LightStateControlInterface lightState;
	private List<Car> cars = new ArrayList<Car>();
	private CarDirectionInterface nextNSRoad;
	private CarDirectionInterface nextEWRoad;
	private CarDirectionInterface nextRoad;
	private double roadEnd;
	double greenLightTime;
	double yellowLightTime;

	Light(double roadEnd) {
		if (Math.random() <= 0.5) {
			lightState = new LightTurnsGreen();
		} else {
			lightState = new LightTurnsRed();
		}

		if (roadEnd < 0.0) {
			throw new IllegalArgumentException("Road can not be <= 0");
		} else {
			this.roadEnd = roadEnd;
		}

		greenLightTime = MP.getvarGreenTrafficLight();
		yellowLightTime = MP.getvarYellowTrafficLight();
	}

	public String getState() {
		return lightState.getState();
	}

	public Color getColor() {
		return lightState.getColor();
	}

	public void run() {

		lightState = lightState.next();
		CarServerEdge.getServer().enqueue(
				CarServerEdge.getServer().currentTime()
						+ lightState.myTime(greenLightTime, yellowLightTime),
				this);

	}

	public void acceptCar(Car d, double frontPosition) {
		if (d == null) {
			throw new IllegalArgumentException("Null Car");
		}
		cars.remove(d);
		if (frontPosition > getRoadClosed()) {
			getNextRoad(d).acceptCar(d, frontPosition - getRoadClosed());
		} else {
			d.setCurrentRoad(this);
			d.setFrontPosition(frontPosition);
			cars.add(d);
			CarServerEdge.getServer().enqueue(
					CarServerEdge.getServer().currentTime()
							+ MP.getvarTimeStep(), d);
		}
	}

	public void removeCar(Car d) {
		if (d == null) {
			throw new IllegalArgumentException("Null Car");
		}
		cars.remove(d);
	}

	public List<Car> getCars() {
		return cars;
	}

	public CarDirectionInterface getNextRoad(Car c) {
		if (c.getNSCar())
			return getNextNorthSouthRoad();
		else
			return getNextEastWestRoad();
	}

	public void setNextRoad(CarDirectionInterface r) {
		nextRoad = r;
	}

	@Override
	public void setNextNorthSouthRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		nextNSRoad = road;
	}

	@Override
	public void setNextEastWestRoad(CarDirectionInterface road) {
		// TODO Auto-generated method stub
		nextEWRoad = road;
	}

	@Override
	public CarDirectionInterface getNextNorthSouthRoad() {
		// TODO Auto-generated method stub
		return nextNSRoad;
	}

	@Override
	public CarDirectionInterface getNextEastWestRoad() {
		// TODO Auto-generated method stub
		return nextEWRoad;
	}

	@Override
	public double getRoadClosed() {
		// TODO Auto-generated method stub
		return roadEnd;
	}

	@Override
	public double distanceToStop(Car car, double fromPosition) {
		// TODO Auto-generated method stub
		double obstacleDist = lightState.distanceToObstacle(car, fromPosition,
				this);

		double obstacle = Inventory.distanceToCarBack(car, fromPosition, cars,
				(CarDirectionInterface) this);
		obstacle = Math.min(obstacle, obstacleDist);
		if (obstacle == Double.POSITIVE_INFINITY) {
			obstacle = (getRoadClosed() - fromPosition + getNextRoad(car)
					.distanceToStop(car, 0));
		}

		return obstacle;
	}
}
