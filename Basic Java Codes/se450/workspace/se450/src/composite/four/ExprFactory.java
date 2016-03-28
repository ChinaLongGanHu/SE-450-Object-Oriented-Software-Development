package composite.four;
import java.util.List;
import java.util.LinkedList;

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
	static public ExprBuilder newPlusBuilder() {
		return new NaryOpBuilder(new OpAdd(), new Const(0));
	}
	static public ExprBuilder newMultBuilder() {
		return new NaryOpBuilder(new OpMul(), new Const(1));
	}
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

final class BinOp implements Expr {
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

final class NaryOp implements Expr {
	private final Expr[] args;
	private final Expr zero;
	private final Op op;
	public NaryOp(Expr[] args, Op op, Expr zero) {
		// Don't need to test these, since the builder checks them.
		//     if ((args == null) || (op == null) || (zero == null))
		//       throw new IllegalArgumentException();
		//     for (int i=0; i<args.length; i++)
		//       if (args[i] == null)
		//         throw new IllegalArgumentException();
		this.op = op;
		this.args = args;
		this.zero = zero;
	}
	public int eval() {
		int result = zero.eval();
		for (int i=0; i<args.length; i++)
			result = op.eval(result, args[i].eval());
		return result;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i=0; i<args.length; i++) {
			sb.append(args[i].toString());
			if (i+1<args.length)
				sb.append(", ");
		}
		sb.append("]");
		sb.append(op.toString());
		sb.append(" ");
		return sb.toString();
	}
}

final class NaryOpBuilder implements ExprBuilder {
	private final List<Expr> args;
	private final Expr zero;
	private final Op op;
	public NaryOpBuilder(Op op, Expr zero) {
		if ((op == null) || (zero == null))
			throw new IllegalArgumentException();
		this.args = new LinkedList<Expr>();
		this.op = op;
		this.zero = zero;
	}
	public void add(Expr e) {
		if (e == null)
			throw new IllegalArgumentException();
		args.add(e);
	}
	public Expr toExpr() {
		return new NaryOp(args.toArray(new Expr[0]), op, zero);
	}
}

interface Op {
	public abstract int eval(int x, int y);
}
final class OpAdd implements Op {
	public String toString() { return "+"; }
	public int eval(int x, int y) { return x+y; }
}
final class OpSub implements Op {
	public String toString() { return "-"; }
	public int eval(int x, int y) { return x-y; }
}
final class OpMul implements Op {
	public String toString() { return "*"; }
	public int eval(int x, int y) { return x*y; }
}
final class OpDiv implements Op {
	public String toString() { return "/"; }
	public int eval(int x, int y) { return x/y; }
}
