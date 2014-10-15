package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

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

}
