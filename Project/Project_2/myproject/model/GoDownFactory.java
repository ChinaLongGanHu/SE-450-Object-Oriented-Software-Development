package myproject.model;

import java.util.List;

public final class GoDownFactory {

	public static Car newCar() {
		return new Car(MP.getCarLength(), MP.getMaxVelocity(),
				MP.getBrakeDistance(), MP.getStopDistance());
	}

	public static CarFunctionalities newLight() {
		return new Light(MP.getIntersectionLength());
	}

	static double distanceToCarBack(Car car, double fromPosition,
			List<Car> cars, CarFunctionalities road) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		if (car.getCurrentRoad() == road) {
			for (Car c : cars)
				if (c != car && c.getBackPosition() >= fromPosition
						&& c.getBackPosition() < carBackPosition)
					carBackPosition = c.getBackPosition() - 1;
		} else {
			for (Car c1 : cars) {
				if (c1.getBackPosition() <= 1)
					carBackPosition = c1.getBackPosition();
				else if (c1.getBackPosition() >= fromPosition
						&& c1.getBackPosition() < carBackPosition)
					carBackPosition = c1.getBackPosition() - 1;
			}
		}
		return carBackPosition - fromPosition
				+ car.getCurrentRoad().getRoadEnd();
	}
}
