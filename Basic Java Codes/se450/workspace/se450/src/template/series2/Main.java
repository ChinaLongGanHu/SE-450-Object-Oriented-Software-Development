package template.series2;
import enumeration2.Op;
interface Series {
	void next();
	void print();
}
class SeriesFactory {
	private SeriesFactory() {}
	public static Series newArith() { return new ConcreteSeries(Op.ADD); }
	public static Series newGeom()  { return new ConcreteSeries(Op.MUL); }
}
class ConcreteSeries implements Series {
	int x;
	int y = 1;
	public void next()  { x++; y = op.eval(y,2); }
	public void print() { System.out.println("x=" + x + "; y=" + y); }
	Op op;
	ConcreteSeries(Op op) { this.op = op; }
}

public class Main {
	public static void main(String[] args) {
		Series x = SeriesFactory.newGeom();
		x.next();
		x.next();
		x.print();
	}
}
