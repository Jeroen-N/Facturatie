package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public interface VerzekeringsmaatschappijManager {
	
	public void addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam);
	
	public boolean deleteVerzekeringsmaatschappij(String naam);
	
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen();
}
