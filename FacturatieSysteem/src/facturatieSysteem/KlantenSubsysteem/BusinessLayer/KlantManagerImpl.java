package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.rmi.RemoteException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.DAOFactoryKlant;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAOImpl;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAO;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.VerzekeringPolisDAOImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

// TODO: Auto-generated Javadoc
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

	/*
	 * 
	 */
	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#createKlant(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.util.ArrayList, java.lang.String)
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#getKlanten()
	 */
	public ArrayList<Klant> getKlanten(){
		// functie voor het ophalen van klanten
		return KlantDAO.getKlantenXML();
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#updateKlant(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, double, java.util.ArrayList, java.lang.String)
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#findKlant(java.lang.String)
	 */
	public ArrayList<Klant> findKlant(String gebDatum) {
		return KlantDAO.findKlantXML(gebDatum);
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#getKlant(java.lang.String)
	 */
	public Klant getKlant(String BSN) {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant;
				}
			}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#toonKlant(java.lang.String)
	 */
	public String toonKlant(String BSN) {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant.toString();
				}
			}
		return "niks gevonden";
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#toonPolis(java.lang.String)
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#verwijderKlantXML(java.lang.String)
	 */
	public boolean verwijderKlantXML(String BSN){
		return KlantDAO.verwijderKlantXML(BSN);
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#checkKlant(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String checkKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RkNummer, String Betaalwijze) {
		errorMessage = "";
		// nog toe tevoegen:
		// controleer de waardes die ingevuld zijn

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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#checkPolis(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#createPolis(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	public VerzekeringPolis createPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polis;
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#addVerzekeringPolisXML(java.lang.String, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.addVerzekeringPolisXML(BSN, polis);
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#updateVerzekeringPolisXML(java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	public boolean updateVerzekeringPolisXML(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.updateVerzekeringPolisXML(polis);
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#deleteVerzekeringPolisXML(java.lang.String, java.lang.String)
	 */
	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN) {
		return polisDAO.verwijderPolisXML(PolisNummer, BSN);
	}

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#createPolisnummer()
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

	/* (non-Javadoc)
	 * @see facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager#getBSNs()
	 */
	public ArrayList<String> getBSNs() {
		new ArrayList<String>();

		return KlantDAO.getBSNs();

	}

}
