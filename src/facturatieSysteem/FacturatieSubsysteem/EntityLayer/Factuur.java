package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

import java.util.Date;

public class Factuur implements ImmutableFactuur {

	private int FactuurNummer;
	private Date FactuurDatum;
	private Date VervalDatum;

	@Override
	public void toonFactuur() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean printFactuur() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void berekenBTW() {
		// TODO Auto-generated method stub

	}

	@Override
	public void berekenEigenRisico() {
		// TODO Auto-generated method stub

	}

}
