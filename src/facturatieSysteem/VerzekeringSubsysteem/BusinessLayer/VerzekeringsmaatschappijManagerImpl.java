package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijManagerImpl implements VerzekeringsmaatschappijManager {
	private ArrayList<Verzekeringsmaatschappij> verzekeringsMaatschappijen = new ArrayList<>();
	
	@Override
	public void addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		verzekeringsMaatschappijen.add(maatschappij);
	}

	@Override
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNaam().equals(naam)){
				return maatschappij;
			}
		}
		return null;
	}

	@Override
	public boolean deleteVerzekeringsmaatschappij(String naam) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNaam().equals(naam)){
				return verzekeringsMaatschappijen.remove(maatschappij);
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
	}

	@Override
	public ArrayList<Verzekeringstype> getTypes(Verzekeringsmaatschappij maatschappij) {
		return maatschappij.getTypes();
	}
	
	public void fill(){
		Verzekeringsmaatschappij maatschappij1 = new Verzekeringsmaatschappij("Dikke shit", "Adres", "Postcode", "plaats", 123456789, 11111111);
		Verzekeringstype type1 = new Verzekeringstype(1, 250, "Type 1");
		Verzekeringstype type2 = new Verzekeringstype(1, 250, "Type 2");
		maatschappij1.addType(type1);
		maatschappij1.addType(type2);
		this.addVerzekeringsmaatschappij(maatschappij1);
		
		System.out.println("DEBUG: Verzekeringen aangemaakt.");
	}
}
