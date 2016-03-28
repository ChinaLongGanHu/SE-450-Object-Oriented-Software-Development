package basics.immutabledata;
public class Main {
	private Main() {}
	static public void main (final String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		System.out.println(new Pair<>(42, "dog"));
	}
}
