package strategy.ftoc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* This example is from Robert C. Martin */
public class Main {
	public static void main(String[] args) {
		(new App(new FtoC())).run();
	}
}
final class App {
	private AppStrategy s;
	public App(AppStrategy s) {
		this.s = s;
	}
	public void run() {
		s.init();
		while (!s.done())
			s.idle();
		s.cleanup();
	}
}
/* public */
interface AppStrategy {
	public void init();
	public void idle();
	public void cleanup();
	public boolean done();
}
final class FtoC implements AppStrategy {
	private boolean isDone = false;
	private BufferedReader br;

	public boolean done() {
		return isDone;
	}
	public void init() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	public void idle() {
		String fstring = readLine();
		if (fstring == null || fstring.length() == 0) {
			isDone = true;
		} else {
			double f = Double.parseDouble(fstring);
			double c = 5.0/9.0*(f-32);
			System.out.println("F=" + f + ", C=" + c);
		}
	}
	public void cleanup() {
		System.out.println("FtoC exit");
	}
	private String readLine() {
		try { return br.readLine(); }
		catch(IOException e) { return null; }
	}
}
