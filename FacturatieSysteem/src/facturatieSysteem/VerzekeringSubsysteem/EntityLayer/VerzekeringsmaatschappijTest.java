package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VerzekeringsmaatschappijTest {

	private Verzekeringsmaatschappij instance;
	
	@Before
	public void setUp() throws Exception {
		instance = new Verzekeringsmaatschappij("001", "Brood Verzekeringen", "Broodlaan 11", "1234AB", "Brooddorp", 123456789, 1123456789);
		Verzekeringstype type = new Verzekeringstype("001", 250, "Testtype");
		instance.addType(type);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNr() throws Exception {
		String expResult = "001";
		assertTrue(instance.getNr() == expResult);
	}
	
	@Test
	public void testGetNaam() throws Exception {
		String expResult = "Brood Verzekeringen";
		assertTrue(instance.getNaam() == expResult);
	}
	
	@Test
	public void testGetAdres() throws Exception {
		String expResult = "Broodlaan 11";
		assertTrue(instance.getAdres() == expResult);
	}
	
	@Test
	public void testGetPostcode() throws Exception {
		String expResult = "1234AB";
		assertTrue(instance.getPostcode() == expResult);
	}
	@Test
	public void testGetPlaats() throws Exception {
		String expResult = "Brooddorp";
		assertTrue(instance.getPlaats() == expResult);
	}
	@Test
	public void testGetKVK() throws Exception {
		int expResult = 123456789;
		assertTrue(instance.getKVK() == expResult);
	}
	@Test
	public void testGetRekNr() throws Exception {
		int expResult = 1123456789;
		assertTrue(instance.getRekeningNR() == expResult);
	}
	
	@Test
	public void testSetNaam() throws Exception {
		String expResult = "Brood Verzekering";
		instance.setNaam(expResult);
		assertTrue(instance.getNaam() == expResult);
	}
	
	@Test
	public void testSetAdres() throws Exception {
		String expResult = "Broodlaan 12";
		instance.setAdres(expResult);
		assertTrue(instance.getAdres() == expResult);
	}
	
	@Test
	public void testSetPostcode() throws Exception {
		String expResult = "1234AC";
		instance.setPostcode(expResult);
		assertTrue(instance.getPostcode() == expResult);
	}
	@Test
	public void testSetPlaats() throws Exception {
		String expResult = "Broodstad";
		instance.setPlaats(expResult);
		assertTrue(instance.getPlaats() == expResult);
	}
	@Test
	public void testSetKVK() throws Exception {
		int expResult = 123456788;
		instance.setKVK(expResult);
		assertTrue(instance.getKVK() == expResult);
	}
	@Test
	public void testSetRekNr() throws Exception {
		int expResult = 2123456789;
		instance.setRekeningNR(expResult);
		assertTrue(instance.getRekeningNR() == expResult);
	}
	
	@Test
	public void testGetTypes() throws Exception{
		assertFalse(instance.getTypes().isEmpty());
	}
}