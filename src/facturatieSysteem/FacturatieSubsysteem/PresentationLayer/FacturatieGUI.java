package facturatieSysteem.FacturatieSubsysteem.PresentationLayer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieGUI {

	private static JFrame frame = new JFrame("Facturatiesysteem");
	private static FacturatieManagerImpl facturatieManagerImpl;
	private static Integer row;
	private static JPanel buttonPanel;
	private static JPanel headPanel;
	private static JPanel detailPanel;
	private static JPanel overzichtPanel;
	private static JTextField zoekbalk;
	private static JButton zoekKnop;
	private static JButton terugKnop;
	private static JButton factureerKnop;
	private static JButton openFactuurKnop;
	private static JButton printFactuurKnop;
	private static JTable overzicht;
	private static JTextArea details;
	private static JLabel paginaNaam;
	private static Container contentpane;
	private static Color GRAY;
	private static JScrollPane factuurTablePanel;
	private static JScrollPane scrollPane = new JScrollPane();
	private static JPanel mainPanel = new JPanel();
	private static Klant klant;
	private static ArrayList<Factuur> facturen;
	private static DataTableModelFactuur dataTableModel;
	private static FacturatieManager facturatieManager;

	public static JPanel FacturatieGUI(FacturatieManagerImpl factManagerImpl, Klant klnt) {
		JPanel paneel = new JPanel();
		paneel.setName("FACTURATIE");
		paneel.add(scrollPane, BorderLayout.CENTER);
		facturatieManagerImpl = factManagerImpl;
		klant = klnt;
		facturen = new ArrayList<>();
		dataTableModel = new DataTableModelFactuur();
		return initComponents();
	}

	public static JPanel initComponents() {
		contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());

		// panels aanmaken

		buttonPanel = new JPanel();
		overzichtPanel = new JPanel();
		headPanel = new JPanel();
		detailPanel = new JPanel();

		// Tekst initialiseren van de knoppen en labels.
		details = new JTextArea();
		details.setBackground(Color.LIGHT_GRAY);
		details.setPreferredSize(new Dimension(250, 400));
		details.setRows(25);
		details.setColumns(40);
		details.setEditable(false);
		details.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = overzicht.getSelectedRow();
				fillField(row);
				}
		});
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		details.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		paginaNaam = new JLabel();
		paginaNaam.setText("Overzicht facturen");

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

		// Tabel onderverdelen in kolommen en vastzetten.
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
	overzichtPanel.add(factuurTablePanel, BorderLayout.CENTER);

		// panels vullen
		buttonPanel.add(factureerKnop);
		buttonPanel.add(openFactuurKnop);
		buttonPanel.add(printFactuurKnop);

		//overzichtPanel.add(overzicht);

		headPanel.add(paginaNaam);
		headPanel.add(zoekKnop);
		headPanel.add(terugKnop);
		headPanel.add(zoekbalk);

		detailPanel.add(details);

		contentpane.add(buttonPanel, BorderLayout.SOUTH);
		contentpane.add(overzichtPanel, BorderLayout.CENTER);
		contentpane.add(headPanel, BorderLayout.NORTH);
		contentpane.add(detailPanel, BorderLayout.EAST);

		mainPanel.add(contentpane);
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
	public static void fillField(int row){
		String factuur_nummer = overzicht.getModel().getValueAt(row, 1)
				.toString();
		details.setText(facturatieManager.toonFactuur(factuur_nummer));
		}
	
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}
