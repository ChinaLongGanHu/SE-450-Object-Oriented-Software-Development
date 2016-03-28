package agent;

import java.util.Observable;
import java.util.PriorityQueue;

public final class TimeServerQueue extends Observable implements TimeServer {
	private static final class Node implements Comparable<Node> {
		final double waketime;
		final Agent agent;
		public Node(double waketime, Agent agent) {
			this.waketime = waketime;
			this.agent = agent;
		}
		public int compareTo(Node that) {
			return (int) (Math.signum(this.waketime - that.waketime));
		}
	}
	private double currentTime;
	private PriorityQueue<Node> queue;

	public TimeServerQueue() {
		queue = new PriorityQueue<Node>();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		String sep = "";
		Node[] nodes = queue.toArray(new Node[0]);
		java.util.Arrays.sort(nodes);
		for (Node node : nodes) {
			sb.append(sep).append("(").append(node.waketime).append(",")
			.append(node.agent).append(")");
			sep = ";";
		}
		sb.append("]");
		return (sb.toString());
	}

	public double currentTime() {
		return currentTime;
	}

	public void enqueue(double waketime, Agent agent)
			throws IllegalArgumentException
	{
		if (waketime < currentTime)
			throw new IllegalArgumentException();
		queue.add(new Node(waketime, agent));
	}

	Agent dequeue()
	{
		return queue.remove().agent;
	}

	int size() {
		return queue.size();
	}

	boolean empty() {
		return queue.isEmpty();
	}

	public void run(double duration) {
		double endtime = currentTime + duration;
		while ((!empty()) && (queue.peek().waketime <= endtime)) {
			if ((currentTime - queue.peek().waketime) < 1e-09) {
				super.setChanged();
				super.notifyObservers();
			}
			currentTime = queue.peek().waketime;
			dequeue().run();
		}
		currentTime = endtime;
	}
}