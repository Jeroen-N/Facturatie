package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringsmaatschappijManager {
	
	public void addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij);
	
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String naam);
	
	public boolean deleteVerzekeringsmaatschappij(String naam);
	
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen();
	
	public ArrayList<Verzekeringstype> getTypes(Verzekeringsmaatschappij maatschappij);
	
	public void fill();
}
