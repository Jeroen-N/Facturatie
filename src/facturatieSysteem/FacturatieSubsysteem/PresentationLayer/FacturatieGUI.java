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

public class FacturatieGUI {

	private static JFrame frame = new JFrame("Facturatiesysteem");
	private static FacturatieManagerImpl facturatieManagerImpl;
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
	private static JScrollPane scrollPane = new JScrollPane();
	private static JPanel mainPanel = new JPanel();

	public FacturatieGUI(FacturatieManagerImpl facturatieManagerImpl) {
		this.facturatieManagerImpl = facturatieManagerImpl;
		FacturatieGUI();
	}

	public static JPanel FacturatieGUI() {
		JPanel paneel = new JPanel();
		paneel.setName("FACTURATIE");
		paneel.add(scrollPane, BorderLayout.CENTER);
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

		// panels vullen
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
}
