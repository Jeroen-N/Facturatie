package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

/**
 * The Interface VerzekeringPolisDAO.
 */
public interface VerzekeringPolisDAO {

	/**
	 * Adds the verzekering polis xml.
	 *
	 * @param BSN the bsn
	 * @param polis the polis
	 * @return true, if successful
	 */
	public boolean addVerzekeringPolisXML(String BSN, VerzekeringPolis polis);

	/**
	 * Update verzekering polis xml.
	 *
	 * @param polis the polis
	 * @return true, if successful
	 */
	public boolean updateVerzekeringPolisXML(VerzekeringPolis polis);

	/**
	 * Verwijder polis xml.
	 *
	 * @param Polisnummer the polisnummer
	 * @param BSN the bsn
	 * @return true, if successful
	 */
	boolean verwijderPolisXML(String Polisnummer, String BSN);
	
}
