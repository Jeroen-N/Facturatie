package facturatieSysteem.FacturatieSubsysteem.EntityLayer;

public interface ImmutableFactuur {
	
	public void toonFactuur();
	
	public boolean printFactuur();

	public void berekenBTW();

	public void berekenEigenRisico();
}
