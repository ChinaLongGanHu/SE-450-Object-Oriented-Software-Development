package stdlib;

import static org.junit.Assert.*;
import org.junit.Test;

public class SubscriptionsTEST {
	public static class Counter {
		private Subscriptions<Counter,String> oh = new Subscriptions<Counter,String>(this);
		public void addSubscriber(Subscriber<Counter,String> subscriber) {
			oh.addSubscriber(subscriber);
		}
		public void notifySubscribers(String data) {
			oh.notifySubscribers(data);
		}

		int j;
		public int get() {
			return j;
		}
		public void inc() {
			j++;
			oh.setChanged();
		}
		public String toString() {
			return "Counter(" + j + ")";
		}
	}

	@Test
	public void testA () {
		Counter c = new Counter();
		c.addSubscriber((sender, data) -> System.out.println ("update(" + sender + "," + data + ")"));
		c.inc();
		c.inc();
		c.notifySubscribers("Dog");
	}
	private String output;
	@Test
	public void testB () {
		Counter c = new Counter();
		c.addSubscriber((sender, data) -> output = ("update(" + sender + "," + data + ")"));
		c.inc();
		c.inc();
		c.notifySubscribers("Dog");
		assertEquals("update(Counter(2),Dog)",output);
	}
}
