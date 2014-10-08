package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.Document;
import javax.swing.text.html.parser.AttributeList;
import javax.swing.text.html.parser.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class KlantDAOImpl implements KlantDAO {
	private Klant klant;
	private ArrayList<Klant> klantOverzicht;

	public boolean addKlantXML(Klant klant){
		this.klant = klant;
		return false;
	}
	
	public ArrayList<Klant> getKlantenXML() {

		//get the factory
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

				try {

					//Using factory get an instance of document builder
					DocumentBuilder db = dbf.newDocumentBuilder();
					
					//parse using builder to get DOM representation of the XML file
					 org.w3c.dom.Document dom = db.parse("C:/Users/Sander Blijlevens/Desktop/ClientFormat.xsd");
					 //org.w3c.dom.Document dom = db.parse("/Users/xandergerreman/Dropbox/_GroepB2/XML/GOED/ClientFormat.xsd");
					
					 org.w3c.dom.Element docEle = dom.getDocumentElement();

						//get a nodelist of elements
						NodeList nl = docEle.getElementsByTagName("Client");	
						
						if(nl != null && nl.getLength() > 0) {
							for(int i = 0 ; i < nl.getLength();i++) {

								//get the employee element
								Node el = nl.item(i);

								//get the Employee object
								Klant e = getKlant(el);

								//add it to list
								klantOverzicht.add(e);
							}
						}

				}catch(ParserConfigurationException pce) {
					pce.printStackTrace();
				}catch(SAXException se) {
					se.printStackTrace();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
		
		
		
				System.out.println("klhsdfadfsdfdsfadsfdsfadsvbdfgafasdfdsa");
		return klantOverzicht;
	}

	private Klant getKlant(Node el) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		String BSN = "hallo";
		String Naam = getTextValue(el,"Name");
		String Adres = getTextValue(el,"Adres");
		String Postcode = getTextValue(el,"Postcode");
		String Woonplaats = getTextValue(el,"Woonplaats");
		Date Geboortedatum = null;
		String TelefoonNr = getTextValue(el,"telefoonnummer");
		String Email = getTextValue(el,"email");
		String RekeningNr = getTextValue(el,"Rekekingnummer");
		double ResterendEigenRisico = (Double) null;
		VerzekeringPolis verzekering = null;
		String Betaalwijze = getTextValue(el,"BetaalMethode");
		System.out.println(Email);
		AttributeList type = (AttributeList) ((DocumentBuilderFactory) el).getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		Klant e = new Klant(BSN, Naam, Adres,  Postcode, Woonplaats, Geboortedatum, TelefoonNr, Email, RekeningNr,ResterendEigenRisico, verzekering, Betaalwijze);

		return e;
	}
	
	
	private String getTextValue(Node el2, String tagName) {
		String textVal = null;
		NodeList nl = ((org.w3c.dom.Document) el2).getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Node el = nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	
	
	public boolean updateKlantXML(Klant klant) {
		this.klant = klant;
		return false;

		// updaten van gegevens van een klant
	}

	public boolean verwijderKlantXML(Klant klant) {
		this.klant = klant;
		return false;
		
		// verwijder klant uit XML
	}
}
