package subclass.ex5;

public class M {
	public static void main(String[] argv) {
		(new B()).m();
	}
}
class A {
	static void p() {
		System.out.println("A.p: " + x); // which x?
	}
	static final int x = 42;
}
@SuppressWarnings("all")
class B extends A {
	void m() {
		this.p();
		System.out.println("B.m: " + x); // which x?
	}
	static final int x = 27;
}
