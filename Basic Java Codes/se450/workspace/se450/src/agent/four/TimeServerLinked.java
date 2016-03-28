package agent.four;

public final class TimeServerLinked implements TimeServer {
	private static final class Node {
		final long waketime;
		final Agent agent;
		Node next;

		public Node(long waketime, Agent agent, Node next) {
			this.waketime = waketime;
			this.agent = agent;
			this.next = next;
		}
	}
	private long currentTime;
	private int size;
	private Node head;

	/*
	 * Invariant: head != null
	 * Invariant: head.agent == null
	 * Invariant: (size == 0) iff (head.next == null)
	 */
	public TimeServerLinked() {
		size = 0;
		head = new Node(0, null, null);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node node = head.next;
		String sep = "";
		while (node != null) {
			sb.append(sep).append("(").append(node.waketime).append(",")
			.append(node.agent).append(")");
			node = node.next;
			sep = ";";
		}
		sb.append("]");
		return (sb.toString());
	}

	public long currentTime() {
		return currentTime;
	}

	public void enqueue(long waketime, Agent agent)
			throws IllegalArgumentException
	{
		if (waketime <= currentTime)
			throw new IllegalArgumentException();
		Node prevElement = head;
		while ((prevElement.next != null) &&
				(prevElement.next.waketime <= waketime)) {
			prevElement = prevElement.next;
		}
		Node newElement = new Node(waketime, agent, prevElement.next);
		prevElement.next = newElement;
		size++;
	}

	Agent dequeue()
			throws IndexOutOfBoundsException
	{
		if (size < 1)
			throw new IndexOutOfBoundsException();
		Agent rval = head.next.agent;
		head.next = head.next.next;
		size--;
		return rval;
	}

	int size() {
		return size;
	}

	boolean empty() {
		return size() == 0;
	}

	public void run(int duration) {
		long endtime = currentTime + duration;
		while ((!empty()) && (head.next.waketime <= endtime)) {
			currentTime = head.next.waketime;

			Node next = head.next;
			while (next!=null && (next.waketime == currentTime)) {
				next.agent.check();
				next=next.next;
			}
			while ((!empty()) && (head.next.waketime == currentTime)) {
				dequeue().run();
			}
		}
		currentTime = endtime;
	}
}
