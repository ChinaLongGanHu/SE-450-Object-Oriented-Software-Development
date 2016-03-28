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

	private int _state;
	private UIMenu[] menus;
	private UIFormTest numberTest;
	private UIFormTest patternTest;
	private UIFormTest integerTest;

	private UIForm getNum;
	private UIForm getPattern;
	private UIForm getMinMax;
	private UIForm getRowCol;

	private UI _ui;

	SimRunnerMain(UI ui) {
		_ui = ui;
		menus = new UIMenu[NUMSTATES];
		_state = START;
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
				return (input.equalsIgnoreCase("simple") || input
						.equalsIgnoreCase("alternating"));
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

		UIFormBuilder form = new UIFormBuilder();
		form.add("Minimum", numberTest);
		form.add("Maximum", numberTest);
		getMinMax = form.toUIForm("Please enter range:");
		form = new UIFormBuilder();
		form.add("Value", numberTest);
		getNum = form.toUIForm("Please enter value:");
		form = new UIFormBuilder();
		form.add("Traffic Pattern", patternTest);
		getPattern = form.toUIForm("Enter pattern(alternating or simple):");
		form = new UIFormBuilder();
		form.add("Rows:", integerTest);
		form.add("Columns:", integerTest);
		getRowCol = form.toUIForm("Please enter Row/Col");
	}

	void run() {
		try {
			while (_state != EXITED) {
				_ui.processMenu(menus[_state]);
			}
		} catch (UIError e) {
			_ui.displayError("UI issue");
		}
	}

	private void addExit(int state) {
		UIMenuBuilder menu = new UIMenuBuilder();
		menu.add("Default", new UIMenuAction() {

			public void run() {
				_ui.displayError("Invalid Selection");
			}

		});
		menu.add("Yes", new UIMenuAction() {
			public void run() {
				_state = EXITED;
			}
		});
		menu.add("No", new UIMenuAction() {
			public void run() {
				_state = START;
			}
		});
		menus[state] = menu.toUIMenu("Are you sure you want to exit?");
	}

	private void addStart(int state) 
	{
		UIMenuBuilder menu = new UIMenuBuilder();
		
		menu.add("Default", new UIMenuAction() 
		{
			public void run() {
				_ui.displayError("Invalid Selection");
			}
		});
		
		menu.add("Run Simulation", new UIMenuAction() {
			public void run() {

				AnimatorBuilder build = new SwingAnimatorBuilder();
				Model model = new Model(build, MP.getGridRows(), MP
						.getGridColumns());
				model.run((int) MP.getRuntime());
				model.dispose();
				CarServerEdge.getServer().reset();

			}
		});
		
		menu.add("Change simulation parameters", new UIMenuAction() {
			public void run() {
				_state = EDIT;
			}
		});
		
		menu.add("Exit", new UIMenuAction() {
			public void run() {
				_state = EXIT;
			}
		});
		menus[state] = menu.toUIMenu("Advait's Traffice Simulation.");
	}

	private void addEdit(int state) {
		UIMenuBuilder menu = new UIMenuBuilder();
		
		// 0
		menu.add("Default", new UIMenuAction() {
			public void run() {
				_ui.displayError("Invalid Selection");
			}
		});
		
		// 1
		menu.add("Show current values", new UIMenuAction() {
			public void run() {
				_ui.displayMessage(MP.output());
			}
		});
		
		// 2
		menu.add("Simulation time step", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getNum);
				MP.setTimeStep(Double.parseDouble(input[0]));
			}
		});
		
		// 3
		menu.add("Simulation run time", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getNum);
				MP.setRuntime(Double.parseDouble(input[0]));
			}
		});
		
		// 4
		menu.add("Grid size", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getRowCol);
				MP.setGridRows(Integer.parseInt((input[0])));
				MP.setGridColumns(Integer.parseInt((input[1])));
				

			}
		});
		
		// 5
		menu.add("Traffic pattern", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getPattern);
				boolean answer = true;
				if (input[0].equalsIgnoreCase("simple"))
					answer = false;
				MP.setTrafficPattern(answer);

			}
		});
		
		// 6
		menu.add("Car entry rate", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setCarEntryRateMin(Double.parseDouble(input[0]));
				MP.setCarEntryRateMax(Double.parseDouble(input[1]));

			}
		});
		
		//7 
		menu.add("Road segment length", new UIMenuAction() 
		{ 
			public void run()
			{ 
				String input[] = _ui.processForm(getMinMax);
				MP.setRoadSegmentLengthMin( Double.parseDouble(input[0]));
				MP.setRoadSegmentLengthMax( Double.parseDouble(input[1]));
			} 
		});
		
		
		// 8
		menu.add("Intersection length", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setIntersectionLengthMin(Double.parseDouble(input[0]));
				MP.setIntersectionLengthMax(Double.parseDouble(input[1]));
			}
		});
		
		// 9
		menu.add("Car length", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setCarLengthMin(Double.parseDouble(input[0]));
				MP.setCarLengthMax(Double.parseDouble(input[1]));
			}
		});
		
		// 10
		menu.add("Car maximum velocity", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setMaxVelocityMin(Double.parseDouble(input[0]));
				MP.setMaxVelocityMax(Double.parseDouble(input[1]));
			}
		});
		
		// 11
		menu.add("Car stop distance", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setCarStopDistanceMin(Double.parseDouble(input[0]));
				MP.setCarStopDistanceMax(Double.parseDouble(input[1]));
			}
		});
		
		// 12
		menu.add("Car break distance", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setBrakeDistanceMin(Double.parseDouble(input[0]));
				MP.setBrakeDistanceMax(Double.parseDouble(input[1]));
			}
		});
		
		// 13
		menu.add("Traffic light green time", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setBrakeDistanceMin(Double.parseDouble(input[0]));
				MP.setBrakeDistanceMax(Double.parseDouble(input[1]));
			}
		});
		
		// 14
		menu.add("Traffic light yellow time", new UIMenuAction() {
			public void run() {
				String input[] = _ui.processForm(getMinMax);
				MP.setYellowTimeMin(Double.parseDouble(input[0]));
				MP.setYellowTimeMax(Double.parseDouble(input[1]));
			}
		});
		
		// 15
		menu.add("Reset simulation and return to the main menu",
				new UIMenuAction() {
					public void run() 
					{
						_ui.displayError("RESET method is not ready");
					}
		});
		
		// 16
		menu.add("Return to the main menu",
				new UIMenuAction() {
					public void run() {
						_state = START;
					}
		});
		menus[state] = menu.toUIMenu("Parameters to edit");
	}
}