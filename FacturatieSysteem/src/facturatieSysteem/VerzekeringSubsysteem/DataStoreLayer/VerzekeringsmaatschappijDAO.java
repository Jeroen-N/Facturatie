package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public interface VerzekeringsmaatschappijDAO {
	/*
	 * @return een arraylist van alle maatschappijen
	 */
	public ArrayList<Verzekeringsmaatschappij> getMaatschappijenXML();
	/*
	 * @param maatschappij de toe te voegen maatschappij
	 * @return true als het succesvol is toegevoegd
	 */
	public boolean addMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	/*
	 * @param maatschappij de aan te passen maatschappij
	 * @return true als het succesvol is aangepast
	 */
	public boolean updateMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	/*
	 * @param naam de naam van de te verwijderen maatschappij
	 * @return true als het succesvol is verwijderd
	 */
	public boolean deleteMaatschappijXML(String naam);
}
