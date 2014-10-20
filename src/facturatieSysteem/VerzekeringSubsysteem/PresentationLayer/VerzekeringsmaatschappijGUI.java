package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JList;

public class VerzekeringsmaatschappijGUI extends JFrame {

	private JPanel facturatiePane;
	private JTextField zoekVeld;

	/**
	 * Create the frame.
	 */
	public VerzekeringsmaatschappijGUI() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		facturatiePane = new JPanel();
		facturatiePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		facturatiePane.setLayout(new BorderLayout(0, 0));

		// / Header paneel
		JPanel headerpaneel = new JPanel();
		facturatiePane.add(headerpaneel, BorderLayout.NORTH);
		headerpaneel.setLayout(new BorderLayout(0, 0));

		JButton btnTerug = new JButton("Terug");
		btnTerug.setPreferredSize(new Dimension(90, 50));
		btnTerug.setAlignmentX(RIGHT_ALIGNMENT);
		btnTerug.setAlignmentY(CENTER_ALIGNMENT);
		headerpaneel.add(btnTerug, BorderLayout.EAST);

		// /Terugknop action
		btnTerug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facturatiePane.setVisible(false);
				///mainPanel.setVisible(true);
			}
		});
		
		/// Zoekveld
		zoekVeld = new JTextField();
		zoekVeld.setSize(new Dimension(6, 20));
		zoekVeld.setMaximumSize(new Dimension(6, 20));
		headerpaneel.add(zoekVeld, BorderLayout.WEST);
		zoekVeld.setColumns(10);

		/// Tabel Paneel
		JPanel tabelpaneel = new JPanel();
		facturatiePane.add(tabelpaneel, BorderLayout.CENTER);
		tabelpaneel.setLayout(new BorderLayout(0, 0));

		// / Totaal lijst
		JScrollPane totaalLijst = new JScrollPane();
		totaalLijst.setAlignmentY(CENTER_ALIGNMENT);
		totaalLijst.setAlignmentX(CENTER_ALIGNMENT);
		tabelpaneel.add(totaalLijst);

		// / Info Paneel (Rechterkant)
		JPanel infopaneel = new JPanel();
		facturatiePane.add(infopaneel, BorderLayout.EAST);
		infopaneel.setLayout(new BorderLayout(5, 5));

		JPanel knoppenPaneel = new JPanel();
		infopaneel.add(knoppenPaneel, BorderLayout.SOUTH);

		// / Info/knoppen Paneel + de Knoppen en Lijst
		JButton btnToevoegen = new JButton("Toevoegen");
		btnToevoegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		knoppenPaneel.setLayout(new BorderLayout(0, 0));
		knoppenPaneel.add(btnToevoegen, BorderLayout.NORTH);

		JButton btnWijzigen = new JButton("Wijzigen");
		knoppenPaneel.add(btnWijzigen, BorderLayout.CENTER);

		JButton btnVerwijderen = new JButton("Verwijderen");
		knoppenPaneel.add(btnVerwijderen, BorderLayout.SOUTH);

		JList list = new JList();
		infopaneel.add(list, BorderLayout.CENTER);

	}
}
