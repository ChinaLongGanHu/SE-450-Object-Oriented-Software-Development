package clone.stack;
public final class IntegerStack implements Cloneable {
	private int[] buffer;
	private int top;

	public IntegerStack(int maxContents) {
		buffer = new int[maxContents];
		top = -1;
	}
	public void push(int val) {
		buffer[++top] = val;
	}
	public int pop() {
		return buffer[top--];
	}
	public Object clone() {
		try {
			IntegerStack result = (IntegerStack) super.clone();
			result.buffer = buffer.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			// cannot happen
			throw new RuntimeException(e);
		}
	}
}
