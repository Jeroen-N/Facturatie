package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public interface VerzekeringsmaatschappijDAO {
	public ArrayList<Verzekeringsmaatschappij> getMaatschappijenXML();
	
	public boolean addMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	
	public boolean updateMaatschappijXML(Verzekeringsmaatschappij maatschappij);
	
	public boolean deleteMaatschappijXML(String naam);
}
