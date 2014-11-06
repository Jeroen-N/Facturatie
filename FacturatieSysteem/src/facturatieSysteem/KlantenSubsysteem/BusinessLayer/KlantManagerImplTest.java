package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

// TODO: Auto-generated Javadoc
/**
 * The Class KlantManagerImplTest.
 */
public class KlantManagerImplTest {

	/** The instance. */
	private Klant instance;
	
	/** The Verzekering polissen. */
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	
	/** The polis. */
	private VerzekeringPolis polis;
	
	/** The klant dao. */
	private KlantDAO klantDAO;
	
	/** The polis dao. */
	private VerzekeringPolisDAO polisDAO;
	
	/** The bsn. */
	private String BSN;
	
	/** The polis nummer. */
	private String polisNummer;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		klantDAO = new KlantDAOImpl();
		polisDAO = new VerzekeringPolisDAOImpl();
		polisNummer = "123456";
		  BSN = "125651201";
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
		klantDAO.verwijderKlantXML(BSN);
	}
	
	/**
	 * Test get klanten.
	 */
	@Test
	public void testGetKlanten() {
		assertTrue(2 == klantDAO.getKlantenXML().size());
	}
	
	/**
	 * Test create klant.
	 */
	@Test
	public void testCreateKlant() {
		int i= klantDAO.getKlantenXML().size();
		klantDAO.addKlantXML(instance);
		assertTrue(i +1 == klantDAO.getKlantenXML().size());
		
	}

	/**
	 * Test update klant.
	 */
	@Test
	public void testUpdateKlant() {
		String BSN = "125651203";
		String expRes = "Breda";
		String plaats = null;
		Klant klant = new Klant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Breda", "31-12-1995","0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incasso");
		klantDAO.updateKlantXML(klant);
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {

				plaats = k1.getWoonplaats();
			}
		}
		assertEquals(expRes, plaats);
	}

	/**
	 * Test find klant.
	 */
	@Test
	public void testFindKlant() {
		assertTrue(2 == klantDAO.findKlantXML("31-12-1995").size());
	}

	/**
	 * Test get klant.
	 */
	@Test
	public void testGetKlant() {
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				assertTrue(k1.getAdres() == "Schijfstraat 26B");
			}
		}
	}

	/**
	 * Test toon klant.
	 */
	@Test
	public void testToonKlant() {
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				assertTrue(k1.getAdres() == "Schijfstraat 26B");
			}
		}
	}

	/**
	 * Test toon polis.
	 */
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

	/**
	 *  Test create polis.
	 */
	@Test
	public void testCreatePolis() {
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011");
		assertTrue(polis1.getPolisNummer() == polisNummer);
	}

	/**
	 * Test add verzekering polis xml.
	 */
	@Test
	public void testAddVerzekeringPolisXML() {
		String BSN = "125651202";
		int i = 0;
		int j = 0;
		for (Klant k1 : klantDAO.getKlantenXML()) {
			if (k1.getBSN().equals(BSN)) {
				i = k1.getVerzekeringPolissen().size();
			}
		}
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011");
		polisDAO.addVerzekeringPolisXML(BSN, polis1);
		for (Klant k2 : klantDAO.getKlantenXML()) {
			if (k2.getBSN().equals(BSN)) {
				j = k2.getVerzekeringPolissen().size();
			}
		}
		
		assertTrue(i + 1 == j);
		
	}

	/**
	 * Test update verzekering polis xml.
	 */
	@Test
	public void testUpdateVerzekeringPolisXML() {
		VerzekeringPolis polis1 = new VerzekeringPolis(polisNummer, "009", 1125.48, "01-01-2010", "31-12-2011");
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

	/**
	 * Test delete verzekering polis xml.
	 */
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



	/**
	 * Test verwijder klant xml.
	 */
	@Test
	public void testVerwijderKlantXML() {
		int i= klantDAO.getKlantenXML().size();
		klantDAO.addKlantXML(instance);
		klantDAO.verwijderKlantXML(BSN);
		assertTrue(i == klantDAO.getKlantenXML().size());
	}
	
}
