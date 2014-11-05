package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

/**
 * The interface BehandelDAOinf which will be implemented by the BehandelingDAO class.
 */
public interface BehandelDAOinf {
	/**
	 * Calls the getPrijs() method from the BehandelingDAO which gets the price of the specified treatmentcode.
	 * 
	 * @param behandelCode
	 * @return double the price
	 */
	public double getPrijs(String behandelCode);

	/**
	 * Calls the getBehandelingen() method from the BehandelingDAO which gets an list of treatments given to the specified client
	 * 
	 * @param klant
	 * @return an list of the given treatments
	 */
	public ArrayList<Behandeling> getBehandelingen(Klant klant);

}
