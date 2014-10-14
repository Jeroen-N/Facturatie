package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	private ArrayList<Klant> zoekresultaat;
	private KlantDAO KlantDAO = new KlantDAOImpl();
	private String errorMessage;
	private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			.toCharArray();

	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze) {

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);

		errorMessage = checkKlant(klant);
		System.out.println(errorMessage);
		if (errorMessage == "") {
			// klant gegevens zijn correct ingevuld
			return KlantDAO.addKlantXML(klant);

		} else {
			// fout melding weergeven in gui dat gegevens niet correct zijn
			// moet eigelijk errorMessage return'en
			return false;
		}
	}

	@Override
	public ArrayList<Klant> getKlanten() {

		// functie voor het ophalen van klanten
		return KlantDAO.getKlantenXML();

	}

	public ArrayList<Klant> findKlant(String gebDatum) {
		return KlantDAO.findKlantXML(gebDatum);

	}

	public String toonKlant(String BSN) {
		for (Klant klant : getKlanten()) {
			if (klant.getBSN().equals(BSN)) {
				return klant.toString();
			}
		}
		return "niks gevonden";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#
	 * verwijderKlantXML(java.lang.String)
	 */
	public boolean verwijderKlantXML(String BSN) {
		// functie voor het verwijderen van een klant uit xml

		// nog toe tevoegen:
		return KlantDAO.verwijderKlantXML(BSN);

	}

	public String checkKlant(Klant klant) {
		errorMessage = "";
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn
		// BSN
		if (!klant.getBSN().matches("([0-9]{9})")) {
			errorMessage = errorMessage + "\nBSN niet correct";
		}
		// Postcode
		if (!klant.getPostcode().matches("([0-9]{4})([A-Z]{2})")) {
			errorMessage = errorMessage + "\nPostcode niet correct";
		}
		// Woonplaats
		if (!klant.getWoonplaats().matches("([A-Z]{1})([a-z]{1,})")) {
			errorMessage = errorMessage + "\nWoonplaats niet correct ";
		}
		// GeboorteDatum
		if (!klant.getGeboortedatum().matches(
				"([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			errorMessage = errorMessage + "\nGeboortedatum niet correct ";
		}
		// Telefoonnummer
		if (!klant.getTelefoonnummer().matches("([0-9]{10})")
				|| !klant.getTelefoonnummer().substring(0, 2).matches("06")) {
			errorMessage = errorMessage + "\nTelefoonnummer niet correct";
		}
		// Email
		if (!klant.getEmail().matches("(.+)([@]{1})(.+)([.]{1})(.+)")) {
			errorMessage = errorMessage + "\nEmail niet correct";
		}
		VerzekeringPolissen = klant.getVerzekeringPolissen();
		// PolisNummer

		for(VerzekeringPolis polis : VerzekeringPolissen){
		if (!polis.getPolisNummer().matches("([0-9A-Z]{6})")) {
			errorMessage = errorMessage + "\nPolisNummer niet correct";
		}
		
		// verzekeringType
		if (!polis.getVerzekeringsType().matches("([0-9]{3})")) {
			errorMessage = errorMessage + "\nVerzekeringsType niet correct";
		}
		
		// StartDatum
		// zet het jaar op het actuele jaar.
		if (!polis.getStartDatum().matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			errorMessage = errorMessage + "\nStart datum niet correct ";
		}
		
		// EindDatum
		// zorgen dat einddatum niet eerder is dan startdatum
		if (!polis.getEindDatum().matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			errorMessage = errorMessage + "\nEind datum niet correct ";
		}

		}
		// System.out.println(errorMessage);
		return errorMessage;
	}

	/*
	 * 
	 */
	public boolean WijzigVerzekeringPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico, Date StartDatum,
			Date EindDatum) {
		// zoek de bestaande polis door middel van het PolisNummer

		// voer de neuwe gegevens in, in de plaats van de oude gegevens.

		return true;
	}

	/*
	 * 
	 */
	public boolean RemoveVerzekeringPolis(String PolisNummer) {
		// zoek de polis door middel van het polisnummer

		// verwijder de polis

		return false;

	}

	public String createPolisnummer() {
		Random random = new SecureRandom();
		char[] result = new char[6];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(CHARSET_AZ_09.length);
			result[i] = CHARSET_AZ_09[randomCharIndex];
		}
		for (Klant klant : getKlanten()) {
			for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
				if (polis.getPolisNummer().equals(result)) {
					createPolisnummer();
				} else {
					return new String(result);
				}
			}
		}
		return "EEEEEE";

	}

}
