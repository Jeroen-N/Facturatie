/* @author Jeroen Nuijten
 * @version 0.1
 * 
 * Realiseert de verzekeringstype klasse, deze onderscheid de typen verzekeringen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

public class Verzekeringstype {
	private int VerzekeringsTypeID;
	private int EigenRisico;
	private int behandelcodes; //klopt dit? waarom is dit...
	// Missen er attributen?
	
	/*
	 * @param VerzekeringsTypeID Het ID van de verzekeringstype
	 * @param EigenRisico Het eigen risico van het type verzekering
	 * @param behandelcodes de behandelcodes
	 */
	public Verzekeringstype(int VerzekeringsTypeID, int EigenRisico, int behandelcodes){
		this.VerzekeringsTypeID = VerzekeringsTypeID;
		this.EigenRisico = EigenRisico;
		this.behandelcodes = behandelcodes;
	}
	
	/*
	 * @return Het eigen risico van het verzekeringstype
	 */
	public int getEigenRisico(){
		return EigenRisico;
	}
}
