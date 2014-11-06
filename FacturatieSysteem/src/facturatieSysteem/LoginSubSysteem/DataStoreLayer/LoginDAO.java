package facturatieSysteem.LoginSubSysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.LoginSubSysteem.EntityLayer.Gebruiker;

/**
 * The Class LoginDAO.
 */
public class LoginDAO {
	
	/** The document. */
	private Document document = null;
	
	/** The dao factory login. */
	private DAOFactoryLogin daoFactoryLogin;
	
	/** The gebruikers. */
	private ArrayList<Gebruiker> gebruikers;

	/**
	 * Instantiates a new login dao.
	 *
	 * @param daoFactoryLogin the dao factory login
	 */
	public LoginDAO(DAOFactoryLogin daoFactoryLogin) {
		this.daoFactoryLogin = daoFactoryLogin;

	}

	/**
	 * Gets the users.
	 *
	 * @return the users in an arraylist
	 */
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
