package basics.composition;
import java.util.Random;
final class Person {
	static private Random random = new Random();
	final  private String name;
	public Person() { name = Integer.toString(random.nextInt()); }
	//public Person(String name) { name = name.clone(); }
	public String toString() { return "Person(" + name + ")"; };
}
