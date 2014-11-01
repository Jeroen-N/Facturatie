package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class VerzekeringPolisDAOImpl implements VerzekeringPolisDAO {

	private Document document;
	private DAOFactoryKlant daoFactory = new DAOFactoryKlant();
	private DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public boolean addVerzekeringPolisXML(String addBSN, VerzekeringPolis polis){
		document = daoFactory.getDocument();
		try{
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			String BSN = clientElement.getAttribute("BSN");
			if(addBSN.equals(BSN)){
				//add verzekeringPolis to verzekeringPolissen
				Element verzekeringPolissen = (Element) clientElement.getElementsByTagName("VerzekeringPolissen").item(0);
				Element verzekeringPolis = document.createElement("VerzekeringPolis");
				verzekeringPolissen.appendChild(document.createTextNode("\n\t\t\t")); // <VerzekeringPolis>
				verzekeringPolissen.appendChild(verzekeringPolis);
				
				Attr polisNummer = document.createAttribute("PolisNummer");
				polisNummer.setValue("" + polis.getPolisNummer());
				verzekeringPolis.setAttributeNode(polisNummer);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element verzekeringsType = document.createElement("VerzekeringType");
				verzekeringsType.appendChild(document.createTextNode(polis.getVerzekeringsType()));
				verzekeringPolis.appendChild(verzekeringsType);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element eigenRisico = document.createElement("EigenRisico");
				eigenRisico.appendChild(document.createTextNode(Double.toString(polis.getExtraEigenRisico())));
				verzekeringPolis.appendChild(eigenRisico);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element startDatum = document.createElement("startDatum");
				startDatum.appendChild(document.createTextNode(df1.format(polis.getStartDatum())));
				verzekeringPolis.appendChild(startDatum);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element eindDatum = document.createElement("eindDatum");
				eindDatum.appendChild(document.createTextNode(df1.format(polis.getEindDatum())));
				verzekeringPolis.appendChild(eindDatum);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));// </VerzekeringPolis>
				break;
			}
		}
		
		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}
	
	@Override
	public boolean updateVerzekeringPolisXML(VerzekeringPolis polis) {
		document = daoFactory.getDocument();
		try{
		
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			NodeList polissen = clientElement.getElementsByTagName("VerzekeringPolis");
			//loop through polissen of Client
			for (int j = 0; j < polissen.getLength();j++){
				Element polisElement = (Element) polissen.item(j);
				String polisnummer = polisElement.getAttribute("PolisNummer");
				if(polisnummer.equals(polis.getPolisNummer())){
					
					//get Polis Elements
					Element verzekeringsType = (Element)  polisElement.getElementsByTagName("VerzekeringType").item(0);
					Element eigenRisico = (Element) polisElement.getElementsByTagName("EigenRisico").item(0);
					Element startDatum = (Element)  polisElement.getElementsByTagName("startDatum").item(0);
					Element eindDatum = (Element)  polisElement.getElementsByTagName("eindDatum").item(0);
					
					//fill elements with the information
					verzekeringsType.setTextContent(polis.getVerzekeringsType());
					eigenRisico.setTextContent(Double.toString((polis.getExtraEigenRisico())));
					startDatum.setTextContent(df1.format(polis.getStartDatum()));
					eindDatum.setTextContent(df1.format(polis.getEindDatum()));
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
	public boolean verwijderPolisXML(String Polisnummer, String BSN) {
		document = daoFactory.getDocument();
		try{
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			Element verzekeringsPolissenElement = (Element) clientElement.getElementsByTagName("VerzekeringPolissen").item(0);
			NodeList polissen = verzekeringsPolissenElement.getElementsByTagName("VerzekeringPolis");
			//loop through all clients
			for(int j = 0; j < polissen.getLength(); j++){
				Element PolisElement = (Element) polissen.item(j);
				String polisNummer = PolisElement.getAttribute("PolisNummer");
			
				if(polisNummer.equals(Polisnummer)){
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
}
