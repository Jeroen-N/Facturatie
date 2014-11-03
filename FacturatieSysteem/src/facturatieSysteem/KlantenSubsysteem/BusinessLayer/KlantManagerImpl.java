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

public class KlantManagerImpl implements KlantManager {
	private Klant klant;
	private DAOFactoryKlant DAOFactory = new DAOFactoryKlant();
	private KlantDAO KlantDAO = new KlantDAOImpl();
	private VerzekeringPolisDAO polisDAO = new VerzekeringPolisDAOImpl();
	private String errorMessage;
	private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
			.toCharArray();
	private ArrayList<String> polissen = new ArrayList<>();

	public KlantManagerImpl() {
		DAOFactory.validateXML();
	}

	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException {

		klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);

		return KlantDAO.addKlantXML(klant);

	}

	public ArrayList<Klant> getKlanten() throws RemoteException {
		// functie voor het ophalen van klanten
		return KlantDAO.getKlantenXML();
	}

	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException {

		Klant klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats,
				Geboortedatum, TelefoonNr, Email, RekeningNr,
				ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);

		return KlantDAO.updateKlantXML(klant);

	}

	public ArrayList<Klant> findKlant(String gebDatum) {
		return KlantDAO.findKlantXML(gebDatum);
	}

	public Klant getKlant(String BSN) {
		try {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String toonKlant(String BSN) {
		try {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					return klant.toString();
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "niks gevonden";
	}

	public ArrayList<String> toonPolis(String BSN) {
		polissen.clear();
		try {
			for (Klant klant : getKlanten()) {
				if (klant.getBSN().equals(BSN)) {
					for (int i = klant.getVerzekeringPolissen().size(); i > 0; i--) {
						polissen.add((klant.getVerzekeringPolissen().get(i - 1))
								.toString());
					}
					return polissen;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean verwijderKlantXML(String BSN) throws RemoteException{
		return KlantDAO.verwijderKlantXML(BSN);
	}

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

	public VerzekeringPolis createPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polis;
	}

	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.addVerzekeringPolisXML(BSN, polis);
	}

	public boolean updateVerzekeringPolisXML(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum) {
		VerzekeringPolis polis = new VerzekeringPolis(PolisNummer,
				VerzekeringsType, ExtraEigenRisico, StartDatum, EindDatum);
		return polisDAO.updateVerzekeringPolisXML(polis);
	}

	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN) {
		return polisDAO.verwijderPolisXML(PolisNummer, BSN);
	}

	public String createPolisnummer() {
		Random random = new SecureRandom();
		char[] result = new char[6];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(CHARSET_AZ_09.length);
			result[i] = CHARSET_AZ_09[randomCharIndex];
		}
		try {
			for (Klant klant : getKlanten()) {
				for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
					if (polis.getPolisNummer().equals(result)) {
						createPolisnummer();
					} else {
						return new String(result);
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "EEEEEE";
	}

	public ArrayList<String> getBSNs() {
		new ArrayList<String>();

		return KlantDAO.getBSNs();

	}

}
