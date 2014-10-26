package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsDAOImpl implements VerzekeringstypeDAO {
	private Document document;
	private DAOFactoryVerzekering daoFactory = new DAOFactoryVerzekering();
	
	@Override
	public boolean addVerzekeringstypeXML(String maatschappijnr,Verzekeringstype type) {
		document = daoFactory.getDocument();
		try {
			Element facturatieSysteem = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
			NodeList maatschappijen = facturatieSysteem.getElementsByTagName("verzekeringsmaatschappij");
			
			for(int i = 0; i < maatschappijen.getLength();i++){
				Element maatschappijElement = (Element) maatschappijen.item(i);
				String nr = maatschappijElement.getAttribute("maatschappijnr");
				if(maatschappijnr.equals(nr)){
					
					Element verzekeringsTypes = (Element) maatschappijElement.getElementsByTagName("verzekeringsTypes").item(0);
					Element verzekeringsType = document.createElement("verzekeringsType");
					Element behandelCodes = document.createElement("behandelCodes");
					verzekeringsTypes.appendChild(document.createTextNode("\n\t\t\t"));
					verzekeringsTypes.appendChild(verzekeringsType);
					
					Attr polisNummer = document.createAttribute("typenr");
					polisNummer.setValue("" + type.getNr());
					verzekeringsType.setAttributeNode(polisNummer);
					
					Element typenaam = document.createElement("naam");
					typenaam.appendChild(document.createTextNode(type.getNaam()));
					verzekeringsType.appendChild(typenaam);
					
					verzekeringsType.appendChild(document.createTextNode("\n\t\t\t\t"));
					Element eigenrisico = document.createElement("verplichtEigenRisico");
					eigenrisico.appendChild(document.createTextNode(Integer.toString(type.getEigenRisico())));
					verzekeringsType.appendChild(eigenrisico);
					
					verzekeringsType.appendChild(document.createTextNode("\n\t\t\t\t"));
					verzekeringsType.appendChild(behandelCodes);
					
					verzekeringsType.appendChild(document.createTextNode("\n\t\t\t"));
					System.out.println(type.getNr());
					break;
				}
			}
		} catch(DOMException e){
			return false;
		}
		return false;
	}

	@Override
	public boolean deleteVerzekeringstypeXML(String typenr, Verzekeringstype type) {
		document = daoFactory.getDocument();
		try{
			Element facturatieSysteem = (Element) document.getElementsByTagName("facturatieSysteem").item(0);
			NodeList maatschappijen = facturatieSysteem.getElementsByTagName("verzekeringsmaatschappij");
			//loop through all clients
			for(int i = 0; i < maatschappijen.getLength();i++){
				Element maatschappijElement = (Element) maatschappijen.item(i);
				Element verzekeringsPolissenElement = (Element) maatschappijElement.getElementsByTagName("verzekeringsTypes").item(0);
				NodeList polissen = verzekeringsPolissenElement.getElementsByTagName("verzekeringsType");
				//loop through all clients
				for(int j = 0; j < polissen.getLength(); j++){
					Element PolisElement = (Element) polissen.item(j);
					String polisNummer = PolisElement.getAttribute("typenr");
				
					if(polisNummer.equals(typenr)){
						//delete client
						PolisElement.getParentNode().removeChild(PolisElement);
						break;
					}
				}
			}
			
			return daoFactory.writeDocument();
			}catch(DOMException e){
				return false;
			}
	}

	@Override
	public boolean updateVerzekeringstypeXML(Verzekeringstype type) {
		return false;
	}

}
