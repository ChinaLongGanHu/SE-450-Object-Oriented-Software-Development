package visitor.expr;
import enumeration.Op;

public interface Expr {
	<T> T accept(ExprVisitor<T> v);
}

class Const implements Expr {
	private final int c;
	public Const(int c) {
		this.c = c;
	}
	public <T> T accept(ExprVisitor<T> v) {
		return v.visitConst(c);
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
	public <T> T accept(ExprVisitor<T> v) {
		return v.visitBinOp(l, op, r);
	}
}
