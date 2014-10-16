/* @author Jeroen Nuijten
 * @version 0.1
 * 
 * Bevat gegevens over de verzekeringsmaatschappij. Deze worden gebruikt bij het aanmaken van facturen.
 */
package facturatieSysteem.VerzekeringSubsysteem.EntityLayer;

import java.util.ArrayList;

public class Verzekeringsmaatschappij {
	private String Naam;
	private String Adres; //Kan als straat+huisnummer apart..?
	private String Postcode;
	private String Plaats;
	private int KVK;
	private int RekeningNR;
	private ArrayList<Verzekeringstype> types;
	
	/*
	 * @param Naam De naam van de verzekeringsmaatschappij
	 * @param Adres Het adres van de verzekeringsmaatschappij
	 * @param Postcode De postcode van de verzekeringsmaatschappij
	 * @param Plaats De plaats van de verzekeringsmaatschappij
	 * @param KVK Het kamer van koophandel van de verzekeringsmaatschappij
	 * @param RekeningNR Het rekening nummer van de verzekeringsmaatschappij
	 */
	public Verzekeringsmaatschappij(String Naam, String Adres, String Postcode, String Plaats, int KVK, int RekeningNR){
		this.setNaam(Naam);
		this.setAdres(Adres);
		this.setPostcode(Postcode);
		this.setPlaats(Plaats);
		this.KVK = KVK;
		this.setRekeningNR(RekeningNR);
	}

	//Zijn de setters nodig of mogen we ervanuit gaan dat de gegevens niet veranderen?
	public String getNaam() {
		return Naam;
	}

	public void setNaam(String naam) {
		Naam = naam;
	}

	public String getAdres() {
		return Adres;
	}

	public void setAdres(String adres) {
		Adres = adres;
	}

	public String getPostcode() {
		return Postcode;
	}

	public void setPostcode(String postcode) {
		Postcode = postcode;
	}

	public String getPlaats() {
		return Plaats;
	}

	public void setPlaats(String plaats) {
		Plaats = plaats;
	}
	
	public int getKVK() {
		return KVK;
	}

	public void setKVK(int kvk) {
		KVK = kvk;
	}

	public int getRekeningNR() {
		return RekeningNR;
	}

	public void setRekeningNR(int rekeningNR) {
		RekeningNR = rekeningNR;
	}
	
	public void addType(Verzekeringstype type){
		types.add(type);
	}
	
	public boolean deleteType(Verzekeringstype type){
		return types.remove(type);
	}
	
	public ArrayList<Verzekeringstype> getTypes(){
		return types;
	}
}
