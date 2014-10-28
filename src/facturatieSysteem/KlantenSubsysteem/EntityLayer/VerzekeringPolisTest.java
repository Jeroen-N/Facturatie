package facturatieSysteem.KlantenSubsysteem.EntityLayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VerzekeringPolisTest {

	private VerzekeringPolis instance;
	
	@Before
	public void setup(){
		String polisNummer = "123456";
		instance = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011"); 
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
	public void testGetStartDatum() {
		String expStartDatum = "01-01-2010";
		assertTrue(instance.getStartDatum() == expStartDatum);
	}

	@Test
	public void testGetEindDatum() {
		String expEindDatum = "31-12-2011";
		assertTrue(instance.getEindDatum() == expEindDatum);
	}
}
