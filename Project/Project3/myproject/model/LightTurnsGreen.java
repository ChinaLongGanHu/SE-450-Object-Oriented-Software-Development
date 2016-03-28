package myproject.model;

import java.awt.Color;

final class LightTurnsGreen implements LightStateControl {

	@Override
	public String getState() {
		return "GREEN";
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public LightStateControl next() {
		return new LightTurnsYellow();
	}

	@Override
	public double time(double green, double yellow) {
		return green;
	}

	@Override
	public double obstacleDistance(Car car, double frontPosition, Light light) {
		double distance = Double.POSITIVE_INFINITY;
		if (car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadEnd() - frontPosition;
		}
		return distance;
	}

}
