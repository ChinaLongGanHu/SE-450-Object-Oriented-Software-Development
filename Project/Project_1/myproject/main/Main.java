package myproject.main;

import myproject.ui.UI;

/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */
public class Main {
	private Main() {}
	public static void main(String[] args) 
	{
		/*
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
		*/
		/*
		UI ui = UIFactory.popupUi();
		Control start = new Control(ui);
		start.run();
		System.exit(0);
		*/
		UI ui = null;
		if (args.length > 0) {
			if ("GUI".equalsIgnoreCase(args[0])) {
				ui = new myproject.ui.PopupUI();
			} else if ("TEXT".equalsIgnoreCase(args[0])) {
				ui = new myproject.ui.TextUI();
			} else {
				System.out.println("Argument must be GUI or TEXT");
				System.exit(1);
			}
		} else {
			if (Math.random() <= 0.5) {
				ui = new myproject.ui.TextUI();
			} else {
				ui = new myproject.ui.PopupUI();
			}
		}
		
		Control control = new Control(ui);
		control.run();
	}
}

