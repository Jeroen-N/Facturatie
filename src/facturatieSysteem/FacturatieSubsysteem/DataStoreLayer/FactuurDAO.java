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
	private DAOFactoryFactuur daoFactoryFacturatie;

	/**
	 * Haalt alle facturen op van de klant waarvan deze geladen moeten worden.
	 * 
	 * @param De
	 *            klant waarvan de facturen geladen worden.
	 * 
	 * @return ArrayList van facturen van de desbetreffende klant
	 */

	public FactuurDAO(DAOFactoryFactuur daoFactoryBehandelcode,
			DAOFactoryFactuur daoFactoryClient,
			DAOFactoryFactuur daoFactoryFacturatie) {
		this.daoFactoryClient = daoFactoryClient;
		this.daoFactoryFacturatie = daoFactoryFacturatie;
	}

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

					System.out.println("");
					System.out.println("BSN: " + BSN);
					Element facturenElement = (Element) clientElement
							.getElementsByTagName("Facturen").item(0);
					NodeList facturenNode = facturenElement
							.getElementsByTagName("Factuur");
					System.out.println("aantal Facturen: "
							+ facturenNode.getLength());
					for (int j = 0; j < facturenNode.getLength(); j++) {

						Element factuurElement = (Element) facturenNode.item(j);
						String factuurNummer = factuurElement
								.getAttribute("FactuurNummer");
						System.out.print("factuur " + (j + 1)
								+ " met factuurnummer " + factuurNummer);
						String factuurDatum = factuurElement
								.getElementsByTagName("FactuurDatum").item(0)
								.getTextContent();
						String vervalDatum = factuurElement
								.getElementsByTagName("VervalDatum").item(0)
								.getTextContent();
						double vergoedeBedrag = Double
								.parseDouble(factuurElement
										.getElementsByTagName("TevergoedenBedrag")
										.item(0).getTextContent());
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

						System.out.println(" heeft "
								+ behandelingenNode.getLength()
								+ " behandelingen");
						for (int k = 0; k < behandelingenNode.getLength(); k++) {

							Element behandelingElement = (Element) behandelingenNode
									.item(k);
							String behandelingid = behandelingElement
									.getAttribute("BehandelingID");
							System.out.println("\t Behandeling " + (k + 1)
									+ " bevat BehandelingId: " + behandelingid);

							NodeList BehandelafspraakIDsNode = behandelingElement
									.getElementsByTagName("BehandelafspraakID");
							ArrayList<String> AfsprakenIDs = new ArrayList<>();
							for (int y = 0; y < BehandelafspraakIDsNode
									.getLength(); y++) {
								String behandelafspraakID = BehandelafspraakIDsNode
										.item(y).getTextContent();
								System.out
										.println("\t\t BehandelafspraakID is:"
												+ behandelafspraakID);
								AfsprakenIDs.add(behandelafspraakID);
							}

							Element behandelingenElement = (Element) clientElement
									.getElementsByTagName("Behandelingen")
									.item(0);
							NodeList behandelingenNode2 = behandelingenElement
									.getElementsByTagName("Behandeling");
							// System.out.println("Behandelingen lengte: " +
							// behandelingenNode2.getLength());

							for (int l = 0; l < behandelingenNode2.getLength(); l++) {
								Element behandelingElement2 = (Element) behandelingenNode2
										.item(l);
								String behandelingid2 = behandelingElement2
										.getAttribute("id");
								if (behandelingid.equals(behandelingid2)) {
									String behandelingId = behandelingElement2
											.getAttribute("id");
									String fysioPraktijkNummer = behandelingElement2
											.getElementsByTagName(
													"fysioPraktijkNummer")
											.item(0).getTextContent();
									String behandelCode = behandelingElement2
											.getElementsByTagName(
													"Behandelcode").item(0)
											.getTextContent();
									String behandelStartDatum = behandelingElement2
											.getElementsByTagName(
													"BehandelStartDatum")
											.item(0).getTextContent();
									String behandelEindDatum = behandelingElement2
											.getElementsByTagName(
													"BehandelEindDatum")
											.item(0).getTextContent(); // matchende
																		// behandeling
																		// zoeken
									/*
									 * // Zoek nu bij alle behandelingen de
									 * afspraken op. NodeList Gefactureerdnode =
									 * behandelingElement2
									 * .getElementsByTagName("Gefactureerd");
									 * NodeList Statusnode =
									 * behandelingElement2.
									 * getElementsByTagName("Status"); int m =
									 * 0; // Loop door de lijst afspraken heen.
									 * for (int n = 0; n <
									 * Gefactureerdnode.getLength(); n++) {
									 * Element GefactureerdElement = (Element)
									 * Gefactureerdnode.item(n); Element
									 * StatusElement = (Element)
									 * Statusnode.item(n);
									 * //System.out.println("Lengte: " +
									 * afspraaknode.getLength()); // Als de
									 * afspraak niet gefactureerd is en deze wel
									 * // voltooid is, wordt l opgehoogd met 1.
									 * if
									 * (GefactureerdElement.getTextContent().equals
									 * ("Ja") &&
									 * StatusElement.getTextContent().equals
									 * ("Voltooid")) { //
									 * System.out.println("Test2"); m++; } }
									 */
									System.out.println("\t\t Behandeling "
											+ (k + 1) + " heeft "
											+ AfsprakenIDs.size()
											+ " afspraken");
									// Reset de tellers en de string die
									// toegevoegd wordt
									// aan de behandelcode.

									Behandeling behandeling = new Behandeling(
											fysioPraktijkNummer, behandelingId,
											behandelCode, behandelStartDatum,
											behandelEindDatum, BSN,
											AfsprakenIDs, 00,
											AfsprakenIDs.size());
									behandelingen.add(behandeling);
									// m = 0;
								}
							}
						}
						factuur = new Factuur(factuurNummer, factuurDatum,
								vervalDatum, invoerBSN, vergoedeBedrag,
								behandelingen, status, totaalPrijs);
						System.out.println();
						facturen.add(factuur);
					}
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}

		return facturen;

	}

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
					
					System.out.println("aantal behandelingen: "+factuur.getBehandelingen().size());
					
					// ArrayList met behandelingen vullen.
					behandelingen = factuur.getBehandelingen();

					// Loopen door de behandelingen en factuurbehandelingen
					// vullen.
					for (Behandeling behandeling : behandelingen) {
						Element factuurBehandeling = document
								.createElement("FactuurBehandeling");
						factuurBehandelingen.appendChild(factuurBehandeling);
						Element behandelAfspraakID = document
								.createElement("BehandelafspraakID");
						System.out.println("aantal afspraken: "+behandeling.getAfspraakIDs().size());
						for (String id : behandeling.getAfspraakIDs()) {
							behandelAfspraakID.appendChild(document
									.createTextNode(id));
							factuurBehandeling.appendChild(behandelAfspraakID);

							Attr BehandelingId = document
									.createAttribute("BehandelingID");
							BehandelingId.setValue(behandeling
									.getbehandelingId());
							behandelAfspraakID.setAttributeNode(BehandelingId);
							
						}
					}
					
					
					Element eigenRisico = document.createElement("TevergoedenBedrag");
					eigenRisico.appendChild(document.createTextNode(Double.toString(factuur.getVergoedeBedrag())));

					Element status = document.createElement("Status");
					status.appendChild(document.createTextNode(factuur
							.getStatus()));
					
					Element totaalPrijs = document.createElement("Totaalprijs");
					totaalPrijs.appendChild(document.createTextNode(Double.toString(factuur.getTotaalPrijs())));
				}
			}
			return daoFactoryClient.writeDocument();
		} catch (DOMException e) {
			return false;
		}
	}

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
							totaalPrijs);
					facturen.add(factuur);

				}

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return facturen;

	}

	public String getBehandelingID() {
		document = daoFactoryClient.getDocument();
		String behandelingID = "";
		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);

				Element facturenElement = (Element) clientElement
						.getElementsByTagName("Facturen").item(0);

				NodeList factuurnode = facturenElement
						.getElementsByTagName("Factuur");

				for (int j = 0; j < factuurnode.getLength(); j++) {
					Element factuurElement = (Element) factuurnode.item(j);

					NodeList factuurBehandelingNode = factuurElement
							.getElementsByTagName("FactuurBehandeling");

					for (int k = 0; k < factuurBehandelingNode.getLength(); k++) {
						Element factuurBehandelingElement = (Element) factuurBehandelingNode
								.item(k);

						behandelingID = factuurBehandelingElement
								.getAttribute("BehandelingID");

					}

				}

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return behandelingID;

	}
}
