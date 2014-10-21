package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.FactuurDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.DataStoreLayer.KlantDAO;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieManagerImpl implements FacturatieManager {

	private KlantDAO klantDAO;
	private BehandelingDAO behandelingDAO;
	private FactuurDAO factuurDAO;

	public FacturatieManagerImpl() {
		this.factuurDAO = new FactuurDAO();
	}

	@Override
	public boolean factureer(Klant klant, ArrayList<Behandeling> behandelingen) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void controleerBehandelingen(ArrayList<Behandeling> behandelingen) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		// TODO Auto-generated method stub
		return factuurDAO.haalFacturen(invoerBSN);
	}

}
