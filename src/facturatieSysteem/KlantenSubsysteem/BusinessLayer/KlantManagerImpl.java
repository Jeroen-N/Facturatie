package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;
	private ArrayList<Klant> zoekresultaat;
	private KlantDAO KlantDAO;


	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, Date Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr, double ResterendEigenRisico,
			VerzekeringPolis Verzekering, String Betaalwijze) {

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr, ResterendEigenRisico, Verzekering,
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

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#getKlanten()
	 */
	@Override
	public ArrayList<Klant> getKlanten() {
		// functie voor het ophalen van klanten

		// nog toe tevoegen:

		KlantDAOImpl klantDAO = new KlantDAOImpl();
		return KlantDAO.getKlantenXML();

	}

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#getKlant(java.util.Date)
	 */
	@Override
	public ArrayList<Klant> getKlant(Date Gebdatum) {
		// functio voor het zoeken van klanten

		// nog toe tevoegen:
		// zoek klanten uit arraylist op bais van de geboortedatum
		return zoekresultaat;
	}

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#toonKlant(java.lang.String)
	 */
	public Klant toonKlant(String BSN) {
		// functie voor het selecteren van een klant

		// nog toe tevoegen:
		// zoek klant uit de arraylist op basis van het BSN

		return klant;

	}

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#verwijderKlantXML(java.lang.String)
	 */
	public boolean verwijderKlantXML(String BSN) {
		// functie voor het verwijderen van een klant uit xml

		// nog toe tevoegen:
		if (KlantDAO.verwijderKlantXML(klant)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#checkKlant(facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant)
	 */
	public boolean checkKlant(Klant klant) {
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#klanten()
	 */
	@Override
	public ArrayList<Klant> klanten() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 
	 */
	public boolean WijzigVerzekeringPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum){
		//zoek de bestaande polis door middel van het PolisNummer	
		
		//voer de neuwe gegevens in, in de plaats van de oude gegevens.
		
		return true;
	}
	
	/*
	 * 
	 */
	public boolean RemoveVerzekeringPolis(String PolisNummer){
		//zoek de polis door middel van het polisnummer
		
		//verwijder de polis
		
		return false;
		
	}

}
