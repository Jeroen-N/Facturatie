package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class KlantDAOImpl implements KlantDAO {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;

	public boolean addKlantXML(Klant klant){
		this.klant = klant;
		return false;
	}
	
	public ArrayList<Klant> getKlantenXML() {
		return klantOverzicht;
	}

	public boolean updateKlantXML(Klant klant) {
		this.klant = klant;
		return false;

		// updaten van gegevens van een klant
	}

	public boolean verwijderKlantXML(Klant klant) {
		this.klant = klant;
		return false;
		
		// verwijder klant uit XML
	}
}
