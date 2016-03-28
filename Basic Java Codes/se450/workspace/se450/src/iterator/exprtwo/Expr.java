package iterator.exprtwo;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import enumeration.Op;

public interface Expr {
	public int evaluate();
	public Iterator<Object> preorderIterator();
	public Iterator<Object> postorderIterator();
	public Iterator<Object> breadthIterator();
}

abstract class AbsExpr implements Expr {
	public abstract int evaluate();
	public Iterator<Object> preorderIterator() {
		PreorderIterator i = new PreorderIterator();
		i.addNode(this);
		return i;
	}
	public Iterator<Object> postorderIterator() {
		PostorderIterator i = new PostorderIterator();
		i.addNode(this);
		return i;
	}
	public Iterator<Object> breadthIterator() {
		BreadthIterator i = new BreadthIterator();
		i.addNode(this);
		return i;
	}
	abstract Object acceptPreOrder(PreorderIterator i);
	abstract Object acceptPostOrder(PostorderIterator i);
	abstract Object acceptBreadth(BreadthIterator i);
}

class Const extends AbsExpr {
	private final int v;
	public Const(int v) {
		this.v = v;
	}
	public int evaluate() {
		return v;
	}
	Object acceptPreOrder(PreorderIterator i) {
		return v;
	}
	Object acceptPostOrder(PostorderIterator i) {
		i.markVisited(this);
		return v;
	}
	Object acceptBreadth(BreadthIterator i) {
		i.markVisited(this);
		return v;
	}
}

class BinOp extends AbsExpr {
	private final AbsExpr l;
	private final AbsExpr r;
	private final Op op;
	public BinOp(AbsExpr l, Op op, AbsExpr r) {
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
	Object acceptPreOrder(PreorderIterator i) {
		i.addNode(r);
		i.addNode(l);
		return op;
	}
	Object acceptPostOrder(PostorderIterator i) {
		i.markVisited(this);
		if (i.visited(r)) {
			return op;
		} else {
			i.addNode(this);
			if (i.visited(l))
				return r.acceptPostOrder(i);
			else
				return l.acceptPostOrder(i);
		}
	}
	Object acceptBreadth(BreadthIterator i) {
		if (i.visited(this)) {
			i.addNode(l);
			i.addNode(r);
			return i.next();
		} else {
			i.markVisited(this);
			i.addNode(this);
			return op;
		}
	}
}

class PreorderIterator implements Iterator<Object> {
	private Stack<AbsExpr> s = new Stack<AbsExpr>();
	public boolean hasNext() {
		return ! s.empty();
	}
	void addNode(AbsExpr e) {
		s.push(e);
	}
	public Object next() {
		if (hasNext())
			return (s.pop()).acceptPreOrder(this);
		throw new NoSuchElementException();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

class PostorderIterator implements Iterator<Object> {
	private Set<AbsExpr> v = new HashSet<AbsExpr>();
	private Stack<AbsExpr> s = new Stack<AbsExpr>();
	public boolean hasNext() {
		return ! s.empty();
	}
	boolean visited(AbsExpr e) {
		return v.contains(e);
	}
	void markVisited(AbsExpr e) {
		v.add(e);
	}
	void addNode(AbsExpr e) {
		s.push(e);
	}
	public Object next() {
		if (hasNext())
			return (s.pop()).acceptPostOrder(this);
		throw new NoSuchElementException();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

class BreadthIterator implements Iterator<Object> {
	private Set<AbsExpr> v = new HashSet<AbsExpr>();
	private List<AbsExpr> l = new ArrayList<AbsExpr>();
	public boolean hasNext() {
		return ! l.isEmpty();
	}
	boolean visited(AbsExpr e) {
		return v.contains(e);
	}
	void markVisited(AbsExpr e) {
		v.add(e);
	}
	void addNode(AbsExpr e) {
		l.add(e);
	}
	public Object next() {
		if (hasNext()) {
			AbsExpr e = l.get(0);
			l.remove(0);
			return e.acceptBreadth(this);
		}
		throw new NoSuchElementException();
	}
	public void remove() {
		throw new UnsupportedOperationException();
	}
}

