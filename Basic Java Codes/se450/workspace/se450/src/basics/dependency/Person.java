package basics.dependency;
import java.util.Random;
final class Person {
	final private String name;
	public Person(String name) { this.name = name; }
	public String toString() { return "Person(" + name + ")"; };
}
class PersonFactory {
	private PersonFactory() {}
	static private Random random = new Random();
	static public  Person randomPerson() {
		return new Person(Integer.toString(random.nextInt()));
	}
}
