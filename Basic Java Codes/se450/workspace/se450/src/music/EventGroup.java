package music;
import java.util.List;
import java.util.LinkedList;
class EventGroup implements Event {
	List<Event> events = new LinkedList<Event>();
	public void add(Event e) {
		events.add(e);
	}

	public void play() {
		for (Event e : events) {
			e.play();
		}
	}
}
