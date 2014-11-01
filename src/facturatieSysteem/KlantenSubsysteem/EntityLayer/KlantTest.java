package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KlantTest {

	private Klant instance;
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	private VerzekeringPolis polis;
	
	
	@Before
	public void setUp() throws Exception {
		String polisNummer = "123456";
		  String BSN = "125651201";
		  VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		  polis = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011")); 
		  VerzekeringPolissen.add(polis);
		instance = new Klant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen",new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995"),"0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incasso");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetTotaalEigenRisico() throws Exception {
		System.out.println("Het totaal eigen risico wordt geset");
		double expResult = 600;
		instance.setTotaalEigenRisico(600);
		assertTrue(instance.getResterendEigenRisico() == expResult);
	}
	
	@Test
	public void testBerekenTotaalEigenRisico(){
		double eindBedrag = 0;
		double bedrag = 10;
		eindBedrag = instance.getResterendEigenRisico() - bedrag;
		assertTrue(eindBedrag != 0);
	}
	
	@Test
	public void testgetVerzekeringPolissen(){
		ArrayList<VerzekeringPolis> testPolis = instance.getVerzekeringPolissen();
		assertTrue(testPolis.size() == 1);
		assertTrue(!testPolis.isEmpty());
	}

	@Test
	public void testGetBSN(){
		String expResult = "125651201";
		String Result = instance.getBSN();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetNaam(){
		String expResult = ("Sander Blijlevens");
		String Result = instance.getNaam();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetAdres(){
		String expResult = ("Schijfstraat 26B");
		String Result = instance.getAdres();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetPostcode(){
		String expResult = ("4847SM");
		String Result = instance.getPostcode();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetWoonplaats(){
		String expResult = ("Teteringen");
		String Result = instance.getWoonplaats();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetGeboortedatum() throws ParseException{
		Date expResult = new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995");
		Date Result = instance.getGeboortedatum();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetTelefoonnummer(){
		String expResult = ("0625235100");
		String Result = instance.getTelefoonnummer();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetEmail(){
		String expResult = ("sjmblijl@avans.nl");
		String Result = instance.getEmail();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetRekening(){
		String expResult = ("NL47RABO0136052185");
		String Result = instance.getRekeningnummer();
		assertEquals(expResult, Result);
	}
	
	@Test
	public void testgetBetaalmethode(){
		String expResult = "incasso";
		String Result = instance.getBetaalMethode();
		assertEquals(expResult, Result);
	}
}
