package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;


import java.util.ArrayList;




import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class FactuurDAO implements FactuurDAOinf {

	private ArrayList<Factuur> facturen;
	private Document document;
	private DAOFactoryFactuur daoFactory = new DAOFactoryFactuur();
	private Factuur factuur;

	/**Haalt alle facturen op van de klant waarvan deze geladen moeten worden.
	 * 
	 * @param De klant waarvan de facturen geladen worden.
	 * 
	 * @return ArrayList van facturen van de desbetreffende klant
	 */
	public ArrayList<Factuur> haalFacturen(Klant klant) {
		document = daoFactory.getDocument();
		try{
			Element rootElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList factuur = rootElement.getElementsByTagName("factuur");
			for(int i = 0; i < factuur.getLength();i++){
				Element factuurElement = (Element) factuur.item(i);
				int factuurNummer = Integer.parseInt(factuurElement.getAttribute("factuurNummer"));
				long factuurDatum = Long.parseLong(factuurElement.getElementsByTagName("factuurDatum").item(0).getTextContent());
				long vervalDatum = Long.parseLong(factuurElement.getElementsByTagName("vervalDatum").item(0).getTextContent());
		
				/*
				System.out.println("factuur: " + (i+1));
				System.out.println(factuurNummer);
				System.out.println(factuurDatum);
				System.out.println(vervalDatum);
				
				System.out.println();
				*/
			
				factuur = new Factuur(factuurNummer, factuurDatum, vervalDatum, null);
				facturen.add(factuur);
			
				/*
				System.out.println(PolisNummer);
				System.out.println(VerzekeringsType);
				System.out.println(EigenRisico);
				System.out.println(startDatum);
				System.out.println(eindDatum);
				System.out.println();
				*/
				
			}

		}catch(DOMException e){
			e.printStackTrace();
		}
		return facturen;	    
			
		}
	/*	System.out.println("haalAlleFacturen");


		if (document != null) {
			// Get all <member> elements from the document
			NodeList list = ((Element) document).getElementsByTagName("factuur");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node instanceof Element) {
					Element child = (Element) node;

					String factuurNummer = child.getElementsByTagName("factuurNummer").item(0).getTextContent();
					String factuurDatum = child.getElementsByTagName("factuurDatum").item(0).getTextContent();
					String vervalDatum = child.getElementsByTagName("vervalDatum").item(0).getTextContent();
					String BSN = child.getElementsByTagName("BSN").item(0).getTextContent();

					System.out.println("Adding " + factuurNummer + " " + factuurDatum + " " + vervalDatum + " to result");
					facturen.add(new Factuur(Integer.parseInt(factuurNummer), Long.parseLong(factuurDatum), Long.parseLong(vervalDatum), BSN));
					
					for(Factuur factuur : facturen){
						if(factuur.getBSN() != klant.getBSN()){
							facturen.remove(factuur);
							
						}
						
					}
				}
			}
		} else {
			System.out.println("XML document is null!");
		}
		
		System.out.println("returning result: " + facturen.size() + " items");
		return facturen; */
		
		
    }

	public Factuur maakFactuur(Klant klant) {

		System.out.println("maakFactuur " + newFactuur.getFactuurNummer());

		//
		// For consistency reasons, we should check whether the newMember
		// doesn't already exist in our data source. Since this file is only
		// for demonstration purpose, we do not perform that check here.
		//

		// Get the members element, of which there is only one.
		Node rootElement = document.getElementsByTagName("facturen").item(0);

		// Create the member with its attributes and sub-elements
		Element factuur = document.createElement("factuur");
		rootElement.appendChild(factuur);

		Element factuurnummer = document.createElement("factuurnummer");
		factuurnummer.appendChild(document.createTextNode(newFactuur.getFactuurNummer()));
		factuur.appendChild(factuurnummer);

		Element factuurDatum = document.createElement("factuurDatum");
		factuurDatum.appendChild(document.createTextNode(newFactuur.getFactuurDatum()));
		factuur.appendChild(factuurDatum);

		Element vervalDatum = document.createElement("vervalDatum");
		vervalDatum.appendChild(document.createTextNode(newFactuur.getVervalDatum()));
		vervalDatum.appendChild(vervalDatum);


		// Adding Reservations is left to the reader.

		domdocument.writeDocument();

		// TODO Return valid result from insertMember in XmlDOMMemberDAO
		return 0;
	}

	@Override
	public Factuur maakFactuur(Behandeling behandeling) {
		// TODO Auto-generated method stub
		return null;
	}
}

}
