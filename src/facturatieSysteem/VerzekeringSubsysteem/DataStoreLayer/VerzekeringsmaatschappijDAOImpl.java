package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;

public class VerzekeringsmaatschappijDAOImpl implements VerzekeringsmaatschappijDAO {
	private Document document;
	private DAOFactoryVerzekering daoFactory = new DAOFactoryVerzekering();
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getMaatschappijenXML() {
		document = daoFactory.getDocument();
		try {
			Element rootElement = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
			NodeList maatschappijen = rootElement.getElementsByTagName("verzekeringsmaatschappij");
			for(int i = 0; i < maatschappijen.getLength(); i++){
				Element maatschappijElement = (Element) maatschappijen.item(i);
				String naam = maatschappijElement.getElementsByTagName("naam").item(0).getTextContent();
				String adres = maatschappijElement.getElementsByTagName("adres").item(0).getTextContent();
				String postcode = maatschappijElement.getElementsByTagName("postcode").item(0).getTextContent();
				String woonplaats = maatschappijElement.getElementsByTagName("woonplaats").item(0).getTextContent();
				//int KVKnummer = maatschappijElement.getElementsByTagName("KVKNummer").item(0).getTextContent();
			}
		} catch(DOMException e){
			return null;
		}
		return null;
	}

	@Override
	public boolean addMaatschappijXML(Verzekeringsmaatschappij maatschappij) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMaatschappijXML(Verzekeringsmaatschappij maatschappij) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMaatschappijXML(String naam) {
		// TODO Auto-generated method stub
		return false;
	}

}
