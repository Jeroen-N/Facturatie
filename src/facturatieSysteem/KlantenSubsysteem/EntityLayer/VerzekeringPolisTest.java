package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class VerzekeringPolisTest {

	private VerzekeringPolis instance;
	
	@Before
	public void setup() throws ParseException{
		String polisNummer = "123456";
		instance = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011")); 
	}

	@Test
	public void testGetPolisNummer() {
		String expPolisNummer = "123456";
		assertTrue(instance.getPolisNummer() == expPolisNummer);
	}

	@Test
	public void testGetVerzekeringsType() {
		String expVerzekeringsType = "007";
		assertTrue(instance.getVerzekeringsType() == expVerzekeringsType);
	}

	@Test
	public void testGetExtraEigenRisico() {
		double expExtraEigenRisico = 1125.48;
		assertTrue(instance.getExtraEigenRisico() == expExtraEigenRisico);
	}

	@Test
	public void testGetStartDatum() throws ParseException {
		Date expStartDatum = new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010");
		assertTrue(instance.getStartDatum() == expStartDatum);
	}

	@Test
	public void testGetEindDatum() throws ParseException {
		Date expEindDatum = new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011");
		assertTrue(instance.getEindDatum() == expEindDatum);
	}
}
