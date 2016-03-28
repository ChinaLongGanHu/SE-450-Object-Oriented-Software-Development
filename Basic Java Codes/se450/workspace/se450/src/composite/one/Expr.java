package composite.one;
public interface Expr {
	int eval();
}

final class Const implements Expr {
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

final class Plus implements Expr {
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

final class Minus implements Expr {
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

final class Mult implements Expr {
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

final class Quot implements Expr {
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
