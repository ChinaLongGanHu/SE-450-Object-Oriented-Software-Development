package composite.two;
public class ExprFactory {
	private ExprFactory() {}
	static public Expr newConst(int v) {
		return new Const(v);
	}
	static public Expr newPlus(Expr l, Expr r) {
		return new Plus(l, r);
	}
	static public Expr newMinus(Expr l, Expr r) {
		return new Minus(l, r);
	}
	static public Expr newMult(Expr l, Expr r) {
		return new Mult(l, r);
	}
	static public Expr newQuot(Expr l, Expr r) {
		return new Quot(l, r);
	}

	private static class Const implements Expr {
		private final int v;
		public Const(int v) {
			this.v = v;
		}
		public int eval() {
			return v;
		}
		public String toString() {
			return Integer.toString(v);
		}
	}

	private static class Plus implements Expr {
		private final Expr l;
		private final Expr r;
		public Plus(Expr l, Expr r) {
			if ((l == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.l = l;
			this.r = r;
		}
		public int eval() {
			return l.eval() + r.eval();
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " +";
		}
	}

	private static class Minus implements Expr {
		private final Expr l;
		private final Expr r;
		public Minus(Expr l, Expr r) {
			if ((l == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.l = l;
			this.r = r;
		}
		public int eval() {
			return l.eval() - r.eval();
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " -";
		}
	}

	private static class Mult implements Expr {
		private final Expr l;
		private final Expr r;
		public Mult(Expr l, Expr r) {
			if ((l == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.l = l;
			this.r = r;
		}
		public int eval() {
			return l.eval() * r.eval();
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " *";
		}
	}

	private static class Quot implements Expr {
		private final Expr l;
		private final Expr r;
		public Quot(Expr l, Expr r) {
			if ((l == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.l = l;
			this.r = r;
		}
		public int eval() {
			return l.eval() / r.eval();
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " /";
		}
	}
}
