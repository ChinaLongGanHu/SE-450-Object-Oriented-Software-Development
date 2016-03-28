package state.ui.main;

import state.ui.UI;
import state.ui.UIMenu;
import state.ui.UIMenuBuilder;

class Control {
	final State EXITED;
	final State EXIT;
	private State state;
	Control(UI ui) {
		EXITED = new ExitedState();
		EXIT = new ExitState(this,ui);
		state = EXIT;
	}

	void run() {
		while (state != EXITED) {
			state = state.run();
		}
	}
}

interface State {
	public State run();
}

final class ExitedState implements State {
	public State run() {
		return this;
	}
}

final class ExitState implements State {
	Control control;
	UI ui;
	UIMenu m;
	ExitState(Control control, UI ui) {
		this.control = control;
		this.ui = ui;

		UIMenuBuilder mb;
		mb = new UIMenuBuilder();

		//mb.add("Default",	new UIMenuAction() { public Object run() {return this;} });
		mb.add("Default",	() -> this);
		mb.add("Yes", () -> control.EXITED);
		mb.add("No", () -> control.EXIT);
		this.m = mb.toUIMenu("Are you sure you want to exit?");
	}
	public State run() {
		return (State) ui.processMenu(m);
	}
}
