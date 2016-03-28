package state.five;

interface I {
	public int f();
	public int g();
	public void changeDirection();
}

class C implements I {
	private State state = new StateMinus();
	private int i;
	private int j;
	public int f() {
		return state.f();
	}
	public int g() {
		return state.g();
	}
	public void changeDirection() {
		state = state.next();
	}

	interface State {
		public int f();
		public int g();
		public State next();
	}
	class StateMinus implements State {
		public int f() {
			i -= 32;
			return i;
		}
		public int g() {
			j -= 27;
			return j;
		}
		public State next() {
			return new StatePlus();
		}
	}
	class StatePlus implements State {
		public int f() {
			i += 26;
			return i;
		}
		public int g() {
			j += 42;
			return j;
		}
		public State next() {
			return new StateMinus();
		}
	}
}

