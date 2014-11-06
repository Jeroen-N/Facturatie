package facturatieSysteem.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandelcode;

// TODO: Auto-generated Javadoc
/**
 * The Class BehandelCodeTest.
 */
public class BehandelCodeTest {

	/** The instance. */
	private Behandelcode instance;


	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		instance = new Behandelcode(001, "Hamstring",
				2, 1, 79.50);
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test get behandel code.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetBehandelCode() throws Exception {
		int expResult = 001;
		assertTrue(instance.getBehandelCode() == expResult);
	}
	
	/**
	 * Test set behandel code.
	 */
	@Test
	public void testSetBehandelCode(){
		int expResult = 002;
		instance.setBehandelCode(002);
		assertTrue(expResult == instance.getBehandelCode());
	}
	
	/**
	 * Test get behandeling naam.
	 */
	@Test
	public void testGetBehandelingNaam(){
		String expResult = "Hamstring";
		assertTrue(expResult == Behandelcode.getBehandelingNaam());
	}

	/**
	 * Test set behandeling naam.
	 */
	@Test
	public void testSetBehandelingNaam(){
		String expResult = "Enkel";
		instance.setBehandelingNaam("Enkel");
		assertTrue(expResult == Behandelcode.getBehandelingNaam());
	}
	
	/**
	 * Test get aantal sessies.
	 */
	@Test
	public void testGetAantalSessies(){
		int expResult = 2;
		assertEquals(expResult, instance.getAantalSessies());
	}
	
	/**
	 * Test set aantal sessies.
	 */
	@Test
	public void testSetAantalSessies(){
		int expResult = 5;
		instance.setAantalSessies(5);
		assertEquals(expResult, instance.getAantalSessies());
	}
	
	/**
	 * Test get sessie duur.
	 */
	@Test
	public void testGetSessieDuur(){
		double expResult = 1;
		assertTrue(expResult == instance.getSessieDuur());
	}
	
	/**
	 * Test set sessie duur.
	 */
	@Test
	public void testSetSessieDuur(){
		double expResult = 5;
		instance.setSessieDuur(5);
		assertTrue(expResult == instance.getSessieDuur());
	}
	
	/**
	 * Test get tarief behandeling.
	 */
	@Test
	public void testGetTariefBehandeling(){
		double expResult = 89.50;
		assertTrue(expResult == instance.getTariefBehandeling());
	}
	
	/**
	 * Test set tarief behandeling.
	 */
	@Test
	public void testSetTariefBehandeling(){
		double expResult = 10;
		instance.setTariefBehandeling(10);
		assertTrue(expResult == instance.getTariefBehandeling());
	}
	
}