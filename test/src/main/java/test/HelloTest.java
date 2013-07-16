package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloTest {

	@Test
	public void testAdd() {
		Hello hello = new Hello();
		assertEquals(hello.add(1, 1),2);
	}

}
