package facturatieSysteem.main;

import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManagerImpl;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class main {
	public static void main(String[] args){
		//FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		//System.out.println(m1.haalFacturen("136521598"));
		
		//TODO: instantieer alle managers hier
		KlantManager KlantManager = new KlantManagerImpl();
		VerzekeringsmaatschappijManager MaatschappijManager = new VerzekeringsmaatschappijManagerImpl();		
		
		// VerzekeringsManager tijdelijke hardcoded data aanmaken
		MaatschappijManager.fill();
		
		//TODO: Geef alle managers hier mee, ipv in de MainGUI aan te maken
		new MainGUI(KlantManager, MaatschappijManager);
	}
}
