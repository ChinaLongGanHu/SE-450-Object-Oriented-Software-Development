package myproject.main;

import myproject.model.AnimatorBuilder;
import myproject.model.CarServerEdge;
import myproject.model.MP;
import myproject.model.Model;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.ui.UI;
import myproject.ui.UIError;
import myproject.ui.UIForm;
import myproject.ui.UIFormBuilder;
import myproject.ui.UIFormTest;
import myproject.ui.UIMenu;
import myproject.ui.UIMenuAction;
import myproject.ui.UIMenuBuilder;

public class SimRunnerMain {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int EDIT = 3;
	private static final int NUMSTATES = 10;

	private int myState;
	private UIMenu[] myMenus;
	private UIFormTest numberTest;
	private UIFormTest patternTest;
	private UIFormTest integerTest;

	private UIForm getNum;
	private UIForm getPattern;
	private UIForm getMinMax;
	private UIForm getRowCol;

	private UI ui;

	SimRunnerMain(UI ui) {
		this.ui = ui;
		myMenus = new UIMenu[NUMSTATES];
		myState = START;
		addStart(START);
		addEdit(EDIT);
		addExit(EXIT);

		numberTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					Double.parseDouble(input);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		patternTest = new UIFormTest() {
			public boolean run(String input) {
				return (input.equalsIgnoreCase("SIMPLE") || input
						.equalsIgnoreCase("ALTERNATING"));
			}
		};
		integerTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					Integer.parseInt(input);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};

