package types.point4;
import java.awt.Color;
interface Colored {
	Color getColor();
}
interface Point {
	double getX();
	double getY();
}
final class CartesianPoint implements Colored, Point {
	
	private final double x;
	private final double y;
	private final Color color;
	public CartesianPoint(double x, double y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	public double getX() { return x; }
	public double getY() { return y; }
	public Color getColor() {
		System.out.println("It's Cartesian!");
		return color;
	}
	
}
final class PolarPoint implements Colored, Point {
	
	private final double theta;
	private final double r;
	private final Color color;
	public PolarPoint(double theta, double r, Color color) {
		this.theta = theta;
		this.r = r;
		this.color = color;
	}
	public double getX() { return r*Math.cos(theta); }
	public double getY() { return r*Math.sin(theta); }
	public Color getColor() {
		System.out.println("It's Polar!");
		return color;
	}
	
}
public class Main {
	private Main() {}
	public static void main(String[] args) {
		CartesianPoint p1 = new CartesianPoint(0,0,Color.RED);
		CartesianPoint q1 = new CartesianPoint(1,1,Color.BLUE);
		PolarPoint r1 = new PolarPoint(0,0,Color.RED);

		Point p2 = p1;
		Point q2 = q1;
		Point r2 = r1;

		Colored p3 = p1;
		Colored q3 = q1;
		Colored r3 = r1;
	}
}
