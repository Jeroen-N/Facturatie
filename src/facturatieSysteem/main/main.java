package facturatieSysteem.main;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;

public class main {
	public static void main(String[] args){
<<<<<<< HEAD
		MainGUI gui = new MainGUI();
=======
		//TODO: initialize GUI/managers
		
		FacturatieManagerImpl facturatieManagerImpl = new FacturatieManagerImpl();
        
        FacturatieGUI facturatieGui = new FacturatieGUI(facturatieManagerImpl);

>>>>>>> FacturatieSubSysteem
	}
}
