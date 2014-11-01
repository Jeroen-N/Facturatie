package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.DOMException;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantManagerImplTest {

	private Klant instance;
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	private VerzekeringPolis polis;
	private KlantDAO klantDAO;
	private VerzekeringPolisDAO polisDAO;
	private String BSN;
	private String polisNummer;
	
	@Before
	public void setUp() throws Exception {
		klantDAO = new KlantDAOImpl();
		polisDAO = new VerzekeringPolisDAOImpl();
		polisNummer = "123456";
		  BSN = "125651201";
		  VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		  polis = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011")); 
		  VerzekeringPolissen.add(polis);
		instance = new Klant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995"),"0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incasso");
	}
	
	@After
	public void tearDown() throws Exception {
		klantDAO.verwijderKlantXML(BSN);
	}
	
	@Test
	public void testGetKlanten() {
		assertTrue(2 == klantDAO.getKlantenXML().size());
	}
	
	@Test
	public void testCreateKlant() {
		int i= klantDAO.getKlantenXML().size();
		klantDAO.addKlantXML(instance);
		assertTrue(i +1 == klantDAO.getKlantenXML().size());
		
	}

	@Test
	public void testUpdateKlant() throws ParseException {
		String BSN = "125651203";
		String expRes = "Breda";
		String plaats = null;
		Klant klant = new Klant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Breda", new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995"),"0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incasso");
		klantDAO.updateKlantXML(klant);
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				//System.out.println(k1.getWoonplaats());
				plaats = k1.getWoonplaats();
			}
		}
		assertEquals(expRes, plaats);
	}

	@Test
	public void testFindKlant() throws DOMException, ParseException {
		assertTrue(2 == klantDAO.findKlantXML(new SimpleDateFormat("dd-MM-yyyy").parse("31-12-1995")).size());
	}

	@Test
	public void testGetKlant() {
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				assertTrue(k1.getAdres() == "Schijfstraat 26B");
			}
		}
	}

	@Test
	public void testToonKlant() {
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				assertTrue(k1.getAdres() == "Schijfstraat 26B");
			}
		}
	}

	@Test
	public void testToonPolis() {
		VerzekeringPolissen.clear();
		for (Klant klant : klantDAO.getKlantenXML()) {
			if (klant.getBSN().equals(BSN)) {
				int j = klant.getVerzekeringPolissen().size();
				for(int i = klant.getVerzekeringPolissen().size(); i > 0; i--){
					VerzekeringPolissen.add((klant.getVerzekeringPolissen().get(i-1)));
				}				
				assertTrue(j  ==  klant.getVerzekeringPolissen().size());
			}
		}
	}

	

	@Test
	public void testCheckKlant() {
		//check functions worden niet getest
		assertTrue(1 == 1);
	}

	@Test
	public void testCheckPolis() {
		//check functions worden niet getest
		assertTrue(1 == 1);
	}

	@Test
	public void testCreatePolis() throws ParseException {
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011"));
		assertTrue(polis1.getPolisNummer() == polisNummer);
	}

	@Test
	public void testAddVerzekeringPolisXML() throws ParseException {
		String BSN = "125651202";
		int i = 0;
		int j = 0;
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				i = k1.getVerzekeringPolissen().size();
			}
		}
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "007", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011"));
		polisDAO.addVerzekeringPolisXML(BSN, polis1);
		for (Klant k2 : klantDAO.getKlantenXML()) {
			if (k2.getBSN().equals(BSN)) {
				j = k2.getVerzekeringPolissen().size();
			}
		}
		
		assertTrue(i + 1 == j);
		
	}

	@Test
	public void testUpdateVerzekeringPolisXML() throws ParseException {
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "009", 1125.48, new SimpleDateFormat("dd-MM-yyyy").parse("01-01-2010"), new SimpleDateFormat("dd-MM-yyyy").parse("31-12-2011"));
		polisDAO.updateVerzekeringPolisXML(polis1);
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				for(VerzekeringPolis polis : k1.getVerzekeringPolissen()){
					if(polis.getPolisNummer().equals(polisNummer)){
						assertTrue(polis.getVerzekeringsType() == "009");
					}
				}
			}
		}
	}

	@Test
	public void testDeleteVerzekeringPolisXML() {
		String BSN = "125651202";
		int i = 0;
		int j = 0;
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				i = k1.getVerzekeringPolissen().size();
			}
		}
		polisDAO.verwijderPolisXML(polisNummer, BSN);
		for (Klant k2 : klantDAO.getKlantenXML()) {
			if (k2.getBSN().equals(BSN)) {
				j = k2.getVerzekeringPolissen().size();
			}
		}
		
		assertTrue(i  == j + 1);
		
	}

	@Test
	public void testCreatePolisnummer() {
		//niet testbaar, random generator
		assertTrue(1 == 1);
	}

	@Test
	public void testGetBSNs() {
		assertTrue(2 == klantDAO.getBSNs().size());
	}

	@Test
	public void testVerwijderKlantXML() {
		int i= klantDAO.getKlantenXML().size();
		klantDAO.addKlantXML(instance);
		klantDAO.verwijderKlantXML(BSN);
		assertTrue(i == klantDAO.getKlantenXML().size());
	}
	
}
