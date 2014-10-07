package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class KlantDAO {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;
	
	public ArrayList<Klant> getKlantenXML(){
		return klantOverzicht;
	}
	
	public void updateKlantXML(Klant klant){
		this.klant = klant;
		
		//updaten van gegevens van een klant
	}
	
	public void verwijderKlantXML(Klant klant){
		this.klant = klant;
		
		//verwijder klant uit XML
	}
}
