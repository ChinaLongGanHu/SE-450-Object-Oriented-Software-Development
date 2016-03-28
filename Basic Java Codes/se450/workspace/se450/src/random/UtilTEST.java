package random;

import static org.junit.Assert.*;
import org.junit.Test;

public class UtilTEST {

	@Test
	public void testEquals() {
		assertTrue (Util.isEquals(0.3, 0.3+0.5*Util.EPSILON));
		assertFalse(Util.isEquals(0.3, 0.3+2.0*Util.EPSILON));
		assertFalse(Util.isLess(0.3, 0.3+0.5*Util.EPSILON));
		assertTrue (Util.isLess(0.3, 0.3+2.0*Util.EPSILON));
		assertTrue (Util.isLessOrEquals(0.3, 0.3+0.5*Util.EPSILON));
		assertTrue (Util.isLessOrEquals(0.3, 0.3+2.0*Util.EPSILON));

		assertTrue (Util.isEquals(0.3+0.5*Util.EPSILON, 0.3));
		assertFalse(Util.isEquals(0.3+2.0*Util.EPSILON, 0.3));
		assertFalse(Util.isLess(0.3+0.5*Util.EPSILON, 0.3));
		assertFalse(Util.isLess(0.3+2.0*Util.EPSILON, 0.3));
		assertTrue (Util.isLessOrEquals(0.3+0.5*Util.EPSILON, 0.3));
		assertFalse(Util.isLessOrEquals(0.3+2.0*Util.EPSILON, 0.3));
	}
	@Test
	public void testRandom() {
		for (int i=0; i<100; i++) {
			double x=Util.nextRandom(i,i*i);
			assertTrue (Util.isLessOrEquals(i,x));
			assertTrue (Util.isLessOrEquals(x,i*i));
		}
	}
}
