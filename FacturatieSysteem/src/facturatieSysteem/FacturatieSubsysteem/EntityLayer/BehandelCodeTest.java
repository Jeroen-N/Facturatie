package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BehandelCodeTest {

	private Behandelcode instance;


	
	@Before
	public void setUp() throws Exception {
		instance = new Behandelcode(001, "Hamstring",
				2, 1, 79.50);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBehandelCode() throws Exception {
		int expResult = 001;
		assertTrue(instance.getBehandelCode() == expResult);
	}
	
	@Test
	public void testSetBehandelCode(){
		int expResult = 002;
		instance.setBehandelCode(002);
		assertTrue(expResult == instance.getBehandelCode());
	}
	
	@Test
	public void testGetBehandelingNaam(){
		String expResult = "Hamstring";
		assertTrue(expResult == Behandelcode.getBehandelingNaam());
	}

	@Test
	public void testSetBehandelingNaam(){
		String expResult = "Enkel";
		instance.setBehandelingNaam("Enkel");
		assertTrue(expResult == Behandelcode.getBehandelingNaam());
	}
	
	@Test
	public void testGetAantalSessies(){
		int expResult = 2;
		assertEquals(expResult, instance.getAantalSessies());
	}
	
	@Test
	public void testSetAantalSessies(){
		int expResult = 5;
		instance.setAantalSessies(5);
		assertEquals(expResult, instance.getAantalSessies());
	}
	
	@Test
	public void testGetSessieDuur(){
		double expResult = 1;
		assertTrue(expResult == instance.getSessieDuur());
	}
	
	@Test
	public void testSetSessieDuur(){
		double expResult = 5;
		instance.setSessieDuur(5);
		assertTrue(expResult == instance.getSessieDuur());
	}
	
	@Test
	public void testGetTariefBehandeling(){
		double expResult = 79.50;
		assertTrue(expResult == instance.getTariefBehandeling());
	}
	
	@Test
	public void testSetTariefBehandeling(){
		double expResult = 10;
		instance.setTariefBehandeling(10);
		assertTrue(expResult == instance.getTariefBehandeling());
	}
	
}