package com.niit.onlineShopBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.onlineShopBackend.dao.UserDAO;
import com.niit.onlineShopBackend.model.Address;
import com.niit.onlineShopBackend.model.Cart;
import com.niit.onlineShopBackend.model.User;



public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.onlineShopBackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	

	@Test
	public void testAddUser() {
		
		user = new User() ;
		user.setFirstName("debimitra");
		user.setLastName("Choudhury");
		user.setEmail("debimitra@gmail.com");
		user.setContactNumber("9888888888");
		user.setRole("ADMIN");
		user.setEnabled(true);
		user.setPassword("$2a$04$hmwlN18b3gjCnZt5IWG84.5lFQrxXXQTgN5J99WkDInZeOtSccE.O");
		
		
		address = new Address();
		address.setAddressLineOne("Main Road,jajpur");
		address.setAddressLineTwo("Near Axis Bank");
		address.setCity("dangadi");
		address.setState("odisha");
		address.setCountry("India");
		address.setPostalCode("6671");
		address.setBilling(true);
		
		cart = new Cart();
		
		// linked the address with the user
		
		
		// linked the cart with the user
		cart.setUser(user);
		// link the user with the cart
		user.setCart(cart);
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.add(user));	
		// add the address
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));

				
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("lane no 4");
		address.setAddressLineTwo("chandikhol market");
		address.setCity("cuttack");
		address.setState("ODISHA");
		address.setCountry("India");
		address.setPostalCode("787889");
		
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}
	
	



	
}
