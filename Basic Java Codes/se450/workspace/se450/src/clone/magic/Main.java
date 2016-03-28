package clone.magic;
public class Main {
	public static void main(String[] argv) throws CloneNotSupportedException {
		B x = new B(42,27);
		System.out.println(x);
		System.out.println(new A(x));
		System.out.println(x.copy());
		System.out.println(x.clone());
	}
}

class A implements Cloneable {
	int i;
	public A(int i) { this.i = i; }

	// A copy constructor
	public A(A that) { this.i = that.i; }

	// A copy method
	public Object copy() { return new A(i); }

	// The clone method
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public String toString() { return "A("+i+")"; }
}

class B extends A {
	int j;
	public B(int i, int j) { super(i); this.j = j; }
	public String toString() { return "B("+i+","+j+")"; }
}
