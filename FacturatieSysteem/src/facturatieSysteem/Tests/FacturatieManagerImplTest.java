package facturatieSysteem.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.DAOFactoryFactuur;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.FactuurDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;

public class FacturatieManagerImplTest {
	private Klant klant;
	private VerzekeringsmaatschappijManagerImpl verzekeringsmngr;
	private FacturatieManagerImpl instance;
	private FactuurDAO factuurDAO;
	private DAOFactoryFactuur daoFactoryBehandelcodes;
	private DAOFactoryFactuur daoFactoryFacturatie;
	private DAOFactoryFactuur daoFactoryClient;

	@Before
	public void setUp() throws Exception {
		ArrayList<VerzekeringPolis> VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		new ArrayList<Factuur>();
		factuurDAO = new FactuurDAO(daoFactoryBehandelcodes,
				daoFactoryClient, daoFactoryFacturatie);
		klant = new Klant("125651201","Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", "31-12-1995","0625235100", "sjmblijl@avans.nl", "NL47RABO0136052185", 25.25, VerzekeringPolissen, "incoasso");
		verzekeringsmngr = new VerzekeringsmaatschappijManagerImpl();
		instance = new FacturatieManagerImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFactureer() {
		factuurDAO.haalAlleFacturen();
		int expResult = factuurDAO.haalAlleFacturen().size() + 1;
		instance.factureer(klant, verzekeringsmngr);
		int result = factuurDAO.haalAlleFacturen().size();


		assertTrue( result == expResult);
	}
	@Test
	public void testHaalFacturen() {
		int result = factuurDAO.haalFacturen("125651201").size();
		int expResult = 2;
		assertTrue(result == expResult);
	}


	@Test
	public void testLoopBehandelingen() {
		fail("Not yet implemented");
	}

}
