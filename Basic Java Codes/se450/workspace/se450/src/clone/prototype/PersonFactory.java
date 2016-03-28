package clone.prototype;
import java.util.Random;
public class PersonFactory {
	private static SSNObj prototypeSSN = new SSNObj();
	private static PersonObj prototypePerson = new PersonObj();

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
		return ((SSNObj)prototypeSSN.clone()).initialize(s);
	}
	static public Person newRandomPerson() {
		return ((PersonObj)prototypePerson.clone()).
				initialize(Integer.toString(random.nextInt()),SSN.INVALID);
	}
	static public Person newPerson(String name, String ssn) {
		return ((PersonObj)prototypePerson.clone()).initialize(name,ssn);
	}
	static public Person newPerson(String name, SSN ssn) {
		return ((PersonObj)prototypePerson.clone()).initialize(name,ssn);
	}
}

final class SSNObj implements SSN {
	private String s;
	private long l;
	SSNObj() {}
	SSNObj initialize(String s) {
		this.s = s;
		this.l = Long.parseLong(s);
		return this;
	}
	public String toString() { return s; }
	public long toLong() { return l; }
	// TODO: define equals, hashcode, compareTo
	public int compareTo(SSN o) { return -1; }
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}

final class PersonObj implements Person {
	private String name;
	private SSN ssn;
	PersonObj() {}
	PersonObj initialize(String name, SSN ssn) {
		if (null == name || 0 == name.trim().length())
			throw new IllegalArgumentException();
		this.name = name.trim();
		this.ssn = ssn;
		return this;
	}
	PersonObj initialize(String name, String ssn) {
		return initialize(name,PersonFactory.newSSN(ssn));
	}
	public String name() { return name; }
	public SSN ssn() { return ssn; }
	// TODO: define equals, hashcode, compareTo
	public int compareTo(Person o) { return -1; }
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
