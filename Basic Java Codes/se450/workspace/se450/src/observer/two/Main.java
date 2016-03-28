package observer.two;
public class Main {
	public static void main(String[] argv) {
		Int c = new Int();
		Runnable r1 = new M(c);
		Runnable r2 = new N(c);
		for (int i=0; i<10000; i++) {
			r1.run();
			r2.run();
		}
	}
}
class Int {
	private int v;
	public int get() { return v; }
	public void inc() { v++; }
	public void dec() { v--; }
}
class M implements Runnable {
	private int numOps;
	private Int c;
	public M(Int c) { this.c = c; }
	public void run() {
		c.inc(); System.out.println (++numOps);
		c.inc(); System.out.println (++numOps);
		c.dec(); System.out.println (++numOps);
	}
}
class N implements Runnable {
	private Int c;
	public N(Int c) { this.c = c; }
	public void run() {
		for (int i=0; i<50; i++) {
			if (i%3==0) {
				c.dec(); /*??*/
			} else {
				c.inc(); /*??*/
			}
		}
	}
}
