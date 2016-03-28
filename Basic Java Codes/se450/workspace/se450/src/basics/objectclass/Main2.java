package basics.objectclass;
// A static class
public class Main2 {
	private Main2() {}
	static public void main (String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		System.out.println(new Circle2 (1));
	}
}
// An object class
final class Circle2 extends Object {
	int radius;
	public Circle2(int radius) { this.radius = radius; }
	public String toString() { return "Circle(" + radius + ")"; }
}
