package subclass.constructor;
class Main {
	public static void main(String[] argv) {
		new X();
		new X();
	}
}
class O {
	int i, j;
	{ i = 42; System.err.println("i"); }
	O() { System.err.println("O"); }
	{ j = 27; System.err.println("j"); }
}
class X extends O {
	int k;
	X() { System.err.println("X"); }
	{ k = 27; System.err.println("k"); }
}

