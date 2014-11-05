/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * Bevat gegevens over de verzekeringsmaatschappij. Deze worden gebruikt bij het aanmaken van facturen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

import java.util.ArrayList;

public class Verzekeringsmaatschappij {
	private String maatschappijnr;
	private String Naam;
	private String Adres;
	private String Postcode;
	private String Plaats;
	private int KVK;
	private int RekeningNR;
	private ArrayList<Verzekeringstype> types = new ArrayList<>();
	
	/*
	 * @param Naam De naam van de verzekeringsmaatschappij
	 * @param Adres Het adres van de verzekeringsmaatschappij
	 * @param Postcode De postcode van de verzekeringsmaatschappij
	 * @param Plaats De plaats van de verzekeringsmaatschappij
	 * @param KVK Het kamer van koophandel van de verzekeringsmaatschappij
	 * @param RekeningNR Het rekening nummer van de verzekeringsmaatschappij
	 */
	public Verzekeringsmaatschappij(String maatschappijnr, String Naam, String Adres, String Postcode, String Plaats, int KVK, int RekeningNR){
		this.maatschappijnr = maatschappijnr;
		this.setNaam(Naam);
		this.setAdres(Adres);
		this.setPostcode(Postcode);
		this.setPlaats(Plaats);
		this.KVK = KVK;
		this.setRekeningNR(RekeningNR);
	}
	
	/*
	 * @return Het maatschappijnr
	 */
	public String getNr(){
		return maatschappijnr;
	}

	/*
	 * @return De naam van de maatschappij
	 */
	public String getNaam() {
		return Naam;
	}
	
	/*
	 * @param Naam De nieuwe naam van de maatschappij
	 */
	public void setNaam(String Naam) {
		this.Naam = Naam;
	}
	
	/*
	 * @return Het adres van de maatschappij
	 */
	public String getAdres() {
		return Adres;
	}
	
	/*
	 * @param Adres Het nieuwe adres van de maatschappij
	 */
	public void setAdres(String Adres) {
		this.Adres = Adres;
	}

	/*
	 * @return De postcode van de maatschappij
	 */
	public String getPostcode() {
		return Postcode;
	}

	/*
	 * @param Postcode De nieuwe postcode van de maatschappij
	 */
	public void setPostcode(String Postcode) {
		this.Postcode = Postcode;
	}
	
	/*
	 * @return De plaats van de maatschappij
	 */
	public String getPlaats() {
		return Plaats;
	}

	/*
	 * @param Plaats De nieuwe plaats van de maatschappij
	 */
	public void setPlaats(String Plaats) {
		this.Plaats = Plaats;
	}
	
	/*
	 * @return Het KVK nummer van de maatschappij
	 */
	public int getKVK() {
		return KVK;
	}

	/*
	 * @param KVK Het nieuwe KVK nummer van de maatschappij
	 */
	public void setKVK(int KVK) {
		this.KVK = KVK;
	}

	/*
	 * @return Het rekening nummer van de verzekeringsmaatschappij
	 */
	public int getRekeningNR() {
		return RekeningNR;
	}
	
	/*
	 * @param RekeningNR Het nieuwe rekening nummer van de verzekeringsmaatschappij
	 */
	public void setRekeningNR(int RekeningNR) {
		this.RekeningNR = RekeningNR;
	}
	
	/*
	 * @param type Het toe te voegen type
	 */
	public void addType(Verzekeringstype type){
		types.add(type);
	}
	
	/*
	 * @param type Het te verwijderen type
	 * @return Of het verzekeringstype wel of niet is verwijderd
	 */
	public boolean deleteType(Verzekeringstype type){
		return types.remove(type);
	}
	
	/*
	 * @return Een Arraylist van de types behorend bij de maatschappij
	 */
	public ArrayList<Verzekeringstype> getTypes(){
		return types;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return 	Naam 									+ "\n" +
				Adres									+ "\n" +
				Postcode + " "			+ Plaats		+ "\n" +
				"KVK nummer: \t"		+ KVK 			+ "\n" +
				"Rekeningnummer: "		+ RekeningNR;
	}
}
