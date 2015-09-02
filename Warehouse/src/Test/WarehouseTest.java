package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Warehouse.Product;
import Warehouse.Warehouse;

public class WarehouseTest {
	
	Product p1;
	Product p2;
	Product p3;
	Product p4;
	Product p5;
	Product p6;
	
	Warehouse w = new Warehouse();
	

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
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReceive() {
		
		w.receive(p1, "A");
		w.receive(p2, "B");
		w.receive(p3, "A");
		w.receive(p4, "B");
		w.receive(p5, "B");
		w.receive(p6, "L");
		
		assertEquals(w.dispatch(p1) , "A");
		assertEquals(w.dispatch(p2) , "B");
		
	}
	
	@Test
	public void testGetItemCount() {
		
		w.receive(p1, "A");
		w.receive(p2, "B");
		assertEquals(w.getItemCount(p1), 2);
	}
	
	@Test
	public void testDispatch() {
		
		w.receive(p1, "A");
		w.dispatch(p1);
		
		assertEquals(w.getItemCount(p1), 0);
		
	}

}
