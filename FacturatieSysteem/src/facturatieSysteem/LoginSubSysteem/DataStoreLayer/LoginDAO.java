package facturatieSysteem.LoginSubSysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.LoginSubSysteem.EntityLayer.Gebruiker;

public class LoginDAO {
	private Document document = null;
	private DAOFactoryLogin daoFactoryLogin;
	private ArrayList<Gebruiker> gebruikers;

	public LoginDAO(DAOFactoryLogin daoFactoryLogin) {
		this.daoFactoryLogin = daoFactoryLogin;

	}

	public ArrayList<Gebruiker> getGebruikers() {
		document = daoFactoryLogin.getDocument();
		gebruikers = new ArrayList<Gebruiker>();
		try {
			Element rootElement = (Element) document.getElementsByTagName(
					"Login").item(0);
			NodeList gebruikersnode = rootElement
					.getElementsByTagName("gebruiker");

			for (int i = 0; i < gebruikersnode.getLength(); i++) {

				Element gebruikerElement = (Element) gebruikersnode.item(i);
				String gebruikersnaam = gebruikerElement
						.getElementsByTagName("gebruikersnaam").item(0)
						.getTextContent();
				String wachtwoord = gebruikerElement
						.getElementsByTagName("wachtwoord").item(0)
						.getTextContent();
				Gebruiker g = new Gebruiker(gebruikersnaam, wachtwoord, false);
				gebruikers.add(g);

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return gebruikers;
	}
}
