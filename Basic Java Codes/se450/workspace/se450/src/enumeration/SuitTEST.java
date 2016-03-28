package enumeration;

import static org.junit.Assert.*;
import org.junit.Test;


/*
 * see http://junit.sourceforge.net/javadoc/index.html
 */
public class SuitTEST {
	@Test
	public void testCompareTo() {
		assertTrue(Suit.CLUBS.compareTo(Suit.CLUBS) == 0);
		assertTrue(Suit.CLUBS.compareTo(Suit.DIAMONDS) == -1);
		assertTrue(Suit.CLUBS.compareTo(Suit.HEARTS) == -2);
		assertTrue(Suit.CLUBS.compareTo(Suit.SPADES) == -3);
	}
}
