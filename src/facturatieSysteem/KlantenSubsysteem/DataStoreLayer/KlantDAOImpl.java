package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
		klantOverzicht = new ArrayList<Klant>();
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try{
			DocumentBuilder dBuilder = builderFactory.newDocumentBuilder();
			Document document = dBuilder.parse("XML/ClientFormat.xml");

			document.normalize();	
			
			Element rootElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList clienten = rootElement.getElementsByTagName("Client");
			for(int i = 0; i < clienten.getLength();i++){
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				String Naam = clientElement.getElementsByTagName("Naam").item(0).getTextContent();
				String Adres = clientElement.getElementsByTagName("Adres").item(0).getTextContent();
				String Postcode = clientElement.getElementsByTagName("Postcode").item(0).getTextContent();
				String Woonplaats = clientElement.getElementsByTagName("Woonplaats").item(0).getTextContent();
				Date Geboortedatum = dateFormat.parse(clientElement.getElementsByTagName("Geboortedatum").item(0).getTextContent());
				String TelefoonNr = clientElement.getElementsByTagName("Telefoonnummer").item(0).getTextContent();
				String Email = clientElement.getElementsByTagName("Email").item(0).getTextContent();
				Double ResterendEigenRisico = Double.parseDouble(clientElement.getElementsByTagName("ResterendEigenRisico").item(0).getTextContent());
				String RekeningNr = clientElement.getElementsByTagName("Rekeningnummer").item(0).getTextContent();
				String Betaalwijze= clientElement.getElementsByTagName("BetaalMethode").item(0).getTextContent();
				
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
				
				Element polisElement = (Element) rootElement.getElementsByTagName("VerzekeringPolis").item(0);
				
				String PolisNummer = polisElement.getAttribute("PolisNummer");
				String VerzekeringsType = clientElement.getElementsByTagName("VerzekeringType").item(0).getTextContent();
				Double EigenRisico = Double.parseDouble(clientElement.getElementsByTagName("EigenRisico").item(0).getTextContent());
				Date startDatum = dateFormat.parse(clientElement.getElementsByTagName("startDatum").item(0).getTextContent());
				Date eindDatum = dateFormat.parse(clientElement.getElementsByTagName("eindDatum").item(0).getTextContent());
				
				VerzekeringPolis Polis = new VerzekeringPolis(PolisNummer,VerzekeringsType,EigenRisico, startDatum, eindDatum); 
				klant = new Klant(BSN, Naam, Adres, Postcode, Woonplaats, Geboortedatum, TelefoonNr, Email, RekeningNr, ResterendEigenRisico, Polis, Betaalwijze);
				klantOverzicht.add(klant);
				
				
				System.out.println(PolisNummer);
				System.out.println(VerzekeringsType);
				System.out.println(EigenRisico);
				System.out.println(startDatum);
				System.out.println(eindDatum);
				System.out.println();
				
				
			}
			System.out.println("aantalklanten: "+klantOverzicht.size());
		} catch (ParserConfigurationException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return klantOverzicht;	    
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
