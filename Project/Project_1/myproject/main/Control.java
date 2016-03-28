package myproject.main;

import myproject.model.MP;
import myproject.model.Model;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.model.text.TextAnimatorBuilder;
import myproject.ui.UI;
import myproject.ui.UIError;
import myproject.ui.UIForm;
import myproject.ui.UIFormBuilder;
import myproject.ui.UIFormTest;
import myproject.ui.UIMenu;
import myproject.ui.UIMenuAction;
import myproject.ui.UIMenuBuilder;

public class Control 
{
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int EDIT = 3;
	private static final int NUM_stateS = 10;

	private int _state;
	private UIMenu[] menus;
	private UIFormTest numberTest;
	private UIFormTest patternTest;
	private UIFormTest integerTest;

	private UIForm getNum;
	private UIForm getPattern;
	private UIForm getMinMax;
	private UIForm getRowCol;

	private UI ui;

	Control(UI mYui) 
	{
		ui = mYui;
		menus = new UIMenu[NUM_stateS];
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
				ui.processMenu(menus[_state]);
			}
		} catch (UIError e) {
			ui.displayError("UI issue");
		}
	}

	private void addExit(int state) {
		UIMenuBuilder menu = new UIMenuBuilder();
		menu.add("Default", new UIMenuAction() {

			public void run() {
				ui.displayError("Invalid Selection");
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

	private void addStart(int state) {
		UIMenuBuilder menu = new UIMenuBuilder();
		menu.add("Default", new UIMenuAction() {
			public void run() {
				ui.displayError("Invalid Selection");
			}
		});
		menu.add("Run Simulation", new UIMenuAction() {
			public void run() 
			{
				/*
				AnimatorBuilder build = new SwingAnimatorBuilder();
				Model model = new Model(build, MP.getGridRows(), MP
						.getGridColumns());
				model.run((int) MP.getRuntime());
				model.dispose();
				//TimeServerLinked.getServer().reset();
				 * 
				 */
				{
					Model m = new Model(new TextAnimatorBuilder(), 0, 1);
					m.run(10);
					m.dispose();
				}
				{
					Model m = new Model(new SwingAnimatorBuilder(), 0, 1);
					m.run(10);
					m.dispose();
				}
				{
					Model m = new Model(new TextAnimatorBuilder(), 1, 1);
					m.run(10);
					m.dispose();
				}
				{
					Model m = new Model(new SwingAnimatorBuilder(), 1, 1);
					m.run(10);
					m.dispose();
				}
				{
					Model m = new Model(new SwingAnimatorBuilder(), 2, 3);
					m.run(500);
					m.run(500);
					m.dispose();
				}
				System.exit(0);
				

			}
		});
		menu.add("Change Simulation Parameter", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		menu.add("Exit", new UIMenuAction() 
		{
			public void run() 
			{
				_state = EXIT;
			}
		});
		menus[state] = menu.toUIMenu("My Traffic Simulation");
	}

	private void addEdit(int state) 
	{
		UIMenuBuilder menu = new UIMenuBuilder();
		// 0
		menu.add("Default", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayError("Invalid Selection");
			}
		});
		// 1
		menu.add("Show current values", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 2
		menu.add("Simulation time step", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 3
		menu.add("Simulation runtime", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 4
		menu.add("Grid size", new UIMenuAction() 
		{
			public void run() 
			{
				//ui.displayMessage("Not Ready Yet");
				String input[] = ui.processForm(getRowCol);
				MP.setGridRows(Integer.parseInt((input[0])));
				MP.setGridColumns(Integer.parseInt((input[1])));

			}
		});
		// 5
		menu.add("Set traffic pattern", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");

			}
		});
		// 6
		menu.add("Set car entry rate", new UIMenuAction() 
		{
			public void run() 
			{
				//ui.displayMessage("Not Ready Yet");
				String input[] = ui.processForm(getMinMax);
				MP.setCarEntryRateMin(Double.parseDouble(input[0]));
				MP.setCarEntryRateMax(Double.parseDouble(input[1]));
				
			}
		});
		//7
		menu.add("Set road length", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");

			}
		});
		// 8
		menu.add("Set intersection length", new UIMenuAction() 
		{
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 9
		menu.add("Set car length", new UIMenuAction() {
			public void run() 
			{
				//ui.displayMessage("Not Ready Yet");
				String input[] = ui.processForm(getMinMax);
				MP.setCarLengthMin(Double.parseDouble(input[0]));
				MP.setCarLengthMax(Double.parseDouble(input[1]));
			}
		});
		// 10
		menu.add("Set max car velocity", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
				
			}
		});
		// 11
		menu.add("Set car stop distance", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 12
		menu.add("Set car break distance", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 13
		menu.add("Set traffic light green times", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 14
		menu.add("Set traffic light yellow times", new UIMenuAction() {
			public void run() 
			{
				ui.displayMessage("Not Ready Yet");
			}
		});
		// 15
		menu.add("Reset simulation and return to the main menu",
				new UIMenuAction() {
					public void run() {
						_state = EXIT;
					}
				});
		// 16
		menu.add("Return to main menu",
				new UIMenuAction() {
					public void run() 
					{
						_state = EXIT;
					}
				});
		menus[_state] = menu.toUIMenu("Welcome Bud !!");
	}
}