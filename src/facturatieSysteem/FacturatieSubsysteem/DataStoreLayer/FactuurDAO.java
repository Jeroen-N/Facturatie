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

	private ArrayList<Factuur> facturen = new ArrayList<Factuur>();
	private Document document;
	private DAOFactoryFactuur daoFactory = new DAOFactoryFactuur();
	private Factuur factuur;

	/**
	 * Haalt alle facturen op van de klant waarvan deze geladen moeten worden.
	 * 
	 * @param De
	 *            klant waarvan de facturen geladen worden.
	 * 
	 * @return ArrayList van facturen van de desbetreffende klant
	 */
	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		document = daoFactory.getDocument();
		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				if (BSN.equals(invoerBSN)) {

					NodeList factuurnode = clientElement
							.getElementsByTagName("factuur");
					for (int j = 0; j < factuurnode.getLength(); j++) {
						Element factuurElement = (Element) factuurnode.item(j);
						int factuurNummer = Integer.parseInt(factuurElement
								.getAttribute("factuurNummer"));
						String factuurDatum = factuurElement
								.getElementsByTagName("factuurDatum").item(0)
								.getTextContent();
						String vervalDatum = factuurElement
								.getElementsByTagName("vervalDatum").item(0)
								.getTextContent();

						/*
						 * System.out.println("factuur: " + (i+1));
						 * System.out.println(factuurNummer);
						 * System.out.println(factuurDatum);
						 * System.out.println(vervalDatum);
						 * 
						 * System.out.println();
						 */

						factuur = new Factuur(factuurNummer, factuurDatum,
								vervalDatum, invoerBSN);
						facturen.add(factuur);

					}

				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		}
		return facturen;

	}

	public boolean maakFactuur(Klant klant, ArrayList<Behandeling> behandelingen) {
		document = daoFactory.getDocument();
		try {
			Element clientenElement = (Element) document.getElementsByTagName(
					"Clienten").item(0);
			NodeList clienten = clientenElement.getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				if (BSN.equals(klant.getBSN())) {

					Node factuur = document.getElementsByTagName("Facturen")
							.item(0);
					factuur.appendChild(document.createTextNode("\n\t"));

					// Create all Elements
					Element factuurtje = document.createElement("Factuur");
					Element clientGegevens = document
							.createElement("ClientGegevens");
					Element verzekeringPolissen = document
							.createElement("VerzekeringPolissen");
					Element verzekeringPolis = document
							.createElement("VerzekeringPolis");

					// Ordering Elements
					factuur.appendChild(factuurtje);
					client.appendChild(document.createTextNode("\n\t\t")); // <ClientGegevens>
					client.appendChild(clientGegevens);
					client.appendChild(document.createTextNode("\n\t\t")); // <VerzekeringPolissen>
					client.appendChild(verzekeringPolissen);
					verzekeringPolissen.appendChild(document
							.createTextNode("\n\t\t\t")); // <VerzekeringPolis>
					verzekeringPolissen.appendChild(verzekeringPolis);

					// create Attribuur BSN
					Attr BSN = document.createAttribute("BSN");
					BSN.setValue("" + klant.getBSN());
					client.setAttributeNode(BSN);

					// fill ClientGegevens
					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));// opmaak XML
					Element naam = document.createElement("Naam");
					naam.appendChild(document.createTextNode(klant.getNaam()));
					clientGegevens.appendChild(naam);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element adres = document.createElement("Adres");
					adres.appendChild(document.createTextNode(klant.getAdres()));
					clientGegevens.appendChild(adres);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element postcode = document.createElement("Postcode");
					postcode.appendChild(document.createTextNode(klant
							.getPostcode()));
					clientGegevens.appendChild(postcode);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element woonplaats = document.createElement("Woonplaats");
					woonplaats.appendChild(document.createTextNode(klant
							.getWoonplaats()));
					clientGegevens.appendChild(woonplaats);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element geboortedatum = document
							.createElement("Geboortedatum");
					geboortedatum.appendChild(document.createTextNode(klant
							.getGeboortedatum()));
					clientGegevens.appendChild(geboortedatum);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element telefoonnummer = document
							.createElement("Telefoonnummer");
					telefoonnummer.appendChild(document.createTextNode(klant
							.getTelefoonnummer()));
					clientGegevens.appendChild(telefoonnummer);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element email = document.createElement("Email");
					email.appendChild(document.createTextNode(klant.getEmail()));
					clientGegevens.appendChild(email);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element betaalMethode = document
							.createElement("BetaalMethode");
					betaalMethode.appendChild(document.createTextNode(klant
							.getBetaalMethode()));
					clientGegevens.appendChild(betaalMethode);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element resterendEigenRisico = document
							.createElement("ResterendEigenRisico");
					resterendEigenRisico.appendChild(document
							.createTextNode(Double.toString(klant
									.getResterendEigenRisico())));
					clientGegevens.appendChild(resterendEigenRisico);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t\t"));
					Element rekeningnummer = document
							.createElement("Rekeningnummer");
					rekeningnummer.appendChild(document.createTextNode(klant
							.getRekeningnummer()));
					clientGegevens.appendChild(rekeningnummer);

					clientGegevens.appendChild(document
							.createTextNode("\n\t\t"));// </ClientGegevens>
					// Fill ArrayList with Polissen

					VerzekeringPolissen = klant.getVerzekeringPolissen();

					for (VerzekeringPolis polis : VerzekeringPolissen) {
						Attr polisNummer = document
								.createAttribute("PolisNummer");
						polisNummer.setValue("" + polis.getPolisNummer());
						verzekeringPolis.setAttributeNode(polisNummer);

						verzekeringPolis.appendChild(document
								.createTextNode("\n\t\t\t\t"));
						Element verzekeringsType = document
								.createElement("VerzekeringType");
						verzekeringsType.appendChild(document
								.createTextNode(polis.getVerzekeringsType()));
						verzekeringPolis.appendChild(verzekeringsType);

						verzekeringPolis.appendChild(document
								.createTextNode("\n\t\t\t\t"));

						Element eigenRisico = document
								.createElement("EigenRisico");
						eigenRisico.appendChild(document.createTextNode(Double
								.toString(polis.getExtraEigenRisico())));
						verzekeringPolis.appendChild(eigenRisico);

						verzekeringPolis.appendChild(document
								.createTextNode("\n\t\t\t\t"));
						Element startDatum = document
								.createElement("startDatum");
						startDatum.appendChild(document.createTextNode(polis
								.getStartDatum()));
						verzekeringPolis.appendChild(startDatum);

						verzekeringPolis.appendChild(document
								.createTextNode("\n\t\t\t\t"));
						Element eindDatum = document.createElement("eindDatum");
						eindDatum.appendChild(document.createTextNode(polis
								.getEindDatum()));
						verzekeringPolis.appendChild(eindDatum);

						verzekeringPolis.appendChild(document
								.createTextNode("\n\t\t\t"));// </VerzekeringPolis>
					}

					verzekeringPolissen.appendChild(document
							.createTextNode("\n\t\t"));// </VerzekeringPolissen>
					client.appendChild(document.createTextNode("\n\t"));// </Client>
					clienten.appendChild(document.createTextNode("\n"));// <Clienten/>

					return daoFactory.writeDocument();
				}
			}
		} catch (DOMException e) {
			return false;
		}

	}

}
