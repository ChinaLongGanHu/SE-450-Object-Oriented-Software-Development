package myproject.model;

import java.util.List;

public final class Inventory {

	public static Car newCar() {
		return new Car(MP.getvarCarLength(), MP.getvarMaximumVelocity(),
				MP.getvarCarBreakDistance(), MP.getvarCarStopDistance());
	}

	public static CarDirectionInterface newEWRoad() {
		return new RoadDirectionEastWest(MP.varRoadLength);
	}

	public static CarDirectionInterface newNSRoad() {
		return new RoadDirectionNorthSouth(MP.varRoadLength);
	}

	public static CarDirectionInterface newSink() {
		return new CarFailure(1);
	}

	public static CarDirectionInterface newSource() {
		return new CarShowCase(0);
	}

	public static CarDirectionInterface newLight() {
		return new Light(MP.getvarIntersectionLength());
	}

	static double distanceToCarBack(Car car, double fromPosition,
			List<Car> cars, CarDirectionInterface road) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		if (car.getCurrentRoad() == road) {
			for (Car c : cars)
				if (c != car && c.getBackPosition() >= fromPosition
						&& c.getBackPosition() < carBackPosition) {
					carBackPosition = c.getBackPosition() - 1;
				}
		} else {
			for (Car c1 : cars) {
				if (c1.getBackPosition() <= 1) {
					carBackPosition = c1.getBackPosition();
				} else if (c1.getBackPosition() >= fromPosition
						&& c1.getBackPosition() < carBackPosition) {
					carBackPosition = c1.getBackPosition() - 1;
				}
			}
		}
		return carBackPosition - fromPosition
				+ car.getCurrentRoad().getRoadClosed();
	}
}
