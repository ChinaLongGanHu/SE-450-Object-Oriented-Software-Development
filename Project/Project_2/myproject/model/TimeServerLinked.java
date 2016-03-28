package myproject.model;

import javax.xml.soap.Node;


public final class TimeServerLinked implements TimeServerInterface 
{
	
	private double currentTime;
	private int size;
	private Node head;

	private volatile static TimeServerInterface timeServerQueue;
	
	TimeServerLinked() 
	{
		
	}
	
	// This is a Double-Checked locking Singleton
	public static TimeServerInterface getServer() 
	{
		if (timeServerQueue == null) 
		{
			synchronized (TimeServerLinked.class) 
			{
				if (timeServerQueue == null) 
				{
					timeServerQueue = new TimeServerLinked();
				}
			}
		}
		return timeServerQueue;
	}

	@Override
	public double currentTime() {
		// TODO Auto-generated method stub
		return currentTime;
	}

	@Override
	public void enqueue(double waketime, Agent thing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(double duration) {
		// TODO Auto-generated method stub
		
	}
}
