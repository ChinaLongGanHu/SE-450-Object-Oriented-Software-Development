package observer.four;
public class Main {
	public static void main(String[] argv) {
		Int c = new Int();
		IntObserver o = new IntObserver(c);
		Runnable r1 = new M(c);
		Runnable r2 = new N(c);
		for (int i=0; i<10000; i++) {
			r1.run();
			r2.run();
		}
	}
}
class IntObserver {
	private int numOps;
	public IntObserver (Int c) { /* ?? */ }
	public void update() {
		System.out.println (++numOps);
	}
}
class Int {
	private int v;
	public int get() { return v; }
	public void inc() { v++; }
	public void dec() { v--; }
}
class M implements Runnable {
	private Int c;
	public M(Int c) { this.c = c; }
	public void run() {
		c.inc();
		c.inc();
		c.dec();
	}
}
class N implements Runnable {
	private Int c;
	public N(Int c) { this.c = c; }
	public void run() {
		for (int i=0; i<50; i++) {
			if (i%3==0) {
				c.dec();
			} else {
				c.inc();
			}
		}
	}
}
