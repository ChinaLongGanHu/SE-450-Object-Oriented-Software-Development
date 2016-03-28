package myproject.model;

import java.awt.Color;

interface LightStateControl {
	String getState();

	Color getColor();

	LightStateControl next();

	double time(double green, double yellow);

	double obstacleDistance(Car car, double frontPosition, Light light);
}
