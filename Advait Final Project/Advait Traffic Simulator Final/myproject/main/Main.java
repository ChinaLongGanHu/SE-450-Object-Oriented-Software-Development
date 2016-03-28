package myproject.main;

import myproject.ui.UI;
import myproject.ui.UIFactory;

/**
 * A static class to demonstrate the visualization aspect of simulation.
 */
public class Main {
	private Main() {
	}

	public static void main(String[] args) {

		UI ui = UIFactory.popupUi();
		SimRunnerMain start = new SimRunnerMain(ui);
		start.run();
		System.exit(0);
	}
}
