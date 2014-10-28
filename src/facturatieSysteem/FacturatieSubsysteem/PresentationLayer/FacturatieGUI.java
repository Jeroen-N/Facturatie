package facturatieSysteem.FacturatieSubsysteem.PresentationLayer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Bon;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class FacturatieGUI {

	private static FacturatieManager facturatieManagerImpl;
	private static Integer row;
	private static JPanel buttonPanel;
	private static JTextField zoekbalk;
	private static JButton zoekKnop;
	private static JButton terugKnop;
	private static JButton factureerKnop;
	private static JButton openFactuurKnop;
	private static JButton printFactuurKnop;
	private static JTable overzicht;
	private static JTextArea factuur;
	private static JScrollPane factuurTablePanel;
	private static JScrollPane scrollPane = new JScrollPane();
	private static JPanel mainPanel = new JPanel();
	private static Klant klant;
	private static ArrayList<Factuur> facturen;
	private static DataTableModelFactuur dataTableModel;
	private static JPanel eastPanel;
	private static JPanel factuurPanel;
	private static JScrollPane scrollFactuur;
	private static VerzekeringsmaatschappijManager m1;
	private static Verzekeringsmaatschappij maatschappijEind;
	private static Color WHITE;
	
	public static JPanel FacturatieGUI(FacturatieManager factManagerImpl, Klant klnt, VerzekeringsmaatschappijManager m2) {
		JPanel paneel = new JPanel();
		paneel.setName("FACTURATIE");
		paneel.add(scrollPane, BorderLayout.CENTER);
		m1 = m2;
		facturatieManagerImpl = factManagerImpl;
		klant = klnt;
		facturen = new ArrayList<>();
		dataTableModel = new DataTableModelFactuur();
		return initComponents(factManagerImpl);
	}

	public static JPanel initComponents(FacturatieManager factManagerImpl) {
		// panels aanmaken

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(30, 144, 255)));
		mainPanel.setBackground(new Color(255, 255, 255));
		eastPanel = new JPanel();
		eastPanel.setLayout(new BorderLayout(0, 0));
		eastPanel.setPreferredSize(new Dimension(500, 10));
		eastPanel.setBackground(new Color(255, 255, 255));
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 255, 255));
		factuurPanel = new JPanel();
		

		

		// Tekst initialiseren van de knoppen, tekstvelden en textarea's.
		factuur = new JTextArea();
		factuur.setEditable(false);
		scrollFactuur = new JScrollPane(factuur);
		scrollFactuur.setPreferredSize(new Dimension(500,10));
		factuur.setRows(43);
		factuur.setColumns(44);
		factuur.setBorder(new TitledBorder(new LineBorder(new Color(
				30, 144, 255)), "Factuur", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		
		zoekKnop = new JButton();
		zoekKnop.setText("Zoek");

		terugKnop = new JButton();
		terugKnop.setText("Terug");

		factureerKnop = new JButton();
		factureerKnop.setText("Factureren");
		
		factureerKnop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				facturatieManagerImpl.factureer(klant, m1);
			}
		});

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
	factuurTablePanel.setPreferredSize(new Dimension(450, 400));
	factuurTablePanel.setBackground(new Color(255, 255, 255));
	overzicht.setFillsViewportHeight(true);
	overzicht.getTableHeader().setReorderingAllowed(false);
	overzicht.getTableHeader().setResizingAllowed(false);
	
	factuurTablePanel.setBorder(new TitledBorder(new LineBorder(new Color(
			30, 144, 255)), "Facturenlijst", TitledBorder.LEADING,
			TitledBorder.TOP, null, null));
	mainPanel.add(factuurTablePanel, BorderLayout.CENTER);

	overzicht.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			row = overzicht.getSelectedRow();
			fillField(row);
			openFactuurKnop.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int row2 = overzicht.getSelectedRow();
					Factuur factuur = vindFactuur(row2);
					maatschappijEind = null;
					for (Verzekeringsmaatschappij maatschappij : m1
							.getVerzekeringsmaatschappijen()) {
						
			        	//Loopen door de typeArray om het te op te halen van de maatschappij
						for (Verzekeringstype type : maatschappij.getTypes()) {
							
							//loopen voor het type
							String polisNaam = "";
							for (VerzekeringPolis polis : klant.getVerzekeringPolissen()) {
								polisNaam = polis.getVerzekeringsType();
							}
								//de maatschappij ophalen aan de hand van de klant zijn type
								if (polisNaam.equals(type.getNaam())) {
									maatschappijEind = maatschappij;
									break;
								}
						}
					}
					Bon bon = new Bon(facturatieManagerImpl, factuur, maatschappijEind, klant, m1);
				}
			});
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
	public static void fillField(int row){
		String factuur_nummer = overzicht.getModel().getValueAt(row, 0).toString();
		System.out.println(factuur_nummer);
		factuur.setText(facturatieManagerImpl.toonFactuur(factuur_nummer, klant));
	}
	
	public static Factuur vindFactuur(int row2){
		String factuur_nummer2 = overzicht.getModel().getValueAt(row2, 0).toString();
		System.out.println(factuur_nummer2);
		return facturatieManagerImpl.getFactuur(factuur_nummer2, klant);
	}
	
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}
