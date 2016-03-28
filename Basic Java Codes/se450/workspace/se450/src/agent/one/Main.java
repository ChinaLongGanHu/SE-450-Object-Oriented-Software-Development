package agent.one;
import agent.Agent;
import agent.TimeServer;
import agent.TimeServerLinked;

public class Main {
	public static void main (String[] args) {
		TimeServer time = new TimeServerLinked();
		Agent a = new Tiger(time);
		time.enqueue(0,a);
		time.run(100);
	}
}

class Tiger implements Agent {
	private TimeServer time;
	public Tiger(TimeServer time) { this.time = time; }
	public void run() {
		System.out.println("It's " + time.currentTime() + " and I'm alive!");
		time.enqueue(10+time.currentTime(), this);
	}
}
