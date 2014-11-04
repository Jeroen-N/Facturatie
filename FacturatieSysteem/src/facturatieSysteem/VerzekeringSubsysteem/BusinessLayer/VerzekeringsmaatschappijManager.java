/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De interface van de verzekeringsmaatschappij manager die wordt geimplementeerd
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringsmaatschappijManager {
	
	public boolean addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	
	public  Verzekeringsmaatschappij getVerzekeringsmaatschappij(String nr);
	
	public boolean addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam);

	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);

	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen();

	boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);

	boolean updateVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);

	boolean updateVerzekeringstype(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type);
	
	public String checkVerzekering(String maatschappijnr, String Naam, String Adres, String Postcode, String Plaats, String KVK, String RekeningNr);
	
	public String checkType(String typenr, String EigenRisico, String Naam);
	
	public boolean checkKvk(Verzekeringsmaatschappij maatschappij);
	
	public Verzekeringstype getVerzekeringstypeByName(Verzekeringsmaatschappij maatschappij, String naam);

	String maatschappijInfo(Verzekeringsmaatschappij maatschappij);

	boolean deleteBehandelcode(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type, String behandelcode);

	boolean addBehandelcode(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type, String behandelcode);
}
