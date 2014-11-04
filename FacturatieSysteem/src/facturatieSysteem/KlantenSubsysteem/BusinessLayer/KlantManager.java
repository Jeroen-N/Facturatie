package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

// TODO: Auto-generated Javadoc
/**
 * The Interface KlantManager.
 */
public interface KlantManager extends Remote {
	/*
	 * Method to call the service name
	 */
	/** The Constant servicename. */
	public static final String servicename = "Hello";


	/**
	 * Creates the klant.
	 *
	 * @param BSN the bsn
	 * @param Naam the naam
	 * @param Adres the adres
	 * @param Postcode the postcode
	 * @param Woonplaats the woonplaats
	 * @param Geboortedatum the geboortedatum
	 * @param TelefoonNr the telefoon nr
	 * @param Email the email
	 * @param RekeningNr the rekening nr
	 * @param ResterendEigenRisico the resterend eigen risico
	 * @param VerzekeringPolissen the verzekering polissen
	 * @param Betaalwijze the betaalwijze
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException;

	/*
	 * Interface method for getting the clients
	 * @throws RemoteException
	 */
	/**
	 * Gets the klanten.
	 *
	 * @return the klanten
	 * @throws RemoteException the remote exception
	 */
	public ArrayList<Klant> getKlanten() throws RemoteException;

	/*
	 * Interface method for updating a client in the xml
	 * @throws RemoteException
	 */
	/**
	 * Update klant.
	 *
	 * @param BSN the bsn
	 * @param Naam the naam
	 * @param Adres the adres
	 * @param Postcode the postcode
	 * @param Woonplaats the woonplaats
	 * @param Geboortedatum the geboortedatum
	 * @param TelefoonNr the telefoon nr
	 * @param Email the email
	 * @param RekeningNr the rekening nr
	 * @param ResterendEigenRisico the resterend eigen risico
	 * @param VerzekeringPolissen the verzekering polissen
	 * @param Betaalwijze the betaalwijze
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException;

	/**
	 * Find klant.
	 *
	 * @param gebDatum the geb datum
	 * @return the array list
	 */
	public ArrayList<Klant> findKlant(String gebDatum);

	/**
	 * Toon klant.
	 *
	 * @param BSN the bsn
	 * @return the string
	 */
	public String toonKlant(String BSN);

	/**
	 * Toon polis.
	 *
	 * @param BSN the bsn
	 * @return the array list
	 */
	public ArrayList<String> toonPolis(String BSN);

	/**
	 * Gets the klant.
	 *
	 * @param BSN the bsn
	 * @return the klant
	 */
	public Klant getKlant(String BSN);

	/**
	 * Verwijder klant xml.
	 *
	 * @param BSN the bsn
	 * @return true, if successful
	 * @throws RemoteException the remote exception
	 */
	public boolean verwijderKlantXML(String BSN) throws RemoteException;

	/**
	 * Check klant.
	 *
	 * @param BSN the bsn
	 * @param Naam the naam
	 * @param Adres the adres
	 * @param Postcode the postcode
	 * @param Woonplaats the woonplaats
	 * @param Geboortedatum the geboortedatum
	 * @param TelefoonNr the telefoon nr
	 * @param Email the email
	 * @param RkNummer the rk nummer
	 * @param Betaalwijze the betaalwijze
	 * @return the string
	 */
	public String checkKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RkNummer, String Betaalwijze);

	/**
	 * Check polis.
	 *
	 * @param PolisNummer the polis nummer
	 * @param type the type
	 * @param StartDatum the start datum
	 * @param EindDatum the eind datum
	 * @return the string
	 */
	public String checkPolis(String PolisNummer, String type,
			String StartDatum, String EindDatum);

	/**
	 * Creates the polis.
	 *
	 * @param PolisNummer the polis nummer
	 * @param VerzekeringsType the verzekerings type
	 * @param ExtraEigenRisico the extra eigen risico
	 * @param StartDatum the start datum
	 * @param EindDatum the eind datum
	 * @return the verzekering polis
	 */
	public VerzekeringPolis createPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	/**
	 * Adds the verzekering polis xml.
	 *
	 * @param BSN the bsn
	 * @param PolisNummer the polis nummer
	 * @param VerzekeringsType the verzekerings type
	 * @param ExtraEigenRisico the extra eigen risico
	 * @param StartDatum the start datum
	 * @param EindDatum the eind datum
	 * @return true, if successful
	 */
	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	/**
	 * Update verzekering polis xml.
	 *
	 * @param PolisNummer the polis nummer
	 * @param VerzekeringsType the verzekerings type
	 * @param ExtraEigenRisico the extra eigen risico
	 * @param StartDatum the start datum
	 * @param EindDatum the eind datum
	 * @return true, if successful
	 */
	public boolean updateVerzekeringPolisXML(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	/**
	 * Delete verzekering polis xml.
	 *
	 * @param PolisNummer the polis nummer
	 * @param BSN the bsn
	 * @return true, if successful
	 */
	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN);

	/**
	 * Creates the polisnummer.
	 *
	 * @return the string
	 */
	public String createPolisnummer();

	/**
	 * Gets the BS ns.
	 *
	 * @return the BS ns
	 */
	public ArrayList<String> getBSNs();

}
