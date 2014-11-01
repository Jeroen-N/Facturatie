package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.DOMException;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface KlantManager {
	
	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, Date Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze);
	
	public  ArrayList<Klant> getKlanten();
	
	public boolean updateKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, Date Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze);

	public ArrayList<Klant> findKlant(Date gebDatum);
	
	public String toonKlant(String BSN);
	
	public ArrayList<String> toonPolis(String BSN);
	
	public Klant getKlant(String BSN);
	
	public boolean verwijderKlantXML(String BSN);
	
	public String checkKlant(String BSN, String Naam, String Adres, String Postcode, String Woonplaats, Date Geboortedatum ,String TelefoonNr, String Email, String RkNummer, String Betaalwijze);
	
	public String checkPolis(String PolisNummer, String type, String StartDatum, String EindDatum);
	
	public VerzekeringPolis createPolis(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum);
	
	public boolean addVerzekeringPolisXML(String BSN, String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum);
	
	public boolean updateVerzekeringPolisXML(String PolisNummer, String VerzekeringsType, double ExtraEigenRisico, Date StartDatum, Date EindDatum);
	
	public boolean deleteVerzekeringPolisXML(String PolisNummer, String BSN);
	
	public String createPolisnummer();
	
	public ArrayList<String> getBSNs();
	
	
}
