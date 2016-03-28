package types.point2;
import java.awt.Color;
final class PolarPoint {
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
		PolarPoint r1 = new PolarPoint(0,0,Color.RED);
	}
}
