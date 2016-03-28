package serialization.custom;
import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

class IntList3 implements IntList, Externalizable
{
	private static final long serialVersionUID = 2008L;
	private LinkedList<Integer> v;
	//private void setV(LinkedList<Integer> v) { v = v; }

	public IntList3()               { v = new LinkedList<Integer>(); }
	public void addBack(int i)      { v.addLast(i); }
	public void addFront(int i)     { v.addFirst(i); }
	public int removeFront()        { return v.removeFirst(); }
	public int removeBack()         { return v.removeLast(); }
	public boolean isEmpty()        { return v.size() == 0; }

	public void writeExternal(ObjectOutput out) throws IOException
	{
		System.out.println("call specialized writer");
		out.writeInt(v.size());
		for (Integer i : v )
			out.writeInt(i);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
	{
		System.out.println("call specialized reader");
		v = new LinkedList<Integer>();
		int size = in.readInt();
		for (int i = 0;  i<size; i++)
			addBack(in.readInt());
	}
}

public class Main3 {
	public static void main(String[] args)
			throws IOException, FileNotFoundException, ClassNotFoundException
	{
		IntList L = new IntList3();
		for (int i=0; i<1000; i++)
			L.addFront(i);

		ObjectOutputStream os = new ObjectOutputStream (new FileOutputStream("out3.dat"));
		os.writeObject(L);
		os.flush();
		os.close();

		ObjectInputStream in = new ObjectInputStream (new FileInputStream("out3.dat"));
		IntList V = (IntList) in.readObject();
		in.close();
	}
}
