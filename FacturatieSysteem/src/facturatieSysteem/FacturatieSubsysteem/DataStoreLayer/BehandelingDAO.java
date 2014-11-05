package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
/**
 * The BehandelingDAO class which implements the BehandelDAOinf interface
 * @author night_000
 *
 */
public class BehandelingDAO implements BehandelDAOinf {
	
	private Document document = null;
	private DAOFactoryFactuur daoFactoryBehandelcode;
	private DAOFactoryFactuur daoFactoryClient;
	private String behandelingId;
	/**
	 * The constructor for the BehandelingDAO which creates an object
	 * of this class with a given daoFactoryBehandelcode and a given daoFactoryClient
	 * @param daoFactoryBehandelcode
	 * @param daoFactoryClient
	 */
	public BehandelingDAO(DAOFactoryFactuur daoFactoryBehandelcode,
			DAOFactoryFactuur daoFactoryClient) {
		this.daoFactoryBehandelcode = daoFactoryBehandelcode;
		this.daoFactoryClient = daoFactoryClient;

	}

	/**
	 * Gets the price of a treatment with the given treatmentcode
	 * 
	 * @param behandelCode
	 * @return double the price
	 */
	public double getPrijs(String invoerbehandelCode) {
		document = daoFactoryBehandelcode.getDocument();
		double tarief = 0;
		try {
			//Zoek naar behandelcodes
			Element codesElement = (Element) document.getElementsByTagName(
					"behandelcodes").item(0);
			NodeList codes = codesElement.getElementsByTagName("behandeling");
			for (int i = 0; i < codes.getLength(); i++) {
				Element behandelingElement = (Element) codes.item(i);
				//Haal het attribuut behandelcode op bij de behandeling
				String behandelcode = behandelingElement
						.getAttribute("behandelcode");
				//Als de behandelcode matcht met de invoerbehandelCode
				if (behandelcode.equals(invoerbehandelCode)) {
					//Haal het tarief op van de behandelcode
					String stringtarief = behandelingElement
							.getElementsByTagName("tariefbehandeling").item(0)
							.getTextContent();
					stringtarief = stringtarief.replaceAll(",", ".");
					tarief = Double.parseDouble(stringtarief);

				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return tarief;
	}

	/**
	 * Gets an list of treatments given to the specified client
	 * 
	 * @param klant
	 * @return an list of the given treatments
	 */
	public ArrayList<Behandeling> getBehandelingen(Klant klant) {
		// Initialiseer een lijst voor behandelingen.
		// Initialiseer een document van de daofactory en maak een string
		// behandelcode aan zonder inhoud.
		ArrayList<Behandeling> behandelingen = new ArrayList<>();
		ArrayList<String> afspraakIDs;
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

						behandelingId = behandelElement.getAttribute("id");

						behandelcode = behandelElement
								.getElementsByTagName("Behandelcode").item(0)
								.getTextContent();
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
						afspraakIDs = new ArrayList<>();
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
											.item(0).getTextContent()
											.equals("Voltooid")) {
								l++;
								afspraakIDs.add(afspraakElement
										.getAttribute("ID"));
							}
						}

						// Reset de tellers en de string die toegevoegd wordt
						// aan de behandelcode.
						Behandeling behandeling = new Behandeling(
								praktijkNummer, behandelingId, behandelcode,
								behandelStartDatum, behandelEindDatum, BSN,
								afspraakIDs, totaalprijs, l);
						if (l > 0) {
							behandelingen.add(behandeling);
						}

						l = 0;
						// afspraakIDs.clear();
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

	/**
	 * Get the name of the treatment with the given treatmentcode
	 * 
	 * @param invoerbehandelCode
	 * @return String of the name
	 */
	public String getNaam(String invoerbehandelCode) {
		document = daoFactoryBehandelcode.getDocument();
		String behandelingNaam = "";
		try {
			//Zoek naar elementen genaamd behandelcodes
			Element codesElement = (Element) document.getElementsByTagName(
					"behandelcodes").item(0);
			NodeList codes = codesElement.getElementsByTagName("behandeling");
			for (int i = 0; i < codes.getLength(); i++) {
				Element behandelingElement = (Element) codes.item(i);
			//Haal de codes op van elke code
				String behandelcode = behandelingElement
						.getAttribute("behandelcode");
				//Als de behandelcode matcht met de invoerbehandelcode
				if (behandelcode.equals(invoerbehandelCode)) {
					//Haal de naam op van de behandeling
					behandelingNaam = behandelingElement
							.getElementsByTagName("behandelingnaam").item(0)
							.getTextContent();

				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return behandelingNaam;
	}
}
