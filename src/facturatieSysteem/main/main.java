package facturatieSysteem.main;

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;

public class main {
	public static void main(String[] args){
		//FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		//System.out.println(m1.haalFacturen("136521598"));
		
		//TODO: instantieer alle managers hier
		VerzekeringsmaatschappijManager VerzekeringManager = new VerzekeringsmaatschappijManagerImpl();
		
		// VerzekeringsManager tijdelijke hardcoded data aanmaken
		VerzekeringManager.fill();
		
		//TODO: Geef alle managers hier mee, ipv in de MainGUI aan te maken
		new MainGUI(VerzekeringManager);
	}
}
