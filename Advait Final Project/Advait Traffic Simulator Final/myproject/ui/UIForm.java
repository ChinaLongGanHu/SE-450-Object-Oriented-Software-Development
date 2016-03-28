package myproject.ui;

/**
 * @see UIFormBuilder
 */
public final class UIForm {
	private final String heading;
	private final Pair[] form;

	static final class Pair {
		final String prompt;
		final UIFormTest test;

		Pair(String thePrompt, UIFormTest theTest) {
			prompt = thePrompt;
			test = theTest;
		}
	}

	UIForm(String heading, Pair[] menu) {
		this.heading = heading;
		form = menu;
	}

	public int size() {
		return form.length;
	}

	public String getHeading() {
		return heading;
	}

	public String getPrompt(int i) {
		return form[i].prompt;
	}

	public boolean checkInput(int i, String input) {
		if (null == form[i])
			return true;
		return form[i].test.run(input);
	}
}
