package facturatieSysteem.main;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManagerImpl;
import facturatieSysteem.LoginSubSysteem.BusinessLayer.LoginManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;
import facturatieSysteem.main.PresentationLayer.MainGUI;

/**
 * The Class main.
 */
public class main {
	
	/**
	 * The main method which starts the whole application
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		//Instantieer alle managers hier
		KlantManager klantManager = new KlantManagerImpl();
		VerzekeringsmaatschappijManager maatschappijManager = new VerzekeringsmaatschappijManagerImpl();
		FacturatieManagerImpl facturatieManager = new FacturatieManagerImpl();
		LoginManager loginManager = new LoginManager();
		//Geeft alle managers hier mee
		new MainGUI(klantManager, maatschappijManager, facturatieManager, loginManager);
	}
}
