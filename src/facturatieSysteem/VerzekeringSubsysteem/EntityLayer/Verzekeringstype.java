/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * Realiseert de verzekeringstype klasse, deze onderscheid de typen verzekeringen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

import java.util.ArrayList;

public class Verzekeringstype {
	private int VerzekeringsTypeID;
	private int EigenRisico;
	private String Naam;
	private ArrayList<String> behandelcodes = new ArrayList<>();
	
	/*
	 * @param VerzekeringsTypeID Het ID van de verzekeringstype
	 * @param EigenRisico Het eigen risico van het type verzekering
	 * @param behandelcodes de behandelcodes
	 */
	public Verzekeringstype(int EigenRisico, String Naam){
		this.EigenRisico = EigenRisico;
		this.Naam = Naam;
	}
	
	/*
	 * @return Het ID van het verzekeringstype
	 */
	public int getID(){
		return VerzekeringsTypeID;
	}
	/*
	 * @return Het eigen risico van het verzekeringstype
	 */
	public int getEigenRisico(){
		return EigenRisico;
	}
	
	/*
	 * @param EigenRisicio Het nieuwe eigen risico
	 */
	public void setEigenRisicio(int EigenRisico){
		this.EigenRisico = EigenRisico;
	}
	
	/*
	 * @return De naam van het type
	 */
	public String getNaam(){
		return Naam;
	}
	
	/*
	 * @param Naam De nieuwe naam van het type
	 */
	public void setNaam(String Naam){
		this.Naam = Naam;
	}
	
	public void addCode(String code){
		this.behandelcodes.add(code);
	}
	
	/*
	 * @return de behandelcode
	 */
	public ArrayList<String> getBehandelcodes(){
		return behandelcodes;
	}
	
}
