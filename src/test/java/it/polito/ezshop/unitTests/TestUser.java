package it.polito.ezshop.unitTests;

import static org.junit.Assert.assertEquals;



import org.junit.Test;

import it.polito.ezshop.model.User;

public class TestUser {
	User u = new User("mario", "password", "Administrator", 1);
	
	@Test
	public void testUserConstructor() {
		assertEquals(u.getId(), (Integer)1);
		assertEquals(u.getPassword(), "password");
		assertEquals(u.getRole(), "Administrator");
		assertEquals(u.getUsername(), "mario");
	}
	
	@Test
	public void testSetterUser() {
		u.setId(2);
		assertEquals(u.getId(), (Integer)2);
		
		u.setPassword("new");
		assertEquals(u.getPassword(), "new");
		
		u.setRole("Cashier");
		assertEquals(u.getRole(), "Cashier");
		
		u.setUsername("andrea");
		assertEquals(u.getUsername(), "andrea");
	}

}
