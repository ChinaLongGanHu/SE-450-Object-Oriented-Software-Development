package myproject.model;

import java.awt.Color;

final class LightTurnsYellow implements LightStateControlInterface {

	@Override
	public String getState() {
		return "YELLOW";
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public LightStateControlInterface next() {
		return new LightTurnsRed();
	}

	@Override
	public double myTime(double greenLight, double yellowLight) {
		// TODO Auto-generated method stub
		return yellowLight;
	}

	@Override
	public double distanceToObstacle(Car car, double frontPosition, Light light) {
		// TODO Auto-generated method stub
		double distance = Double.POSITIVE_INFINITY;
		if (car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadClosed() - frontPosition;
		}
		if (!car.getNSCar()) {
			if (car.getMaxVelocity() > car.getCurrentRoad().getRoadClosed()
					- frontPosition + light.getRoadClosed()) {
				distance = Double.POSITIVE_INFINITY;
			} else {
				distance = car.getCurrentRoad().getRoadClosed() - frontPosition;
			}
		}
		return distance;
	}

}
