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
	private CarAcceptor currentRoad;
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
	
	public double getPosition() {
		return position;
	}
	public java.awt.Color getColor() {
		return color;
	}
	public void run(double time) 
	{
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
	}
}
