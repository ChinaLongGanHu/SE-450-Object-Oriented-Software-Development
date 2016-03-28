package agent.three;
import agent.Agent;
import agent.TimeServer;
import agent.TimeServerLinked;
import java.util.Observer;

public class Main {
	public static void main (String[] args) {
		World w = World.instance;
		Agent a = new Tiger();
		w.enqueue(0,a);
		w.set(0,0,a);
		w.run(100);
	}
}

class Util {
	private Util() {}
	private final static long SEED = 2497;
	private final static java.util.Random r = new java.util.Random(SEED);
	static int random(int n) { return r.nextInt(n); }
	static boolean randomBoolean() { return r.nextBoolean(); }
}

interface World extends TimeServer {
	public static final World instance = new WorldObj();
	public int maxx();
	public int maxy();
	public Object get(int i, int j);
	public void set(int i, int j, Object a);
}

class WorldObj implements World {
	private final static int MAXX = 100;
	private final static int MAXY = 100;
	private TimeServer time = new TimeServerLinked();
	private Object[][] space = new Object[MAXX][MAXY];

	public int maxx()                      { return MAXX; }
	public int maxy()                      { return MAXY; }
	public Object get(int x, int y)        { return space[x%MAXX][y%MAXY]; }
	public void set(int x, int y, Object a){ space[x%MAXX][y%MAXY] = a; }
	public double currentTime()            { return time.currentTime(); }
	public void enqueue(double t, Agent a) { time.enqueue(t,a); }
	public void run(double d)              { time.run(d); }
	public void addObserver(Observer o)    { time.addObserver(o); }
	public void deleteObserver(Observer o) { time.deleteObserver(o); }
}

/* BUGS HERE */
class Tiger implements Agent {
	private int x;
	private int y;
	private World w = World.instance;
	private void moveRandom() {
		w.set(x,y,null);
		if (Util.randomBoolean()) { x = (x+1)%w.maxx(); }
		if (Util.randomBoolean()) { y = (y+1)%w.maxy(); }
		w.set(x,y,this);
	}
	public void run() {
		moveRandom();
		System.out.println("It's " + w.currentTime() + " and I'm alive at (" + x + "," + y + ")!");
		w.enqueue(10+w.currentTime(), this);
	}

}
