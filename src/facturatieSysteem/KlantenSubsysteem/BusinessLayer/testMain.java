package facturatieSysteem.KlantenSubsysteem.BusinessLayer;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class testMain {
	private KlantManager manager;
	public static void main(String[] args) {

		KlantManager manager = new KlantManagerImpl();
		
		System.out.println("aantalklanten: "+ manager.getKlanten().size());
		String polisNummer = "123456";//moet nog automatisch aangemaakt worden.
		if(manager.createKlant("125651201", "Sander Blijlevens", "Schijfstraat 26B", "4847SM", "Teteringen", "31-12-1995","0625235100","sjmblijl@avans.nl","NL47RABO0136052185",25.25,new VerzekeringPolis(polisNummer, "007", 125.48, "01-01-2000", "31-12-2001"),"incoasso")){
			System.out.println("Klant succesvol toegevoegd");
		}else{
			System.out.println("Klant toevoegen mislukt");
		}
		
		System.out.println("aantalklanten: "+ manager.getKlanten().size());
		/*
		if(manager.verwijderKlantXML("125651201")){
			System.out.println("Toegevoegde klant weer verwijdert");
		}else{
			System.out.println("verwijderen van klant mislukt");
		}

		System.out.println("aantalklanten: "+ manager.getKlanten().size());
	
		if(manager.findKlant("06-09-1991").size() != 0){
			System.out.println("aantal gevonden klanten: "+ manager.findKlant("06-09-1991").size());
		}
		else{
			System.out.println("er zijn geen klanten gevonden");
		}
		*/
	}
	
		//manager.getInfo("236584595");
}

