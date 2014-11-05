package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface FactuurDAOinf {
	/**
	 * gets the facturen for the specified client
	 * 
	 * @param invoerBSN
	 * @return arraylist<factuur>
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	/**
	 * creates a new factuur
	 * 
	 * @param klant
	 * @param factuur
	 * @return true, if successfull
	 */
	public boolean maakFactuur(Klant klant, Factuur factuur);

	/**
	 * gets all facturen
	 * 
	 * @return ArrayList<Factuur>
	 */
	public ArrayList<Factuur> haalAlleFacturen();
}
