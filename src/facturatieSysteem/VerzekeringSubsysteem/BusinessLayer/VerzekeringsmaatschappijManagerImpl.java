/* @author Jeroen Nuijten
 * @version 0.2
 * 
 * De verzekeringsmaatschappij manager
 */
package facturatieSysteem.VerzekeringSubsysteem.BusinessLayer;

import java.util.ArrayList;

import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.*;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class VerzekeringsmaatschappijManagerImpl implements VerzekeringsmaatschappijManager {
	private ArrayList<Verzekeringsmaatschappij> verzekeringsMaatschappijen = new ArrayList<>();
	private VerzekeringsmaatschappijDAO VerzekeringDAO = new VerzekeringsmaatschappijDAOImpl();
	private VerzekeringstypeDAO VerzekeringtypeDAO = new VerzekeringsDAOImpl();
	
	@Override
	public boolean addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		for(Verzekeringsmaatschappij verzekering : verzekeringsMaatschappijen){
			if(!verzekering.getNr().equals(maatschappij.getNr())){
				break;
			}
		}

		return verzekeringsMaatschappijen.add(maatschappij) &&
				VerzekeringDAO.addMaatschappijXML(maatschappij);
	}

	@Override
	public Verzekeringsmaatschappij getVerzekeringsmaatschappij(String nr) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNr().equals(nr)){
				return maatschappij;
			}
		}
		return null;
	}

	@Override
	public boolean deleteVerzekeringsmaatschappij(String nr) {
		for(Verzekeringsmaatschappij maatschappij : verzekeringsMaatschappijen){
			if(maatschappij.getNr().equals(nr)){
				return verzekeringsMaatschappijen.remove(maatschappij) &&
					   VerzekeringDAO.deleteMaatschappijXML(nr); //Als beide true zijn is het succesvol uitgevoerd
			}
		}
		return false;
	}
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
	}
	
	@Override
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		for(Verzekeringstype vtype : maatschappij.getTypes()){
			if(vtype.getNr().equals(type.getNr())){
				break;
			}
		}
		maatschappij.addType(type);
		VerzekeringtypeDAO.addVerzekeringstypeXML(maatschappij.getNr(), type);
	}
	
	@Override
	public Verzekeringstype getVerzekeringstype(Verzekeringsmaatschappij maatschappij, String nr) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNr().equals(nr)){
				return type;
			}
		}
		return null;
	}
	
	@Override
	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, String nr) {
		for(Verzekeringstype type : maatschappij.getTypes()){
			if(type.getNr().equals(nr)){
				return maatschappij.deleteType(type);
			}
		}
		return false;
	}
	
	public void importData(ArrayList<Verzekeringsmaatschappij> lijst){
		for(Verzekeringsmaatschappij maatschappij : lijst){
			this.addVerzekeringsmaatschappij(maatschappij);
		}
	}
	
	public void fill(){
		//MBV DAO aanmaken gegevens
		ArrayList<Verzekeringsmaatschappij> lijst = VerzekeringDAO.getMaatschappijenXML();
		this.importData(lijst);
		
		Verzekeringsmaatschappij m1 = new Verzekeringsmaatschappij("002","Naam", "adres", "dd", "da", 123,123);
		Verzekeringstype t1 = new Verzekeringstype("005",124,"test");
		t1.addCode("001");
		m1.addType(t1);
		
		//Testcode toevoegen en verwijderen
		//this.addVerzekeringstype(m1, t1);
	}
}
