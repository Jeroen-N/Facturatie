package facturatieSysteem.KlantenSubsysteem.BusinessLayer;

import java.util.ArrayList;
import java.util.Date;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface KlantManager {

	public ArrayList<Klant> getKlant(Date Gebdatum);

	public Klant toonKlant(String BSN);

	public void verwijderKlantXML(String BSN);

	public void checkKlant(Klant klant);

	public ArrayList<Klant> klanten();

	public Klant klant();

}
