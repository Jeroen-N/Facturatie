package facturatieSysteem.KlantenSubsysteem.BusinessLayer;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

// TODO: Auto-generated Javadoc
/**
 * The Class testMain.
 */
public class testMain {
	
	/** The Verzekering polissen. */
	private static ArrayList<VerzekeringPolis> VerzekeringPolissen;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		KlantManager manager = new KlantManagerImpl();
		String polisNummer = manager.createPolisnummer();
		polisNummer = "123456";
		String BSN = "125651201";
		VerzekeringPolissen = new ArrayList<>();
		VerzekeringPolis polis = new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011"); 
		VerzekeringPolissen.add(polis);
		
		//Klant toevoegen
			try {
				if(manager.createKlant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", "31-12-1995","0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incoasso")){
					System.out.println("Klant met BSN nummer: "+BSN+" succesvol toegevoegd");
				}else{
					System.out.println("klant toevoegen mislukt");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
		polisNummer = manager.createPolisnummer();
		//Polis aan klant toevoegen
			if(manager.addVerzekeringPolisXML(BSN, polisNummer, "007", 1125.48, "01-01-1910", "31-12-1911")){
				System.out.println("polis met polisNummer: "+polisNummer+" succesvol toegevoegd");
			}else{
				System.out.println("toevoegen polis mislukt");
			}
		
		//Wijzigen klant
			if(manager.updateKlant(BSN, "piet jan henk", "Marialaan 9a", "4834VG", "Breda", "10-06-1994","0683144715","xpjgerre@avans.nl","NL47RABO01326884668",2.50,VerzekeringPolissen,"incoasso")){
				System.out.println("klant is gewijzigd");
			}else{
				System.out.println("klant wijzigen mislukt");
			}
		
		//Wijzigen polis	
			//Polisnummer mag niet opnieuw gecreerd worden
			if(manager.updateVerzekeringPolisXML(polisNummer, "007", 25.48, "01-01-2000", "31-12-2001")){
				System.out.println("polis met polisNummer : "+polisNummer+" is gewijzigd");
			}else{
				System.out.println("polis wijzigen mislukt");
			}
		
		//Klanten zoeken
			String gbDatum = "06-09-1991";
			if(manager.findKlant(gbDatum).size() != 0){
				System.out.println("aantal gevonden klanten met "+gbDatum+" als geboorteDatum: "+ manager.findKlant(gbDatum).size());
			}
			else{
				System.out.println("er zijn geen klanten gevonden met geboorteDatum "+ gbDatum);
			}
		/*
		//Klant verwijderen
			if(manager.verwijderKlantXML(BSN)){
				System.out.println("Klant met BSN nummer: "+BSN+" succesvol verwijderen");
			}else{
				System.out.println("Klant verwijderen Mislukt");
			}	
		*/					
	}
}

