package enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Typesafe enum (From Bloch)
//
public final class Card {
	// Rank of Card
	private final Rank rank;

	// Suit of Card
	private final Suit suit;

	// Private constructor: All instances created in the class
	private Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public int compareRank(Card c) {
		return rank.compareTo(c.rank);
	}

	public int compareSuit(Card c) {
		return suit.compareTo(c.suit);
	}

	public Rank getRank() {
		return rank;
	}

	public int getRankValue() {
		return rank.getValue();
	}

	public Suit getSuit() {
		return suit;
	}

	public static final List<Card> VALUES;
	static {
		List<Card> values = new ArrayList<Card>(56);
		for (Suit s : Suit.VALUES) {
			for (Rank r : Rank.VALUES) {
				values.add(new Card(r, s));
			}
		}
		VALUES = Collections.unmodifiableList(values);
	}
}
