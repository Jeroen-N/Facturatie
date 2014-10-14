package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public interface KlantDAO {
	
	public ArrayList<Klant> getKlantenXML();
	
	public boolean addKlantXML(Klant klant);

	public boolean updateKlantXML(Klant klant);

	public boolean verwijderKlantXML(String BSN);

	public ArrayList<Klant> findKlantXML(String gebDatum);

	public boolean addPolisXML(String bSN, VerzekeringPolis polis);

}
