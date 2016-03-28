package basics.immutabledata;
final public class Pair<S extends Comparable<S>, T extends Comparable<T>>
implements Comparable<Pair<S,T>>
{
	final private S x;
	final private T y;
	public Pair(S x, T y) {
		if (x == null || y == null)
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}
	public S first() { return x; }
	public T second() { return y; }
	public String toString() { return "Pair(" + x + "," + y + ")"; }
	public boolean equals(Object thatObject) {
		if (thatObject == null)
			return false;
		if (this == thatObject)
			return true;
		// equals should not throw ClassCastException
		if (!(this.getClass().equals(thatObject.getClass())))
			return false;
		@SuppressWarnings("unchecked")
		Pair<S,T> that = (Pair<S,T>) thatObject;
		return x.equals(that.x)
				&& y.equals(that.y);
	}
	private int hcode;
	public int hashCode() {
		if (hcode == 0) {
			hcode = 17;
			hcode = 37*hcode + x.hashCode();
			hcode = 37*hcode + y.hashCode();
		}
		return hcode;
	}
	public int compareTo(Pair<S,T> that) {
		// compareTo may throw ClassCastException
		int ix = x.compareTo(that.x);
		if (ix != 0)
			return ix;
		return y.compareTo(that.y);
	}
}
