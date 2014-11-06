package facturatieSysteem.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.VerzekeringsmaatschappijDAO;
import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.VerzekeringsmaatschappijDAOImpl;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijManagerImplTest {

	private Verzekeringsmaatschappij instance;
	private VerzekeringsmaatschappijManager manager;
	private VerzekeringsmaatschappijDAO verzekeringDAO;
	private ArrayList<Verzekeringsmaatschappij> verzekeringsMaatschappijen;
	private ArrayList<Verzekeringstype> typelijst;
	private Verzekeringstype type;

	@Before
	public void setUp() throws Exception {
		instance = new Verzekeringsmaatschappij("001", "Naam", "Straat 12",
				"3333HG", "Plaats", 87654321, 987654321);
		verzekeringsMaatschappijen = new ArrayList<Verzekeringsmaatschappij>();
		verzekeringDAO = new VerzekeringsmaatschappijDAOImpl();
		type = new Verzekeringstype("001", 250, "Typenaam");
		typelijst = new ArrayList<Verzekeringstype>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testgetVerzekeringsMaatschappij() {
		assertTrue(2 == verzekeringDAO.getMaatschappijenXML().size());
	}

	@Test
	public void testaddVerzekeringsmaatschappij() {
		int i = verzekeringDAO.getMaatschappijenXML().size();
		verzekeringDAO.addMaatschappijXML(instance);
		assertTrue(i + 1 == verzekeringDAO.getMaatschappijenXML().size());
	}

	@Test
	public void testupdateVerzekeringsmaatschappij() {
		String nr = "002";
		String rekNr = "987654322";
		String KVK = "88776655";
		String plaats = null;
		String expRes = "Breda";
		Verzekeringsmaatschappij maatschappij = new Verzekeringsmaatschappij(
				nr, "Alex", "Hogeschoollaan 12", "3333GG", "Breda",
				Integer.parseInt(rekNr), Integer.parseInt(KVK));
		verzekeringDAO.updateMaatschappijXML(maatschappij);
		for (Verzekeringsmaatschappij m1 : verzekeringDAO
				.getMaatschappijenXML()) {
			if (m1.getNr().equals(nr)) {
				plaats = m1.getPlaats();
			}
		}
		assertEquals(expRes, plaats);
	}

	@Test
	public void testdeleteVerzekeringsmaatschappij() {
		manager.addVerzekeringsmaatschappij(instance);
		manager.deleteVerzekeringsmaatschappij(instance);
		assertTrue(manager.getVerzekeringsmaatschappij("002") == null);
	}

	
	@Test
	public void testgetVerzekeringsmaatschappijen() {
		verzekeringsMaatschappijen.clear();
		verzekeringsMaatschappijen.add(instance);
		assertTrue(manager.getVerzekeringsmaatschappijen().size() >= 1);
	}
	

	@Test
	public void testgetVerzekeringstype() {
		typelijst.clear();
		typelijst.add(type);
		assertTrue(instance.getTypes().size() >= 1);
	}

	@Test
	public void testaddVerzekeringstype() {
		int i = instance.getTypes().size();
		instance.addType(type);
		assertTrue(i + 1 == instance.getTypes().size());
	}

	@Test
	public void testupdateVerzekeringstype() {
		String nummer = "255";
		String naam = null;
		String expRes = "Typenaam";
		Verzekeringstype type = new Verzekeringstype("001", 255, "Typenaam");
		manager.updateVerzekeringstype(instance, type);
		for (Verzekeringstype t1 : instance.getTypes()) {
			if (t1.getNr().equals(nummer)) {
				naam = t1.getNaam();
			}
		}
		assertEquals(expRes, naam);
	}

	
	@Test
	public void testImportdata() {
		verzekeringsMaatschappijen.clear();
		verzekeringsMaatschappijen.add(instance);
		assertTrue(manager.getVerzekeringsmaatschappijen().size() >= 1);
	}

}
