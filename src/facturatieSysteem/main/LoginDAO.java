package facturatieSysteem.main;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class LoginDAO {
	private Document document = null;
	private DAOFactoryLogin daoFactoryLogin;
	
		public LoginDAO(DAOFactoryLogin daoFactoryLogin){
			this.daoFactoryLogin = daoFactoryLogin;
			
		}
		
	public ArrayList<Gebruiker> getGebruikers(){
		
		
	}
}
