package myproject.model;

public interface CarServerInterface {
	public double currentTime();

	public void enqueue(double waketime, Agent thing);

	public void reset();

	public void run(double duration);
}
