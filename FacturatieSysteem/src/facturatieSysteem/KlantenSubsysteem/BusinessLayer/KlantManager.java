package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface KlantManager extends Remote {

	public static final String servicename = "Hello";

	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException;

	public ArrayList<Klant> getKlanten() throws RemoteException;

	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze)
			throws RemoteException;

	public ArrayList<Klant> findKlant(String gebDatum);

	public String toonKlant(String BSN);

	public ArrayList<String> toonPolis(String BSN);

	public Klant getKlant(String BSN);

	public boolean verwijderKlantXML(String BSN) throws RemoteException;

	public String checkKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RkNummer, String Betaalwijze);

	public String checkPolis(String PolisNummer, String type,
			String StartDatum, String EindDatum);

	public VerzekeringPolis createPolis(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	public boolean updateVerzekeringPolisXML(String PolisNummer,
			String VerzekeringsType, double ExtraEigenRisico,
			String StartDatum, String EindDatum);

	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN);

	public String createPolisnummer();

	public ArrayList<String> getBSNs();

}
