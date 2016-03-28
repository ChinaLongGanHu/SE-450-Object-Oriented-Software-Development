package template.series4;

interface Series {
	void next();
	void print();
}
class SeriesFactory {
	private SeriesFactory() {}
	public static Series newArith() { return new ArithSeries(); }
	public static Series newGeom()  { return new GeomSeries(); }
}
abstract class AbstractSeries implements Series {
	int x;
	int y = 1;
	public void next()  { x++; y = this.eval(y,2); }
	public void print() { System.out.println("x=" + x + "; y=" + y); }

	abstract protected int eval(int x, int y);
}

class ArithSeries extends AbstractSeries { protected int eval(int x, int y) { return x+y; } }
class GeomSeries extends AbstractSeries { protected int eval(int x, int y) { return x*y; } }

public class Main {
	public static void main(String[] args) {
		Series x = SeriesFactory.newGeom();
		x.next();
		x.next();
		x.print();
	}
}
