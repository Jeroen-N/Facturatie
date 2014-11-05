package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

	/**
	 * The interface FactoorDAOinf which will be implemented by the FactuurDAO
	 * @author night_000
	 *
	 */
public interface FactuurDAOinf {
	/**
	 * Gets the facturen for the specified client in the haalFacturen() method of the FactuurDAO
	 * 
	 * @param invoerBSN
	 * @return arraylist<factuur>
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	/**
	 * Creates a new factuur in the maakFactuur() method of the FactuurDAO
	 * 
	 * @param klant
	 * @param factuur
	 * @return true, if successfull
	 */
	public boolean maakFactuur(Klant klant, Factuur factuur);

	/**
	 * Gets all the objects of facturen in the haalAlleFacturen() method of the FactuurDAO
	 * 
	 * @return ArrayList<Factuur>
	 */
	public ArrayList<Factuur> haalAlleFacturen();
}
