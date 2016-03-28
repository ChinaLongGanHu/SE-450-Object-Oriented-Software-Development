package myproject.model;

import java.awt.Color;

final class LightTurnsYellowNorthSouth implements LightStateControl {

	@Override
	public String getState() {
		return "RED";
	}

	@Override
	public Color getColor() {
		return Color.RED;
	}

	@Override
	public LightStateControl next() {
		return new LightTurnsGreen();
	}

	@Override
	public double time(double green, double yellow) {
		return yellow;
	}

	@Override
	public double obstacleDistance(Car car, double frontPosition, Light light) {
		double distance = Double.POSITIVE_INFINITY;
		if (!car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadEnd() - frontPosition;
		}
		if (car.getNSCar()) {
			if (car.getMaxVelocity() > car.getCurrentRoad().getRoadEnd()
					- frontPosition + light.getRoadEnd()) {
				distance = Double.POSITIVE_INFINITY;
			} else {
				distance = car.getCurrentRoad().getRoadEnd() - frontPosition;
			}
			if (car.getCurrentRoad() == light) {
				distance = Double.POSITIVE_INFINITY;
			}
		}
		return distance;
	}

}
