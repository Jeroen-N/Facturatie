package facturatieSysteem.main;

import java.util.ArrayList;

public class LoginManager {
	private DAOFactoryLogin daoFactoryLogin = new DAOFactoryLogin(
			"XML/Login.xml", "XML/Login.xsd");
	private LoginDAO loginDAO;
	private ArrayList<Gebruiker> gebruikers;

	public LoginManager() {
		this.loginDAO = new LoginDAO(daoFactoryLogin);
		gebruikers = new ArrayList<>();

	}

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