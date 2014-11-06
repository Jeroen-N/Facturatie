package facturatieSysteem.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

// TODO: Auto-generated Javadoc
/**
 * The Class KlantTest.
 */
public class KlantTest {

	/** The instance. */
	private Klant instance;
	
	/** The Verzekering polissen. */
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	
	/** The polis. */
	private VerzekeringPolis polis;
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		String polisNummer = "123456";
		  String BSN = "125651201";
		  VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		  polis = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011"); 
		  VerzekeringPolissen.add(polis);
		instance = new Klant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", "31-12-1995","0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incasso");
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
	 * Test set totaal eigen risico.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSetTotaalEigenRisico() throws Exception {
		System.out.println("Het totaal eigen risico wordt geset");
		double expResult = 600;
		instance.setTotaalEigenRisico(600);
		assertTrue(instance.getResterendEigenRisico() == expResult);
	}
	
	/**
	 * Test bereken totaal eigen risico.
	 */
	@Test
	public void testBerekenTotaalEigenRisico(){
		double eindBedrag = 0;
		double bedrag = 10;
		eindBedrag = instance.getResterendEigenRisico() - bedrag;
		assertTrue(eindBedrag != 0);
	}
	
	/**
	 * Testget verzekering polissen.
	 */
	@Test
	public void testgetVerzekeringPolissen(){
		ArrayList<VerzekeringPolis> testPolis = instance.getVerzekeringPolissen();
		assertTrue(testPolis.size() == 1);
		assertTrue(!testPolis.isEmpty());
	}

	/**
	 * Test get bsn.
	 */
	@Test
	public void testGetBSN(){
		String expResult = "125651201";
		String Result = instance.getBSN();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget naam.
	 */
	@Test
	public void testgetNaam(){
		String expResult = ("Sander Blijlevens");
		String Result = instance.getNaam();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget adres.
	 */
	@Test
	public void testgetAdres(){
		String expResult = ("Schijfstraat 26B");
		String Result = instance.getAdres();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget postcode.
	 */
	@Test
	public void testgetPostcode(){
		String expResult = ("4847SM");
		String Result = instance.getPostcode();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget woonplaats.
	 */
	@Test
	public void testgetWoonplaats(){
		String expResult = ("Teteringen");
		String Result = instance.getWoonplaats();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget geboortedatum.
	 */
	@Test
	public void testgetGeboortedatum(){
		String expResult = ("31-12-1995");
		String Result = instance.getGeboortedatum();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget telefoonnummer.
	 */
	@Test
	public void testgetTelefoonnummer(){
		String expResult = ("0625235100");
		String Result = instance.getTelefoonnummer();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget email.
	 */
	@Test
	public void testgetEmail(){
		String expResult = ("sjmblijl@avans.nl");
		String Result = instance.getEmail();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget rekening.
	 */
	@Test
	public void testgetRekening(){
		String expResult = ("NL47RABO0136052185");
		String Result = instance.getRekeningnummer();
		assertEquals(expResult, Result);
	}
	
	/**
	 * Testget betaalmethode.
	 */
	@Test
	public void testgetBetaalmethode(){
		String expResult = "incasso";
		String Result = instance.getBetaalMethode();
		assertEquals(expResult, Result);
	}
}
