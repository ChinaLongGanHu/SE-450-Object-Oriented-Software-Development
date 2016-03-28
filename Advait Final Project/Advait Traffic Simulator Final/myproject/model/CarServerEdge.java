package myproject.model;

import java.util.NoSuchElementException;

public final class CarServerEdge implements CarServerInterface {

	private static final class Service {

		final double earlyThrough;
		final Agent myAgent;
		Service upComingRoute;

		public Service(double earlyThrough, Agent myAgent, Service upComingRoute) {
			this.earlyThrough = earlyThrough;
			this.myAgent = myAgent;
			this.upComingRoute = upComingRoute;
		}
	}

	private static double currentTime;
	private static int size;
	private static Service head = new Service(0, null, null);
	private volatile static CarServerInterface timeServerQueue;

	public static CarServerInterface getServer() {
		if (timeServerQueue == null) {
			synchronized (CarServerEdge.class) {
				if (timeServerQueue == null) {
					timeServerQueue = new CarServerEdge();
				}
			}
		}
		return timeServerQueue;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Service node = head.upComingRoute;
		String sep = "";
		while (node != null) {
			sb.append(sep).append("(").append(node.earlyThrough).append(",")
					.append(node.myAgent).append(")");
			node = node.upComingRoute;
			sep = ";";
		}
		sb.append("]");
		return (sb.toString());
	}

	public double currentTime() {
		return currentTime;
	}

	public void enqueue(double earlyThrough, Agent myAgent)
			throws IllegalArgumentException {
		if (earlyThrough < currentTime) {
			throw new IllegalArgumentException("Oppps !! Something went wrong");
		}
		Service prevElement = head;
		while ((prevElement.upComingRoute != null)
				&& (prevElement.upComingRoute.earlyThrough <= earlyThrough)) {
			prevElement = prevElement.upComingRoute;
		}
		Service newElement = new Service(earlyThrough, myAgent,
				prevElement.upComingRoute);
		prevElement.upComingRoute = newElement;
		size++;
	}

	Agent dequeue() {
		if (size < 1) {
			throw new NoSuchElementException();
		} else {
			Agent realValue = head.upComingRoute.myAgent;
			head.upComingRoute = head.upComingRoute.upComingRoute;
			size--;
			return realValue;
		}
	}

	int size() {
		return size;
	}

	boolean empty() {
		return size() == 0;
	}

	public void reset() {
		timeServerQueue = null;
		head = new Service(0, null, null);
		size = 0;
		currentTime = 0;
	}

	public void run(double duration) {
		double endtime = currentTime + duration;
		while ((!empty()) && (head.upComingRoute.earlyThrough <= endtime)) {
			currentTime = head.upComingRoute.earlyThrough;
			dequeue().run();
		}
		currentTime = endtime;
	}
}
