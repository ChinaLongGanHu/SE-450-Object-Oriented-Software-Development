package template.ftoc;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* This example is from Robert C. Martin */
public class Main {
	public static void main(String[] args) {
		(new FtoC()).run();
	}
}
/* public */
abstract class Application {
	private boolean isDone = false;

	protected abstract void init();
	protected abstract void idle();
	protected abstract void cleanup();
	protected boolean done() {
		return isDone;
	}
	protected void setDone() {
		isDone = true;
	}
	public void run() {
		init();
		while (!done())
			idle();
		cleanup();
	}
}
final class FtoC extends Application {
	private BufferedReader br;

	protected void init() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	protected void idle() {
		String fstring = readLine();
		if (fstring == null || fstring.length() == 0) {
			setDone();
		} else {
			double f = Double.parseDouble(fstring);
			double c = 5.0/9.0*(f-32);
			System.out.println("F=" + f + ", C=" + c);
		}
	}
	protected void cleanup() {
		System.out.println("FtoC exit");
	}
	private String readLine() {
		try { return br.readLine(); }
		catch(IOException e) { return null; }
	}
}
