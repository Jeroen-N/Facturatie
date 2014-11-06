package facturatieSysteem.LoginSubSysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.LoginSubSysteem.DataStoreLayer.DAOFactoryLogin;
import facturatieSysteem.LoginSubSysteem.DataStoreLayer.LoginDAO;
import facturatieSysteem.LoginSubSysteem.EntityLayer.Gebruiker;

/**
 * The Class LoginManager.
 */
public class LoginManager {
	
	/** The dao factory login. */
	private DAOFactoryLogin daoFactoryLogin = new DAOFactoryLogin(
			"XML/Login.xml", "XML/Login.xsd");
	
	/** The login dao. */
	private LoginDAO loginDAO;
	
	/** The gebruikers. */
	private ArrayList<Gebruiker> gebruikers;

	/**
	 * Instantiates a new login manager.
	 */
	public LoginManager() {
		this.loginDAO = new LoginDAO(daoFactoryLogin);
		gebruikers = new ArrayList<>();

	}

	/**
	 * Checks the given username and password
	 *
	 * @param gebruikersnaam the username
	 * @param wachtwoord the password
	 * @return true, if successful
	 */
	public boolean check(String gebruikersnaam, String wachtwoord) {
		gebruikers = loginDAO.getGebruikers();
		boolean z = false;
		if (gebruikers.size() != 0) {
			for (Gebruiker gebruiker : gebruikers) {
				if (gebruiker.getGebruikersnaam().equals(gebruikersnaam)) {
					if (gebruiker.getWachtwoord().equals(wachtwoord)) {
						z = true;
						break;
					} else {
						z = false;
					}
				} else {
					z = false;
				}
			}
		}
		if(!z){
			System.out.println("Gebruikersnaam en/of wachtwoord klopt niet.");
			
		}
		
		else{
			System.out.println("Inlog succesvol");
			
		}
		return z;
	}
}