package myproject.model;

import junit.framework.TestCase;

public class LightTEST extends TestCase {
	double length = 750;

	public void testConstructorAndAttributes() {
		CarDirectionInterface light1 = new Light(length);
		CarDirectionInterface light2 = Inventory.newLight();
		CarDirectionInterface light3 = Inventory.newLight();

		String a = ((Light) light1).getState();

		boolean redLight = false;
		boolean greenLight = false;
		boolean yellowLight = false;

		if (a.equalsIgnoreCase("GREEN")) {
			greenLight = true;
		}

		if (a.equalsIgnoreCase("RED")) {
			redLight = true;
		}

		if (a.equalsIgnoreCase("YELLOW")) {
			yellowLight = true;
		}

		assertNotSame(greenLight, yellowLight);
		assertNotSame(greenLight, redLight);
		assertNotSame(yellowLight, greenLight);
		assertNotSame(yellowLight, redLight);
		assertNotSame(redLight, greenLight);
		assertNotSame(redLight, yellowLight);
		assertSame(greenLight, a.equalsIgnoreCase("GREEN"));
		assertSame(yellowLight, a.equalsIgnoreCase("YELLOW"));
		assertSame(redLight, a.equalsIgnoreCase("RED"));

		light1.setNextEastWestRoad(light2);
		light2.setNextEastWestRoad(light3);

		assertEquals((int) length, (int) light1.getRoadClosed());

		assertNotSame(light1, light2);
		assertSame(light1, light1);
		assertSame(light1.getNextEastWestRoad(), light2);
		assertSame(light2.getNextEastWestRoad(), light3);
		assertSame(light3.getNextEastWestRoad(), null);

		try {
			light1.setNextNorthSouthRoad(light2);
		} catch (IllegalArgumentException e) {

		}

		try {
			light1.getNextNorthSouthRoad();
		} catch (IllegalArgumentException e) {

		}
	}
}
