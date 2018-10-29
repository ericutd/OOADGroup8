package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import permit.PermitDAO;
import service.PermitRegisterService;

public class PermitRegisterServiceTest {

	PermitRegisterService service;
	PermitDAO permit; 
	
	@Before
	public void setup() {
		permit = PermitDAO.builder(1, (double) 100, "BLUE", "2018-10-10");
		service = new PermitRegisterService();
	}
	
	@Test
	public void regValidPermitForUser() {
		service.setPermit(permit);
		assertTrue(service.invoke());
	}
	
	@Test
	public void regInvalidPermitForUser() {
		assertFalse(service.invoke());
	}
	
}
