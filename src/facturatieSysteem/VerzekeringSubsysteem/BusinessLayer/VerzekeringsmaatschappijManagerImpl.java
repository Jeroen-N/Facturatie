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
	
	public VerzekeringsmaatschappijManagerImpl(){
		ArrayList<Verzekeringsmaatschappij> lijst = VerzekeringDAO.getMaatschappijenXML();
		this.importData(lijst);
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
	public boolean addVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) == null){
			verzekeringsMaatschappijen.add(maatschappij);
			VerzekeringDAO.addMaatschappijXML(maatschappij);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) != null){
			verzekeringsMaatschappijen.remove(getVerzekeringsmaatschappij(maatschappij.getNr()));
			verzekeringsMaatschappijen.add(maatschappij);
			VerzekeringDAO.addMaatschappijXML(maatschappij);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteVerzekeringsmaatschappij(Verzekeringsmaatschappij maatschappij) {
		if(getVerzekeringsmaatschappij(maatschappij.getNr()) != null){
			verzekeringsMaatschappijen.remove(maatschappij);
			VerzekeringDAO.deleteMaatschappijXML(maatschappij.getNr());
		}
		return false;
	}
	
	@Override
	public ArrayList<Verzekeringsmaatschappij> getVerzekeringsmaatschappijen() {
		return verzekeringsMaatschappijen;
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
	public void addVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) == null){
			maatschappij.addType(type);
			VerzekeringtypeDAO.addVerzekeringstypeXML(maatschappij.getNr(), type);
		}
	}
	
	@Override
	public void updateVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			maatschappij.deleteType(getVerzekeringstype(maatschappij, type.getNr()));
			maatschappij.addType(type);
			VerzekeringtypeDAO.updateVerzekeringstypeXML(maatschappij.getNr(), type);
		}
	}
	
	@Override
	public boolean deleteVerzekeringstype(Verzekeringsmaatschappij maatschappij, Verzekeringstype type) {
		if(getVerzekeringstype(maatschappij, type.getNr()) != null){
			return VerzekeringtypeDAO.deleteVerzekeringstypeXML(maatschappij.getNr(), type);
		}
		return false;
	}
	
	public void importData(ArrayList<Verzekeringsmaatschappij> lijst){
		for(Verzekeringsmaatschappij maatschappij : lijst){
			verzekeringsMaatschappijen.add(maatschappij);
		}
	}
}
