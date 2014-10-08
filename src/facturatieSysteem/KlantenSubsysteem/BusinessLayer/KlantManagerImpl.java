package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;
	private ArrayList<Klant> zoekresultaat;

	public ArrayList<Klant> getKlanten() {
		// functie voor het ophalen van klanten

		// nog toe tevoegen:

		// KlantDAO.getKlantenXML();
		return klantOverzicht;
	}

	public ArrayList<Klant> getKlant(Date Gebdatum) {
		// functio voor het zoeken van klanten

		// nog toe tevoegen:
		// zoek klanten uit arraylist op bais van de geboortedatum
		return zoekresultaat;
	}

	public Klant toonKlant(String BSN) {
		// functie voor het selecteren van een klant

		// nog toe tevoegen:
		// zoek klant uit de arraylist op basis van het BSN

		return klant;

	}

	public void verwijderKlantXML(String BSN) {
		// functie voor het verwijderen van een klant uit xml

		// nog toe tevoegen:
		// KlantDAO.verwijderKlantXML(klant);
	}

	public void checkKlant(Klant klant) {
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn
	}

	@Override
	public ArrayList<Klant> klanten() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Klant klant() {
		// TODO Auto-generated method stub
		return null;
	}

}
