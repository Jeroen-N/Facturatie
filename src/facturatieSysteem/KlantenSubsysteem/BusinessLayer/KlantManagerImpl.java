package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;
	private ArrayList<Klant> zoekresultaat;
	private KlantDAO KlantDAO;

	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, Date Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			VerzekeringPolis Verzekering, String Betaalwijze) {

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr, Verzekering,
				Betaalwijze);

		if (checkKlant(klant)) {
			// klant gegevens zijn correct ingevuld
			if (KlantDAO.addKlantXML(klant)) {
				return true;
			} else {
				return false;
			}
		} else {
			// fout melding weergeven in gui dat gegevens niet correct zijn
			return false;
		}
	}

	@Override
	public ArrayList<Klant> getKlanten() {
		// functie voor het ophalen van klanten

		// nog toe tevoegen:

		return KlantDAO.getKlantenXML();

	}

	@Override
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

	public boolean verwijderKlantXML(String BSN) {
		// functie voor het verwijderen van een klant uit xml

		// nog toe tevoegen:
		if (KlantDAO.verwijderKlantXML(klant)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkKlant(Klant klant) {
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn
		return true;
	}

	@Override
	public ArrayList<Klant> klanten() {
		// TODO Auto-generated method stub
		return null;
	}

}
