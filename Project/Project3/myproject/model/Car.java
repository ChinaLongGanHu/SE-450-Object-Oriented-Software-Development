package myproject.model;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent {
	
	Car() { }
	
	private double length;
	private double maxVelocity;
	private double brakeDistance;
	private double stopDistance;
	private CarDirections currentRoad;
	private double frontPosition;
	private boolean NorthSouthCarDir;

	private java.awt.Color _color = new java.awt.Color((int) Math.ceil(Math
			.random() * 255), (int) Math.ceil(Math.random() * 255),
			(int) Math.ceil(Math.random() * 255));
	
	public Car(double length, double maxVelocity, double brakeDistance,
			double stopDistance) {
		if (length <= 0.0 || maxVelocity <= 0.0 || brakeDistance <= 0.0
				|| stopDistance <= 0.0) {
			throw new IllegalArgumentException();
		} 
		else 
		{
			this.length = length;
			this.maxVelocity = maxVelocity;
			this.brakeDistance = brakeDistance;
			this.stopDistance = stopDistance;
			currentRoad = null;
			frontPosition = 0;
			NorthSouthCarDir = true;
		}
	}
	
	public CarDirections getCurrentRoad() {
		return currentRoad;
	}

	public void setCurrentRoad(CarDirections newCurrentRoad) {
		currentRoad = newCurrentRoad;
	}

	public CarDirections getNextRoad() {
		if (NorthSouthCarDir) {
			return currentRoad.getNextNSRoad();
		} else
			return currentRoad.getNextEWRoad();
	}

	public double getPosition() 
	{
		return getFrontPosition() - length / 2;
	}

	public double getFrontPosition() {
		return frontPosition;
	}

	public boolean getNSCar() {
		return NorthSouthCarDir;
	}

	public void setNSCar(boolean NSDirection) {
		NorthSouthCarDir = NSDirection;
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
			carVelocity = carVelocity / 2;
		return carVelocity;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		double distanceToObstacle = currentRoad.distanceUntilStop(this,
				frontPosition) - getCurrentRoad().getRoadEnd();

		double carVelocity;
		if (distanceToObstacle > maxVelocity) 
		{
			carVelocity = maxVelocity;
		} 
		else if (distanceToObstacle <= stopDistance) 
		{
			carVelocity = 0;
		} 
		else 
		{
			carVelocity = calculateVelocity(distanceToObstacle);
		}
		currentRoad.acceptCar(this,
				frontPosition + carVelocity * MP.getTimeStep());
	}

}