package myproject.model;

import java.util.Random;

/**
 * Static class for model parameters.
 */

public class MP {

	static {
		reset();
	}

	private static Random randGenerator = new Random(System.currentTimeMillis());

	private static double getRandomDigit(double min, double max) {
		return (randGenerator.nextDouble() * (max - min)) + min;
	}

	// 02 Simulation time step
	private static double varTimeStep = 0.1;

	public static double getvarTimeStep() {
		return varTimeStep;
	}

	public static void setvarTimeStep(double time) {
		varTimeStep = time;
	}

	// 03 Simulation run time
	private static double varRuntime = 1000.0;

	public static double getvarRuntime() {
		return varRuntime;
	}

	public static void setvarRuntime(double runt) {
		varRuntime = runt;
	}

	// 04 Grid size
	public static int varGridRows = 2;
	public static int varGridColumns = 3;

	public static void setGridvarGridRows(int row) {
		varGridRows = row;
	}

	public static int getGridvarGridRows() {
		return varGridRows;
	}

	public static void setGridvarGridColumns(int column) {
		varGridColumns = column;
	}

	public static int getGridvarGridColumns() {
		return varGridColumns;
	}

	// 05 TrafficPattern pattern
/*	
	private static boolean varTrafficPattern = true;

	public static void setvarTrafficPatternPattern(boolean input) {
		if (input)
			varTrafficPattern = true;
		else
			varTrafficPattern = false;
	}

	public static boolean getvarTrafficPatternPattern() {
		return varTrafficPattern;
	}

	public static String varTrafficPatternPatternToString() {
		if (varTrafficPattern)
			return "alternating";
		else
			return "simple";
	}
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
	// 06 Car entry rate
	public static double varEntryRate = 10;
	private static double varEntryRateMin = 2.0;
	private static double varEntryRateMax = 25.0;

	public static double getvarEntryRate() {
		return getRandomDigit(varEntryRateMin, varEntryRateMax);
	}

	public static double getvarEntryRateMin() {
		return varEntryRateMin;
	}

	public static double getvarEntryRateMax() {
		return varEntryRateMax;
	}

	public static void setvarEntryRateMin(double min) {
		varEntryRateMin = min;
	}

	public static void setvarEntryRateMax(double max) {
		varEntryRateMax = max;
	}

	// 07 Road segment length
	public static double varRoadLength = 210;
	private static double varRoadLengthMin = 200;
	private static double varRoadLengthMax = 500;

	public static double getRoadSegmentLength() {
		return getRandomDigit(varRoadLengthMin, varRoadLengthMax);
	}

	public static double getRoadSegmentLengthMin() {
		return varRoadLengthMin;
	}

	public static double getRoadSegmentLengthMax() {
		return varRoadLengthMax;
	}

	public static void setRoadSegmentLengthMin(double min) {
		varRoadLengthMin = min;
	}

	public static void setRoadSegmentLengthMax(double max) {
		varRoadLengthMax = max;
	}

	// 08 Intersection length
	public static double varIntersectionLength = 10;
	private static double varIntersectionLengthMin = 10;
	private static double varIntersectionLengthMax = 15;

	public static double getvarIntersectionLength() {
		return getRandomDigit(varIntersectionLengthMin,
				varIntersectionLengthMax);
	}

	public static double getvarIntersectionLengthMin() {
		return varIntersectionLengthMin;
	}

	public static double getvarIntersectionLengthMax() {
		return varIntersectionLengthMax;
	}

	public static void setvarIntersectionLengthMin(double min) {
		varIntersectionLengthMin = min;
	}

	public static void setvarIntersectionLengthMax(double max) {
		varIntersectionLengthMax = max;
	}

	// 09 Car length
	public static double varCarLength = 10;
	private static double varCarLengthMin = 5.0;
	private static double varCarLengthMax = 10.0;

	public static double getvarCarLength() {
		return getRandomDigit(varCarLengthMin, varCarLengthMax);
	}

	public static double getvarCarLengthMin() {
		return varCarLengthMin;
	}

	public static double getvarCarLengthMax() {
		return varCarLengthMax;
	}

	public static void setvarCarLengthMin(double min) {
		varCarLengthMin = min;
	}

	public static void setvarCarLengthMax(double max) {
		varCarLengthMax = max;
	}

	// 10 Car maximum velocity
	public static double varMaximumVelocity = 40;
	private static double varMaximumVelocityMin = 10;
	private static double varMaximumVelocityMax = 30;

	public static double getvarMaximumVelocity() {
		return getRandomDigit(varMaximumVelocityMin, varMaximumVelocityMax);
	}

	public static double getvarMaximumVelocityMin() {
		return varMaximumVelocityMin;
	}

	public static double getvarMaximumVelocityMax() {
		return varMaximumVelocityMax;
	}

	public static void setvarMaximumVelocityMin(double min) {
		varMaximumVelocityMin = min;
	}

	public static void setvarMaximumVelocityMax(double max) {
		varMaximumVelocityMax = max;
	}

	// 11 Car stop distance
	public static double varCarStopDistance = 2;
	private static double carvarCarStopDistanceMin = 0.5;
	private static double carvarCarStopDistanceMax = 5;

	public static double getvarCarStopDistance() {
		return getRandomDigit(carvarCarStopDistanceMin,
				carvarCarStopDistanceMax);
	}

	public static double getCarvarCarStopDistanceMin() {
		return carvarCarStopDistanceMin;
	}

	public static double getCarvarCarStopDistanceMax() {
		return carvarCarStopDistanceMax;
	}

	public static void setCarvarCarStopDistanceMin(double min) {
		carvarCarStopDistanceMin = min;
	}

	public static void setCarvarCarStopDistanceMax(double max) {
		carvarCarStopDistanceMax = max;
	}

	// 12 Car brake distance
	public static double varCarBreakDistance = 9;
	private static double varCarBreakDistanceMin = 9;
	private static double varCarBreakDistanceMax = 10;

	public static double getvarCarBreakDistance() {
		return getRandomDigit(varCarBreakDistanceMin, varCarBreakDistanceMax);
	}

	public static double getvarCarBreakDistanceMin() {
		return varCarBreakDistanceMin;
	}

	public static double getvarCarBreakDistanceMax() {
		return varCarBreakDistanceMax;
	}

	public static void setvarCarBreakDistanceMin(double min) {
		varCarBreakDistanceMin = min;
	}

	public static void setvarCarBreakDistanceMax(double max) {
		varCarBreakDistanceMax = max;
	}

	// 13 varTrafficPattern light green time
	private static double varGreenTrafficLightMin = 30.0;
	private static double varGreenTrafficLightMax = 180.0;

	public static double getvarGreenTrafficLight() {
		return getRandomDigit(varGreenTrafficLightMin, varGreenTrafficLightMax);
	}

	public static double getvarGreenTrafficLightMin() {
		return varGreenTrafficLightMin;
	}

	public static double getvarGreenTrafficLightMax() {
		return varGreenTrafficLightMax;
	}

	public static void setvarGreenTrafficLightMin(double min) {
		varGreenTrafficLightMin = min;
	}

	public static void setvarGreenTrafficLightMax(double max) {
		varGreenTrafficLightMax = max;
	}

	// 14 varTrafficPattern light yellow time
	public static double varYellowTrafficLight = 5.0;
	private static double yellowMin = 4.0;
	private static double yellowMax = 5.0;

	public static double getvarYellowTrafficLight() {
		return getRandomDigit(yellowMin, yellowMax);
	}

	public static double getyellowMin() {
		return yellowMin;
	}

	public static double getyellowMax() {
		return yellowMax;
	}

	public static void setyellowMin(double min) {
		yellowMin = min;
	}

	public static void setyellowMax(double max) {
		yellowMax = max;
	}

	// Generation Delay
	private static double genDelayMin = 2.0;
	private static double genDelayMax = 20.0;

	public static double getCarGenerationDelay() {
		return getRandomDigit(genDelayMin, genDelayMax);
	}

	public static double getgenDelayMin() {
		return genDelayMin;
	}

	public static double getgenDelayMax() {
		return genDelayMax;
	}

	public static void setgenDelayMin(double min) {
		genDelayMin = min;
	}

	public static void setgenDelayMax(double max) {
		genDelayMax = max;
	}

	public static String displayValues() {
		StringBuilder SB = new StringBuilder();
		SB.append("Simulation time step (seconds)\t\t[")
				.append(getvarTimeStep()).append("]\n");
		SB.append("Simulation run time (seconds)\t\t[").append(getvarRuntime())
				.append("]\n");
		SB.append("Grid size (number of roads)\t\t[Rows=")
				.append(getGridvarGridRows()).append(",Columns=")
				.append(getGridvarGridColumns()).append("]\n");
		//SB.append("TrafficPattern Pattern\t\t\t\t[")
				//.append(varTrafficPatternPatternToString()).append("]\n");
		SB.append("Car entry rate (seconds/car)\t\t[min=")
				.append(getvarEntryRateMin()).append(",max=")
				.append(getvarEntryRateMax()).append("]\n");
		SB.append("Road segment length (meters)\t\t[min=")
				.append(getRoadSegmentLengthMin()).append(",max=")
				.append(getRoadSegmentLengthMax()).append("]\n");
		SB.append("Intersection length (meters)\t\t[min=")
				.append(getvarIntersectionLengthMin()).append(",max=")
				.append(getvarIntersectionLengthMax()).append("]\n");
		SB.append("Car length (meters)\t\t\t[min=")
				.append(getvarCarLengthMin()).append(",max=")
				.append(getvarCarLengthMax()).append("]\n");
		SB.append("Car maximum velocity (meters/second)\t[min=")
				.append(getvarMaximumVelocityMin()).append(",max=")
				.append(getvarMaximumVelocityMax()).append("]\n");
		SB.append("Car stop distance (meters)\t\t[min=")
				.append(getCarvarCarStopDistanceMin()).append(",max=")
				.append(getCarvarCarStopDistanceMax()).append("]\n");
		SB.append("Car brake distance (meters)\t\t[min=")
				.append(getvarCarBreakDistanceMin()).append(",max=")
				.append(getvarCarBreakDistanceMax()).append("]\n");
		SB.append("Traffic light green time (seconds)\t[min=")
				.append(getvarGreenTrafficLightMin()).append(",max=")
				.append(getvarGreenTrafficLightMax()).append("]\n");
		SB.append("Traffic light yellow time (seconds)\t[min=")
				.append(getyellowMin()).append(",max=").append(getyellowMax())
				.append("]\n");

		return SB.toString();
	}

	public static String aboutAuthor() {
		StringBuilder SB = new StringBuilder();
		SB.append("Developer Name :- Advait Patel").append("\n");
		SB.append("Student ID     :- 152918").append("\n");
		SB.append("Major          :- Computer Science").append("\n");
		SB.append(
				"Course         :- SE-450 Object Oriented Software Development")
				.append("\n");
		SB.append("Project Title  :- Traffic Simulator").append("\n");
		SB.append("Language Used  :- Java").append("\n");
		SB.append("Project Guide  :- Prof. James Reily").append("\n");

		return SB.toString();
	}

	public static void reset() {
		varTimeStep = 0.1;
		varGridRows = 02;
		varGridColumns = 03;
		//varTrafficPattern = true;
		varEntryRateMin = 02;
		varEntryRateMax = 25;
		varRoadLengthMin = 200;
		varRoadLengthMax = 500;
		varIntersectionLengthMin = 10;
		varIntersectionLengthMax = 15;
		varCarLengthMin = 05;
		varCarLengthMax = 10;
		varMaximumVelocityMin = 10;
		varMaximumVelocityMax = 30;
		carvarCarStopDistanceMin = 0.5;
		carvarCarStopDistanceMax = 05;
		varCarBreakDistanceMin = 9.0;
		varCarBreakDistanceMax = 10;
		varGreenTrafficLightMin = 030;
		varGreenTrafficLightMax = 180;
		yellowMin = 04;
		yellowMax = 05;
		genDelayMin = 1;
		genDelayMax = 05;
		varRoadLength = 12;
		varRuntime = 1000;
	}

}