		UIFormBuilder formBuilder = new UIFormBuilder();
		formBuilder.add("MINIMUM", numberTest);
		formBuilder.add("MAXIMUM", numberTest);
		getMinMax = formBuilder.toUIForm("ENTER RANGE:");
		formBuilder = new UIFormBuilder();
		formBuilder.add("VALUE", numberTest);
		getNum = formBuilder.toUIForm("ENTER VALUE:");
		formBuilder = new UIFormBuilder();
		formBuilder.add("TRAFFIC PATTERN", patternTest);
		getPattern = formBuilder.toUIForm("PATTERN (ALTERNATING or SIMPLE):");
		formBuilder = new UIFormBuilder();
		formBuilder.add("ROWS ", integerTest);
		formBuilder.add("COLUMNS ", integerTest);
		getRowCol = formBuilder.toUIForm("ENTER ROW | COLUMN");
	}

	void run() {
		try {
			while (myState != EXITED) {
				ui.processMenu(myMenus[myState]);
			}
		} catch (UIError e) {
			ui.displayError("UI issue");
		}
	}

	private void addExit(int state) {
		UIMenuBuilder DASHBOARD = new UIMenuBuilder();
		DASHBOARD.add("DEFAULT", new UIMenuAction() {

			public void run() {
				ui.displayError("INVALID ENTRY");
			}

		});
		DASHBOARD.add("YES", new UIMenuAction() {
			public void run() {
				myState = EXITED;
			}
		});
		DASHBOARD.add("NO", new UIMenuAction() {
			public void run() {
				myState = START;
			}
		});
		myMenus[state] = DASHBOARD.toUIMenu("ARE YOU SURE TO EXIT?");
	}

	private void addStart(int state) {
		UIMenuBuilder DASHBOARD = new UIMenuBuilder();
		DASHBOARD.add("Default", new UIMenuAction() {
			public void run() {
				ui.displayError("INVALID SELECTION");
			}
		});
		DASHBOARD.add("Run Simulation", new UIMenuAction() {
			public void run() {

				AnimatorBuilder build = new SwingAnimatorBuilder();
				Model model = new Model(build, MP.getGridvarGridRows(), MP
						.getGridvarGridColumns());
				model.run((int) MP.getvarRuntime());
				model.dispose();
				CarServerEdge.getServer().reset();

			}
		});
		DASHBOARD.add("Edit default simulation parameters", new UIMenuAction() {
			public void run() {
				myState = EDIT;
			}
		});
		DASHBOARD.add("See Current Values", new UIMenuAction() {
			public void run() {
				ui.displayMessage(MP.displayValues());
			}
		});
		DASHBOARD.add("About Developer", new UIMenuAction() {
			public void run() {

				ui.displayMessage(MP.aboutAuthor());

			}
		});
		DASHBOARD.add("Exit", new UIMenuAction() {
			public void run() {
				myState = EXIT;
			}
		});
		myMenus[state] = DASHBOARD.toUIMenu("Chicago Traffic Simulation");
	}

	private void addEdit(int state) {
		UIMenuBuilder DASHBOARD = new UIMenuBuilder();
		// 0
		DASHBOARD.add("Default", new UIMenuAction() {
			public void run() {
				ui.displayError("We have 1-15 options");
			}
		});
		// 1 Show current values
		DASHBOARD.add("Show current values", new UIMenuAction() {
			public void run() {
				ui.displayMessage(MP.displayValues());
			}
		});
		// 2 Simulation time step
		DASHBOARD.add("Simulation time step", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getNum);
				MP.setvarTimeStep(Double.parseDouble(input[0]));
			}
		});
		// 3 Simulation run time
		DASHBOARD.add("Simulation run time", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getNum);
				MP.setvarRuntime(Double.parseDouble(input[0]));
			}
		});
		// 4 Grid size
		DASHBOARD.add("Grid size", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getRowCol);
				MP.setGridvarGridRows(Integer.parseInt((input[0])));
				MP.setGridvarGridColumns(Integer.parseInt((input[1])));

			}
		});
		// 5 Traffic pattern
		DASHBOARD.add("Traffic pattern - Alternating or Simple",
				new UIMenuAction() {
					public void run() {
						String input[] = ui.processForm(getPattern);
						boolean answer = true;
						if (input[0].equalsIgnoreCase("Alternating"))
							answer = false;
						MP.setTrafficPattern(answer);

					}
				});

		// 6 Car entry rate
		DASHBOARD.add("Car entry rate", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarEntryRateMin(Double.parseDouble(input[0]));
				MP.setvarEntryRateMax(Double.parseDouble(input[1]));

			}
		});
		// 7 Road segment length
		DASHBOARD.add("Road segment length", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setRoadSegmentLengthMin(Double.parseDouble(input[0]));
				MP.setRoadSegmentLengthMax(Double.parseDouble(input[1]));
			}
		});
		// 8 Intersection length
		DASHBOARD.add("Intersection length", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarIntersectionLengthMin(Double.parseDouble(input[0]));
				MP.setvarIntersectionLengthMax(Double.parseDouble(input[1]));
			}
		});
		// 9 Car length
		DASHBOARD.add("Car length", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarCarLengthMin(Double.parseDouble(input[0]));
				MP.setvarCarLengthMax(Double.parseDouble(input[1]));
			}
		});
		// 10 Car maximum velocity
		DASHBOARD.add("Car maximum velocity", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarMaximumVelocityMin(Double.parseDouble(input[0]));
				MP.setvarMaximumVelocityMax(Double.parseDouble(input[1]));
			}
		});
		// 11 Car stop distance
		DASHBOARD.add("Car stop distance", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setCarvarCarStopDistanceMin(Double.parseDouble(input[0]));
				MP.setCarvarCarStopDistanceMax(Double.parseDouble(input[1]));
			}
		});
		// 12 Car brake distance
		DASHBOARD.add("Car break distance", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarCarBreakDistanceMin(Double.parseDouble(input[0]));
				MP.setvarCarBreakDistanceMax(Double.parseDouble(input[1]));
			}
		});
		// 13 Traffic light green time
		DASHBOARD.add("Traffic light green time", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setvarGreenTrafficLightMin(Double.parseDouble(input[0]));
				MP.setvarGreenTrafficLightMax(Double.parseDouble(input[1]));
			}
		});
		// 14 Traffic light yellow time
		DASHBOARD.add("Traffic light yellow time", new UIMenuAction() {
			public void run() {
				String input[] = ui.processForm(getMinMax);
				MP.setyellowMin(Double.parseDouble(input[0]));
				MP.setyellowMax(Double.parseDouble(input[1]));
			}
		});
		// 15 Reset simulation and return to the main menu
		DASHBOARD.add("Reset simulation and return to the main menu",
				new UIMenuAction() {
					public void run() {
						// MP.reset();
						myState = START;
					}
				});

		myMenus[state] = DASHBOARD.toUIMenu("Parameters to edit");
	}
}