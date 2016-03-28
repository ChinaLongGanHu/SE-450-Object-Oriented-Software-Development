package clone.deepVshallow;
class Node implements Cloneable {
	private int i;
	private Node next;

	public Node(int i, Node next) { this.i = i; this.next = next; }
	public Node(int i)            { this(i,null); }

	public Object shallow_copy() {
		return new Node(i, next);
	}

	public Object shallow_clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean shallow_equals(Object o) {
		if (!(this.getClass().equals(o.getClass())))
			return false;
		Node that = (Node) o;
		return (i == that.i) && (next == that.next);
	}

	public Object deep_copy() {
		Node next_copy = (next==null) ? null : (Node) next.deep_copy();
		return new Node(i, next_copy);
	}

	public Object deep_clone() throws CloneNotSupportedException {
		Node result = (Node) super.clone();
		result.next = (next==null) ? null : (Node) next.deep_clone();
		return result;
	}

	public boolean deep_equals(Object o) {
		if (!(this.getClass().equals(o.getClass())))
			return false;
		Node that = (Node) o;
		return (i == that.i)
				&& ((next==null) ? (that.next==null) : next.deep_equals(that.next));
	}
}
