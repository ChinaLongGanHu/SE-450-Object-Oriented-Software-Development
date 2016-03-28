package music;
class Transpose implements Event {
	Event e;
	double f;
	public Transpose(Event e, double factor) {
		this.e = e;
		this.f = factor;
	}
	public void play() {
		Music.scalePitch(f);
		e.play();
		Music.scalePitch(1.0/f);
	}
}

