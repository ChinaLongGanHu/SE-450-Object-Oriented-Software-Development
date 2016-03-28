package myproject.ui;

public class UIFactory 
{
	
	private UIFactory() {}
	
	static private UI popupUI = new PopupUI();
	static private UI textUI = new TextUI();
	
	/*
	static public UI ui () 
	{
		return UI;
	}
	*/
	
	static public UI popupUi() 
	{
		return popupUI;
	}

	static public UI textUI() 
	{
		return textUI;
	}
}
