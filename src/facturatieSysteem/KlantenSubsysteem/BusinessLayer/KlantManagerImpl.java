package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	private DAOFactoryKlant DAOFactory = new DAOFactoryKlant();
	private KlantDAO KlantDAO = new KlantDAOImpl();
	private VerzekeringPolisDAO polisDAO = new VerzekeringPolisDAOImpl();
	private String errorMessage;
	private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	private ArrayList<String> polissen = new ArrayList<>();
	
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
		
			return KlantDAO.addKlantXML(klant);

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

			return KlantDAO.updateKlantXML(klant);

	}
	
	
	public ArrayList<Klant> findKlant(String gebDatum) {
		return KlantDAO.findKlantXML(gebDatum);
	}

	public Klant getKlant(String BSN){
		for (Klant klant : getKlanten()) {
			if (klant.getBSN().equals(BSN)) {
				return klant;
			}
		}
		return null;
	}
	
	
	public String toonKlant(String BSN) {
		for (Klant klant : getKlanten()) {
			if (klant.getBSN().equals(BSN)) {
				return klant.toString();
			}
		}
		return "niks gevonden";
	}
	
	public ArrayList<String> toonPolis(String BSN){
		polissen.clear();
		for (Klant klant : getKlanten()) {
			if (klant.getBSN().equals(BSN)) {
				for(int i = klant.getVerzekeringPolissen().size(); i > 0; i--){
					polissen.add((klant.getVerzekeringPolissen().get(i-1)).toString());
				}				
				return polissen;
			}
		}
		return null;
	}

	public boolean verwijderKlantXML(String BSN) {
		return KlantDAO.verwijderKlantXML(BSN);
	}

	public String checkKlant(String BSN, String Naam, String Adres, String Postcode, String Woonplaats, String Geboortedatum,String TelefoonNr, String Email, String RkNummer, String Betaalwijze) {
		errorMessage = "";
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn
		
		// BSN
		if (!BSN.matches("([0-9]{9})")) {
			if (BSN.length() < 1) {
				errorMessage = errorMessage + "\nBSN niet ingevuld";
			}
			else{
					errorMessage = errorMessage + "\nBSN niet correct";
			}
		}
		else{
			for(int i = 0; i <getBSNs().size();i++){
				if((getBSNs().get(i)).equals(BSN)){
				    errorMessage = errorMessage + "BSN is al bekend";
				    break;
				}
			}
		}
		// Naam
		if (Naam.length() < 1) {
			errorMessage = errorMessage + "\nNaam niet ingevuld";
		}
		
		// GeboorteDatum
		if (!Geboortedatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			if (Geboortedatum.length() < 1) {
				errorMessage = errorMessage + "\nGeboortedatum niet ingevuld";
			}else{
			errorMessage = errorMessage + "\nGeboortedatum niet correct ";
			}
		}
		
		// adres
		if (Adres.length() < 1) {
			errorMessage = errorMessage + "\nAdres niet ingevuld";
		}
		
		// Postcode
		if (!Postcode.matches("([0-9]{4})([A-Z]{2})")) {
			if (Postcode.length() < 1) {
				errorMessage = errorMessage + "\nPostcode niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nPostcode niet correct";
			}
		}
		
		//woonplaats
		if (Woonplaats.length() < 1) {
			errorMessage = errorMessage + "\nWoonplaats niet ingevuld";
		}
		
		// Telefoonnummer
		if (!TelefoonNr.matches("([0-9]{10})")
				|| !TelefoonNr.substring(0, 2).matches("06")) {
			if (TelefoonNr.length() < 1) {
				errorMessage = errorMessage + "\nTelefoonnummer niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nTelefoonnummer niet correct";
			}
		}
		
		// Email
		if (!Email.matches("(.+)([@]{1})(.+)([.]{1})(.+)")) {
			if (Email.length() < 1) {
				errorMessage = errorMessage + "\nEmailadres niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nEmailadres niet correct";
			}
		}
		
		//Betaalwijze
		if (Betaalwijze.length() < 1) {
			errorMessage = errorMessage + "\nEr is geen betaalwijze gekozen";
		}
		
		//RekeningNummer
		if (RkNummer.length() < 1) {
			errorMessage = errorMessage + "\nRekeningnummer niet ingevuld";
		}
		return errorMessage;
	}
	
	public String checkPolis(String PolisNummer, String type, String StartDatum, String EindDatum){
		errorMessage = "";
		// PolisNummer
		if (!PolisNummer.matches("([0-9A-Z]{6})")) {
			errorMessage = errorMessage + "\nPolisNummer niet correct";
		}
		
		if (type.length() < 1) {
			errorMessage = errorMessage + "\nGeen verzekeringstype gekozen";
		}
		
		// StartDatum
		// zet het jaar op het actuele jaar.
		if (!StartDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			if (StartDatum.length() < 1) {
				errorMessage = errorMessage + "\nStart datum niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nStart datum niet correct ";
			}			
		}
		
		// EindDatum
		// zorgen dat einddatum niet eerder is dan startdatum
		if (!EindDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			if (EindDatum.length() < 1) {
				errorMessage = errorMessage + "\nEind datum niet ingevuld";
			}
			else{
				errorMessage = errorMessage + "\nEind datum niet correct ";
			}	
		}
		return errorMessage;
	}
	
	public VerzekeringPolis createPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, String StartDatum, String EindDatum){
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer, VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polis;
	}
	
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
