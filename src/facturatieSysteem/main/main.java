package facturatieSysteem.main;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;

public class main {
	public static void main(String[] args){
		//MainGUI gui = new MainGUI();
		FacturatieManagerImpl m1 = new FacturatieManagerImpl();
		System.out.println(m1.haalFacturen("136521598"));
		
	}
}
