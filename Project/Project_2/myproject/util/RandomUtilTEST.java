package myproject.util;

import junit.framework.TestCase;

public class RandomUtilTEST extends TestCase {
	public RandomUtilTEST(String name) {
		super(name);
	}

	public void testequals() {
		assertTrue(RandomUtil.isEquals(0.3,
				0.3 + 0.5 * RandomUtil.EPSILON));
		assertFalse(RandomUtil.isEquals(0.3,
				0.3 + 2.0 * RandomUtil.EPSILON));
		assertFalse(RandomUtil.isLess(0.3,
				0.3 + 0.5 * RandomUtil.EPSILON));
		assertTrue(RandomUtil
				.isLess(0.3, 0.3 + 2.0 * RandomUtil.EPSILON));
		assertTrue(RandomUtil.isLessOrEquals(0.3,
				0.3 + 0.5 * RandomUtil.EPSILON));
		assertTrue(RandomUtil.isLessOrEquals(0.3,
				0.3 + 2.0 * RandomUtil.EPSILON));

		assertTrue(RandomUtil.isEquals(0.3 + 0.5 * RandomUtil.EPSILON,
				0.3));
		assertFalse(RandomUtil.isEquals(0.3 + 2.0 * RandomUtil.EPSILON,
				0.3));
		assertFalse(RandomUtil.isLess(0.3 + 0.5 * RandomUtil.EPSILON,
				0.3));
		assertFalse(RandomUtil.isLess(0.3 + 2.0 * RandomUtil.EPSILON,
				0.3));
		assertTrue(RandomUtil.isLessOrEquals(
				0.3 + 0.5 * RandomUtil.EPSILON, 0.3));
		assertFalse(RandomUtil.isLessOrEquals(
				0.3 + 2.0 * RandomUtil.EPSILON, 0.3));
	}

	public void testrandomDouble() {
		for (int i = 0; i < 100; i++) {
			double x = RandomUtil.randomDouble(i, i * i);
			assertTrue(RandomUtil.isLessOrEquals(i, x));
			assertTrue(RandomUtil.isLessOrEquals(x, i * i));
		}
	}

	public void testrandomInt() {
		for (int i = 1; i < 100; i++) {
			int x = RandomUtil.randomInt(i, 100);
			// System.out.println(x);
			// System.out.println(i+","+x+","+i*i);
			assertTrue(RandomUtil.isLessOrEquals(x, 100));
			assertTrue(RandomUtil.isLessOrEquals(i, 100));
		}
	}

	public void testrandomColor() {
		// will not do test on this
	}
}
