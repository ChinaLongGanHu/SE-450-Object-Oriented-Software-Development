package singleton.lazy;
public class S {
	private S() {}
	static private SState state;
	static public int inc() {
		if (state == null) {
			if ("linux".equals(System.getProperty("os.name"))) {
				state = new SLinux();
			} else {
				state = new SOther();
			}
		}
		return state.inc();
	}

	static private interface SState {
		public int inc();
	}
	static private class SLinux implements SState {
		private int i;
		public int inc() {return ++i;}
	}
	static private class SOther implements SState {
		private int i;
		public int inc() {return --i;}
	}
}
