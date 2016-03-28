package state.ui;

public class UIFactory {
	private UIFactory() {}
	static private TextUI UI = new TextUI();
	static public UI ui () {
		return UI;
	}
}
