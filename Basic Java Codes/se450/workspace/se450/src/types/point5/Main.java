package types.point5;
import java.awt.Color;
interface Colored {
	Color getColor();
}
interface Point {
	double getX();
	double getY();
}
interface ColoredPoint
extends Colored, Point
{ }
final class CartesianPoint implements ColoredPoint {
	
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
final class PolarPoint implements ColoredPoint {
	
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
	public static void printColoredPoint(ColoredPoint cp) {
		System.out.println(cp.getX());
		System.out.println(cp.getY());
		System.out.println(cp.getColor());
	}
	public static void main(String[] args) {
		printColoredPoint(new CartesianPoint(0,0,Color.RED));
		printColoredPoint(new PolarPoint(0,0,Color.RED));
	}
}
