package com.niit.onlineShopBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.onlineShopBackend.dao.ProductDAO;
import com.niit.onlineShopBackend.model.Product;



public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static ProductDAO productDAO;
	
	
	private Product product;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.onlineShopBackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	@Test
	public void testCRUDProduct() {
		
		// create operation
		product = new Product();
				
		product.setName("redmi");
		product.setBrand("xiomi");
		product.setDescription("This is some description for xiomi mobile phones!");
		product.setUnitPrice(16000);
		product.setActive(true);
		product.setCategoryId(6);
		product.setSupplierId(6);
		
		assertEquals("Something went wrong while inserting a new product!",
				true,productDAO.add(product));		
		
		
		// reading and updating the category
				product = productDAO.get(1);
				product.setName("Samsung Galaxy S7");
				assertEquals("Something went wrong while updating the existing record!",
						true,productDAO.update(product));		
						
				assertEquals("Something went wrong while deleting the existing record!",
						true,productDAO.delete(product));		
				
				// list
				assertEquals("Something went wrong while fetching the list of products!",
						2,productDAO.list().size());	
				
	}
		
	@Ignore
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				1,productDAO.listActiveProducts().size());				
	} 
	
	@Ignore
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of products!",
				2,productDAO.listActiveProductsByCategory(1).size());
	} 
	@Ignore
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!",
				1,productDAO.getLatestActiveProducts(3).size());
		
	} 
	
	
	
		
}
