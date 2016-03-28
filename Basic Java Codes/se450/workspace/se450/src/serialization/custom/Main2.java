package serialization.custom;
import java.util.LinkedList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

class IntList2 implements IntList
{
	private LinkedList<Integer> v;
	//private void setV(LinkedList<Integer> v) { v = v; }

	public IntList2()               { v = new LinkedList<Integer>(); }
	public void addBack(int i)      { v.addLast(i); }
	public void addFront(int i)     { v.addFirst(i); }
	public int removeFront()        { return v.removeFirst(); }
	public int removeBack()         { return v.removeLast(); }
	public boolean isEmpty()        { return v.size() == 0; }

	private void writeObject(ObjectOutputStream out) throws IOException
	{ System.out.println("call specialized writer");
	out.writeInt(v.size());
	for (Integer i : v )
		out.writeInt(i);
	}

	private void readObject(ObjectInputStream in) throws IOException
	{ System.out.println("call specialized reader");
	v = new LinkedList<Integer>();
	int size = in.readInt();
	for (int i = 0;  i<size; i++)
		addBack(in.readInt());
	}
}

public class Main2 {
	public static void main(String[] args)
			throws IOException, FileNotFoundException, ClassNotFoundException
	{
		IntList L = new IntList2();
		for (int i=0; i<1000; i++)
			L.addFront(i);

		ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("out2.dat"));
		os.writeObject(L);
		os.flush();
		os.close();

		ObjectInputStream in = new ObjectInputStream (new FileInputStream("out2.dat"));
		IntList V = (IntList) in.readObject();
		in.close();
	}
}
