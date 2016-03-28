package state.four;

interface I {
	public int f();
	public int g();
	public void changeDirection();
}

class C implements I {
	private CState state = new CStateMinus(this);
	int i;
	int j;
	public int f() {
		return state.f();
	}
	public int g() {
		return state.g();
	}
	public void changeDirection() {
		state = state.next();
	}
}

interface CState {
	public int f();
	public int g();
	public CState next();
}
class CStateMinus implements CState {
	C x;
	CStateMinus(C x) { this.x = x; }
	public int f() {
		x.i -= 32;
		return x.i;
	}
	public int g() {
		x.j -= 27;
		return x.j;
	}
	public CState next() {
		return new CStatePlus(x);
	}
}
class CStatePlus implements CState {
	C x;
	CStatePlus(C x) { this.x = x; }
	public int f() {
		x.i += 26;
		return x.i;
	}
	public int g() {
		x.j += 42;
		return x.j;
	}
	public CState next() {
		return new CStateMinus(x);
	}
}

