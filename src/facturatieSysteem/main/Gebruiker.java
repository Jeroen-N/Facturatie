package facturatieSysteem.main;

public class Gebruiker {
	private String gebruikersnaam;
	private String wachtwoord;
	private boolean statusingelogd;

	public Gebruiker(String gebruikersnaam, String wachtwoord, boolean statusingelogd) {
		this.gebruikersnaam = gebruikersnaam;
		this.wachtwoord = wachtwoord;
		this.statusingelogd = statusingelogd;
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public boolean isStatusingelogd() {
		return statusingelogd;
	}

	public void setStatusingelogd(boolean statusingelogd) {
		this.statusingelogd = statusingelogd;
	}
	
	
}
