package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JList;

import org.apache.log4j.Logger;

import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.AddVerzekeringPolisDialog;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.*;
import facturatieSysteem.main.*;

public class VerzekeringsmaatschappijGUI extends JFrame {

	private JPanel VerzekeringPanel;
	private JTextField zoekVeld;
	private JTable Verzekering_Table;
	private JScrollPane totaalLijst;
	

	// The datamodel to be displayed in the JTable.
	private DataTableModel dataTableModel;
	private ArrayList<Verzekeringsmaatschappij> verzekeringList = null;

	// Get a logger instance for the current class
	static Logger logger = Logger.getLogger(MainGUI.class);
	private JTable totaalTable;
	
	private DataTableModelVerzekeringen dataTableModelVerzekeringen;

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
		
		/// TABEL VULLEN
		Verzekering_Table = new JTable(dataTableModel) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		String[] headers = new String[] { "Naam", "Adres", "Postcode",
		"Plaats", "KVK", "RekeningNr"};
		dataTableModel.setTableHeader(headers);
		String[][] initialValues = new String[][] { { "", "", "", "" } };
		
		TableColumn column = Verzekering_Table.getColumnModel().getColumn(0);
		column.setPreferredWidth(6);
		
		// Handle row selection, only one row can be selected
		Verzekering_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		fillTable();
				
		totaalLijst = new JScrollPane(totaalTable);
		
		totaalTable = new JTable();
		totaalLijst.setViewportView(totaalTable);
		Verzekering_Table.setFillsViewportHeight(true);
		tabelpaneel.setBorder(new TitledBorder(new LineBorder(new Color(
						0, 0, 0)), "Verzekeringenlijst", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
		VerzekeringPanel.add(tabelpaneel, BorderLayout.CENTER);

	}

	public void fillTable(){
		verzekeringList = VerzekeringsmaatschappijManager.getVerzekeringsmaatschappijen();
		int count = (verzekeringList == null) ? 0 : verzekeringList.size();
		
		if(count > 0){
		dataTableModelVerzekeringen.setValues(verzekeringList);
		}
	}
}
