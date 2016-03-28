package stdlib;

public class Logger {
	private Logger() { }
	private static boolean on = true;
	public static void on () { on = true; }
	public static void off () { on = false; }
	public static void println (String s) {
		if (on) {
			System.out.println (s);
		}
	}
}
