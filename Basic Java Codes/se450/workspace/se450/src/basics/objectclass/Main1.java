package basics.objectclass;
// A static class
public class Main1 {
	private Main1() {}
	static public void main (String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		Circle1 c = new Circle1(1);
		String s = ((c==null) ? "null" : c.toString());
		System.out.println(s);
	}
}
// An object class
final class Circle1 extends Object {
	final int radius;
	public Circle1(int radius) { super(); this.radius = radius; }
	public String toString() { return "Circle(" + radius + ")"; }
}
