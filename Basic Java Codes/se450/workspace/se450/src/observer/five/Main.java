package observer.five;
public class Main {
	public static void main(String[] argv) {
		IntObserver o = new IntObserver();
		Int c = new Int(o);
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
	public void update() {
		System.out.println (++numOps);
	}
}
class Int {
	private IntObserver o;
	public Int(IntObserver o) { this.o = o; }
	private int v;
	public int get() { return v; }
	public void inc() { v++; o.update(); }
	public void dec() { v--; o.update(); }
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
