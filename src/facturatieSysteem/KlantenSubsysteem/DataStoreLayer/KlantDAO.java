package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.DOMException;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface KlantDAO {
	
	public ArrayList<Klant> getKlantenXML();
	
	public boolean addKlantXML(Klant klant);

	public boolean updateKlantXML(Klant klant);

	public boolean verwijderKlantXML(String BSN);

	public ArrayList<Klant> findKlantXML(Date gebDatum) throws DOMException, ParseException;
	
	public ArrayList<String> getBSNs();

}
