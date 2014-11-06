package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

/**
 * The Class Behandelcode.
 */
public class Behandelcode {

	/** The behandel code. */
	private int behandelCode;
	
	/** The behandeling naam. */
	private static String behandelingNaam;
	
	/** The aantal sessies. */
	private int aantalSessies;
	
	/** The sessie duur. */
	private double sessieDuur;
	
	/** The tarief behandeling. */
	private double tariefBehandeling;

	/**
	 * Instantiates a new behandelcode.
	 *
	 * @param behandelCode the behandel code
	 * @param behandelingNaam the behandeling naam
	 * @param aantalSessies the aantal sessies
	 * @param sessieDuur the sessie duur
	 * @param tariefBehandeling the tarief behandeling
	 */
	public Behandelcode(int behandelCode, String behandelingNaam,
			int aantalSessies, double sessieDuur, double tariefBehandeling) {
		this.behandelCode = behandelCode;
		Behandelcode.behandelingNaam = behandelingNaam;
		this.aantalSessies = aantalSessies;
		this.sessieDuur = sessieDuur;
		this.tariefBehandeling = tariefBehandeling;
	}

	/**
	 * Gets the behandel code.
	 *
	 * @return the behandel code
	 */
	public int getBehandelCode() {
		return behandelCode;
	}

	/**
	 * Sets the behandel code.
	 *
	 * @param behandelCode the new behandel code
	 */
	public void setBehandelCode(int behandelCode) {
		this.behandelCode = behandelCode;
	}

	/**
	 * Gets the behandeling naam.
	 *
	 * @return the behandeling naam
	 */
	public static String getBehandelingNaam() {
		return behandelingNaam;
	}

	/**
	 * Sets the behandeling naam.
	 *
	 * @param behandelingNaam the new behandeling naam
	 */
	public void setBehandelingNaam(String behandelingNaam) {
		Behandelcode.behandelingNaam = behandelingNaam;
	}

	/**
	 * Gets the aantal sessies.
	 *
	 * @return the aantal sessies
	 */
	public int getAantalSessies() {
		return aantalSessies;
	}

	/**
	 * Sets the aantal sessies.
	 *
	 * @param aantalSessies the new aantal sessies
	 */
	public void setAantalSessies(int aantalSessies) {
		this.aantalSessies = aantalSessies;
	}

	/**
	 * Gets the sessie duur.
	 *
	 * @return the sessie duur
	 */
	public double getSessieDuur() {
		return sessieDuur;
	}

	/**
	 * Sets the sessie duur.
	 *
	 * @param sessieDuur the new sessie duur
	 */
	public void setSessieDuur(double sessieDuur) {
		this.sessieDuur = sessieDuur;
	}

	/**
	 * Gets the tarief behandeling.
	 *
	 * @return the tarief behandeling
	 */
	public double getTariefBehandeling() {
		return tariefBehandeling;
	}

	/**
	 * Sets the tarief behandeling.
	 *
	 * @param tariefBehandeling the new tarief behandeling
	 */
	public void setTariefBehandeling(double tariefBehandeling) {
		this.tariefBehandeling = tariefBehandeling;
	}

}
