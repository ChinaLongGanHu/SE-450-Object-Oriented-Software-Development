package iterator.exprone;
import java.util.Iterator;
import java.util.NoSuchElementException;
import enumeration.Op;

public interface Expr {
	int evaluate();
	Iterator<Object> postorderIterator();
}

class Const implements Expr {
	private final int v;
	public Const(int v) {
		this.v = v;
	}

	public int evaluate() {
		return v;
	}
	public Iterator<Object> postorderIterator() {
		return new LeafIterator<>(v);
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
	public Iterator<Object> postorderIterator() {
		return new PostorderIterator<>
		(op, l.postorderIterator(), r.postorderIterator());
	}
}


class PostorderIterator<T> implements Iterator<T> {
	private T v;
	private final Iterator<T> l;
	private final Iterator<T> r;

	PostorderIterator(T v, Iterator<T> l, Iterator<T> r) {
		this.v = v;
		this.l = l;
		this.r = r;
	}
	public boolean hasNext() {
		return r.hasNext() || l.hasNext() || (v != null);
	}
	public T next() {
		if (l.hasNext()) {
			return l.next();
		} else if (r.hasNext()) {
			return r.next();
		} else if (v != null) {
			T result = this.v;
			this.v = null;
			return result;
		}
		throw new NoSuchElementException();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

class LeafIterator<T> implements Iterator<T> {
	private T v;

	public LeafIterator(T v) {
		this.v = v;
	}
	public boolean hasNext() {
		return (v != null);
	}
	public T next() {
		if (v != null) {
			T result = this.v;
			this.v = null;
			return result;
		}
		throw new NoSuchElementException();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
