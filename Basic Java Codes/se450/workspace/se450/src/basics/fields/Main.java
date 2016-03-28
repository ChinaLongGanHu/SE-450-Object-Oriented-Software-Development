package basics.fields;
public class Main {
	private Main() {}
	static public void main (String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		Int x = new Int(42);
		Int y = new Int(27);
		System.out.println(x);
		System.out.println(y);
	}
}
final class Int {
	private final int v;
	public Int(int v) { this.v = v; }
	public String toString() { return "Int(" + v + ")"; }
}
