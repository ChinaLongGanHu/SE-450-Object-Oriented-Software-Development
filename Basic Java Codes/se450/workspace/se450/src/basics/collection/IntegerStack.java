package basics.collection;
import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;
final public class IntegerStack {
	final private List<Integer> l;
	public IntegerStack() { l = new ArrayList<Integer>(); }
	public boolean isEmpty() { return l.isEmpty(); }
	public void push(Integer x) {
		if (x == null)
			throw new IllegalArgumentException();
		l.add(x);
	}
	public Integer pop() {
		if (l.isEmpty())
			throw new EmptyStackException();
		return l.remove(l.size()-1);
	}
}
