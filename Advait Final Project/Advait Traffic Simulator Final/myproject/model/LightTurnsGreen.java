package myproject.model;

import java.awt.Color;

final class LightTurnsGreen implements LightStateControlInterface {

	@Override
	public String getState() {
		return "GREEN";
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public LightStateControlInterface next() {
		return new LightTurnsYellow();
	}

	@Override
	public double distanceToObstacle(Car car, double frontPosition, Light light) {
		// TODO Auto-generated method stub
		double distance = Double.POSITIVE_INFINITY;
		if (car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadClosed() - frontPosition;
		}
		return distance;
	}

	@Override
	public double myTime(double greenLight, double yellowLight) {
		// TODO Auto-generated method stub
		return greenLight;
	}

}
