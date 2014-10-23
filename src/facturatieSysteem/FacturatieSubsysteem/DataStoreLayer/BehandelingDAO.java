package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class BehandelingDAO implements BehandelDAOinf {

	private Document document = null;
	private DAOFactoryFactuur daoFactoryBehandelcode;
	private DAOFactoryFactuur daoFactoryClient;
	private DAOFactoryFactuur daoFactoryFacturatie;

	public BehandelingDAO(DAOFactoryFactuur daoFactoryBehandelcode,
			DAOFactoryFactuur daoFactoryClient,
			DAOFactoryFactuur daoFactoryFacturatie) {
		this.daoFactoryBehandelcode = daoFactoryBehandelcode;
		this.daoFactoryClient = daoFactoryClient;
		this.daoFactoryFacturatie = daoFactoryFacturatie;
	}

	public double getPrijs(String invoerbehandelCode) {
		document = daoFactoryBehandelcode.getDocument();
		System.out.println(invoerbehandelCode);
		double tarief = 0;
		try {
			Element codesElement = (Element) document.getElementsByTagName(
					"behandelcodes").item(0);
			NodeList codes = codesElement.getElementsByTagName("behandeling");
			for (int i = 0; i < codes.getLength(); i++) {
				Element behandelingElement = (Element) codes.item(i);
				String behandelcode = behandelingElement
						.getAttribute("behandelcode");
				if (behandelcode.equals(invoerbehandelCode)) {

					String stringtarief = behandelingElement
							.getElementsByTagName("tariefbehandeling").item(0)
							.getTextContent();
					stringtarief = stringtarief.replaceAll(",", ".");
					tarief = Double.parseDouble(stringtarief);
					/*
					 * System.out.println("tarief");
					 * 
					 * 
					 * System.out.println();
					 */

				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return tarief;
	}

	public ArrayList<Behandeling> getBehandelingen(Klant klant) {
		// Initialiseer een lijst voor behandelingen.
		// Initialiseer een document van de daofactory en maak een string
		// behandelcode aan zonder inhoud.
		ArrayList<Behandeling> behandelingen = new ArrayList<>();
		document = daoFactoryClient.getDocument();
		String behandelcode = "";
		String praktijkNummer = "";
		String behandelStartDatum = "";
		String behandelEindDatum = "";
		double totaalprijs = 00;
		

		// Start proces om behandelingen op te halen.
		try {
			// Loop door alle klanten heen
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				// Als de BSN van een klant overeenkomt met de BSN van de
				// meegegeven klant, blijf in het element van deze klant.
				if (BSN.equals(klant.getBSN())) {
					Element behandelingenElement = (Element) clientElement
							.getElementsByTagName("Behandelingen").item(0);
					// Zoek in deze klant alle behandelingen op.
					NodeList behandelingnode = behandelingenElement
							.getElementsByTagName("Behandeling");
					// Haal in elke behandeling de behandelcode op.
					for (int j = 0; j < behandelingnode.getLength(); j++) {
						Element behandelElement = (Element) behandelingnode
								.item(j);
						behandelcode = behandelElement
								.getAttribute("Behandelcode");
						praktijkNummer = behandelElement
								.getElementsByTagName("fysioPraktijkNummer")
								.item(0).getTextContent();
						behandelStartDatum = behandelElement
								.getElementsByTagName("BehandelStartDatum")
								.item(0).getTextContent();
						behandelEindDatum = behandelElement
								.getElementsByTagName("BehandelEindDatum")
								.item(0).getTextContent();
						// Zoek nu bij alle behandelingen de afspraken op.
						NodeList afspraaknode = behandelElement
								.getElementsByTagName("behandelafspraak");
						int l = 0;
						// Loop door de lijst afspraken heen.
						for (int k = 0; k < afspraaknode.getLength(); k++) {
							Element afspraakElement = (Element) afspraaknode
									.item(k);
							// Als de afspraak niet gefactureerd is en deze wel
							// voltooid is, wordt l opgehoogd met 1.
							if (!afspraakElement
									.getElementsByTagName("Gefactureerd")
									.item(0).getTextContent().equals("Ja")
									&& afspraakElement
											.getElementsByTagName("Status")
											.item(0).equals("Voltooid")) {
								l++;
							}
						}
				
						// Reset de tellers en de string die toegevoegd wordt
						// aan de behandelcode.
						l = 0;
						Behandeling behandeling = new Behandeling(praktijkNummer, behandelcode, behandelStartDatum, behandelEindDatum, BSN, totaalprijs, l);
						behandelingen.add(behandeling);
					}
				}
			}
			// Vang fouten af.
		} catch (DOMException e) {
			e.printStackTrace();
		}
		// Stuur de lijst met codes en het aantal behandelingen terug
		return behandelingen;

	}
}
