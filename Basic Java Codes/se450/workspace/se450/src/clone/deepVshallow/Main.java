package clone.deepVshallow;
public class Main {
	public static void main(String[] argv) throws CloneNotSupportedException {
		Node w = new Node(1, new Node(2, new Node(3)));
		Node v = w;
		Node x = (Node) w.shallow_copy();
		Node y = (Node) w.deep_copy();
		Node z = (Node) w.shallow_clone();

		System.out.println("w==v: " + ((w == v) ? "true" : "false"));
		System.out.println("w==x: " + ((w == x) ? "true" : "false"));
		System.out.println("w==y: " + ((w == y) ? "true" : "false"));
		System.out.println("w==z: " + ((w == z) ? "true" : "false"));
		System.out.println("w.equals(v): " + ((w.equals(v)) ? "true" : "false"));
		System.out.println("w.equals(x): " + ((w.equals(x)) ? "true" : "false"));
		System.out.println("w.equals(y): " + ((w.equals(y)) ? "true" : "false"));
		System.out.println("w.equals(z): " + ((w.equals(z)) ? "true" : "false"));
		System.out.println("w.shallow_equals(v): " +
				((w.shallow_equals(v)) ? "true" : "false"));
		System.out.println("w.shallow_equals(x): " +
				((w.shallow_equals(x)) ? "true" : "false"));
		System.out.println("w.shallow_equals(y): " +
				((w.shallow_equals(y)) ? "true" : "false"));
		System.out.println("w.shallow_equals(z): " +
				((w.shallow_equals(z)) ? "true" : "false"));
		System.out.println("w.deep_equals(v): " +
				((w.deep_equals(v)) ? "true" : "false"));
		System.out.println("w.deep_equals(x): " +
				((w.deep_equals(x)) ? "true" : "false"));
		System.out.println("w.deep_equals(y): " +
				((w.deep_equals(y)) ? "true" : "false"));
		System.out.println("w.deep_equals(z): " +
				((w.deep_equals(z)) ? "true" : "false"));
	}
}
