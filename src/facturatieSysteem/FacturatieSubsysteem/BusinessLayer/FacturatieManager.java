package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManagerImpl;

public interface FacturatieManager {

	public Factuur factureer(Klant klant, VerzekeringsmaatschappijManager verzekeringsmanager);

	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	public String toonFactuur(String factuur_nummer, Klant klant);
	
	public String loopBehandelingen(Factuur factuur);
	
	public double getTotaalPrijs(Factuur factuur);
	
	public double getTotaalinclBTW(Factuur factuur);
	
	public Factuur getFactuur(String factuur_nummer, Klant klant);
	
	public BehandelingDAO getBDAO();
}
