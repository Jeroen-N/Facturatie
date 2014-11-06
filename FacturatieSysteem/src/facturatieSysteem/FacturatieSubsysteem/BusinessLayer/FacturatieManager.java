package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;

/**
 * The Interface FacturatieManager.
 * This interface will be implemented by the FacturatieManagerImpl
 */
public interface FacturatieManager {

	/**
	 * This method will initiate the factureer() method from the FacturatieManagerImpl which will create a factuur.
	 *
	 * @param klant the klant
	 * @param verzekeringsmanager the verzekeringsmanager
	 * @return the factuur
	 */
	public Factuur factureer(Klant klant, VerzekeringsmaatschappijManager verzekeringsmanager);

	/**
	 * This method will initiate the haalFacturen() method from the FacturatieManagerImpl which will get al the objects
	 * from factuur that exist.
	 *
	 * @param invoerBSN the invoer bsn
	 * @return the array list
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	/**
	 * This method will initate the toonFactuur() method from the FacturatieManagerImpl which will get the specified 
	 * object of factuur by searching with the given customer and a factuur number.
	 *
	 * @param factuur_nummer the factuur_nummer
	 * @param klant the klant
	 * @return the string
	 */
	public String toonFactuur(String factuur_nummer, Klant klant);
	
	/**
	 * This method will initate the loopBehandelingen() method from the FacturatieManagerImpl which will run through all the
	 * treatments which are needed.
	 *
	 * @param factuur the factuur
	 * @return the string
	 */
	public String loopBehandelingen(Factuur factuur);
	
	/**
	 * This method will initiate getTotaalPrijs() method from the FacturatieManagerImpl which gets the total cost
	 * of an object from the type factuur.
	 *
	 * @param factuur the factuur
	 * @return the totaal prijs
	 */
	public double getTotaalPrijs(Factuur factuur);
	
	/**
	 * This method will initiate the getTotaalinclBTW() method from the FacturatieManagerImpl which gets the total cost
	 * with the tax price included.
	 *
	 * @param factuur the factuur
	 * @return the totaalincl btw
	 */
	public double getTotaalinclBTW(Factuur factuur);
	
	/**
	 * This method will initiate the getFactuur() method from the FacturatieManagerImpl which gets a specific object of factuur
	 * by searching with the given number of factuur and the customer.
	 *
	 * @param factuur_nummer the factuur_nummer
	 * @param klant the klant
	 * @return the factuur
	 */
	public Factuur getFactuur(String factuur_nummer, Klant klant);
	
	/**
	 * This method will initiate the getBDAO() method from the FactoratieManagerImpl which returns an object of BehandelDAO
	 *
	 * @return the bdao
	 */
	public BehandelingDAO getBDAO();
}
