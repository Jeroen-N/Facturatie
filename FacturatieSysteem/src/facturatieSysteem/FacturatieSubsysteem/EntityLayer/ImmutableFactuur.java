package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.util.ArrayList;

/**
 * The Interface ImmutableFactuur.
 */
public interface ImmutableFactuur {

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus();

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status);

	/**
	 * Gets the behandelingen.
	 *
	 * @return the behandelingen
	 */
	public ArrayList<Behandeling> getBehandelingen();

	/**
	 * Sets the behandelingen.
	 *
	 * @param behandelingen the new behandelingen
	 */
	public void setBehandelingen(ArrayList<Behandeling> behandelingen);

	/**
	 * Gets the vergoede bedrag.
	 *
	 * @return the vergoede bedrag
	 */
	public double getVergoedeBedrag();

	/**
	 * Sets the vergoede bedrag.
	 *
	 * @param vergoedeBedrag the new vergoede bedrag
	 */
	public void setVergoedeBedrag(double vergoedeBedrag);

	/**
	 * Bereken btw.
	 */
	public void berekenBTW();

	/**
	 * Bereken eigen risico.
	 */
	public void berekenEigenRisico();

	/**
	 * Gets the factuur nummer.
	 *
	 * @return the factuur nummer
	 */
	public String getFactuurNummer();

	/**
	 * Sets the factuur nummer.
	 *
	 * @param factuurNummer the new factuur nummer
	 */
	public void setFactuurNummer(String factuurNummer);

	/**
	 * Gets the factuur datum.
	 *
	 * @return the factuur datum
	 */
	public String getFactuurDatum();

	/**
	 * Sets the factuur datum.
	 *
	 * @param factuurDatum the new factuur datum
	 */
	public void setFactuurDatum(String factuurDatum);

	/**
	 * Gets the verval datum.
	 *
	 * @return the verval datum
	 */
	public String getVervalDatum();

	/**
	 * Sets the verval datum.
	 *
	 * @param vervalDatum the new verval datum
	 */
	public void setVervalDatum(String vervalDatum);

	/**
	 * Gets the bsn.
	 *
	 * @return the bsn
	 */
	public String getBSN();

	/**
	 * Sets the bsn.
	 *
	 * @param bSN the new bsn
	 */
	public void setBSN(String bSN);

	/**
	 * To string.
	 *
	 * @param factuur the factuur
	 * @param behandelingen the behandelingen
	 * @return the string
	 */
	public String toString(Factuur factuur, String behandelingen);
	
	/**
	 * Gets the totaal prijs.
	 *
	 * @return the totaal prijs
	 */
	public double getTotaalPrijs();


	/**
	 * Sets the totaal prijs.
	 *
	 * @param totaalPrijs the new totaal prijs
	 */
	public void setTotaalPrijs(double totaalPrijs);

}
