package myClassTest;

import static org.junit.Assert.*;
import org.junit.*;


public class CalcTest {
	@Test
	public void testAdd() {
		assertTrue("Calc sum incorrect", 5 == myClass.Calc.add(2, 3));
	}
}
