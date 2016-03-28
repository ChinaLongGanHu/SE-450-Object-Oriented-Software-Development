package myproject.model;

import java.awt.Color;

final class LightTurnsRed implements LightStateControlInterface {

	@Override
	public String getState() {
		return "RED";
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public LightStateControlInterface next() {
		return new LightTurnsYellowNorthSouth();
	}

	@Override
	public double myTime(double greenLight, double yellowLight) {
		// TODO Auto-generated method stub
		return (greenLight - yellowLight);
	}

	@Override
	public double distanceToObstacle(Car car, double frontPosition, Light light) {
		// TODO Auto-generated method stub
		double distance = Double.POSITIVE_INFINITY;

		if (!car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadClosed() - frontPosition;
		}

		return distance;
	}

}
