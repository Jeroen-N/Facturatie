package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface FactuurDAOinf {

	public ArrayList<Factuur> haalFacturen(Klant klant);

	public Factuur maakFactuur(Behandeling behandeling);
}
