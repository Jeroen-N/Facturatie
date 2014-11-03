package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantDAOImpl implements KlantDAO {
	private ArrayList<VerzekeringPolis> VerzekeringPolissen;
	private ArrayList<Klant> klantOverzicht;
	private ArrayList<Klant> zoekResultaat;
	private Klant klant;
	private Document document;
	private DAOFactoryKlant daoFactory = new DAOFactoryKlant();
	private ArrayList<String> BSNs;
	
	@Override
	public boolean addKlantXML(Klant klant){
		document = daoFactory.getDocument();
		try{
		this.klant = klant;
		
		Node clienten = document.getElementsByTagName("Clienten").item(0);
		clienten.appendChild(document.createTextNode("\n\t"));
		
		//Create all Elements
		Element client = document.createElement("Client");
		Element clientGegevens = document.createElement("ClientGegevens");
		Element verzekeringPolissen = document.createElement("VerzekeringPolissen");
		Element verzekeringPolis = document.createElement("VerzekeringPolis");
		Element Facturen = document.createElement("Facturen");
		Element Behandelingen = document.createElement("Behandelingen");
		Element Diagnoses = document.createElement("Diagnoses");
		
		//Ordering Elements
		clienten.appendChild(client);
			client.appendChild(document.createTextNode("\n\t\t")); // <ClientGegevens>
			client.appendChild(clientGegevens);
			client.appendChild(document.createTextNode("\n\t\t")); // <VerzekeringPolissen>
			client.appendChild(verzekeringPolissen);
				verzekeringPolissen.appendChild(document.createTextNode("\n\t\t\t")); // <VerzekeringPolis>
				verzekeringPolissen.appendChild(verzekeringPolis);
			client.appendChild(document.createTextNode("\n\t\t"));
			client.appendChild(Facturen);
			client.appendChild(document.createTextNode("\n\t\t"));
			client.appendChild(Behandelingen);
			client.appendChild(document.createTextNode("\n\t\t"));
			client.appendChild(Diagnoses);
		
		//create Attribuut BSN
		Attr BSN = document.createAttribute("BSN");
		BSN.setValue("" + klant.getBSN());
		client.setAttributeNode(BSN);

		//fill ClientGegevens
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));//opmaak XML
			Element naam = document.createElement("Naam");
			naam.appendChild(document.createTextNode(klant.getNaam()));
			clientGegevens.appendChild(naam);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element adres = document.createElement("Adres");
			adres.appendChild(document.createTextNode(klant.getAdres()));
			clientGegevens.appendChild(adres );
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element postcode = document.createElement("Postcode");
			postcode.appendChild(document.createTextNode(klant.getPostcode()));
			clientGegevens.appendChild(postcode);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element woonplaats = document.createElement("Woonplaats");
			woonplaats.appendChild(document.createTextNode(klant.getWoonplaats()));
			clientGegevens.appendChild(woonplaats);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element geboortedatum = document.createElement("Geboortedatum");
			geboortedatum.appendChild(document.createTextNode(klant.getGeboortedatum()));
			clientGegevens.appendChild(geboortedatum);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element telefoonnummer = document.createElement("Telefoonnummer");
			telefoonnummer.appendChild(document.createTextNode(klant.getTelefoonnummer()));
			clientGegevens.appendChild(telefoonnummer);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element email = document.createElement("Email");
			email.appendChild(document.createTextNode(klant.getEmail()));
			clientGegevens.appendChild(email);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element betaalMethode = document.createElement("BetaalMethode");
			betaalMethode.appendChild(document.createTextNode(klant.getBetaalMethode()));
			clientGegevens.appendChild(betaalMethode);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element resterendEigenRisico = document.createElement("ResterendEigenRisico");
			resterendEigenRisico.appendChild(document.createTextNode(Double.toString(klant.getResterendEigenRisico())));
			clientGegevens.appendChild(resterendEigenRisico);
			
			clientGegevens.appendChild(document.createTextNode("\n\t\t\t"));
			Element rekeningnummer = document.createElement("Rekeningnummer");
			rekeningnummer.appendChild(document.createTextNode(klant.getRekeningnummer()));
			clientGegevens.appendChild(rekeningnummer);
		
			clientGegevens.appendChild(document.createTextNode("\n\t\t"));// </ClientGegevens>
		//Fill ArrayList with Polissen

			VerzekeringPolissen = klant.getVerzekeringPolissen();
			
			for(VerzekeringPolis polis : VerzekeringPolissen){
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
				startDatum.appendChild(document.createTextNode(polis.getStartDatum()));
				verzekeringPolis.appendChild(startDatum);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t\t"));
				Element eindDatum = document.createElement("eindDatum");
				eindDatum.appendChild(document.createTextNode(polis.getEindDatum()));
				verzekeringPolis.appendChild(eindDatum);
				
				verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));// </VerzekeringPolis>
			}
			
			verzekeringPolissen.appendChild(document.createTextNode("\n\t\t"));// </VerzekeringPolissen>
			client.appendChild(document.createTextNode("\n\t"));// </Client>
			clienten.appendChild(document.createTextNode("\n"));// <Clienten/>

		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}
	
	@Override
	public ArrayList<Klant> getKlantenXML() {
		document = daoFactory.getDocument();
		klantOverzicht = new ArrayList<Klant>();
		try{
			Element rootElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList clienten = rootElement.getElementsByTagName("Client");
			//loop through clients
			for(int i = 0; i < clienten.getLength();i++){
				//get client information
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				String Naam = clientElement.getElementsByTagName("Naam").item(0).getTextContent();
				String Adres = clientElement.getElementsByTagName("Adres").item(0).getTextContent();
				String Postcode = clientElement.getElementsByTagName("Postcode").item(0).getTextContent();
				String Woonplaats = clientElement.getElementsByTagName("Woonplaats").item(0).getTextContent();
				String Geboortedatum = clientElement.getElementsByTagName("Geboortedatum").item(0).getTextContent();
				String TelefoonNr = clientElement.getElementsByTagName("Telefoonnummer").item(0).getTextContent();
				String Email = clientElement.getElementsByTagName("Email").item(0).getTextContent();
				Double ResterendEigenRisico = Double.parseDouble(clientElement.getElementsByTagName("ResterendEigenRisico").item(0).getTextContent());
				String RekeningNr = clientElement.getElementsByTagName("Rekeningnummer").item(0).getTextContent();
				String Betaalwijze= clientElement.getElementsByTagName("BetaalMethode").item(0).getTextContent();
				Element polissenElement = (Element) clientElement.getElementsByTagName("VerzekeringPolissen").item(0);
				NodeList polissen = polissenElement.getElementsByTagName("VerzekeringPolis");
				ArrayList<VerzekeringPolis> VerzekeringPolissen = new ArrayList<>();
				//loop through polissen of Client
				for (int j = 0; j < polissen.getLength();j++){	
					Element polisElement = (Element) polissen.item(j);
					//get Polis information
					String PolisNummer = polisElement.getAttribute("PolisNummer");
					String VerzekeringsType = polisElement.getElementsByTagName("VerzekeringType").item(0).getTextContent();
					Double EigenRisico = Double.parseDouble(polisElement.getElementsByTagName("EigenRisico").item(0).getTextContent());
					String startDatum = polisElement.getElementsByTagName("startDatum").item(0).getTextContent();
					String eindDatum = polisElement.getElementsByTagName("eindDatum").item(0).getTextContent();
					
					//create Polis and add to ArrayList
					VerzekeringPolis Polis = new VerzekeringPolis(PolisNummer,VerzekeringsType,EigenRisico, startDatum, eindDatum);
					VerzekeringPolissen.add(Polis);
				}
				//create Klant add to ArrayList
				klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats, Geboortedatum, TelefoonNr, Email, RekeningNr, ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);
				klantOverzicht.add(klant);

				
			}

		}catch(DOMException e){
			e.printStackTrace();
		}
		return klantOverzicht;	    
	}
	
	@Override
	public ArrayList<Klant> findKlantXML(String gebDatum){
		document = daoFactory.getDocument();
		zoekResultaat = new ArrayList<Klant>();
		ArrayList<VerzekeringPolis> VerzekeringPolissen = new ArrayList<>();
		
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			String Geboortedatum = clientElement.getElementsByTagName("Geboortedatum").item(0).getTextContent();
			if(gebDatum.equals(Geboortedatum)){
				//get all information of Client
				String BSN = clientElement.getAttribute("BSN");
				String Naam = clientElement.getElementsByTagName("Naam").item(0).getTextContent();
				String Adres = clientElement.getElementsByTagName("Adres").item(0).getTextContent();
				String Postcode = clientElement.getElementsByTagName("Postcode").item(0).getTextContent();
				String Woonplaats = clientElement.getElementsByTagName("Woonplaats").item(0).getTextContent();
				String TelefoonNr = clientElement.getElementsByTagName("Telefoonnummer").item(0).getTextContent();
				String Email = clientElement.getElementsByTagName("Email").item(0).getTextContent();
				Double ResterendEigenRisico = Double.parseDouble(clientElement.getElementsByTagName("ResterendEigenRisico").item(0).getTextContent());
				String RekeningNr = clientElement.getElementsByTagName("Rekeningnummer").item(0).getTextContent();
				String Betaalwijze= clientElement.getElementsByTagName("BetaalMethode").item(0).getTextContent();
			
				NodeList polissen = clientElement.getElementsByTagName("VerzekeringPolis");
				VerzekeringPolissen.clear();
				for (int j = 0; j < polissen.getLength();j++){
					Element polisElement = (Element) polissen.item(j);
					String PolisNummer = polisElement.getAttribute("PolisNummer");
					String VerzekeringsType = clientElement.getElementsByTagName("VerzekeringType").item(0).getTextContent();
					Double EigenRisico = Double.parseDouble(clientElement.getElementsByTagName("EigenRisico").item(0).getTextContent());
					String startDatum = clientElement.getElementsByTagName("startDatum").item(0).getTextContent();
					String eindDatum = clientElement.getElementsByTagName("eindDatum").item(0).getTextContent();
					
					VerzekeringPolis Polis = new VerzekeringPolis(PolisNummer,VerzekeringsType,EigenRisico, startDatum, eindDatum);
					VerzekeringPolissen.add(Polis);	
				}
				//create Klant and add to ArrayList
				klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats, Geboortedatum, TelefoonNr, Email, RekeningNr, ResterendEigenRisico, VerzekeringPolissen, Betaalwijze);
				zoekResultaat.add(klant);
				
			}
			
		}
		return zoekResultaat;	
	}
	
	@Override
	public boolean updateKlantXML(Klant klant) {
		document = daoFactory.getDocument();
		try{
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			String BSN = clientElement.getAttribute("BSN");
			if(BSN.equals(klant.getBSN())){
				//get all elements of Client
				Element Naam = (Element) clientElement.getElementsByTagName("Naam").item(0);
				Element Adres = (Element) clientElement.getElementsByTagName("Adres").item(0);
				Element Postcode = (Element) clientElement.getElementsByTagName("Postcode").item(0);
				Element Woonplaats = (Element) clientElement.getElementsByTagName("Woonplaats").item(0);
				Element Geboortedatum = (Element) clientElement.getElementsByTagName("Geboortedatum").item(0);
				Element TelefoonNr = (Element) clientElement.getElementsByTagName("Telefoonnummer").item(0);
				Element Email = (Element) clientElement.getElementsByTagName("Email").item(0);
				Element ResterendEigenRisico = (Element) clientElement.getElementsByTagName("ResterendEigenRisico").item(0);
				Element RekeningNr = (Element) clientElement.getElementsByTagName("Rekeningnummer").item(0);
				Element Betaalwijze= (Element) clientElement.getElementsByTagName("BetaalMethode").item(0);
				
				//fill elements with the information
				Naam.setTextContent(klant.getNaam());
				Adres.setTextContent(klant.getAdres());
				Postcode.setTextContent(klant.getPostcode());
				Woonplaats.setTextContent(klant.getWoonplaats());
				Geboortedatum.setTextContent(klant.getGeboortedatum());
				TelefoonNr.setTextContent(klant.getTelefoonnummer());
				Email.setTextContent(klant.getEmail());
				Betaalwijze.setTextContent(klant.getBetaalMethode());
				ResterendEigenRisico.setTextContent(Double.toString(klant.getResterendEigenRisico()));
				RekeningNr.setTextContent(klant.getRekeningnummer());
				break;
			}
		}
		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
	}
	
	@Override
	public boolean verwijderKlantXML(String verwijderBSN) {
		document = daoFactory.getDocument();
		try{
		Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
		NodeList clienten = clientenElement.getElementsByTagName("Client");
		//loop through all clients
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			String BSN = clientElement.getAttribute("BSN");
			
			if(BSN.equals(verwijderBSN)){
				//delete client
				clientElement.getParentNode().removeChild(clientElement);
				break;
			}
		}
		
		return daoFactory.writeDocument();
		}catch(DOMException e){
			return false;
		}
		
	}
	
	@Override
	public ArrayList<String> getBSNs(){
		BSNs = new ArrayList<String>();
		document = daoFactory.getDocument();
		try{
			Element rootElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList clienten = rootElement.getElementsByTagName("Client");
			//loop through all clients
			for(int i = 0; i < clienten.getLength();i++){
				//add BSN to ArrayList
				Element clientElement = (Element) clienten.item(i);
				BSNs.add(clientElement.getAttribute("BSN"));
			}
		}catch(DOMException e){
			e.printStackTrace();
		}
		return BSNs;
	}
	
}
