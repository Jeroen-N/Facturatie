package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public interface BehandelDAOinf {
	/**
	 * gets the price of the specified treatment
	 * 
	 * @param behandelCode
	 * @return double the price
	 */
	public double getPrijs(String behandelCode);

	/**
	 * gets an list of treatments given to the specified client
	 * 
	 * @param klant
	 * @return an list of the given treatments
	 */
	public ArrayList<Behandeling> getBehandelingen(Klant klant);

}
