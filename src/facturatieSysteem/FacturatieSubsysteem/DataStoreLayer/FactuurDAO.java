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
				if (BSN.equals(invoerBSN)) {					//klant
					
					System.out.println("");
					System.out.println("BSN: " +BSN);
					Element facturenElement = (Element) clientElement.getElementsByTagName("Facturen").item(0);
					NodeList facturenNode = facturenElement.
							getElementsByTagName("Factuur");
					System.out.println("aantal Facturen: " + facturenNode.getLength());
					for (int j = 0; j < facturenNode.getLength(); j++) {
						
						Element factuurElement = (Element) facturenNode.item(j);
						String factuurNummer = factuurElement
								.getAttribute("FactuurNummer");
						System.out.print("factuur "+ (j+1) + " met factuurnummer "+ factuurNummer);
						String factuurDatum = factuurElement
								.getElementsByTagName("FactuurDatum").item(0)
								.getTextContent();
						String vervalDatum = factuurElement
								.getElementsByTagName("VervalDatum").item(0)
								.getTextContent();
						double vergoedeBedrag = Double.parseDouble(factuurElement
								.getElementsByTagName("EigenRisico").item(0)
								.getTextContent());								//factuur
						
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						//TODO behandelingennode heeft maar 1 behandeling terwijl er 2 aanwezig zijn. Node2 echter heeft er wel gewoon 2 terwijl dit er ook 2 moeten zijn.//
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						ArrayList<Behandeling> behandelingen = new ArrayList<>();
						Element factuurBehandelingenElement = (Element) factuurElement.getElementsByTagName("FactuurBehandelingen").item(0);
						NodeList behandelingenNode = factuurBehandelingenElement.getElementsByTagName("BehandelingID");					
						
						System.out.println(" heeft " +behandelingenNode.getLength()+" behandelingen");
						for(int k = 0; k < behandelingenNode.getLength(); k++){
							Element behandelingElement = (Element) behandelingenNode.item(k);
							String behandelingid = behandelingElement.getTextContent(); //behandelingid van factuur
								System.out.println("\t Behandeling "+(k+1)+" bevat BehandelingId: "+  behandelingid);
							
							Element behandelingenElement = (Element) clientElement.getElementsByTagName("Behandelingen").item(0);
							NodeList behandelingenNode2 = behandelingenElement.getElementsByTagName("Behandeling");
							//System.out.println("Behandelingen lengte: " + behandelingenNode2.getLength());

							for (int l = 0; l < behandelingenNode2.getLength(); l++) {
								Element behandelingElement2 = (Element) behandelingenNode2.item(l);
								String behandelingid2 = behandelingElement2.getAttribute("id");
								if(behandelingid.equals(behandelingid2)){
									String fysioPraktijkNummer = behandelingElement2.getElementsByTagName("fysioPraktijkNummer").item(0).getTextContent();
									String behandelCode = behandelingElement2.getElementsByTagName("Behandelcode").item(0).getTextContent();
									String behandelStartDatum = behandelingElement2.getElementsByTagName("BehandelStartDatum").item(0).getTextContent();
									String behandelEindDatum = behandelingElement2.getElementsByTagName("BehandelEindDatum").item(0).getTextContent();   //matchende behandeling zoeken
									
									// Zoek nu bij alle behandelingen de afspraken op. 
									NodeList Gefactureerdnode = behandelingElement2.getElementsByTagName("Gefactureerd");
									NodeList Statusnode = behandelingElement2.getElementsByTagName("Status");
									int m = 0;
									// Loop door de lijst afspraken heen.
									for (int n = 0; n < Gefactureerdnode.getLength(); n++) {
										Element GefactureerdElement = (Element) Gefactureerdnode.item(n);
										Element StatusElement = (Element) Statusnode.item(n);
										//System.out.println("Lengte: " + afspraaknode.getLength());
										// Als de afspraak niet gefactureerd is en deze wel
										// voltooid is, wordt l opgehoogd met 1.
										if ( GefactureerdElement.getTextContent().equals("Ja")
												&& StatusElement.getTextContent().equals("Voltooid")) {
										//	System.out.println("Test2");
											m++;
										}
										
									}
									System.out.println("\t\t Behandeling "+(k+1)+" heeft " + m + " afspraken");
									// Reset de tellers en de string die toegevoegd wordt
									// aan de behandelcode.
								
									Behandeling behandeling = new Behandeling(fysioPraktijkNummer, behandelCode, behandelStartDatum, behandelEindDatum, BSN, 00, m);
									behandelingen.add(behandeling);
									m = 0;
								}
							}
						}
						factuur = new Factuur(factuurNummer, factuurDatum,
								vervalDatum, invoerBSN, vergoedeBedrag, behandelingen, "Niet betaald");
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

					double vergoedeBedrag = Double.parseDouble(factuurElement
							.getElementsByTagName("EigenRisico").item(0)
							.getTextContent());
					
					NodeList behandelingenNode = factuurElement
							.getElementsByTagName("FactuurBehandelingen");
					ArrayList<Behandeling> behandelingen = new ArrayList<>();
					for(int k = 0; k < behandelingenNode.getLength(); k++){
						Element behandelingElement = (Element) behandelingenNode.item(k);
						String behandelingid = behandelingElement.getElementsByTagName("BehandelingID").item(0).getTextContent();
						
						Element behandelingenElement = (Element) clientElement.getElementsByTagName("Behandelingen").item(0);
						NodeList behandelingenNode2 = behandelingenElement.getElementsByTagName("Behandeling");
						for (int l = 0; l < behandelingenNode2.getLength(); l++) {
							Element behandelingElement2 = (Element) behandelingenNode2.item(l);
							String behandelingid2 = behandelingElement2.getAttribute("id");
							if(behandelingid.equals(behandelingid2)){
								String fysioPraktijkNummer = behandelingElement2.getElementsByTagName("fysioPraktijkNummer").item(0).getTextContent();
								String behandelCode = behandelingElement2.getElementsByTagName("Behandelcode").item(0).getTextContent();
								String behandelStartDatum = behandelingElement2.getElementsByTagName("BehandelStartDatum").item(0).getTextContent();
								String behandelEindDatum = behandelingElement2.getElementsByTagName("BehandelEindDatum").item(0).getTextContent();
								
								
								// Zoek nu bij alle behandelingen de afspraken op.
								NodeList afspraaknode = behandelingElement2
										.getElementsByTagName("behandelafspraak");
								int m = 0;
								// Loop door de lijst afspraken heen.
								
								for (int n = 0; n < afspraaknode.getLength(); n++) {
									Element afspraakElement = (Element) afspraaknode
											.item(n);
									// Als de afspraak niet gefactureerd is en deze wel
									// voltooid is, wordt l opgehoogd met 1.
									if (!afspraakElement
											.getElementsByTagName("Gefactureerd")
											.item(0).getTextContent().equals("Ja")
											&& afspraakElement
													.getElementsByTagName("Status")
													.item(0).getTextContent().equals("Voltooid")) {
										m++;
									}
								}
						
								// Reset de tellers en de string die toegevoegd wordt
								// aan de behandelcode.
							
								Behandeling behandeling = new Behandeling(fysioPraktijkNummer, behandelCode, behandelStartDatum, behandelEindDatum, BSN, 00, m);
								behandelingen.add(behandeling);
								m = 0;
							}
						}
					}
					factuur = new Factuur(factuurNummer, factuurDatum,
							vervalDatum, BSN, vergoedeBedrag, behandelingen, "Niet betaald");
					facturen.add(factuur);
	

				}

			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return facturen;

	}
}
