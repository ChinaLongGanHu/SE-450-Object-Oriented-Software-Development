package myproject.util;

import java.awt.Color;
import java.util.Random;

public class RandomUtil {

	private RandomUtil() {
	}

	private static Random random = new Random();

	/** doubles that differ by less than EPSILON should be considered equals */
	static public final double EPSILON = 1e-9;

	static public boolean isEquals(double x, double y) {
		return Math.abs(x - y) <= EPSILON;
	}

	static public boolean isEquals(int x, int y) {
		return Math.abs(x - y) <= 0;
	}

	static public boolean isLessOrEquals(double x, double y) {
		return (x - y) <= EPSILON;
	}

	static public boolean isLessOrEquals(int x, int y) {
		return (x - y) <= 0;
	}

	static public boolean isLess(double x, double y) {
		return (x - y) < -EPSILON;
	}

	static public boolean isLess(int x, int y) {
		return (x - y) < 0;
	}

	static public void setRandomSeed(long seed) {
		random.setSeed(seed);
	}

	static public double randomDouble(double min, double max) {
		if (RandomUtil.isLess(max, min))
			throw new IllegalArgumentException(max + " is smaller than " + min);
		return min + ((random.nextDouble()) * (max - min));
	}

	public static int randomInt(int min, int max) {
		if (RandomUtil.isLess(max, min))
			throw new IllegalArgumentException(max + " is smaller than " + min);
		return random.nextInt(max - min) + min;
	}

	public static Color randomColor() {
		return new Color(random.nextFloat(), random.nextFloat(),
				random.nextFloat());
	}

}