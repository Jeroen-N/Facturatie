package facturatieSysteem.KlantenSubsysteem.DataStoreLayer;

import java.util.ArrayList;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;

public class VerzekeringPolisDAOImpl implements VerzekeringPolisDAO {

	private ArrayList<VerzekeringPolis> VerzekeringOverzicht = new ArrayList<VerzekeringPolis>();

	public VerzekeringPolisDAOImpl(ArrayList<VerzekeringPolis> VerzekeringOverzicht){
		//ArrayList<VerzekeringPolis> VerzekeringOverzicht = new ArrayList<>();
		this.VerzekeringOverzicht = VerzekeringOverzicht;
	}
	
	public ArrayList<VerzekeringPolis> getVerzekeringPolis() {
		// ophalen van de klanten in het overzicht en opslaan in een arraylist.
		return VerzekeringOverzicht;
	}

	public boolean addVerzekeringPolisXML(VerzekeringPolis polis) {
		// toevoegen van een verzekeringpolis in het xml bestand
		return false;
	}

	public boolean updateVerzekeringPolisXML(VerzekeringPolis polis) {
		// updaten van de verzekeringpolis in het xml bestand.
		return false;
	}

	public boolean verwijderVerzekeringPolisXML(VerzekeringPolis polis) {
		// verwijderen van de verzekeringPolis in het xml bestand.
		return false;
	}
}
