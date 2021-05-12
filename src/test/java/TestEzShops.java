import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import it.polito.ezshop.model.*;

public class TestEzShops {
	
	@Test
	public void testINcreaseQuantity() {
		ProductType p = new it.polito.ezshop.model.ProductType();
		int quantity = p.getQuantity();
		p.increaseQuantity(5);
		int x = p.getQuantity();
		assertEquals(x,5);
		//boundary
		
		p.increaseQuantity(5);
		x = p.getQuantity();
		assertEquals(x,10);
	}
	
	
	@Test
	public void testSetAndGetQuantity() {
		ProductType p = new it.polito.ezshop.model.ProductType();
		p.setQuantity(5);
		int x = p.getQuantity();
		assertEquals(x,5);
	}
	

	

}
