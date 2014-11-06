package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

/**
 * The Interface KlantDAO.
 */
public interface KlantDAO {
	
	/**
	 * Gets the klanten xml.
	 *
	 * @return the klanten xml
	 */
	public ArrayList<Klant> getKlantenXML();
	
	/**
	 * Adds the klant xml.
	 *
	 * @param klant the klant
	 * @return true, if successful
	 */
	public boolean addKlantXML(Klant klant);

	/**
	 * Update klant xml.
	 *
	 * @param klant the klant
	 * @return true, if successful
	 */
	public boolean updateKlantXML(Klant klant);

	/**
	 * Verwijder klant xml.
	 *
	 * @param BSN the bsn
	 * @return true, if successful
	 */
	public boolean verwijderKlantXML(String BSN);

	/**
	 * Find klant xml.
	 *
	 * @param gebDatum the geb datum
	 * @return the array list
	 */
	public ArrayList<Klant> findKlantXML(String gebDatum);
	
	/**
	 * Gets the BS ns.
	 *
	 * @return the BS ns
	 */
	public ArrayList<String> getBSNs();

}
