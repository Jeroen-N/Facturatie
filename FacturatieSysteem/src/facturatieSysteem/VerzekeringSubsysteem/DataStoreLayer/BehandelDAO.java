package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import java.util.ArrayList;

public interface BehandelDAO {

	/*
	 * @return De gevonden behandelcodes uit de behandelcodes XML
	 */
	ArrayList<String> getBehandelcodes();

}
