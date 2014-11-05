/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * Realiseert de verzekeringstype klasse, deze onderscheid de typen verzekeringen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

import java.util.ArrayList;

public class Verzekeringstype {
	private String typenr;
	private int EigenRisico;
	private String Naam;
	private ArrayList<String> behandelcodes = new ArrayList<>();
	
	/*
	 * @param VerzekeringsTypeID Het ID van de verzekeringstype
	 * @param EigenRisico Het eigen risico van het type verzekering
	 * @param behandelcodes de behandelcodes
	 */
	public Verzekeringstype(String typenr, int EigenRisico, String Naam){
		this.EigenRisico = EigenRisico;
		this.Naam = Naam;
		this.typenr = typenr;
	}
	
	/*
	 * @return Het nummer van het verzekeringstype
	 */
	public String getNr(){
		return typenr;
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
	
	/*
	 * @param code de toe te voegen code
	 */
	public void addCode(String code){
		this.behandelcodes.add(code);
	}
	
	/*
	 * @param code de te verwijderen code
	 * @return boolean of de code is verwijderd
	 */
	public boolean deleteCode(String code){
		return this.behandelcodes.remove(code);
	}
	
	/*
	 * @return de behandelcode
	 */
	public ArrayList<String> getBehandelcodes(){
		return behandelcodes;
	}
	
}
