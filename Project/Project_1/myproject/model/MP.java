package myproject.model;

import java.util.Random;

/**
 * Static class for model parameters.
 */
public class MP 
{
	
	private MP() {}
	
	/** Length of cars, in meters */
	//public static double carLength = 10;
	
	/** Length of roads, in meters */
	public static double roadLength = 200;
	
	/** Maximum car velocity, in meters/second */
	public static double maxVelocity = 6;
	
	/*
	 * Car entry rate: (second/car)
	 */
	public static double carEntryRate = 10;
	private static double carEntryRateMin = 2.0;
	private static double carEntryRateMax = 15.0;
	
	private static Random generator = new Random(System.currentTimeMillis());
	
	private static double getRandomNum(double min, double max) {
		return (generator.nextDouble() * (max - min)) + min;
	}
	
	public static double getCarEntryRate() 
	{
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

	/*
	 * By default it will take runtime as 1000.0 seconds
	 */
	public static int getGridColumns() {
		return columns;
	}
	
	private static double runtime = 1000.0;
	public static double getRuntime() {
		return runtime;
	}

	public static void setRuntime(double runt) {
		runtime = runt;
	}
	
	
	/*
	 * Car length:
	 */
	public static double carLength = 10;
	private static double carLengthMin = 5.0;
	private static double carLengthMax = 10.0;
	public static void setCarLengthMin(double min) {
		carLengthMin = min;
	}

	public static void setCarLengthMax(double max) {
		carLengthMax = max;
	}
}

