package basics.aggregation;
final class Person {
	//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
	final private String name;
	public Person(String name) { this.name = name; }
	public String toString() { return "Person(" + name + ")"; };
}
