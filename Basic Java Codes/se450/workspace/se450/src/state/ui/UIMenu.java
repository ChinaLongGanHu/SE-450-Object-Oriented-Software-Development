package state.ui;

/**
 * @see UIMenuBuilder
 */
public final class UIMenu {
	private final String heading;
	private final Pair[] menu;

	static final class Pair {
		final String prompt;
		final UIMenuAction action;

		Pair(String thePrompt, UIMenuAction theAction) {
			prompt = thePrompt;
			action = theAction;
		}
	}

	UIMenu(String heading, Pair[] menu) {
		this.heading = heading;
		this.menu = menu;
	}
	public int size() {
		return menu.length;
	}
	public String getHeading() {
		return heading;
	}
	public String getPrompt(int i) {
		return menu[i].prompt;
	}
	public Object runAction(int i) {
		return menu[i].action.run();
	}
}
