package facturatieSysteem.FacturatieSubsysteem.PresentationLayer;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.EntityLayer.Factuur;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;

public class FacturatieGUI extends JFrame{

	private JFrame frame = new JFrame();
	private FacturatieManagerImpl facturatieManagerImpl;
	private JPanel panel = new JPanel();
	private JTextField zoekbalk = new JTextField();
	private JButton zoekKnop = new JButton();
	private JButton terugKnop = new JButton();
	private JButton factureerKnop = new JButton();
	private JButton openFactuurKnop = new JButton();
	private JButton printFactuurKnop = new JButton();
	private JTable overzicht = new JTable();
	private JTextArea details = new JTextArea();
	private JLabel paginaNaam = new JLabel();

	public FacturatieGUI(FacturatieManagerImpl facturatieManagerImpl) {
		this.facturatieManagerImpl = facturatieManagerImpl;
		initComponents();
		setVisible(true);
	}

	public void initComponents() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);

		// Tekst initialiseren van de knoppen en labels.
		paginaNaam.setText("Overzicht facturen");
		zoekKnop.setText("Zoek");
		terugKnop.setText("Terug");
		factureerKnop.setText("Factureren");
		openFactuurKnop.setText("Open factuur");
		printFactuurKnop.setText("Print factuur");
		zoekbalk.setText("Vul factuurcode in");

		// Tabel onderverdelen in kolommen en vastzetten.
		overzicht.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Naam", "Datum", "Status" }));
		overzicht.setEnabled(false);
		overzicht.getTableHeader().setReorderingAllowed(false);
		
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
