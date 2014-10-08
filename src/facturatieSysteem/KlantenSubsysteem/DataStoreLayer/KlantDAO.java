package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface KlantDAO {

	//ik weet niet waarom dit static is maar anders krijg ik foutmeldingen.
	
	public static ArrayList<Klant> getKlantenXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
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
