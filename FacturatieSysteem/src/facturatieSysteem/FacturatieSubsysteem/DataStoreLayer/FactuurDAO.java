package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FactuurDAO implements FactuurDAOinf {

	private ArrayList<Factuur> facturen = new ArrayList<Factuur>();
	private Document document = null;
	private ArrayList<Behandeling> behandelingen = new ArrayList<>();
	private Factuur factuur;
	private DAOFactoryFactuur daoFactoryClient;
	
	/**
	 * De constructor van de klasse Factuurdao welke 3 objecten nodig heeft van de verschillende soorten daoFactories
	 * @param daoFactoryBehandelcode
	 * @param daoFactoryClient
	 * @param daoFactoryFacturatie
	 */
	public FactuurDAO(DAOFactoryFactuur daoFactoryBehandelcode,
			DAOFactoryFactuur daoFactoryClient,
			DAOFactoryFactuur daoFactoryFacturatie) {
		this.daoFactoryClient = daoFactoryClient;
	}

	/**
	 * Haalt alle facturen op van de klant waarvan deze geladen moeten worden.
	 * 
	 * @param De
	 *            klant waarvan de facturen geladen worden.
	 * 
	 * @return ArrayList van facturen van de desbetreffende klant
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		document = daoFactoryClient.getDocument();
		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				if (BSN.equals(invoerBSN)) { // klant
					Element facturenElement = (Element) clientElement
							.getElementsByTagName("Facturen").item(0);
					NodeList facturenNode = facturenElement
							.getElementsByTagName("Factuur");
					for (int j = 0; j < facturenNode.getLength(); j++) {

						Element factuurElement = (Element) facturenNode.item(j);
						String factuurNummer = factuurElement
								.getAttribute("FactuurNummer");
						String factuurDatum = factuurElement
								.getElementsByTagName("FactuurDatum").item(0)
								.getTextContent();
						String vervalDatum = factuurElement
								.getElementsByTagName("VervalDatum").item(0)
								.getTextContent();
						double vergoedeBedrag = Double
								.parseDouble(factuurElement
										.getElementsByTagName(
												"TevergoedenBedrag").item(0)
										.getTextContent());
						String status = factuurElement
								.getElementsByTagName("Status").item(0)
								.getTextContent();

						double totaalPrijs = Double.parseDouble(factuurElement
								.getElementsByTagName("Totaalprijs").item(0)
								.getTextContent());
						// factuur

						ArrayList<Behandeling> behandelingen = new ArrayList<>();
						Element factuurBehandelingenElement = (Element) factuurElement
								.getElementsByTagName("FactuurBehandelingen")
								.item(0);
						NodeList behandelingenNode = factuurBehandelingenElement
								.getElementsByTagName("FactuurBehandeling");

						for (int k = 0; k < behandelingenNode.getLength(); k++) {

							Element behandelingElement = (Element) behandelingenNode
									.item(k);
							String behandelingId = behandelingElement
									.getAttribute("BehandelingID");

							NodeList BehandelafspraakIDsNode = behandelingElement
									.getElementsByTagName("BehandelafspraakID");
							ArrayList<String> AfsprakenIDs = new ArrayList<>();
							for (int y = 0; y < BehandelafspraakIDsNode
									.getLength(); y++) {
								String behandelafspraakID = BehandelafspraakIDsNode
										.item(y).getTextContent();
								AfsprakenIDs.add(behandelafspraakID);
							}
							Behandeling behandeling = new Behandeling(null,
									behandelingId, null, null, null, BSN,
									AfsprakenIDs, 00, AfsprakenIDs.size());
							behandelingen.add(behandeling); //behandeling

						}
						factuur = new Factuur(factuurNummer, factuurDatum,
								vervalDatum, invoerBSN, vergoedeBedrag,
								behandelingen, status, totaalPrijs);
						facturen.add(factuur); //factuur objecten maken en aan de arraylist toevoegen
					}
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}

		return facturen;

	}

	/**
	 * Creates an object of the type factuur
	 *
	 * @param klant
	 * @param factuur
	 * @return true, if successfull
	 */
	public boolean maakFactuur(Klant klant, Factuur factuur) {
		document = daoFactoryClient.getDocument();

		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				if (BSN.equals(klant.getBSN())) {

					Element facturenElement = (Element) clientElement
							.getElementsByTagName("Facturen").item(0);
					Element factuurElement = document.createElement("Factuur");
					facturenElement.appendChild(factuurElement);

					// Attribuut factuurnummer genereren en setten.
					Attr factuurNummer = document
							.createAttribute("FactuurNummer");
					factuurNummer.setValue("" + factuur.getFactuurNummer());
					factuurElement.setAttributeNode(factuurNummer);

					// factuurDatum genereren en setten.
					Element factuurDatum = document
							.createElement("FactuurDatum");
					factuurDatum.appendChild(document.createTextNode(factuur
							.getFactuurDatum()));
					factuurElement.appendChild(factuurDatum);

					// vervalDatum genereren en setten.
					Element factuurVervalDatum = document
							.createElement("VervalDatum");
					factuurVervalDatum.appendChild(document
							.createTextNode(factuur.getVervalDatum()));
					factuurElement.appendChild(factuurVervalDatum);

					// Element factuurbehandelingen maken.
					Element factuurBehandelingen = document
							.createElement("FactuurBehandelingen");
					factuurElement.appendChild(factuurBehandelingen);

					// ArrayList met behandelingen vullen.
					behandelingen = factuur.getBehandelingen();

					// Loopen door de behandelingen en factuurbehandelingen
					// vullen.

					Element BehandelingenElement = (Element) clientElement
							.getElementsByTagName("Behandelingen").item(0);
					@SuppressWarnings("unused")
					NodeList Behandelingen = BehandelingenElement
							.getElementsByTagName("Behandeling");
					//Voor elke behandeling en elke afspraak binnen die behandeling
					for (Behandeling behandeling : behandelingen) {
						Element factuurBehandeling = document
								.createElement("FactuurBehandeling");
						factuurBehandelingen.appendChild(factuurBehandeling);

						for (String id : behandeling.getAfspraakIDs()) {
							Element behandelAfspraakID = document
									.createElement("BehandelafspraakID");
							behandelAfspraakID.appendChild(document
									.createTextNode(id));
							factuurBehandeling.appendChild(behandelAfspraakID);
							//Maak de behandelingID aan
							Attr BehandelingId = document
									.createAttribute("BehandelingID");
							BehandelingId.setValue(behandeling
									.getbehandelingId());
							factuurBehandeling.setAttributeNode(BehandelingId);

						}

					}
					//Maak element tevergoedenbedrag aan
					Element eigenRisico = document
							.createElement("TevergoedenBedrag");
					eigenRisico.appendChild(document.createTextNode(Double
							.toString(factuur.getVergoedeBedrag())));
					factuurElement.appendChild(eigenRisico);
					//Maak element status aan
					Element status = document.createElement("Status");
					status.appendChild(document.createTextNode(factuur
							.getStatus()));
					factuurElement.appendChild(status);
					//Maak element totaalprijs aan
					Element totaalPrijs = document.createElement("Totaalprijs");
					totaalPrijs.appendChild(document.createTextNode(Double
							.toString(factuur.getTotaalPrijs())));
					factuurElement.appendChild(totaalPrijs);

					// ResterendEigenRisico bijwerken
					Element ResterendEigenRisico = (Element) clientElement
							.getElementsByTagName("ResterendEigenRisico").item(
									0);
					ResterendEigenRisico.setTextContent(Double.toString(klant
							.getResterendEigenRisico()));
				}
			}
			return daoFactoryClient.writeDocument();
		} catch (DOMException e) {
			return false;
		}
	}

	/**
	 * Gets all the objects of facturen in the XML file
	 * 
	 * @return the list of facturen
	 */
	public ArrayList<Factuur> haalAlleFacturen() {
		document = daoFactoryClient.getDocument();
		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");

				Element facturenElement = (Element) clientElement
						.getElementsByTagName("Facturen").item(0);

				NodeList factuurnode = facturenElement
						.getElementsByTagName("Factuur");
				for (int j = 0; j < factuurnode.getLength(); j++) {
					Element factuurElement = (Element) factuurnode.item(j);
					String factuurNummer = factuurElement
							.getAttribute("FactuurNummer");
					String factuurDatum = factuurElement
							.getElementsByTagName("FactuurDatum").item(0)
							.getTextContent();
					String vervalDatum = factuurElement
							.getElementsByTagName("VervalDatum").item(0)
							.getTextContent();

					double vergoedeBedrag = Double.parseDouble(factuurElement
							.getElementsByTagName("TevergoedenBedrag").item(0)
							.getTextContent());
					String status = factuurElement
							.getElementsByTagName("Status").item(0)
							.getTextContent();

					double totaalPrijs = Double.parseDouble(factuurElement
							.getElementsByTagName("Totaalprijs").item(0)
							.getTextContent());

					factuur = new Factuur(factuurNummer, factuurDatum,
							vervalDatum, BSN, vergoedeBedrag, null, status,
							totaalPrijs); //Maak factuur aan met alle voorgaande opgehaalde onderdelen
					facturen.add(factuur);

				}

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return facturen;

	}

}
