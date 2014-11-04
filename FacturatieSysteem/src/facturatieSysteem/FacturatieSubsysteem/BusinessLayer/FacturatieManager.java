package facturatieSysteem.FacturatieSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.FacturatieSubsysteem.DataStoreLayer.BehandelingDAO;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;

// TODO: Auto-generated Javadoc
/**
 * The Interface FacturatieManager.
 */
public interface FacturatieManager {

	/**
	 * Factureer.
	 *
	 * @param klant the klant
	 * @param verzekeringsmanager the verzekeringsmanager
	 * @return the factuur
	 */
	public Factuur factureer(Klant klant, VerzekeringsmaatschappijManager verzekeringsmanager);

	/**
	 * Haal facturen.
	 *
	 * @param invoerBSN the invoer bsn
	 * @return the array list
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN);

	/**
	 * Toon factuur.
	 *
	 * @param factuur_nummer the factuur_nummer
	 * @param klant the klant
	 * @return the string
	 */
	public String toonFactuur(String factuur_nummer, Klant klant);
	
	/**
	 * Loop behandelingen.
	 *
	 * @param factuur the factuur
	 * @return the string
	 */
	public String loopBehandelingen(Factuur factuur);
	
	/**
	 * Gets the totaal prijs.
	 *
	 * @param factuur the factuur
	 * @return the totaal prijs
	 */
	public double getTotaalPrijs(Factuur factuur);
	
	/**
	 * Gets the totaalincl btw.
	 *
	 * @param factuur the factuur
	 * @return the totaalincl btw
	 */
	public double getTotaalinclBTW(Factuur factuur);
	
	/**
	 * Gets the factuur.
	 *
	 * @param factuur_nummer the factuur_nummer
	 * @param klant the klant
	 * @return the factuur
	 */
	public Factuur getFactuur(String factuur_nummer, Klant klant);
	
	/**
	 * Gets the bdao.
	 *
	 * @return the bdao
	 */
	public BehandelingDAO getBDAO();
}
