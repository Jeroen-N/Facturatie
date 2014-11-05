package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringstypeDAO {
	/*
	 * @param maatschappijnr het nr van de maatschappij waar het type aan wordt toegevoegd
	 * @param typenr het nr van het toe te voegen type
	 * @return true als het succesvol is toegevoegd
	 */
	boolean addVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);
	/*
	 * @param maatschappijnr het nr van de maatschappij waar het type wordt geupdatet
	 * @param typenr het nr van het te updaten type
	 * @return true als het succesvol is geupdatet
	 */
	boolean deleteVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);
	/*
	 * @param maatschappijnr het nr van de maatschappij waar het type wordt verwijderd
	 * @param typenr het nr van het te verwijderen type
	 * @return true als het succesvol is verwijderd
	 */
	boolean updateVerzekeringstypeXML(String maatschappijnr,
			Verzekeringstype type);
	/*
	 * @param maatschappijnr het nr van de maatschappij waar de behandelcode aan wordt toegevoegd
	 * @param typenr het typenr waar de behandelcode wordt toegevoegd
	 * @return true als het succesvol is toegevoegd
	 */
	boolean addBehandelCode(String maatschappijnr, String typenr, String behandelcode);
	/*
	 * @param maatschappijnr het nr van de maatschappij waar de behandelcode wordt verwijderd
	 * @param typenr het typenr waar de behandelcode wordt verwijderd
	 * @return true als het succesvol is verwijderd
	 */
	boolean removeBehandelCode(String maatschappijnr, String typenr, String behandelcode);
}
