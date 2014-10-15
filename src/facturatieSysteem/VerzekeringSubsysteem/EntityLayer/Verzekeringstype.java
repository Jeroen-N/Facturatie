/* @author Jeroen Nuijten
 * @version 0.1
 * 
 * Realiseert de verzekeringstype klasse, deze onderscheid de typen verzekeringen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

public class Verzekeringstype {
	private int VerzekeringsTypeID;
	private int EigenRisico;
	private String Naam;
	private Verzekeringsmaatschappij maatschappij;
	
	/*
	 * @param VerzekeringsTypeID Het ID van de verzekeringstype
	 * @param EigenRisico Het eigen risico van het type verzekering
	 * @param behandelcodes de behandelcodes
	 */
	public Verzekeringstype(int VerzekeringsTypeID, int EigenRisico, String Naam){
		this.VerzekeringsTypeID = VerzekeringsTypeID;
		this.EigenRisico = EigenRisico;
		this.Naam = Naam;
	}
	
	public int getID(){
		return VerzekeringsTypeID;
	}
	/*
	 * @return Het eigen risico van het verzekeringstype
	 */
	public int getEigenRisico(){
		return EigenRisico;
	}
	
	public String getNaam(){
		return Naam;
	}
	
	public void setNaam(String Naam){
		this.Naam = Naam;
	}
}
