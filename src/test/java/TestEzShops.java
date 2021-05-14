import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.*;

public class TestEzShops {
	
	@Test
	public void test14Digit() {
		Tools t = new Tools();
		assertTrue(t.checkDigit("12345678901231"));
	}
	@Test
	public void test13Digit() {
		Tools t = new Tools();
		assertTrue(t.checkDigit("8032089001236"));
	}
	@Test
	public void test12Digit() {
		Tools t = new Tools();
		assertTrue(t.checkDigit("123456789012"));
	}
	@Test
	public void testShortString() {
		Tools t = new Tools();
		assertFalse(t.checkDigit("1234567890"));
	}
	@Test
	public void testLongString() {
		Tools t = new Tools();
		assertFalse(t.checkDigit("12345678900"));
	}
	
	
	

	

}
