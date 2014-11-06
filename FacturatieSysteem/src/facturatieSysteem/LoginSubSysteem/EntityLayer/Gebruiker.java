package facturatieSysteem.LoginSubSysteem.EntityLayer;

/**
 * The Class Gebruiker.
 */
public class Gebruiker {
	
	/** The username. */
	private String gebruikersnaam;
	
	/** The password. */
	private String wachtwoord;
	
	/** The status of log in. */
	private boolean statusingelogd;

	/**
	 * Instantiates a new username.
	 *
	 * @param gebruikersnaam the username
	 * @param wachtwoord the password
	 * @param statusingelogd the status of log in
	 */
	public Gebruiker(String gebruikersnaam, String wachtwoord, boolean statusingelogd) {
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
		this.statusingelogd = statusingelogd;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	/**
	 * Sets the username.
	 *
	 * @param gebruikersnaam the new username
	 */
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * Gets the passoword.
	 *
	 * @return the password
	 */
	public String getWachtwoord() {
		return wachtwoord;
	}

	/**
	 * Sets the password.
	 *
	 * @param wachtwoord the new password
	 */
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	/**
	 * Checks if is user is logged in
	 *
	 * @return true, if status is logged in
	 */
	public boolean isStatusingelogd() {
		return statusingelogd;
	}

	/**
	 * Sets the status of logged in
	 *
	 * @param statusingelogd the new status of logged in
	 */
	public void setStatusingelogd(boolean statusingelogd) {
		this.statusingelogd = statusingelogd;
	}
	
	
}
