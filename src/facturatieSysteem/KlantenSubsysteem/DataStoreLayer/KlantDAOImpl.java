package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
 










import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.util.Date;

import javax.naming.spi.ObjectFactory;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;












import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantDAOImpl implements KlantDAO {
	private String BSN;
	private Klant klant;
	private VerzekeringPolis polis;
	private ArrayList<Klant> klantOverzicht;
	private String xmlPath = "XML/ClientFormat.xml";
	private String xsdPath = "XML/ClientFormat.xsd";
	private Document document;
	
	public KlantDAOImpl(){
	try{
		Schema schema = getValidationSchema();
		if (schema == null) {
			System.out.println("Schema file not found or contains errors, XML file not validated!");
			// Here we could decide to cancel initialization of the application.
			// For now, we do not.
		} else {
			validateDocument(xmlPath, schema);
		}
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
		 document = dBuilder.parse(xmlPath);
		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} 

	}
	
	public boolean addKlantXML(Klant klant){
		this.klant = klant;
		
		Node rootElement = document.getElementsByTagName("Clienten").item(0);
		rootElement.appendChild(document.createTextNode("\n\t"));
		
		//Elementen aanmaken
		Element newKlant = document.createElement("Client");
		Element clientGegevens = document.createElement("ClientGegevens");
		Element verzekeringPolis = document.createElement("VerzekeringPolis");
		
		//Elementen ordenen
		rootElement.appendChild(newKlant);
		newKlant.appendChild(document.createTextNode("\n\t\t"));//<ClientGegevens>
		newKlant.appendChild(clientGegevens);
		newKlant.appendChild(document.createTextNode("\n\t\t"));//<ClientGegevens>
		newKlant.appendChild(verzekeringPolis);
		
		//Atribuut BSN aan client meegeven
		Attr BSN = document.createAttribute("BSN");
		BSN.setValue("" + klant.getBSN());
		newKlant.setAttributeNode(BSN);

		
		
		
		//ClientGegevens vullen
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
			
			//
		//VerzekeringPolis vullen	
			polis = klant.getVerzekering();
			
			Attr polisNummer = document.createAttribute("PolisNummer");
			polisNummer.setValue("" + polis.getPolisNummer());
			verzekeringPolis.setAttributeNode(polisNummer);
			
			verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));
			Element verzekeringsType = document.createElement("VerzekeringType");
			verzekeringsType.appendChild(document.createTextNode(polis.getVerzekeringsType()));
			verzekeringPolis.appendChild(verzekeringsType);
			
			verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));
			Element eigenRisico = document.createElement("EigenRisico");
			eigenRisico.appendChild(document.createTextNode(Double.toString(polis.getExtraEigenRisisco())));
			verzekeringPolis.appendChild(eigenRisico);
			
			verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));
			Element startDatum = document.createElement("startDatum");
			startDatum.appendChild(document.createTextNode(polis.getStartDatum()));
			verzekeringPolis.appendChild(startDatum);
			
			verzekeringPolis.appendChild(document.createTextNode("\n\t\t\t"));
			Element eindDatum = document.createElement("eindDatum");
			eindDatum.appendChild(document.createTextNode(polis.getEindDatum()));
			verzekeringPolis.appendChild(eindDatum);
			
			verzekeringPolis.appendChild(document.createTextNode("\n\t\t"));//</VerzekeringPolis>
			newKlant.appendChild(document.createTextNode("\n\t"));//</Client>
			rootElement.appendChild(document.createTextNode("\n"));//<Clienten/>
		if(writeDocument()){
			document.normalize();
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	
	public ArrayList<Klant> getKlantenXML() {
		klantOverzicht = new ArrayList<Klant>();
		try{
			Element rootElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList clienten = rootElement.getElementsByTagName("Client");
			for(int i = 0; i < clienten.getLength();i++){
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
				/*
				System.out.println("client: " + (i+1));
				System.out.println(BSN);
				System.out.println(Naam);
				System.out.println(Adres);
				System.out.println(Postcode);
				System.out.println(Woonplaats);
				System.out.println(Geboortedatum);
				System.out.println(TelefoonNr);
				System.out.println(Email);
				System.out.println(ResterendEigenRisico);
				System.out.println(RekeningNr);
				System.out.println(Betaalwijze);
				System.out.println();
				*/
				Element polisElement = (Element) rootElement.getElementsByTagName("VerzekeringPolis").item(0);
				
				String PolisNummer = polisElement.getAttribute("PolisNummer");
				String VerzekeringsType = clientElement.getElementsByTagName("VerzekeringType").item(0).getTextContent();
				Double EigenRisico = Double.parseDouble(clientElement.getElementsByTagName("EigenRisico").item(0).getTextContent());
				String startDatum = clientElement.getElementsByTagName("startDatum").item(0).getTextContent();
				String eindDatum = clientElement.getElementsByTagName("eindDatum").item(0).getTextContent();
				
				VerzekeringPolis Polis = new VerzekeringPolis(PolisNummer,VerzekeringsType,EigenRisico, startDatum, eindDatum); 
				klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats, Geboortedatum, TelefoonNr, Email, RekeningNr, ResterendEigenRisico, Polis, Betaalwijze);
				klantOverzicht.add(klant);
				
				/*
				System.out.println(PolisNummer);
				System.out.println(VerzekeringsType);
				System.out.println(EigenRisico);
				System.out.println(startDatum);
				System.out.println(eindDatum);
				System.out.println();
				*/
				
			}
			
			} catch (DOMException e) {
			e.printStackTrace();
		} 
	
		return klantOverzicht;	    
	}

	public boolean updateKlantXML(Klant klant) {
		this.klant = klant;
		return false;

		// updaten van gegevens van een klant
	}

	public boolean verwijderKlantXML(String verwijderBSN) {
		// verwijder klant uit XML
		NodeList clienten = document.getElementsByTagName("Client");
		for(int i = 0; i < clienten.getLength();i++){
			Element clientElement = (Element) clienten.item(i);
			String BSN = clientElement.getAttribute("BSN");
			
			if(BSN.equals(verwijderBSN)){
				System.out.println("hij komt in if statement");
				clientElement.getParentNode().removeChild(clientElement);
			}
		}
		if(writeDocument()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean writeDocument() {

		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(xmlPath));
			transformer.transform(source, result);
			return true;
		} catch (TransformerConfigurationException e) {
			
		} catch (TransformerException e) {
			
		}
		return false;
	}
	
	private Schema getValidationSchema() {
		Schema schema = null;

		try {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			System.out.println("getValidationSchema " + xsdPath);
			schema = factory.newSchema(new File(xsdPath));
		} catch (Exception e) {
			System.out.println("Error reading schema file: " + e.getMessage());
		}

		return schema;
	}
	private boolean validateDocument(String xmlFile, Schema schema) {
		
		System.out.println("validateDocument");

		boolean result = false;

		try {
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlFile));
			result = true;
			System.out.println("Valid XML: " + xmlFile);
		} catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		} catch (SAXException e) {
			System.out.println("Parse exception: " + e.getMessage());
		}
		return result;

	}
	
}
