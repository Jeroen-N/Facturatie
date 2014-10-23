package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	private Document document = null;

	private Factuur factuur;
	private DAOFactoryFactuur daoFactoryBehandelcode;
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
		this.daoFactoryBehandelcode = daoFactoryBehandelcode;
		this.daoFactoryClient = daoFactoryClient;
		this.daoFactoryFacturatie = daoFactoryFacturatie;
	}

	public ArrayList<Factuur> haalFacturen(String invoerBSN) {
		document = daoFactoryClient.getDocument();
		try {
			Element clientenElement = (Element) document.getElementsByTagName("Clienten").item(0);
			NodeList clienten = 
					clientenElement.
					getElementsByTagName("Client");
			for (int i = 0; i < clienten.getLength(); i++) {
				Element clientElement = (Element) clienten.item(i);
				String BSN = clientElement.getAttribute("BSN");
				if (BSN.equals(invoerBSN)) {
					
					Element facturenElement = (Element) clientElement.getElementsByTagName("Facturen").item(0);
					NodeList facturenNode = facturenElement.
							getElementsByTagName("Factuur");
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
						//double eigenRisico = Double.parseDouble(factuurElement
						//		.getElementsByTagName("EigenRisico").item(0)
						//		.getTextContent());

						factuur = new Factuur(factuurNummer, factuurDatum,
								vervalDatum, invoerBSN, 00, null);
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
		document = daoFactoryFacturatie.getDocument();
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
					Element factuurElement = document.createElement("factuur");
					facturenElement.appendChild(factuurElement);

					// Maak een tijdelijke lijst aan van alle facturen die er
					// zijn en bepaal hier het hoogste factuurnummer

					// Maak het factuurnummer het hoogste nummer.
					// factuurNummer.setValue("" + n2 + 1);
					// factuurtje.setAttributeNode(factuurNummer);

					// create Attribuut factuurummer
					Attr factuurNummer = document
							.createAttribute("factuurDatum");
					factuurNummer.setValue("" + factuur.getFactuurNummer());
					factuurElement.setAttributeNode(factuurNummer);

					// factuurDatum
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date = new Date();
					String vandaag = dateFormat.format(date);
					Element factuurDatum = document
							.createElement("factuurDatum");
					factuurDatum.appendChild(document.createTextNode(vandaag));
					factuurElement.appendChild(factuurDatum);

					Element vervalDatum = document.createElement("vervalDatum");
					// TODO date + 14 dagen zodat vervalDatum klopt
					String verval = dateFormat.format(date);
					factuurDatum.appendChild(document.createTextNode(verval));
					factuurElement.appendChild(factuurDatum);

					/*
					 * // Ordering Elements factuur.appendChild(factuurtje);
					 * factuurtje
					 * .appendChild(document.createTextNode("\n\t\t")); //
					 * <ClientGegevens> factuurtje.appendChild(factuurDatum);
					 * factuurtje
					 * .appendChild(document.createTextNode("\n\t\t")); //
					 * <VerzekeringPolissen>
					 * factuurtje.appendChild(vervalDatum);
					 * 
					 * 
					 * 
					 * 
					 * 
					 * // Vul factuurDatum door huidige datum op te halen en als
					 * tekst weg te zetten DateFormat dateFormat = new
					 * SimpleDateFormat("dd/MM/yyyy"); Date date = new Date();
					 * 
					 * String vandaag = dateFormat.format(date);
					 * factuurDatum.appendChild(document
					 * .createTextNode("\n\t\t\t"));// opmaak XML Element fDatum
					 * = document.createElement("");
					 * fDatum.appendChild(document.createTextNode(vandaag));
					 * factuurDatum.appendChild(fDatum);
					 * 
					 * // Vul vervalDatum vervalDatum
					 * .appendChild(document.createTextNode("\n\t\t\t"));
					 * Element vDatum = document.createElement("vervalDatum");
					 * vDatum
					 * .appendChild(document.createTextNode(klant.getAdres()));
					 * vervalDatum.appendChild(vDatum);
					 * 
					 * // Sluit het document af
					 * factuurtje.appendChild(document.createTextNode
					 * ("\n\t"));// <Factuurtje/>
					 * factuur.appendChild(document.createTextNode("\n"));//
					 * <Facturen/>
					 */
				}
			}
			return daoFactoryFacturatie.writeDocument();
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

					// TODO in xml zetten zodat deze een waarde heeft
					// double eigenRisico = Double.parseDouble(factuurElement
					// .getElementsByTagName("EigenRisico").item(0)
					// .getTextContent());

					// TODO status meegeven!
					/*
					 * System.out.println("factuur: " + (i+1));
					 * System.out.println(factuurNummer);
					 * System.out.println(factuurDatum);
					 * System.out.println(vervalDatum);
					 * 
					 * System.out.println();
					 */

					factuur = new Factuur(factuurNummer, factuurDatum,
							vervalDatum, BSN, 00, null);
					facturen.add(factuur);

				}

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return facturen;

	}
}
