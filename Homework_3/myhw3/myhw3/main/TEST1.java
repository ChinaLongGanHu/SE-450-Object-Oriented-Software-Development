package myhw3.main;

import static org.junit.Assert.*;
import org.junit.Test;
import myhw3.command.Command;
import myhw3.data.Data;
import myhw3.data.Inventory;
import myhw3.data.Record;
import myhw3.data.Video;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 {
	private Inventory inventory = Data.newInventory();
	private void check(Video v, int numOwned, int numOut, int numRentals) {
		Record r = inventory.get(v);
		assertEquals(numOwned, r.numOwned());
		assertEquals(numOut, r.numOut());
		assertEquals(numRentals, r.numRentals());
	}

	@Test
	public void test1() {
		Command clearCmd = Data.newClearCmd(inventory);
		clearCmd.run();

		Video v1 = Data.newVideo("Title1", 2000, "Director1");
		assertEquals(0, inventory.size());
		assertTrue(Data.newAddCmd(inventory, v1, 5).run());
		assertEquals(1, inventory.size());
		assertTrue(Data.newAddCmd(inventory, v1, 5).run());
		assertEquals(1, inventory.size());
		check(v1,10,0,0);

		// TODO
	}
}
