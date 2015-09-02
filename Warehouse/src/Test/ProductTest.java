package Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Warehouse.Product;

public class ProductTest {
	
	Product p1;
	Product p2;
	Product p3;
	Product p4;
	Product p5;
	Product p6;
	
	Set<Product> productTestSet;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		p1 = new Product("sokid", 42);
		p2 = new Product("sokid", 42);
		p3 = new Product("kindad", 10);
		p4 = new Product("kindad", 9);
		p5 = new Product("mantel", 60);
		p6 = new Product("pyksid", 56);
		
		productTestSet = new HashSet<Product>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsItself(){
		assertEquals(p1, p1);
	}
	
	@Test
	public void testEqualsSameContent(){
		assertEquals(p2, p1);
	}
	
	@Test
	public void testEqualsDifferentSize(){
		assertEquals(p3, p4);
	}
	
	@Test
	public void testHashp1(){
		assertTrue(productTestSet.add(p1));
	}
	
	@Test
	public void testHashp2(){
		assertTrue(productTestSet.add(p1));
		assertFalse(productTestSet.add(p2));
	}
	
	@Test
	public void testHashpContains(){
		assertTrue(productTestSet.add(p1));
		assertTrue(productTestSet.contains(new Product("sokid", 42)));
	}
	

}
