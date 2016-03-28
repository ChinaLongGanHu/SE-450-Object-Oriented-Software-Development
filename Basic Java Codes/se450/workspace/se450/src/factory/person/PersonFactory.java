package factory.person;
import java.util.Random;
public class PersonFactory {
	private PersonFactory() {}
	static private Random random = new Random();
	static public SSN newSSN(String s) {
		final int REQUIREDLENGTH = 9;
		if (null == s || REQUIREDLENGTH != s.length())
			throw new IllegalArgumentException();
		boolean someNonZeroDigit = false;
		for (int i=0; i<REQUIREDLENGTH; i++) {
			char c = s.charAt(i);
			if (!Character.isDigit(c))
				throw new IllegalArgumentException();
			if (0 != Character.digit(c,10))
				someNonZeroDigit = true;
		}
		if (!someNonZeroDigit)
			throw new IllegalArgumentException();
		return new SSNObj(s);
	}
	static public Person newRandomPerson() {
		return new PersonObj(Integer.toString(random.nextInt()),SSN.INVALID);
	}
	static public Person newPerson(String name, String ssn) {
		return new PersonObj(name,ssn);
	}
	static public Person newPerson(String name, SSN ssn) {
		return new PersonObj(name,ssn);
	}
}

final class SSNObj implements SSN {
	final private String s;
	final private long l;
	SSNObj(String s) {
		this.s = s;
		this.l = Long.parseLong(s);
	}
	public String toString() { return s; }
	public long toLong() { return l; }
	// TODO: define equals, hashcode, compareTo
	public int compareTo(SSN that) { return -1; }
}

final class PersonObj implements Person {
	final private String name;
	final private SSN ssn;
	PersonObj(String name, SSN ssn) {
		if (null == name || 0 == name.trim().length())
			throw new IllegalArgumentException();
		this.name = name.trim();
		this.ssn = ssn;
	}
	PersonObj(String name, String ssn) {
		this(name,PersonFactory.newSSN(ssn));
	}
	public String name() { return name; }
	public SSN ssn() { return ssn; }
	// TODO: define equals, hashcode, compareTo
	public int compareTo(Person that) { return -1; }
}
