package types.point1;
import java.awt.Color;
final class CartesianPoint {
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
public class Main {
	private Main() {}
	public static void main(String[] args) {
		CartesianPoint p1 = new CartesianPoint(0,0,Color.RED);
		CartesianPoint q1 = new CartesianPoint(1,1,Color.BLUE);
	}
}
