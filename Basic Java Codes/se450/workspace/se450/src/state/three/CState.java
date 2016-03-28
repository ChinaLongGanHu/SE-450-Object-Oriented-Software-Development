package state.three;

interface CState {
	public int f(C x);
	public int g(C x);

	public static final CState MINUS = new CStateMinus();
	public static final CState PLUS = new CStatePlus();
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

