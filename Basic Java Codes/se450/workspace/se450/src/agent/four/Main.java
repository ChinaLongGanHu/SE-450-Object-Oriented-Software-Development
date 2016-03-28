package agent.four;

public class Main {
	public static void main (String[] args) {
		World w = WorldF.instance(100,100);
		int i = 0;
		while (i<20) {
			try {
				new Tiger
				(Integer.toString(i), Util.random(w.maxx()), Util.random(w.maxy()));
				i++;
			} catch (SpaceOccupiedException e) {
			}
		}
		w.run(1000);
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
	public int maxx();
	public int maxy();
	public Visible get(int i, int j);
	public boolean set(int i, int j, Visible a);
}

class WorldF {
	private static World W;
	public static World instance(int maxx, int maxy) {
		if (W != null)
			throw new IllegalStateException();
		W = new WorldObj(maxx, maxy);
		return W;
	}
	public static World instance() {
		if (W == null)
			throw new IllegalStateException();
		return W;
	}
}

class WorldObj implements World {
	private final int maxx;
	private final int maxy;
	private final TimeServer time;
	private final Visible[][] space;

	WorldObj(int maxx, int maxy) {
		this.maxx = maxx;
		this.maxy = maxy;
		time = new TimeServerLinked();
		space = new Visible[maxx][maxy];
		for (int x = 0; x < maxx; x++ )
			for (int y = 0; y < maxy; y++ )
				space[x][y] = NullVisible.instance;
	}
	public int maxx() { return maxx; }
	public int maxy() { return maxy; }
	public Visible get(int x, int y) {
		return space[(x+maxx)%maxx][(y+maxy)%maxy];
	}
	public boolean set(int x, int y, Visible a){
		if (a == null) {
			a = NullVisible.instance;
		} else if (get(x,y) != NullVisible.instance) {
			return false;
		}
		space[(x+maxx)%maxx][(y+maxy)%maxy] = a;
		return true;
	}
	public long currentTime()              { return time.currentTime(); }
	public void enqueue(long t, Agent a)   { time.enqueue(t,a); }
	public void run(int d)                 { time.run(d); }
}

interface Visible {
	public final static int NULL = 0;
	public final static int TIGER = 1;
	public int type();
}

class NullVisible implements Visible {
	private NullVisible() {}
	public final static Visible instance = new NullVisible();
	public int type() { return Visible.NULL; }
	public String toString() { return "Null"; }
}

class SpaceOccupiedException extends RuntimeException {
	private static final long serialVersionUID = 2008L;
};

class Tiger implements Agent, Visible {
	private String name;
	private int x;
	private int y;
	private World w = WorldF.instance();

	public Tiger(String name, int x, int y)
			throws SpaceOccupiedException
	{
		this.name = name;
		if (!w.set(x,y,this))
			throw new SpaceOccupiedException();
		this.x = x;
		this.y = y;
		w.enqueue(1+w.currentTime(), this);
	}

	public String toString() { return name + "@(" + x + "," + y + ")"; }

	public int type() { return Visible.TIGER; }
	public void check() {
		checkAjacent();
	}
	public void run() {
		//System.out.print(this + " moves to ");
		moveRandom();
		//System.out.println(this);
		w.enqueue(10+w.currentTime(), this);
	}

	private void checkAjacent() {
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (i==0 && j==0)
					continue;
				if (w.get(x+i,y+j).type() == Visible.TIGER)
					System.out.println(this + " roars at " + w.get(x+i,y+j) + " at " + w.currentTime());
			}
		}
	}
	private void moveRandom() {
		w.set(x,y,null);
		int xNew, yNew;
		do {
			xNew = (w.maxx() + this.x -1 + Util.random(2)) % w.maxx();
			yNew = (w.maxy() + this.y -1 + Util.random(2)) % w.maxy();
			//System.out.println("Trying (" + x + "," + y + ")");
		} while (!w.set(xNew,yNew,this));
		this.x = xNew; this.y = yNew;
	}
}
