package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.DAOFactoryKlant;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	private DAOFactoryKlant DAOFactory = new DAOFactoryKlant();
	private KlantDAO KlantDAO = new KlantDAOImpl();
	private VerzekeringPolisDAO polisDAO = new VerzekeringPolisDAOImpl();
	private String errorMessage;
	private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	
	public KlantManagerImpl(){
		DAOFactory.validateXML();
	}
	
	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze) {

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);
		
		errorMessage = checkKlant(klant);
		
		for(int i = 0; i <getBSNs().size();i++){
			if(getBSNs().get(i).equals(BSN)){
				errorMessage = errorMessage + "BSN is al bekend";
				break;
			}
		}
		
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
	
	public ArrayList<Klant> getKlanten() {
		// functie voor het ophalen van klanten
		return KlantDAO.getKlantenXML();
	}

	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze){
		
		Klant klant = new Klant(BSN,Naam,Adres,Postcode,Woonplaats,Geboortedatum,TelefoonNr,Email,RekeningNr,ResterendEigenRisico,VerzekeringPolissen,Betaalwijze); 
		errorMessage = checkKlant(klant);
		System.out.println(errorMessage);
		if (errorMessage == "") {
			// klant gegevens zijn correct ingevuld
			return KlantDAO.updateKlantXML(klant);

		} else {
			// fout melding weergeven in gui dat gegevens niet correct zijn
			// moet eigelijk errorMessage return'en
			return false;
		}
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

	public boolean verwijderKlantXML(String BSN) {
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
	
	//create Verzekering Polis nog aan maken
	
	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, String StartDatum, String EindDatum){
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer, VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.addVerzekeringPolisXML(BSN, polis);
	}
	
	public boolean updateVerzekeringPolisXML(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, String StartDatum, String EindDatum){
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer, VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.updateVerzekeringPolisXML(polis);
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

	public ArrayList<String> getBSNs(){
		new ArrayList<String>();
		
		return KlantDAO.getBSNs();
		
	}

	
}
