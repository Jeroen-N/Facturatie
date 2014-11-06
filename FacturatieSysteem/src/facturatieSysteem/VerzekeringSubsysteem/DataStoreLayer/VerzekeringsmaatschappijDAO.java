package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

/**
 * The Interface VerzekeringsmaatschappijDAO.
 */
public interface VerzekeringsmaatschappijDAO {
	
	/**
	 * Gets the maatschappijen xml.
	 *
	 * @return een arraylist van alle maatschappijen
	 */
	public ArrayList<Verzekeringsmaatschappij> getMaatschappijenXML();
	
	/**
	 * Adds the maatschappij xml.
	 *
	 * @param maatschappij de toe te voegen maatschappij
	 * @return true als het succesvol is toegevoegd
	 */
	public boolean addMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	
	/**
	 * Update maatschappij xml.
	 *
	 * @param maatschappij de aan te passen maatschappij
	 * @return true als het succesvol is aangepast
	 */
	public boolean updateMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	
	/**
	 * Delete maatschappij xml.
	 *
	 * @param naam de naam van de te verwijderen maatschappij
	 * @return true als het succesvol is verwijderd
	 */
	public boolean deleteMaatschappijXML(String naam);
}
