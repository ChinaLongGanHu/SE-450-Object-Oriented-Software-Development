package template.series1;

interface Series {
	void next();
	void print();
}
class SeriesFactory {
	private SeriesFactory() {}
	public static Series newArith() { return new ArithSeries(); }
	public static Series newGeom()  { return new GeomSeries(); }
}
class ArithSeries implements Series {
	int x;
	int y = 1;
	public void next()  { x++; y = y+2; }
	public void print() { System.out.println("x=" + x + "; y=" + y); }
}
class GeomSeries implements Series {
	int x;
	int y = 1;
	public void next()  { x++; y = y*2; }
	public void print() { System.out.println("x=" + x + "; y=" + y); }
}

public class Main {
	public static void main(String[] args) {
		Series x = SeriesFactory.newGeom();
		x.next();
		x.next();
		x.print();
	}
}
