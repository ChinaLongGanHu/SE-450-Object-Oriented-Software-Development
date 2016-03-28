package myproject.model;

import myproject.ui.UI;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent 
{
	Car() { } // Created only by this package
	
	private UI ui;
	
	private double length;
	private double maxVelocity;
	private double brakeDistance;
	private double stopDistance;
	private CarFunctionalities currentRoad;
	private double frontPosition;
	private boolean NSCar;
	
	private boolean backAndForth = Math.round(Math.random())==1 ? true : false;
	private double position = 0;
	private double velocity = (int) Math.ceil(Math.random() * MP.maxVelocity);
	private java.awt.Color color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));

	public Car(double length, double maxVelocity, double brakeDistance,
			double stopDistance) 
	{
		if (length <= 0.0 || maxVelocity <= 0.0 || brakeDistance <= 0.0
				|| stopDistance <= 0.0) 
		{
			//throw new IllegalArgumentException();
			ui.displayError("Oops something went wrong !!");
		} 
		else 
		{
			this.length = length;
			this.maxVelocity = maxVelocity;
			this.brakeDistance = brakeDistance;
			this.stopDistance = stopDistance;
			this.currentRoad = null;
			this.frontPosition = 0;
			this.NSCar = true;
		}
	}
	
	public CarFunctionalities getCurrentRoad() 
	{
		return currentRoad;
	}

	public void setCurrentRoad(CarFunctionalities newCurrentRoad) 
	{
		currentRoad = newCurrentRoad;
	}

	public CarFunctionalities getNextRoad() 
	{
		if (NSCar) 
		{
			return currentRoad.getNextNSRoad();
		} 
		else
		{
			return currentRoad.getNextEWRoad();
		}
	}
	
	public double getPosition() 
	{
		return getFrontPosition() - length / 2;
	}
	
	public double getFrontPosition() 
	{
		return frontPosition;
	}
	
	public boolean getNSCar() {
		return NSCar;
	}

	public void setNSCar(boolean NSDirection) {
		NSCar = NSDirection;
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
		return color;
	}
	
	private double calculateVelocity(double distanceToObsticle) 
	{
		double carVelocity;
		carVelocity = (maxVelocity / (brakeDistance - stopDistance))
				* (distanceToObsticle - stopDistance);
		carVelocity = Math.max(0.0, carVelocity);
		carVelocity = Math.min(maxVelocity, carVelocity);
		
		while (carVelocity > distanceToObsticle)
		{
			carVelocity = carVelocity / 2; 
			// Where carVelocity is 0-ZERO at this time
		}
		return carVelocity;
	}
	
	public void run(double time) 
	{
		
		// This will find the closest obstacle on the road at front side 
		//double distanceToObstacle = currentRoad.distanceUntilStop(this,frontPosition) - getCurrentRoad().getRoadEnd();
		
		// Sorry Sir, But I hve to comment this code to make some efficient changes.
		if (backAndForth) 
		{
			if (((position + velocity) < 0) || ((position + velocity) > (MP.roadLength-MP.carLength)))
				velocity *= -1;
		} 
		else 
		{
			if ((position + velocity) > (MP.roadLength-MP.carLength))
				position = 0;
		}
		position += velocity;
		
		/*
		double distanceToObstacle = currentRoad.distanceUntilStop(this,frontPosition) - getCurrentRoad().getRoadEnd();
		// Calculate New Velocity
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
		currentRoad.acceptCar(this,frontPosition + carVelocity * MP.getTimeStep());
		*/
	}
}
