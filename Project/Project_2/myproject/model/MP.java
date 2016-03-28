package myproject.model;

import java.util.Random;

/**
 * Static class for model parameters.
 */
public class MP {
	private MP() {
	}

	/** Length of cars, in meters */
	// public static double carLength = 10;
	/** Length of roads, in meters */
	// public static double roadLength = 200;
	/** Maximum car velocity, in meters/second */
	// public static double maxVelocity = 6;

	// test 1, 2, 3
	/*
	 * Ray defined fields for whole project.
	 */
	private static Random generator = new Random(System.currentTimeMillis());

	private static double getRandomNum(double min, double max) {
		return (generator.nextDouble() * (max - min)) + min;
	}

	/*
	 * Car information Car stop distance
	 */

	public static double stopDistance = 2;
	private static double carStopDistanceMin = .5;
	private static double carStopDistanceMax = 5;

	public static double getStopDistance() {
		return getRandomNum(carStopDistanceMin, carStopDistanceMax);
	}

	public static double getCarStopDistanceMin() {
		return carStopDistanceMin;
	}

	public static double getCarStopDistanceMax() {
		return carStopDistanceMax;
	}

	public static void setCarStopDistanceMin(double min) {
		carStopDistanceMin = min;
	}

	public static void setCarStopDistanceMax(double max) {
		carStopDistanceMax = max;
	}

	/*
	 * Car length:
	 */
	public static double carLength = 10;
	private static double carLengthMin = 5.0;
	private static double carLengthMax = 10.0;

	public static double getCarLength() {
		return getRandomNum(carLengthMin, carLengthMax);
	}

	public static double getCarLengthMin() {
		return carLengthMin;
	}

	public static double getCarLengthMax() {
		return carLengthMax;
	}

	public static void setCarLengthMin(double min) {
		carLengthMin = min;
	}

	public static void setCarLengthMax(double max) {
		carLengthMax = max;
	}

	/*
	 * car maxVelocity
	 */
	public static double maxVelocity = 40;
	private static double maxVelocityMin = 25;
	private static double maxVelocityMax = 60;

	public static double getMaxVelocity() {
		return getRandomNum(maxVelocityMin, maxVelocityMax);
	}

	public static double getMaxVelocityMin() {
		return maxVelocityMin;
	}

	public static double getMaxVelocityMax() {
		return maxVelocityMax;
	}

	public static void setMaxVelocityMin(double min) {
		maxVelocityMin = min;
	}

	public static void setMaxVelocityMax(double max) {
		maxVelocityMax = max;
	}

	/*
	 * car brake distance:
	 */
	public static double brakeDistance = 9;
	private static double brakeDistanceMin = 9;
	private static double brakeDistanceMax = 10;

	public static double getBrakeDistance() {
		return getRandomNum(brakeDistanceMin, brakeDistanceMax);
	}

	public static double getBrakeDistanceMin() {
		return brakeDistanceMin;
	}

	public static double getBrakeDistanceMax() {
		return brakeDistanceMax;
	}

	public static void setBrakeDistanceMin(double min) {
		brakeDistanceMin = min;
	}

	public static void setBrakeDistanceMax(double max) {
		brakeDistanceMax = max;
	}

	/*
	 * Lights: Greenlight time:
	 */
	private static double greenTimeMin = 30.0;
	private static double greenTimeMax = 60.0;

	public static double getGreenTime() {
		return getRandomNum(greenTimeMin, greenTimeMax);
	}

	public static double getGreenTimeMin() {
		return greenTimeMin;
	}

	public static double getGreenTimeMax() {
		return greenTimeMax;
	}

	public static void setGreenTimeMin(double min) {
		greenTimeMin = min;
	}

	public static void setGreenTimeMax(double max) {
		greenTimeMax = max;
	}

	/*
	 * yellow light time:
	 */
	public static double yellowTime = 5.0;
	private static double yellowTimeMin = 4.0;
	private static double yellowTimeMax = 5.0;

	public static double getYellowTime() {
		return getRandomNum(yellowTimeMin, yellowTimeMax);
	}

