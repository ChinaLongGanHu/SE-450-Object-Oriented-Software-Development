package myproject.model;

/**
 * A car remembers its position from the beginning of its road. Cars have random
 * velocity and random movement pattern: when reaching the end of a road, the
 * dot either resets its position to the beginning of the road, or reverses its
 * direction.
 */
public class Car implements Agent {

	private double length;
	private double maxVelocity;
	private double brakeDistance;
	private double stopDistance;
	private CarDirectionInterface currentRoad;
	private double frontPosition;
	private boolean NSDirectionCar;

	private java.awt.Color _color = new java.awt.Color((int) Math.ceil(Math
			.random() * 255), (int) Math.ceil(Math.random() * 255),
			(int) Math.ceil(Math.random() * 255));

	public Car(double length, double maxVelocity, double brakeDistance,
			double stopDistance) {
		if (length <= 0.0 || maxVelocity <= 0.0 || brakeDistance <= 0.0
				|| stopDistance <= 0.0) {
			throw new IllegalArgumentException();
		} else {
			this.length = length;
			this.maxVelocity = maxVelocity;
			this.brakeDistance = brakeDistance;
			this.stopDistance = stopDistance;
			this.currentRoad = null;
			this.frontPosition = 0;
			this.NSDirectionCar = true;
		}
	}

	public CarDirectionInterface getCurrentRoad() {
		return currentRoad;
	}

	public void setCurrentRoad(CarDirectionInterface newCurrentRoad) {
		currentRoad = newCurrentRoad;
	}

	public CarDirectionInterface getNextRoad() {
		if (NSDirectionCar) {
			return currentRoad.getNextNorthSouthRoad();
		} else {
			return currentRoad.getNextEastWestRoad();
		}
	}

	public double getPosition() {
		return getFrontPosition() - length / 2;
	}

	public double getFrontPosition() {
		return frontPosition;
	}

	public boolean getNSCar() {
		return NSDirectionCar;
	}

	public void setNSCar(boolean NSDirection) {
		NSDirectionCar = NSDirection;
	}

	public void setFrontPosition(double newFrontPosition) {
		frontPosition = newFrontPosition;
	}

	double getBackPosition() {
		return frontPosition - getLength();
	}

	public double getLength() {
		return length;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}

	public double getBrakeDistance() {
		return brakeDistance;
	}

	public double getStopDistance() {
		return stopDistance;
	}

	public java.awt.Color getColor() {
		return _color;
	}

	private double calculateVelocity(double distanceToObsticle) {
		double carVelocity;
		carVelocity = (maxVelocity / (brakeDistance - stopDistance))
				* (distanceToObsticle - stopDistance);
		carVelocity = Math.max(0.0, carVelocity);
		carVelocity = Math.min(maxVelocity, carVelocity);
		while (carVelocity > distanceToObsticle)
			carVelocity = carVelocity / 2; // carVelocity = 0;
		return carVelocity;
	}

	public void run() {

		// Now Finding closest obstacle
		double distanceToObstacle = currentRoad.distanceToStop(this,
				frontPosition) - getCurrentRoad().getRoadClosed();

		// Now Calculating New Velocity
		double carVelocity;

		if (distanceToObstacle > maxVelocity) {
			carVelocity = maxVelocity;
		} else if (distanceToObstacle <= stopDistance) {
			carVelocity = 0;
		} else {
			carVelocity = calculateVelocity(distanceToObstacle);
		}
		currentRoad.acceptCar(this,
				frontPosition + carVelocity * MP.getvarTimeStep());
	}

}
