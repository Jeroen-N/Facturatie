package facturatieSysteem.FacturatieSubsysteem.DataStoreLayer;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Behandeling;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class BehandelingDAO implements BehandelDAOinf {

	private ArrayList<Behandeling> behandelingen;

	private Document document = null;

	public BehandelingDAO() {

	}

	@Override
	public ArrayList<Behandeling> haalBehandelingen(Klant klant) {
		
		if (document != null) {
			// Get all <member> elements from the document
			NodeList list = document.getElementsByTagName("behandeling");

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node instanceof Element) {
					Element child = (Element) node;

					String fysioPraktijkNummer = child
							.getElementsByTagName("FysioPraktijkNummer")
							.item(0).getTextContent();
					String behandelCode = child
							.getElementsByTagName("BehandelCodes").item(0)
							.getTextContent();
					String behandelStartDatum = child
							.getElementsByTagName("BehandelStartDatum").item(0)
							.getTextContent();
					String behandelEindDatum = child
							.getElementsByTagName("BehandelEindDatum").item(0)
							.getTextContent();
					String BSN = child.getElementsByTagName("BSN").item(0).getTextContent();

					System.out.println("Adding " + fysioPraktijkNummer + " "
							+ behandelCode + " " + behandelStartDatum + " "
							+ behandelEindDatum + " " + BSN + " to result");
					behandelingen.add(new Behandeling(Integer
							.parseInt(fysioPraktijkNummer), Integer
							.parseInt(behandelCode), Long
							.parseLong(behandelStartDatum), Long
							.parseLong(behandelEindDatum), BSN));

					for (Behandeling behandeling : behandelingen) {
						if (behandeling.getBSN() != klant.getBSN()) {
							behandelingen.remove(behandeling);

						}

					}
				}
			}
		} else {
			System.out.println("XML document is null!");
		}

		System.out.println("returning result: " + behandelingen.size() + " items");
		return behandelingen;
	}

	

}
