package facturatieSysteem.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringstypeTest {

	private Verzekeringstype instance;
	
	@Before
	public void setUp() throws Exception {
		instance = new Verzekeringstype("001", 250, "Testtype");
		instance.addCode("001");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTypeNr() throws Exception {
		String expResult = "001";
		assertTrue(instance.getNr() == expResult);
	}
	
	@Test
	public void testGetTypeNaam(){
		String expResult = "Testtype";
		assertTrue(expResult == instance.getNaam());
	}

	@Test
	public void testSetTypeNaam(){
		String expResult = "Enkel";
		instance.setNaam("Enkel");
		assertTrue(expResult == instance.getNaam());
	}
	
	@Test
	public void testGetEigenRisico(){
		int expResult = 250;
		assertTrue(expResult == instance.getEigenRisico());
	}
	
	@Test
	public void testSetEigenRisico(){
		int expResult = 300;
		instance.setEigenRisicio(300);
		assertTrue(expResult == instance.getEigenRisico());
	}
	
	@Test
	public void testAddCode(){
		String expResult = "002";
		instance.addCode(expResult);
		assertTrue(instance.getBehandelcodes().contains(expResult));
	}
	
	@Test
	public void testDeleteCode(){
		String expResult = "001";
		instance.deleteCode(expResult);
		assertTrue(!instance.getBehandelcodes().contains(expResult));
	}
	
	@Test
	public void testGetBehandelcodes(){
		assertTrue(!instance.getBehandelcodes().isEmpty());
	}
}