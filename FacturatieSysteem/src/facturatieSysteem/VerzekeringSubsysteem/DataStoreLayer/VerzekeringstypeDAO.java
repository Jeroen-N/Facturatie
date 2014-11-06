package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

/**
 * The Interface VerzekeringstypeDAO.
 */
public interface VerzekeringstypeDAO {
	
	/**
	 * Adds the verzekeringstype xml.
	 *
	 * @param maatschappijnr het nr van de maatschappij waar het type aan wordt toegevoegd
	 * @param type the type
	 * @return true als het succesvol is toegevoegd
	 */
	boolean addVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);
	
	/**
	 * Delete verzekeringstype xml.
	 *
	 * @param maatschappijnr het nr van de maatschappij waar het type wordt geupdatet
	 * @param type the type
	 * @return true als het succesvol is geupdatet
	 */
	boolean deleteVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);
	
	/**
	 * Update verzekeringstype xml.
	 *
	 * @param maatschappijnr het nr van de maatschappij waar het type wordt verwijderd
	 * @param type the type
	 * @return true als het succesvol is verwijderd
	 */
	boolean updateVerzekeringstypeXML(String maatschappijnr,
			Verzekeringstype type);
	
	/**
	 * Adds the behandel code.
	 *
	 * @param maatschappijnr het nr van de maatschappij waar de behandelcode aan wordt toegevoegd
	 * @param typenr het typenr waar de behandelcode wordt toegevoegd
	 * @param behandelcode the behandelcode
	 * @return true als het succesvol is toegevoegd
	 */
	boolean addBehandelCode(String maatschappijnr, String typenr, String behandelcode);
	
	/**
	 * Removes the behandel code.
	 *
	 * @param maatschappijnr het nr van de maatschappij waar de behandelcode wordt verwijderd
	 * @param typenr het typenr waar de behandelcode wordt verwijderd
	 * @param behandelcode the behandelcode
	 * @return true als het succesvol is verwijderd
	 */
	boolean removeBehandelCode(String maatschappijnr, String typenr, String behandelcode);
}
