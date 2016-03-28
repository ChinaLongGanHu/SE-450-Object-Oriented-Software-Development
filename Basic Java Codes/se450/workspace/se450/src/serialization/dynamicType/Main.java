package serialization.dynamicType;
import java.io.*;

public class Main {
	public static void main(String args[])
			throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectOutputStream os
		= new ObjectOutputStream (new FileOutputStream("out.dat"));
		Entry eo = new Entry(1, new X0());
		os.writeObject(eo);
		os.close();

		ObjectInputStream is
		= new ObjectInputStream (new FileInputStream("out.dat"));
		Entry ei = (Entry) is.readObject();
		System.out.println(ei);
		is.close();
	}
}

class Entry implements Serializable {
	private static final long serialVersionUID = 2008L;
	private int i;
	private X x;

	public Entry(int i, X x) { this.i = i; this.x = x; }
	public String toString() {
		return "Entry(" + Integer.toString(i) + "," + x + ")";
	}
}

interface X {
	public String toString();
}

@SuppressWarnings("serial")
class X0 implements X, Serializable {
	public String toString() { return "X0"; }
}

class X1 implements X {
	public String toString() { return "X1"; }
}
