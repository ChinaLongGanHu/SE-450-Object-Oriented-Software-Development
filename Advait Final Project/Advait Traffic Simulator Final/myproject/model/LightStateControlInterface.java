package myproject.model;

import java.awt.Color;

interface LightStateControlInterface {
	String getState();

	Color getColor();

	LightStateControlInterface next();

	double myTime(double greenLight, double yellowLight);

	double distanceToObstacle(Car car, double frontPosition, Light light);
}
