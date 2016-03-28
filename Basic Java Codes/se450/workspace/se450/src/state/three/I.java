package state.three;

interface I {
	public int f();
	public int g();
	public void changeDirection();
}

class C implements I {
	private CState state = CState.MINUS;
	int i;
	int j;
	public int f() {
		return state.f(this);
	}
	public int g() {
		return state.g(this);
	}
	public void changeDirection() {
		state = (state==CState.MINUS) ? CState.PLUS : CState.MINUS;
	}
}

