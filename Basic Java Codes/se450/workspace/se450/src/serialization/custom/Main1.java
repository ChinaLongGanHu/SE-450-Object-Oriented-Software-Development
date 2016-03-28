package serialization.custom;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

@SuppressWarnings("serial")
class IntList1 implements IntList
{
	private LinkedList<Integer> v;
	//private void setV(LinkedList<Integer> v) { v = v; }

	public IntList1()               { v = new LinkedList<Integer>(); }
	public void addBack(int i)      { v.addLast(i); }
	public void addFront(int i)     { v.addFirst(i); }
	public int removeFront()        { return v.removeFirst(); }
	public int removeBack()         { return v.removeLast(); }
	public boolean isEmpty()        { return v.size() == 0; }
}

public class Main1 {
	public static void main(String[] args)
			throws IOException, FileNotFoundException, ClassNotFoundException
	{
		IntList L = new IntList1();
		for (int i=0; i<1000; i++)
			L.addFront(i);

		ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("out1.dat"));
		os.writeObject(L);
		os.flush();
		os.close();

		ObjectInputStream in = new ObjectInputStream (new FileInputStream("out1.dat"));
		IntList V = (IntList) in.readObject();
		in.close();
	}
}
