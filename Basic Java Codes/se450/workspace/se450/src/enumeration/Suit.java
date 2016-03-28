package enumeration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// Ordinal-based typesafe enum (From Bloch)
//
// No need to override equals, since only one instance of each suit.
// Note that instanceNum and name are instance fields.
// Note that cNumInstances is a class field.
// Note that the order of the constant definitions is important.
// Note that VALUES is an immutable collection.
// Java arrays are always mutable :-(
public final class Suit implements Comparable<Suit> {
	// Number of instances
	private static int cNumInstances = 0;

	// Ordinal for this instance
	private final int instanceNum;

	// Name of Suit
	private final String name;

	// Private constructor: All instances created in the class
	private Suit(String name) {
		this.name = name;
		this.instanceNum = Suit.cNumInstances++;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Suit that) {
		return this.instanceNum - that.instanceNum;
	}

	public static final Suit CLUBS = new Suit("clubs");
	public static final Suit DIAMONDS = new Suit("diamonds");
	public static final Suit HEARTS = new Suit("hearts");
	public static final Suit SPADES = new Suit("spades");

	public static final List<Suit> VALUES;
	static {
		Suit[] values = { CLUBS, DIAMONDS, HEARTS, SPADES };
		VALUES = Collections.unmodifiableList(Arrays.asList(values));
	}
}
