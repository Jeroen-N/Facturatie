package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface KlantDAO {

	public ArrayList<Klant> getKlantenXML();
	
	public static boolean addKlantXML(Klant klant) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateKlantXML(Klant klant);

	public static boolean verwijderKlantXML(Klant klant) {
		// TODO Auto-generated method stub
		return false;
	}
}
