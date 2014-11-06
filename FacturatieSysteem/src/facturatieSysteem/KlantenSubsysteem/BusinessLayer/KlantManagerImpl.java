package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import Shared.KlantManagerIFrmi;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.DAOFactoryKlant;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

/**
 * The Class KlantManagerImpl.
 */
public class KlantManagerImpl implements KlantManager, KlantManagerIFrmi {
	
	/** The klant. */
	private Klant klant;
	
	/** The DAO factory. */
	private DAOFactoryKlant DAOFactory = new DAOFactoryKlant();
	
	/** The Klant dao. */
	private KlantDAO KlantDAO = new KlantDAOImpl();
	
	/** The polis dao. */
	private VerzekeringPolisDAO polisDAO = new VerzekeringPolisDAOImpl();
	
	/** The error message. */
	private String errorMessage;
	
	/** The Constant CHARSET_AZ_09. */
	private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			.toCharArray();
	
	/** The polissen. */
	private ArrayList<String> polissen = new ArrayList<>();

	/**
	 * Instantiates a new klant manager impl.
	 */
	public KlantManagerImpl() {
		DAOFactory.validateXML();
	}

	/**
	 * Create the klant by requesting the addKlantXML from the DAO
	 * 
	 * @param String BSN
	 * @param String Naam
	 * @param String Adres
	 * @param String Postcode
	 * @param String Woonplaats
	 * @param String Geboortedatum
	 * @param String TelefoonNr
	 * @param String Email
	 * @param String RekeningNr
	 * @param double ResterendEigenRisico
	 * @param ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze
	 */
	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze){

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);

		return KlantDAO.addKlantXML(klant);

	}

	/**
	 * get the klanten by calling the getKlantenXML methode from the KlantDAO
	 * 
	 * @return ArrayList<Klant> filled with klanten
	 */
	public ArrayList<Klant> getKlanten(){
		// functie voor het ophalen van klanten
		return KlantDAO.getKlantenXML();
	}

	/**
	 * update the klant by requesting the updateKlantXML from the DAO
	 * 
	 * @param String BSN
	 * @param String Naam
	 * @param String Adres
	 * @param String Postcode
	 * @param String Woonplaats
	 * @param String Geboortedatum
	 * @param String TelefoonNr
	 * @param String Email
	 * @param String RekeningNr
	 * @param double ResterendEigenRisico
	 * @param ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze
	 * 
	 * @return boolean if the method has failed or passed
	 */
	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze){

		Klant klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);

		return KlantDAO.updateKlantXML(klant);

	}

	/**
	 * Find the klant by using his birthdate
	 * 
	 * @param String gebDatum
	 * @return ArrayList<Klant> 
	 */
	public ArrayList<Klant> findKlant(String gebDatum) {
		return KlantDAO.findKlantXML(gebDatum);
	}

	/**
	 * Get the klant by using his BSN
	 * 
	 * @param String BSN
	 * @return klant when going in the if otherwise null
	 */
	public Klant getKlant(String BSN) {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant;
				}
			}
		
		return null;
	}

	/**
	 * Toon the klant using his BSN
	 * 
	 * @param String BSN
	 * @return the klant information to string
	 */
	public String toonKlant(String BSN) {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant.toString();
				}
			}
		return "niks gevonden";
	}

	/**
	 * toon the polis by searching for it using the BSN
	 * 
	 * @param String BSN
	 * @return ArrayList of the polissen
	 */
	public ArrayList<String> toonPolis(String BSN) {
		polissen.clear();
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					for (int i = klant.getVerzekeringPolissen().size(); i > 0; i--) {
						polissen.add((klant.getVerzekeringPolissen().get(i - 1))
								.toString());
					}
					return polissen;
				}
			}
		return null;
	}
	
	/**
	 * Checkt of een type in gebruik is
	 */
	public boolean typeGebruikt(String typenaam){
		for(Klant klant : getKlanten()){
			for(VerzekeringPolis polis : klant.getVerzekeringPolissen()){
				if(polis.getVerzekeringsType().equals(typenaam)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * verwijder de klant in de xml
	 * 
	 * @param String BSN
	 * @return boolean if failed or not
	 */
	public boolean verwijderKlantXML(String BSN){
		return KlantDAO.verwijderKlantXML(BSN);
	}

	/**
	 * check the klant
	 */
	public String checkKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RkNummer, String Betaalwijze) {
		errorMessage = "";

		// BSN
		if (!BSN.matches("([0-9]{9})")) {
			if (BSN.length() < 1) {
				errorMessage = errorMessage + "\nBSN niet ingevuld";
			} else {
				errorMessage = errorMessage + "\nBSN niet correct";
			}
		} else {
			for (int i = 0; i < getBSNs().size(); i++) {
				if ((getBSNs().get(i)).equals(BSN)) {
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
			} else {
				errorMessage = errorMessage + "\nGeboortedatum niet correct ";
			}
		} else {
			Date gbDatum;
			try {
				gbDatum = new SimpleDateFormat("dd-MM-yyyy")
						.parse(Geboortedatum);
				Date date = new Date();
				if (gbDatum.after(date)) {
					errorMessage = errorMessage
							+ "\nDe geboortedatum kan niet in de toekomst zijn";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			} else {
				errorMessage = errorMessage + "\nPostcode niet correct";
			}
		}

		// woonplaats
		if (Woonplaats.length() < 1) {
			errorMessage = errorMessage + "\nWoonplaats niet ingevuld";
		}

		// Telefoonnummer
		if (!TelefoonNr.matches("([0-9]{10})")
				|| !TelefoonNr.substring(0, 2).matches("06")) {
			if (TelefoonNr.length() < 1) {
				errorMessage = errorMessage + "\nTelefoonnummer niet ingevuld";
			} else {
				errorMessage = errorMessage + "\nTelefoonnummer niet correct";
			}
		}

		// Email
		if (!Email.matches("(.+)([@]{1})(.+)([.]{1})(.+)")) {
			if (Email.length() < 1) {
				errorMessage = errorMessage + "\nEmailadres niet ingevuld";
			} else {
				errorMessage = errorMessage + "\nEmailadres niet correct";
			}
		}

		// Betaalwijze
		if (Betaalwijze.length() < 1) {
			errorMessage = errorMessage + "\nEr is geen betaalwijze gekozen";
		}

		// RekeningNummer
		if (RkNummer.length() < 1) {
			errorMessage = errorMessage + "\nRekeningnummer niet ingevuld";
		}
		return errorMessage;
	}

	/**
	 * check polis
	 */
	public String checkPolis(String PolisNummer, String type,
			String StartDatum, String EindDatum) {
		errorMessage = "";
		// PolisNummer
		if (!PolisNummer.matches("([0-9A-Z]{6})")) {
			errorMessage = errorMessage + "\nPolisNummer niet correct";
		}

		if (type.length() < 1) {
			errorMessage = errorMessage + "\nGeen verzekeringstype gekozen";
		}

		// StartDatum
		if (!StartDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			if (StartDatum.length() < 1) {
				errorMessage = errorMessage + "\nStart datum niet ingevuld";
			} else {
				errorMessage = errorMessage + "\nStart datum niet correct ";
			}
		}

		// EindDatum
		if (!EindDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			if (EindDatum.length() < 1) {
				errorMessage = errorMessage + "\nEind datum niet ingevuld";
			} else {
				errorMessage = errorMessage + "\nEind datum niet correct ";
			}
		}

		if (StartDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")
				&& EindDatum.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
			try {
				Date startDate = new SimpleDateFormat("dd-MM-yyyy")
						.parse(StartDatum);
				Date endDate = new SimpleDateFormat("dd-MM-yyyy")
						.parse(EindDatum);

				if (startDate.after(endDate)) {
					errorMessage = errorMessage
							+ "\nDe einddatum is eerder dan de startdatum";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return errorMessage;
	}

	/**
	 * Create the polis
	 * 
	 * @param String PolisNummer
	 * @param String VerzekeringsType
	 * @param double ExtraEigenRisico
	 * @param String StartDatum
	 * @param String EindDatum
	 * 
	 * @return polis
	 */
	public VerzekeringPolis createPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polis;
	}

	/**
	 * add verzekeringPolis to the XML
	 * 
	 * @param String PolisNummer
	 * @param String VerzekeringsType
	 * @param double ExtraEigenRisico
	 * @param String StartDatum
	 * @param String EindDatum
	 * 
	 * @return boolean to see if it failed
	 */
	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.addVerzekeringPolisXML(BSN, polis);
	}

	/**
	 * update the verzekeringPolis in the xml
	 * 
	 * @param String PolisNummer
	 * @param String VerzekeringsType
	 * @param double ExtraEigenRisico
	 * @param String StartDatum
	 * @param String EindDatum
	 * 
	 * @return boolean to see if failed
	 */
	public boolean updateVerzekeringPolisXML(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.updateVerzekeringPolisXML(polis);
	}

	/**
	 * delete the polis from the xml
	 * 
	 * @param string polisnummer
	 * @param string bsn
	 * 
	 * @return boolean if done
	 */
	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN) {
		return polisDAO.verwijderPolisXML(PolisNummer, BSN);
	}

	/**
	 * method to create the polisnumber
	 * 
	 * @return the number as string
	 */
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

	/**
	 * Get the bsn's in a ArrayList of strings
	 * 
	 * @retun getBSNs from the KlantDAO
	 */
	public ArrayList<String> getBSNs() {
		new ArrayList<String>();

		return KlantDAO.getBSNs();

	}

	/**
	 * Method to give the klanten to B1
	 * 
	 * @return ArrayList<ArrayList<String>> klanten
	 */
	public ArrayList<ArrayList<String>> getKlantenRMI() throws RemoteException{
		ArrayList<ArrayList<String>> klanten = new ArrayList<ArrayList<String>>();
		for(Klant klant : getKlanten()){
			ArrayList<String> klant1 = new ArrayList<String>();
			klant1.add(klant.getBSN());// ID 0 
			klant1.add(klant.getNaam());// ID 1
			klant1.add(klant.getGeboortedatum());// ID 2
			klant1.add(klant.getAdres());// ID 3
			klant1.add(klant.getWoonplaats());// ID 4
			klant1.add(klant.getPostcode());// ID 5
			klant1.add(klant.getTelefoonnummer());// ID 6
			klant1.add(klant.getEmail());// ID 7
			klanten.add(klant1);
		}
		return klanten;
	}
}
