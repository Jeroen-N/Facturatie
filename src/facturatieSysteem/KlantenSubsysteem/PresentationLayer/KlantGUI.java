package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManagerImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import java.awt.Color;
import java.awt.FlowLayout;

public class KlantGUI {

	private static KlantManagerImpl klantManager = new KlantManagerImpl();
	private static JScrollPane scrollPane = new JScrollPane();
	private static JTable klantList = new JTable();

	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel KlantGUI(){
		JPanel paneel = new JPanel();
		paneel.setBackground(Color.PINK);
		paneel.setName("KLANT");
		paneel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		scrollPane.setViewportView(klantTotalList());
		paneel.add(scrollPane);
		return paneel;
	}
	
	public static JTable klantTotalList(){
		ArrayList<Klant> klantOverzicht = klantManager.getKlanten();
		
		klantList.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Klantnaam", "BSN", "GeboorteDatum"
                })
        );
		klantList.setEnabled(true);
		klantList.getTableHeader().setReorderingAllowed(false);
		
		DefaultTableModel model = (DefaultTableModel) klantList.getModel();
        if (klantOverzicht.size() > 0) {
            for (Klant klant : klantOverzicht) {

                String name = klant.getNaam();

                String BSN = klant.getBSN();
                String geboorteDatum = klant.getGeboortedatum();

                model.addRow(new Object[]{name /* Klantnaam */, BSN /* BSN */, geboorteDatum /* Price each */});
            }
        }

        return klantList;
		
	}
	
}
