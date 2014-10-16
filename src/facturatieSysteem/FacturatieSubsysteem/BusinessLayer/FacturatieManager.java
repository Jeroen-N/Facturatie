package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface FacturatieManager {

	public ArrayList<Klant> haalKlanten();

	public Factuur factureer(Klant klant);

	public void controleerBehandelingen(ArrayList<Behandeling> behandelingen);

	public ArrayList<Factuur> haalFacturen(String invoerBSN);
}
