/** @author Jeroen Nuijten
 * @version 0.2
 * 
 * De interface van de verzekeringsmaatschappij manager die wordt geimplementeerd
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringsmaatschappijManager {
	
	/**
	 * @param maatschappij de toe te voegen maatschappij
	 * @return boolean of de maatschappij is toegevoegd
	 */
	boolean addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	/**
	 * @param nr Het nummer van de maatschappij
	 * @return De maatschappij die is opgevraagd
	 */
	Verzekeringsmaatschappij getVerzekeringsmaatschappij(String nr);
	/**
	 * @param maatschappij de maatschappij waar de type aan wordt toegevoegd
	 * @param type de toe te voegen verzekeringstype
	 * @return boolean of de maatschappij is toegevoegd
	 */
	boolean addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);
	/**
	 * @param maatschappij de toe te voegen maatschappij
	 * @param nr Het nummer van de op te zoeken type
	 * @return het opgezochte type
	 */
	Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String nr);
	/**
	 * @param maatschappij de toe te voegen maatschappij
	 * @param Naam De naam van de op te zoeken type
	 * @return het opgezochte type
	 */
	Verzekeringstype getVerzekeringstypeByName(Verzekeringsmaatschappij maatschappij, String naam);
	/**
	 * @param maatschappij de maatschappij waar het type wordt verwijderd
	 * @param Naam De naam van het type dat wordt verwijderd
	 * @return boolean of het type is verwijderd
	 */
	boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);
	/**
	 * @return Alle verzekeringsmaatschappijen
	 */
	ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen();
	/**
	 * @param maatschappij de te verwijderen maatschappij
	 * @return boolean of de maatschappij wel of niet succesvol is verwijderd
	 */
	boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	/**
	 * @param maatschappij de te updaten maatschappij
	 * @return boolean of de maatschappij wel of niet succesvol is geupdatet
	 */
	boolean updateVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	/**
	 * @param maatschappij de maatschappij van het te updaten type
	 * @param type de type die moet worden geupdate
	 * @return boolean of de maatschappij wel of niet succesvol is geupdatet
	 */
	boolean updateVerzekeringstype(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type);
	/**
	 * @return Checkt of de verzekering parameters kloppen
	 */
	public String checkVerzekering(String maatschappijnr, String Naam, String Adres, String Postcode, String Plaats, String KVK, String RekeningNr);
	/**
	 * @return Checkt of de type parameters kloppen
	 */
	public String checkType(String typenr, String EigenRisico, String Naam);
	
	/**
	 * @return Checkt of het KVK nummer klopt
	 */
	public boolean checkKvk(Verzekeringsmaatschappij maatschappij);
	
	/**
	 * @param maatschappij de maatschappij waar info over wordt opgevraagd
	 * @return De informatie van de verzekeringsmaatschappij
	 */
	String maatschappijInfo(Verzekeringsmaatschappij maatschappij);

	/**
	 * @param maatschappij de maatschappij van het type
	 * @param type het type waar de behandelcode wordt verwijderd
	 * @param behandelcode de behandelcode die wordt verwijderd
	 * @return de boolean die aangeeft of het wel of niet is gelukt
	 */
	boolean deleteBehandelcode(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type, String behandelcode);
	/**
	 * @param maatschappij de maatschappij van het type
	 * @param type het type waar de behandelcode aan wordt toegevoegd
	 * @param behandelcode de behandelcode die wordt toegevoegd
	 * @return de boolean die aangeeft of het wel of niet is gelukt
	 */
	boolean addBehandelcode(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type, String behandelcode);
	/**
	 * @param lijst de lijst met verzekeringsmaatschappijen die in de manager moet worden opgenomen
	 */
	void importData(ArrayList<Verzekeringsmaatschappij> lijst);
}
