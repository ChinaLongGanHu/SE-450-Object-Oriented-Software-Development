package singleton.pub4;
public abstract class S {
	private static S instance;
	static {
		if ("linux".equals(System.getProperty("os.name")))
			instance = new SLinux();
		else
			instance = new SOther();
	}
	public static S get() {return instance;}
	public abstract int inc();
}
final class SLinux extends S {
	private int i;
	public int inc() {return ++i;}
}
final class SOther extends S {
	private int i;
	public int inc() {return --i;}
}
