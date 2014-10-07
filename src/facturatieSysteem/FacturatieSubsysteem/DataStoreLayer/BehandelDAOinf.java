package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface BehandelDAOinf {

	public ArrayList<Behandeling> haalBehandelingen(Klant klant);
}
