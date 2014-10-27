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
	
	public  Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam);
	
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String Naam);

	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type);

	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen();

	boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);

	boolean updateVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);

	void updateVerzekeringstype(Verzekeringsmaatschappij maatschappij,
			Verzekeringstype type);
	
	public Verzekeringstype getVerzekeringstypeByName(Verzekeringsmaatschappij maatschappij, String naam);

	String maatschappijInfo(Verzekeringsmaatschappij maatschappij);
}
