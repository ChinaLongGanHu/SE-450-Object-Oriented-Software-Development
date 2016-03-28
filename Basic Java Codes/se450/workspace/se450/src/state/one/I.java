package state.one;

interface I {
	public int f();
	public int g();
	public void changeDirection();
}

class C implements I {
	private boolean state;
	private int i;
	private int j;
	public int f() {
		if (state)
			i += 26;
		else
			i -= 32;
		return i;
	}
	public int g() {
		if (state)
			j += 42;
		else
			j -= 27;
		return j;
	}
	public void changeDirection() {
		state = !state;
	}
}
