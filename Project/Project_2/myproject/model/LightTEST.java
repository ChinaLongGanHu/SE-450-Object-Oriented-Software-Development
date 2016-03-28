package myproject.model;

import junit.framework.TestCase;

public class LightTEST extends TestCase 
{
	public LightTEST() 
	{
		
	}

	double length = 80;

	public void testConstructorAndAttributes() {

		CarFunctionalities light1 = new Light(length);
		CarFunctionalities light2 = GoDownFactory.newLight();
		CarFunctionalities light3 = GoDownFactory.newLight();

		light1.setNextEWRoad(light2);
		light2.setNextEWRoad(light3);

		assertEquals((int) length, (int) light1.getRoadEnd());

		assertNotSame(light1, light2);
		assertSame(light1, light1);
		assertSame(light1.getNextEWRoad(), light2);
		assertSame(light2.getNextEWRoad(), light3);
		assertSame(light3.getNextEWRoad(), null);

		try 
		{
			light1.setNextNSRoad(light2);
		} 
		catch (IllegalArgumentException e) 
		{
			
		}

		try 
		{
			light1.getNextNSRoad();
		} 
		catch (IllegalArgumentException e) 
		{
			
		}

		String a = ((Light) light1).getState();

		boolean redLight = false;
		boolean greenLight = false;

		if (a.equalsIgnoreCase("GREEN"))
		{
			greenLight = true;
		}

		if (a.equalsIgnoreCase("RED"))
		{
			redLight = true;
		}

		assertNotSame(redLight, greenLight);
		
		boolean yellowLight = false;
		
		if (a.equalsIgnoreCase("GREEN"))
		{
			greenLight = true;
		}

		if (a.equalsIgnoreCase("YELLOW"))
		{
			yellowLight = true;
		}

		assertNotSame(yellowLight, greenLight);

	}

	public void testConstructorException() 
	{
		try 
		{
			// -ve value of light not accepted, it should be fail
			CarFunctionalities light = new Light(-1);
			fail();
		} 
		catch (IllegalArgumentException e) 
		{
			
		}
	}
}
