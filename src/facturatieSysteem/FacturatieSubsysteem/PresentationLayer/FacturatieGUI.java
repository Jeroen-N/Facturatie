package facturatieSysteem.FacturatieSubsysteem.PresentationLayer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieGUI{

	private JFrame frame;
	private FacturatieManagerImpl facturatieManagerImpl;
	private JPanel buttonPanel;
	private JPanel headPanel;
	private JPanel detailPanel;
	private JPanel overzichtPanel;
	private JTextField zoekbalk;
	private JButton zoekKnop;
	private JButton terugKnop;
	private JButton factureerKnop;
	private JButton openFactuurKnop;
	private JButton printFactuurKnop;
	private JTable overzicht;
	private JTextArea details;
	private JLabel paginaNaam;
	private Container contentpane;
	private Color GRAY;

	public FacturatieGUI(FacturatieManagerImpl facturatieManagerImpl) {
		this.facturatieManagerImpl = facturatieManagerImpl;
		initComponents();

	}

	public void initComponents() {
		frame = new JFrame("Facturatiesysteem");
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
		details.setPreferredSize(new Dimension(250,400));
		details.setText("test");
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
		overzicht = new JTable();
		overzicht.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Naam", "Datum", "Status" }));
		overzicht.setEnabled(false);
		overzicht.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel model = new DefaultTableModel();

		
		//panels vullen
		buttonPanel.add(factureerKnop);
		buttonPanel.add(openFactuurKnop);
		buttonPanel.add(printFactuurKnop);
		
		overzichtPanel.add(overzicht);
		
		headPanel.add(paginaNaam);
		headPanel.add(zoekKnop);
		headPanel.add(terugKnop);
		headPanel.add(zoekbalk);
		
		detailPanel.add(details);
		
		
		contentpane.add(buttonPanel, BorderLayout.SOUTH);
		contentpane.add(overzichtPanel, BorderLayout.CENTER);
		contentpane.add(headPanel, BorderLayout.NORTH);
		contentpane.add(detailPanel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		
	}

	public void toonFacturatieGUI() {

	}

	public void toonFacturenscherm() {

	}

	public void vulKlantenlijst(ArrayList<Klant> klanten) {

	}

	public void vulFacturenlijst(ArrayList<Factuur> facturen) {

	}

	public void toonKlant(String voornaam, String achternaam) {

	}

	public void toonFactuur(Factuur factuur) {

	}
}
