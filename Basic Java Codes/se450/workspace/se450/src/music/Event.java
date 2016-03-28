package music;
interface Event {
	public void play();
}
class Note implements Event {
	int d;
	double f;
	public Note(int duration, double factor) {
		this.d = duration;
		this.f = factor;
	}
	public void play() {
		Music.scalePitch(f);
		Music.play(d);
		Music.scalePitch(1.0/f);
	}
}
class Rest implements Event {
	int d;
	public Rest(int duration) {
		d = duration;
	}
	public void play() {
		Music.rest(d);
	}
}
