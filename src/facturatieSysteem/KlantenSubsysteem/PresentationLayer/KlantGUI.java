package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import javax.swing.JPanel;

import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;

public class KlantGUI {

	private KlantManager klantmanager;
	
	public KlantGUI(KlantManager klantmanager){
		this.klantmanager = klantmanager;
		
		KlantGUI();
	}
	
	public static JPanel KlantGUI(){
		JPanel paneel = new JPanel();
		paneel.setName("KLANT");
		
		return paneel;
	}
}
