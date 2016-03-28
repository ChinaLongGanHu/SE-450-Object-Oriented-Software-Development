package state.two;

interface I {
	public int f();
	public int g();
	public void changeDirection();
}

class C implements I {
	private CState[] states = new CState[] { new CStateMinus(), new CStatePlus() };
	private int index;
	int i;
	int j;
	public int f() {
		return states[index].f(this);
	}
	public int g() {
		return states[index].g(this);
	}
	public void changeDirection() {
		index = (index+1) % 2;
	}
}

interface CState {
	public int f(C x);
	public int g(C x);
}
class CStateMinus implements CState {
	public int f(C x) {
		x.i -= 32;
		return x.i;
	}
	public int g(C x) {
		x.j -= 27;
		return x.j;
	}
}
class CStatePlus implements CState {
	public int f(C x) {
		x.i += 26;
		return x.i;
	}
	public int g(C x) {
		x.j += 42;
		return x.j;
	}
}

