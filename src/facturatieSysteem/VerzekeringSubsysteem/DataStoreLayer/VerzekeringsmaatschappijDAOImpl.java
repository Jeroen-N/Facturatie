package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijDAOImpl implements VerzekeringsmaatschappijDAO {
	private Document document;
	private DAOFactoryVerzekering daoFactory = new DAOFactoryVerzekering();
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getMaatschappijenXML() {
		document = daoFactory.getDocument();
		try {
			//Maak een lege arraylist
			ArrayList<Verzekeringsmaatschappij> maatschappijlijst = new ArrayList<>();
			//Vind de maatschappijen
			Element rootElement = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
			NodeList maatschappijen = rootElement.getElementsByTagName("verzekeringsmaatschappij");
			for(int i = 0; i < maatschappijen.getLength(); i++){
				Element maatschappijElement = (Element) maatschappijen.item(i);
				String naam = maatschappijElement.getElementsByTagName("naam").item(0).getTextContent();
				String adres = maatschappijElement.getElementsByTagName("adres").item(0).getTextContent();
				String postcode = maatschappijElement.getElementsByTagName("postcode").item(0).getTextContent();
				String woonplaats = maatschappijElement.getElementsByTagName("woonplaats").item(0).getTextContent();
				int KVKnummer = Integer.parseInt(maatschappijElement.getElementsByTagName("KVKNummer").item(0).getTextContent());
				int rekeningnummer = Integer.parseInt(maatschappijElement.getElementsByTagName("rekeningNummer").item(0).getTextContent());
				//Maak een nieuwe maatschappij met de gevonden gegevens
				Verzekeringsmaatschappij maatschappij = new Verzekeringsmaatschappij(naam,adres,postcode,woonplaats,KVKnummer, rekeningnummer);
				//Vind de verzekeringstypes
				Element typesElement = (Element) document.getElementsByTagName("verzekeringsTypes").item(0);
				NodeList types = typesElement.getElementsByTagName("verzekeringsType");
				for(int j = 0; j < types.getLength(); j++){
					Element typeElement = (Element) types.item(j);
					int id = Integer.parseInt(typeElement.getAttribute("id"));
					String typenaam = typeElement.getElementsByTagName("naam").item(0).getTextContent();
					int eigenrisico = Integer.parseInt(typeElement.getElementsByTagName("verplichtEigenRisico").item(0).getTextContent());
					//Vind de behandelcodes
					Element codesElement = (Element) document.getElementsByTagName("behandelCodes").item(0);
					NodeList codes = codesElement.getElementsByTagName("behandelcode");
					ArrayList<String> behandelcodes = new ArrayList<>();
					for(int k = 0; k < codes.getLength(); k++){
						behandelcodes.add(codes.item(k).getTextContent());
					}
					Verzekeringstype type = new Verzekeringstype(id, eigenrisico, typenaam, behandelcodes);
					maatschappij.addType(type);
				}
				maatschappijlijst.add(maatschappij);
			}
			return maatschappijlijst;
		} catch(DOMException e){
			return null;
		}
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
