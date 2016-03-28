package basics.mutabledata;
public class Main {
	private Main() {}
	static public void main (final String[] args) {
		//stdlib.Trace.graphvizShowSteps (true); stdlib.Trace.run ();
		final MutPair<Integer,String> mp1 = new MutPair<>();
		mp1.setFirst(42);
		mp1.setSecond("dog");
		System.out.println(mp1);
	}
}
