package basics.testing;

import static org.junit.Assert.*;
import org.junit.Test;
import basics.immutabledata.Pair;

public class PairTEST {
	/**
	 *  Constructor should throw IllegalArgumentException if either
	 *  argument is null.
	 */
	@Test
	public void testConstructor() {
		try {
			new Pair<String,String>(null,"cat");
			// constructor should have thrown exception
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new Pair<String,String>("dog",null);
			// constructor should have thrown exception
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new Pair<String,String>(null,null);
			// constructor should have thrown exception
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new Pair<String,String>("dog","cat");
		} catch (IllegalArgumentException e) {
			// constructor should have succeeded
			fail();
		}
	}

	/**
	 *  first() and second() should return references to the objects
	 *  given to the constructor.
	 */
	@Test
	public void testFirstAndSecond() {
		Integer i = 42;
		Integer j = 91;
		Pair<Integer,Integer> p = new Pair<Integer,Integer>(i, j);
		assertSame(i, p.first());
		assertSame(j, p.second());
	}

	/**
	 *  Here is a weaker version of the above test; this version allows
	 *  Pair() to clone its arguments.
	 */
	@Test
	public void testFirstAndSecond_WeakerVersion() {
		Integer i = 42;
		Integer j = 91;
		Pair<Integer,Integer> p = new Pair<Integer,Integer>(i, j);
		assertEquals(i, p.first());
		assertEquals(j, p.second());
	}

	@Test
	public void testToString() {
		Pair<Integer,Integer> p = new Pair<Integer,Integer>(42, 91);
		assertEquals("Pair(42,91)", p.toString());
	}

	@Test
	public void testEquals() {
		Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
		Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
		assertTrue(p1.equals(p1));
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(p4));
		assertFalse(p1.equals(new Object()));
	}

	@Test
	public void testHashCode() {
		Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
		Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
		assertEquals(p1.hashCode(), p1.hashCode());
		assertEquals(p1.hashCode(), p2.hashCode());
		assertTrue(p1.hashCode() != p3.hashCode());
		assertTrue(p1.hashCode() != p4.hashCode());
	}

	@Test
	public void testCompareTo() {
		Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(42, 91);
		Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(43, 91);
		Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(42, 92);
		assertTrue(0 == p1.compareTo(p1));
		assertTrue(0 == p1.compareTo(p2));
		assertTrue(0 >  p1.compareTo(p3));
		assertTrue(0 >  p1.compareTo(p4));
		assertTrue(0 == p2.compareTo(p1));
		assertTrue(0 <  p3.compareTo(p1));
		assertTrue(0 <  p4.compareTo(p1));
		// Using generic types, the following will not compile.
		// try {
		//   p1.compareTo(new Object());
		//   // compareTo should have thrown exception
		//   fail();
		// } catch (ClassCastException e) { }
	}
}
