package facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer;

import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public interface VerzekeringstypeDAO {

	boolean addVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);

	boolean deleteVerzekeringstypeXML(String maatschappijnr, Verzekeringstype type);

	boolean updateVerzekeringstypeXML(String maatschappijnr,
			Verzekeringstype type);

	boolean addBehandelCode(String behandelcode);

	boolean removeBehandelCode(String behandelcode);
}
