package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImmutableBehandeling.
 */
public interface ImmutableBehandeling {

	/**
	 * Gets the totaalprijs.
	 *
	 * @return the totaalprijs
	 */
	public double getTotaalprijs();

	/**
	 * Sets the totaalprijs.
	 *
	 * @param totaalprijs the new totaalprijs
	 */
	public void setTotaalprijs(double totaalprijs);

	/**
	 * Gets the sessies.
	 *
	 * @return the sessies
	 */
	public int getSessies();

	/**
	 * Sets the sessies.
	 *
	 * @param sessies the new sessies
	 */
	public void setSessies(int sessies);

	/**
	 * Gets the behandelingen.
	 *
	 * @return the behandelingen
	 */
	public Behandeling getBehandelingen();

	/**
	 * Gets the fysio praktijk nummer.
	 *
	 * @return the fysio praktijk nummer
	 */
	public String getFysioPraktijkNummer();

	/**
	 * Sets the fysio praktijk nummer.
	 *
	 * @param fysioPraktijkNummer the new fysio praktijk nummer
	 */
	public void setFysioPraktijkNummer(String fysioPraktijkNummer);

	/**
	 * Gets the behandel code.
	 *
	 * @return the behandel code
	 */
	public String getBehandelCode();

	/**
	 * Sets the behandel code.
	 *
	 * @param behandelCode the new behandel code
	 */
	public void setBehandelCode(String behandelCode);

	/**
	 * Gets the behandel start datum.
	 *
	 * @return the behandel start datum
	 */
	public String getBehandelStartDatum();

	/**
	 * Sets the behandel start datum.
	 *
	 * @param behandelStartDatum the new behandel start datum
	 */
	public void setBehandelStartDatum(String behandelStartDatum);

	/**
	 * Gets the behandel eind datum.
	 *
	 * @return the behandel eind datum
	 */
	public String getBehandelEindDatum();

	/**
	 * Sets the behandel eind datum.
	 *
	 * @param behandelEindDatum the new behandel eind datum
	 */
	public void setBehandelEindDatum(String behandelEindDatum);

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

}