	public static double getYellowTimeMin() {
		return yellowTimeMin;
	}

	public static double getYellowTimeMax() {
		return yellowTimeMax;
	}

	public static void setYellowTimeMin(double min) {
		yellowTimeMin = min;
	}

	public static void setYellowTimeMax(double max) {
		yellowTimeMax = max;
	}

	/*
	 * traffic pattern type:(set as boolean type) alternating = true simple =
	 * false
	 */
	private static boolean traffic = true;

	public static void setTrafficPattern(boolean input) {
		if (input)
			traffic = true;
		else
			traffic = false;
	}

	public static boolean getTrafficPattern() {
		return traffic;
	}

	public static String trafficPatternToString() {
		if (traffic)
			return "alternating";
		else
			return "simple";
	}

	/*
	 * intersection length in meters
	 */
	public static double intersectionLength = 10;
	private static double intersectionLengthMin = 10;
	private static double intersectionLengthMax = 15;

	public static double getIntersectionLength() {
		return getRandomNum(intersectionLengthMin, intersectionLengthMax);
	}

	public static double getIntersectionLengthMin() {
		return intersectionLengthMin;
	}

	public static double getIntersectionLengthMax() {
		return intersectionLengthMax;
	}

	public static void setIntersectionLengthMin(double min) {
		intersectionLengthMin = min;
	}

	public static void setIntersectionLengthMax(double max) {
		intersectionLengthMax = max;
	}

	/*
	 * roadsegment length. in meters
	 */

	public static double roadLength = 210;
	private static double roadLengthMin = 220;
	private static double roadLengthMax = 250;

	public static double getRoadSegmentLength() {
		return getRandomNum(roadLengthMin, roadLengthMax);
	}

	public static double getRoadSegmentLengthMin() {
		return roadLengthMin;
	}

	public static double getRoadSegmentLengthMax() {
		return roadLengthMax;
	}

	public static void setRoadSegmentLengthMin(double min) {
		roadLengthMin = min;
	}

	public static void setRoadSegmentLengthMax(double max) {
		roadLengthMax = max;
	}

	/*
	 * Car entry rate: (second/car)
	 */
	public static double carEntryRate = 10;
	private static double carEntryRateMin = 2.0;
	private static double carEntryRateMax = 15.0;

	public static double getCarEntryRate() {
		return getRandomNum(carEntryRateMin, carEntryRateMax);
	}

	public static double getCarEntryRateMin() {
		return carEntryRateMin;
	}

	public static double getCarEntryRateMax() {
		return carEntryRateMax;
	}

	public static void setCarEntryRateMin(double min) {
		carEntryRateMin = min;
	}

	public static void setCarEntryRateMax(double max) {
		carEntryRateMax = max;
	}

	private static double carGenerationDelayMin = 2.0;
	private static double carGenerationDelayMax = 20.0;

	public static double getCarGenerationDelay() {
		return getRandomNum(carGenerationDelayMin, carGenerationDelayMax);
	}

	public static double getCarGenerationDelayMin() {
		return carGenerationDelayMin;
	}

	public static double getCarGenerationDelayMax() {
		return carGenerationDelayMax;
	}

	public static void setCarGenerationDelayMin(double min) {
		carGenerationDelayMin = min;
	}

	public static void setCarGenerationDelayMax(double max) {
		carGenerationDelayMax = max;
	}

	/*
	 * Grid size: standard 2x3
	 */
	public static int rows = 3;
	public static int columns = 5;

	public static void setGridRows(int row) {
		rows = row;
	}

	public static int getGridRows() {
		return rows;
	}

	public static void setGridColumns(int column) {
		columns = column;
	}

	public static int getGridColumns() {
		return columns;
	}

	/*
	 * default runtime 1500.0 seconds
	 */
	private static double runtime = 1500.0;

	public static double getRuntime() {
		return runtime;
	}

	public static void setRuntime(double runt) {
		runtime = runt;
	}

	private static double timeStep = .1;

	public static double getTimeStep() {
		return timeStep;
	}

	public static void setTimeStep(double time) {
		timeStep = time;
	}

	

}
