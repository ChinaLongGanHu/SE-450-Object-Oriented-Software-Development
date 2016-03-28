package composite.three;

public class ExprFactory {
	private ExprFactory() {}
	static public Expr newConst(int v) {
		return new Const(v);
	}
	static public Expr newPlus(Expr l, Expr r) {
		return new BinOp(l, new OpAdd(), r);
	}
	static public Expr newMinus(Expr l, Expr r) {
		return new BinOp(l, new OpSub(), r);
	}
	static public Expr newMult(Expr l, Expr r) {
		return new BinOp(l, new OpMul(), r);
	}
	static public Expr newQuot(Expr l, Expr r) {
		return new BinOp(l, new OpDiv(), r);
	}

	private static final class Const implements Expr {
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

	private static final class BinOp implements Expr {
		private final Expr l;
		private final Expr r;
		private final Op op;
		public BinOp(Expr l, Op op, Expr r) {
			if ((l == null) || (op == null) || (r == null)) {
				throw new IllegalArgumentException();
			}
			this.op = op;
			this.l = l;
			this.r = r;
		}
		public int eval() {
			return op.eval(l.eval(), r.eval());
		}
		public String toString() {
			return l.toString() + " " + r.toString() + " " + op.toString();
		}
	}

	private static interface Op {
		public abstract int eval(int x, int y);
	}
	private static final class OpAdd implements Op {
		public String toString() { return "+"; }
		public int eval(int x, int y) { return x+y; }
	}
	private static final class OpSub implements Op {
		public String toString() { return "-"; }
		public int eval(int x, int y) { return x-y; }
	}
	private static final class OpMul implements Op {
		public String toString() { return "*"; }
		public int eval(int x, int y) { return x*y; }
	}
	private static final class OpDiv implements Op {
		public String toString() { return "/"; }
		public int eval(int x, int y) { return x/y; }
	}
}

