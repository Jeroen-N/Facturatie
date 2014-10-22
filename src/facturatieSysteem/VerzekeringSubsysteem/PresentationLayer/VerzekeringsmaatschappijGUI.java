package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JList;

import facturatieSysteem.KlantenSubsysteem.PresentationLayer.AddVerzekeringPolisDialog;
import facturatieSysteem.main.*;

public class VerzekeringsmaatschappijGUI extends JFrame {

	private JPanel VerzekeringPanel;
	private JTextField zoekVeld;

	/**
	 * Create the frame.
	 */
	public VerzekeringsmaatschappijGUI() {

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		VerzekeringPanel = new JPanel();
		VerzekeringPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		VerzekeringPanel.setLayout(new BorderLayout(0, 0));

		// / Header paneel
		JPanel headerpaneel = new JPanel();
		VerzekeringPanel.add(headerpaneel, BorderLayout.NORTH);
		headerpaneel.setLayout(new BorderLayout(0, 0));

		JButton btnTerug = new JButton("Terug");
		btnTerug.setPreferredSize(new Dimension(90, 50));
		btnTerug.setAlignmentX(RIGHT_ALIGNMENT);
		btnTerug.setAlignmentY(CENTER_ALIGNMENT);
		headerpaneel.add(btnTerug, BorderLayout.EAST);

		// /Terugknop action
		btnTerug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VerzekeringPanel.setVisible(false);
			/*	KlantenPanel.setVisible(true);*/

			}
		});

		// / Zoekveld
		zoekVeld = new JTextField();
		zoekVeld.setSize(new Dimension(6, 20));
		zoekVeld.setMaximumSize(new Dimension(6, 20));
		headerpaneel.add(zoekVeld, BorderLayout.WEST);
		zoekVeld.setColumns(10);

		// / Tabel Paneel
		JPanel tabelpaneel = new JPanel();
		VerzekeringPanel.add(tabelpaneel, BorderLayout.CENTER);
		tabelpaneel.setLayout(new BorderLayout(0, 0));

		// / Totaal lijst
		JScrollPane totaalLijst = new JScrollPane();
		totaalLijst.setAlignmentY(CENTER_ALIGNMENT);
		totaalLijst.setAlignmentX(CENTER_ALIGNMENT);
		tabelpaneel.add(totaalLijst);

		// / Info Paneel (Rechterkant)
		JPanel infopaneel = new JPanel();
		VerzekeringPanel.add(infopaneel, BorderLayout.EAST);
		infopaneel.setLayout(new BorderLayout(5, 5));

		JPanel knoppenPaneel = new JPanel();
		knoppenPaneel.setPreferredSize(new Dimension(250, 40));
		knoppenPaneel.setMaximumSize(new Dimension(250, 250));
		infopaneel.add(knoppenPaneel, BorderLayout.SOUTH);

		// / Info/knoppen Paneel + de Knoppen en Lijst
		final JButton btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addMouseListener(new MouseAdapter() {
			/*@Override
			public void mouseClicked(MouseEvent e) {
				if (btnWijzigen.isEnabled()) {
					AddVerzekeringDialog addVerzekeringDialog =  new AddVerzekeringDialog(VerzekeringsmaatschappijManager, maatschappijManager, Verzekering_Table.getModel().getValueAt(Verzekering_Table.getSelectedRow(), 1).toString());
					addVerzekeringDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					addVerzekeringDialog.setModal(true);
					addVerzekeringDialog.setVisible(true);
					addVerzekeringDialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
					    	System.out.println("window is closed");
							VerzekeringPanel.removeAll();
							//Verzekering_info.removeAll();
							//insertTable();
						} 
					});
				}
				
			} */
			
		});
		knoppenPaneel.setLayout(new BorderLayout(0, 0));
		knoppenPaneel.add(btnToevoegen, BorderLayout.WEST);
		
		JButton btnWijzigen = new JButton("Wijzigen");
		knoppenPaneel.add(btnWijzigen, BorderLayout.CENTER);
		
		JButton btnVerwijderen = new JButton("Verwijderen");
		knoppenPaneel.add(btnVerwijderen, BorderLayout.EAST);

		JList list = new JList();
		infopaneel.add(list, BorderLayout.CENTER);

	}
}
