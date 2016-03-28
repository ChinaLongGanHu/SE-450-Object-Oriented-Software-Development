package enumeration2;

public interface Expr {
	void printPostorder();
	int evaluate();
}

class Const implements Expr {
	private final int v;
	public Const(int v) {
		this.v = v;
	}

	public int evaluate() {
		return v;
	}
	public void printPostorder() {
		System.out.print(v + " ");
	}
}

class BinOp implements Expr {
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

	public int evaluate() {
		return op.eval(l.evaluate(), r.evaluate());
	}
	public void printPostorder() {
		l.printPostorder();
		r.printPostorder();
		System.out.print(op + " ");
	}
}
