package facturatieSysteem.main;

import facturatieSysteem.LoginSubSysteem.BusinessLayer.LoginManager;

public class testMain {

	public static void main(String[] args) {

		LoginManager m1 = new LoginManager();
		String gebruikersnaam = "b1";
		String wachtwoord = "fysio";
		if(m1.check(gebruikersnaam, wachtwoord)){
			
		}
	}

}
