package state.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

final class TextUI implements UI {
	final BufferedReader in;
	final PrintStream out;

	TextUI() {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = System.out;
	}

	public void displayMessage(String message) {
		out.println(message);
	}

	public void displayError(String message) {
		out.println(message);
	}

	public Object processMenu(UIMenu menu) {
		out.println(menu.getHeading());
		out.println("Enter choice by number:");

		for (int i = 1; i < menu.size(); i++) {
			out.println("  " + i + ". " + menu.getPrompt(i));
		}

		String responseString;

		try {
			responseString = in.readLine();
		} catch (IOException e) {
			throw new UIError(); // re-throw UIError
		}
		if (responseString == null) {
			throw new UIError(); // input closed
		}

		int selection;
		try {
			selection = Integer.parseInt(responseString, 10);
			if ((selection < 0) || (selection >= menu.size()))
				selection = 0;
		} catch (NumberFormatException e) {
			selection = 0;
		}

		return menu.runAction(selection);
	}

	public String[] processForm(UIForm form) {
		// TODO
		return null;
	}
}
