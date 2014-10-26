package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
				String maatschappijnr = maatschappijElement.getAttribute("maatschappijnr");
				String naam = maatschappijElement.getElementsByTagName("naam").item(0).getTextContent();
				String adres = maatschappijElement.getElementsByTagName("adres").item(0).getTextContent();
				String postcode = maatschappijElement.getElementsByTagName("postcode").item(0).getTextContent();
				String woonplaats = maatschappijElement.getElementsByTagName("woonplaats").item(0).getTextContent();
				int KVKnummer = Integer.parseInt(maatschappijElement.getElementsByTagName("KVKNummer").item(0).getTextContent());
				int rekeningnummer = Integer.parseInt(maatschappijElement.getElementsByTagName("rekeningNummer").item(0).getTextContent());
				//Maak een nieuwe maatschappij met de gevonden gegevens
				Verzekeringsmaatschappij maatschappij = new Verzekeringsmaatschappij(maatschappijnr,naam,adres,postcode,woonplaats,KVKnummer, rekeningnummer);
				//Vind de verzekeringstypes
				Element typesElement = (Element) document.getElementsByTagName("verzekeringsTypes").item(0);
				NodeList types = typesElement.getElementsByTagName("verzekeringsType");
				for(int j = 0; j < types.getLength(); j++){
					Element typeElement = (Element) types.item(j);
					String typenr = typeElement.getAttribute("typenr");
					String typenaam = typeElement.getElementsByTagName("naam").item(0).getTextContent();
					int eigenrisico = Integer.parseInt(typeElement.getElementsByTagName("verplichtEigenRisico").item(0).getTextContent());
					//Vind de behandelcodes
					Element codesElement = (Element) typeElement.getElementsByTagName("behandelCodes").item(0);
					NodeList codes = codesElement.getElementsByTagName("behandelcode");
					
					Verzekeringstype type = new Verzekeringstype(typenr, eigenrisico, typenaam);
					for(int k = 0; k < codes.getLength(); k++){
						type.addCode(codes.item(k).getTextContent());
					}
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
		document = daoFactory.getDocument();
		try{
		
		Node facturatieSysteem = document.getElementsByTagName("facturatieSysteem").item(0);
		facturatieSysteem.appendChild(document.createTextNode("\t"));
		
		//Create all Elements
		Element verzekeringsmaatschappij = document.createElement("verzekeringsmaatschappij");
		Element verzekeringsTypes = document.createElement("verzekeringsTypes");
		Element verzekeringsType = document.createElement("verzekeringsType");
		Element behandelCodes = document.createElement("behandelCodes");
		
		
		//Ordering Elements
		facturatieSysteem.appendChild(verzekeringsmaatschappij);
		
		Attr maatschappijnr = document.createAttribute("maatschappijnr");
		maatschappijnr.setValue("" + maatschappij.getNr());
		verzekeringsmaatschappij.setAttributeNode(maatschappijnr);

		//fill Verzekeringsmaatschappij
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));//opmaak XML
			Element naam = document.createElement("naam");
			naam.appendChild(document.createTextNode(maatschappij.getNaam()));
			verzekeringsmaatschappij.appendChild(naam);
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			Element adres = document.createElement("adres");
			adres.appendChild(document.createTextNode(maatschappij.getAdres()));
			verzekeringsmaatschappij.appendChild(adres );
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			Element postcode = document.createElement("postcode");
			postcode.appendChild(document.createTextNode(maatschappij.getPostcode()));
			verzekeringsmaatschappij.appendChild(postcode);
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			Element woonplaats = document.createElement("woonplaats");
			woonplaats.appendChild(document.createTextNode(maatschappij.getPlaats()));
			verzekeringsmaatschappij.appendChild(woonplaats);
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			Element kvk = document.createElement("KVKNummer");
			kvk.appendChild(document.createTextNode(Integer.toString(maatschappij.getKVK())));
			verzekeringsmaatschappij.appendChild(kvk);
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			Element rekeningNummer = document.createElement("rekeningNummer");
			rekeningNummer.appendChild(document.createTextNode(Integer.toString(maatschappij.getRekeningNR())));
			verzekeringsmaatschappij.appendChild(rekeningNummer);
			
			ArrayList<Verzekeringstype> VerzekeringTypes = maatschappij.getTypes();
			
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t\t"));
			verzekeringsmaatschappij.appendChild(verzekeringsTypes);
			
			//Verwerk de types
			if(VerzekeringTypes.size() > 0){
				verzekeringsTypes.appendChild(document.createTextNode("\n\t\t\t"));
				verzekeringsTypes.appendChild(verzekeringsType);
				verzekeringsType.appendChild(document.createTextNode("\n\t\t\t\t"));
				System.out.println("x");
			}
			
			Attr typenr = document.createAttribute("typenr");
			
			for(Verzekeringstype polis : VerzekeringTypes){
				typenr.setValue("" + polis.getNr());
				verzekeringsType.setAttributeNode(typenr);
				
				Element typenaam = document.createElement("naam");
				typenaam.appendChild(document.createTextNode(polis.getNaam()));
				verzekeringsType.appendChild(typenaam);
				
				verzekeringsType.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element eigenrisico = document.createElement("verplichtEigenRisico");
				eigenrisico.appendChild(document.createTextNode(Integer.toString(polis.getEigenRisico())));
				verzekeringsType.appendChild(eigenrisico);
				
				verzekeringsType.appendChild(document.createTextNode("\n\t\t\t\t"));
				verzekeringsType.appendChild(behandelCodes);
				ArrayList<String> behandelcodes = polis.getBehandelcodes();
				for(int i = 0; i < behandelcodes.size(); i++){
					behandelCodes.appendChild(document.createTextNode("\n\t\t\t\t\t"));
					Element behandelcode = document.createElement("behandelcode");
					behandelcode.appendChild(document.createTextNode(behandelcodes.get(i)));
					behandelCodes.appendChild(behandelcode);
				}
				behandelCodes.appendChild(document.createTextNode("\n\t\t\t\t"));
				verzekeringsType.appendChild(document.createTextNode("\n\t\t\t"));
			}
			
			verzekeringsTypes.appendChild(document.createTextNode("\n\t\t"));// </VerzekeringPolissen>
			verzekeringsmaatschappij.appendChild(document.createTextNode("\n\t"));// </Client>
			facturatieSysteem.appendChild(document.createTextNode("\n"));// <Clienten/>

		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}

	@Override
	public boolean updateMaatschappijXML(Verzekeringsmaatschappij maatschappij) {
		document = daoFactory.getDocument();
		try{
		Element facturatieSysteem = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
		NodeList verzekeringsmaatschappijen = facturatieSysteem.getElementsByTagName("verzekeringsmaatschappij");
		//loop through all clients
		for(int i = 0; i < verzekeringsmaatschappijen.getLength();i++){
			Element verzekeringsmaatschappij = (Element) verzekeringsmaatschappijen.item(i);
			String naam = verzekeringsmaatschappij.getElementsByTagName("naam").item(0).getTextContent();
			if(maatschappij.getNaam().equals(naam)){
				//get all elements of Client
				Element Naam = (Element) verzekeringsmaatschappij.getElementsByTagName("naam").item(0);
				Element Adres = (Element) verzekeringsmaatschappij.getElementsByTagName("adres").item(0);
				Element Postcode = (Element) verzekeringsmaatschappij.getElementsByTagName("postcode").item(0);
				Element Woonplaats = (Element) verzekeringsmaatschappij.getElementsByTagName("woonplaats").item(0);
				Element KVK = (Element) verzekeringsmaatschappij.getElementsByTagName("KVKNummer").item(0);
				Element Rekening = (Element) verzekeringsmaatschappij.getElementsByTagName("rekeningNummer").item(0);
				
				//fill elements with the information
				Naam.setTextContent(maatschappij.getNaam());
				Adres.setTextContent(maatschappij.getAdres());
				Postcode.setTextContent(maatschappij.getPostcode());
				Woonplaats.setTextContent(maatschappij.getPlaats());
				KVK.setTextContent(Integer.toString(maatschappij.getKVK()));
				Rekening.setTextContent(Integer.toString(maatschappij.getRekeningNR()));
				break;
			}
		}
		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}

	@Override
	public boolean deleteMaatschappijXML(String nr) {
		document = daoFactory.getDocument();
		try{
		Element clientenElement = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
		NodeList maatschappijen = clientenElement.getElementsByTagName("verzekeringsmaatschappij");
		// loop through all clients
		for(int i = 0; i < maatschappijen.getLength();i++){
			Element maatschappijElement = (Element) maatschappijen.item(i);
			String maatschappijnr = maatschappijElement.getAttribute("maatschappijnr");
			
			if(maatschappijnr.equals(nr)){
				// delete client
				maatschappijElement.getParentNode().removeChild(maatschappijElement);
				break;
			}
		}
		
		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}

}
