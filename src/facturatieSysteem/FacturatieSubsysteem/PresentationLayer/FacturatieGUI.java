package facturatieSysteem.FacturatieSubsysteem.PresentationLayer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieGUI {

	private static FacturatieManagerImpl facturatieManagerImpl;
	private static Integer row;
	private static JPanel buttonPanel;
	private static JPanel overzichtPanel;
	private static JTextField zoekbalk;
	private static JButton zoekKnop;
	private static JButton terugKnop;
	private static JButton factureerKnop;
	private static JButton openFactuurKnop;
	private static JButton printFactuurKnop;
	private static JTable overzicht;
	private static JTextArea factuur;
	private static JLabel paginaNaam;
	private static JScrollPane factuurTablePanel;
	private static JScrollPane scrollPane = new JScrollPane();
	private static JPanel mainPanel = new JPanel();
	private static Klant klant;
	private static ArrayList<Factuur> facturen;
	private static DataTableModelFactuur dataTableModel;
	private static JPanel eastPanel;
	private static JPanel factuurPanel;
	private static JScrollPane scrollFactuur;
	private static Border border;

	public static JPanel FacturatieGUI(FacturatieManagerImpl factManagerImpl, Klant klnt) {
		JPanel paneel = new JPanel();
		paneel.setName("FACTURATIE");
		paneel.add(scrollPane, BorderLayout.CENTER);
		facturatieManagerImpl = factManagerImpl;
		klant = klnt;
		facturen = new ArrayList<>();
		dataTableModel = new DataTableModelFactuur();
		return initComponents(factManagerImpl);
	}

	public static JPanel initComponents(FacturatieManagerImpl factManagerImpl) {
		// panels aanmaken

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout(0, 0));
		buttonPanel = new JPanel();
		factuurPanel = new JPanel();
		

		// Tekst initialiseren van de knoppen, tekstvelden en textarea's.
		factuur = new JTextArea();
		factuur.setEditable(false);
		scrollFactuur = new JScrollPane(factuur);
		factuur.setRows(43);
		factuur.setColumns(40);
		factuur.setBorder(new TitledBorder(new LineBorder(new Color(
				0, 0, 0)), "Factuur", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
	
		
		zoekKnop = new JButton();
		zoekKnop.setText("Zoek");

		terugKnop = new JButton();
		terugKnop.setText("Terug");

		factureerKnop = new JButton();
		factureerKnop.setText("Factureren");

		openFactuurKnop = new JButton();
		openFactuurKnop.setText("Open factuur");

		printFactuurKnop = new JButton();
		printFactuurKnop.setText("Print factuur");

		zoekbalk = new JTextField();
		zoekbalk.setText("Vul factuurcode in");

		//overzicht tabel aanmaken en vullen.
		overzicht = new JTable(dataTableModel){

			private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}
	};
	String[] headers = new String[] { "Factuurnummer", "Factuurdatum", "Vervaldatum", "Status"};
	dataTableModel.setTableHeader(headers);

	TableColumn column = overzicht.getColumnModel().getColumn(0);
	column.setPreferredWidth(6);

	// Handle row selection, only one row can be selected
	overzicht.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
	fillTable(klant);
	
	factuurTablePanel = new JScrollPane(overzicht);
	overzicht.setFillsViewportHeight(true);
	overzicht.getTableHeader().setReorderingAllowed(false);
	overzicht.getTableHeader().setResizingAllowed(false);
	
	factuurTablePanel.setBorder(new TitledBorder(new LineBorder(new Color(
			0, 0, 0)), "Facturenlijst", TitledBorder.LEADING,
			TitledBorder.TOP, null, null));
	mainPanel.add(factuurTablePanel, BorderLayout.CENTER);

	overzicht.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			row = overzicht.getSelectedRow();
			fillField(row, factManagerImpl, klant);
			}
	});
	
		// panels vullen
		buttonPanel.add(factureerKnop);
		buttonPanel.add(openFactuurKnop);
		buttonPanel.add(printFactuurKnop);
		
		factuurPanel.add(factuur);
		scrollFactuur.add(factuurPanel);
		
		eastPanel.add(factuurPanel, BorderLayout.CENTER);
		eastPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		return mainPanel;

	}

	public void toonFacturatieGUI() {

	}

	public void toonFacturenscherm() {

	}

	public void vulFacturenlijst(ArrayList<Factuur> facturen) {

	}

	public void toonFactuur(Factuur factuur) {

	}
	
	public static void fillTable(Klant klant){
		
		facturen = facturatieManagerImpl.haalFacturen(klant.getBSN());
		int count = (facturen == null) ? 0 : facturen.size();
				
		
		if(count > 0){
		dataTableModel.setValues(facturen);
		}
	}
	
	/*
	 * Methode om het informatie veld te kunnen vullen en updaten
	 */
	public static void fillField(int row, FacturatieManagerImpl factManagerImpl, Klant klant){
		String factuur_nummer = overzicht.getModel().getValueAt(row, 0).toString();
		System.out.println(factuur_nummer);
		factuur.setText(factManagerImpl.toonFactuur(factuur_nummer, klant));
		}
	
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}
