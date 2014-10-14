package facturatieSysteem.KlantenSubsysteem.BusinessLayer;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class testMain {
	private KlantManager manager;
	private static ArrayList<VerzekeringPolis> VerzekeringPolissen;
	public static void main(String[] args) {

		KlantManager manager = new KlantManagerImpl();
		VerzekeringPolissen = new ArrayList<VerzekeringPolis>();
		String polisNummer = manager.createPolisnummer();
		VerzekeringPolis polis = new VerzekeringPolis(manager.createPolisnummer(), "007", 125.48, "01-01-2000", "31-12-2001");
		System.out.println("aantalklanten: "+ manager.getKlanten().size());
		
		String BSN = "125651201";
		/*
		VerzekeringPolissen.add(polis);
		if(manager.createKlant(BSN, "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", "31-12-1995","0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,VerzekeringPolissen,"incoasso")){
			System.out.println("Klant succesvol toegevoegd");
		}else{
			System.out.println("Klant toevoegen mislukt");
		}
		*/
		if(manager.addVerzekeringPolis(BSN, new VerzekeringPolis(polisNummer, "007", 1125.48, "01-01-2010", "31-12-2011"))){
			System.out.println("polis met polisNummer: "+polisNummer+" succesvol toegevoegd");
		}else{
			System.out.println("toevoegen polis mislukt");
		}
		
		/*
		System.out.println("aantalklanten: "+ manager.getKlanten().size());

		if(manager.verwijderKlantXML(BSN)){
			System.out.println("Toegevoegde klant weer verwijdert");
		}else{
			System.out.println("verwijderen van klant mislukt");
		}
		*/
		System.out.println("aantalklanten: "+ manager.getKlanten().size());
		
		if(manager.findKlant("06-09-1991").size() != 0){
			System.out.println("aantal gevonden klanten: "+ manager.findKlant("06-09-1991").size());
			for(Klant klant : manager.findKlant("06-09-1991")){
				System.out.println("aantal Verzekeringen: "+ klant.getVerzekeringPolissen().size());
			}
		}
		else{
			System.out.println("er zijn geen klanten gevonden");
		}
		
		
		
	}
	
		//manager.getInfo("236584595");
}

