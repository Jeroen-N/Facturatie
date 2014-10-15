package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface KlantManager {
	
	public  ArrayList<Klant> getKlanten();
	
	public boolean createKlant(String BSN, String Naam, String Adres,
			String Postcode, String Woonplaats, String Geboortedatum,
			String TelefoonNr, String Email, String RekeningNr,
			double ResterendEigenRisico,
			ArrayList<VerzekeringPolis> VerzekeringPolissen, String Betaalwijze);

	public String toonKlant(String BSN);

	public boolean verwijderKlantXML(String BSN);

	public String checkKlant(Klant klant);

	public ArrayList<Klant> findKlant(String gebDatum);
	
	public String createPolisnummer();

	public boolean addVerzekeringPolis(String BSN, VerzekeringPolis polis);
	
	public boolean updateKlant(Klant klant);
}
