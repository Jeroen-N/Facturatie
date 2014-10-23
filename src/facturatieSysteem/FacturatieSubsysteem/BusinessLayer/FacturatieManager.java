package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;

public interface FacturatieManager {

	public boolean factureer(Klant klant, VerzekeringsmaatschappijManager verzekeringsmanager);

	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	public String toonFactuur(String factuur_nummer, Klant klant);
}
