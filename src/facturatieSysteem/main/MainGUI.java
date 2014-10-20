package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.AddKlantDialog;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.ChangeKlantDialog;
import static java.awt.Frame.MAXIMIZED_BOTH;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MainGUI {
	private JFrame frame;
	private JTable Klant_Table;
	private JPanel Header, Footer, MainPanel, FacturatiePanel, KlantenPanel, VerzekeringsMaatschappijPanel, Klanten, knoppen, Klant_info, links, rechts, VerzekeringPanel, Header_Button;
	private KlantManager KlantManager;
	private JLabel lblCreatedByInfosys, lblFacturatiesysteem;
	private String[][] data;
	private ArrayList<Klant> klanten;
	private JTextArea Uitgebreide_Info;
	private JButton btnAddKlant, btnChangeKlant, btnFacturatie, btnVerzekeringmaatschapij, btnVerzekeringbeheer, btnKlantenbeheer;
	private FacturatieManagerImpl facturatieManager = new FacturatieManagerImpl();
	private VerzekeringsmaatschappijManager maatschappijManager;
	
	public MainGUI(KlantManager klantManager, VerzekeringsmaatschappijManager verzekeringsmaatschappijmanager) {
		this.KlantManager = klantManager;
		this.maatschappijManager = verzekeringsmaatschappijmanager;
		makeFrame();
	}

	@SuppressWarnings("unused")
	public void makeFrame() {

		// Originele code..
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		MainPanel = new JPanel();
		frame.getContentPane().add(MainPanel);
		MainPanel.setLayout(new CardLayout(0, 0));

		FacturatiePanel = new JPanel();
		MainPanel.add(FacturatiePanel, "name_11228791079497");
		FacturatiePanel.setLayout(new BorderLayout(0, 0));

		KlantenPanel = new JPanel();
		MainPanel.add(KlantenPanel, "name_11236108644850");
		KlantenPanel.setLayout(new BorderLayout(0, 0));

		Klanten = new JPanel();
		KlantenPanel.add(Klanten, BorderLayout.CENTER);
		Klanten.setLayout(new BorderLayout(0, 0));

		Klant_info = new JPanel();
		KlantenPanel.add(Klant_info, BorderLayout.EAST);
		Klant_info.setLayout(new BorderLayout(0, 0));

		knoppen = new JPanel();
		Klant_info.add(knoppen, BorderLayout.SOUTH);
		knoppen.setLayout(new BorderLayout(0, 0));

		links = new JPanel();
		knoppen.add(links, BorderLayout.WEST);

		rechts = new JPanel();
		knoppen.add(rechts, BorderLayout.EAST);
		rechts.setLayout(new BorderLayout(0, 0));

		Footer = new JPanel();
		frame.getContentPane().add(Footer, BorderLayout.SOUTH);
		Footer.setBackground(Color.ORANGE);
		FlowLayout fl_Footer = (FlowLayout) Footer.getLayout();
		
		VerzekeringPanel = new JPanel();
		MainPanel.add(VerzekeringPanel, "name_11244230620371");
		VerzekeringPanel.setLayout(new BorderLayout(0, 0));

		VerzekeringsMaatschappijPanel = new JPanel();
		MainPanel.add(VerzekeringsMaatschappijPanel, "name_11248877742559");
		VerzekeringsMaatschappijPanel.setLayout(new BorderLayout(0, 0));

		Header = new JPanel();
		frame.getContentPane().add(Header, BorderLayout.NORTH);
		Header.setBackground(Color.ORANGE);
		
		Header_Button = new JPanel();
		Header_Button.setBackground(Color.ORANGE);
		Header.add(Header_Button, BorderLayout.EAST);
		Header_Button.setLayout(new BorderLayout(0, 50));

		lblCreatedByInfosys = new JLabel("Created by InfoSys");
		lblCreatedByInfosys.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 12));
		Footer.add(lblCreatedByInfosys);

		/*
		 * Table aanmaken en vullen
		 */
		klanten = KlantManager.getKlanten();

		data = new String[klanten.size()][4];

		int i = 0;

		for (Klant klant : klanten) {
			data[i][0] = klant.getNaam();
			data[i][1] = klant.getBSN();
			data[i][2] = klant.getGeboortedatum();
			data[i][3] = klant.getAdres();

			i++;
		}

		String[] columnNames = { "Naam", "BSN", "Geboortedatum", "Adres" };
		Klant_Table = new JTable(data, columnNames);
		Klanten.add(Klant_Table.getTableHeader(), BorderLayout.PAGE_START);
		Klanten.add(Klant_Table, BorderLayout.CENTER);

		Uitgebreide_Info = new JTextArea();
		Uitgebreide_Info.setColumns(40);
		Uitgebreide_Info.setEditable(false);
		Klant_info.add(Uitgebreide_Info);
		Klant_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = Klant_Table.getSelectedRow();
				String b_s_n = Klant_Table.getModel().getValueAt(row, 1).toString();
				Uitgebreide_Info.setText(KlantManager.toonKlant(b_s_n));
			}
		});

		/*
		 * Toevoegen van de klant knop wordt toegevoegd, en de action listener wordt eraan gekoppeld
		 */
		btnAddKlant = new JButton("");
		btnAddKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				AddKlantDialog addKlantDialog = new AddKlantDialog(
						KlantManager, maatschappijManager);

				addKlantDialog.setVisible(true);
			}
		});
		links.setLayout(new BorderLayout(0, 0));
		btnAddKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAddKlant.setMinimumSize(new Dimension(0, 0));
		btnAddKlant.setIcon(new ImageIcon(
				"Pictures/add-contact-icon-xsmall.png"));
		links.add(btnAddKlant, BorderLayout.WEST);

		/*
		 * Toevoegen van de knop klant veranderen en verwijderen + actionlistener
		 */
		btnChangeKlant = new JButton("Verander Klant");
		links.add(btnChangeKlant);
		btnChangeKlant.setEnabled(false);
		btnChangeKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// "Wijzigen klant wordt geklikt"
				if (btnChangeKlant.isEnabled()) {
					// System.out.println("klant geselecteerd!");
					ChangeKlantDialog changeKlantDialog = new ChangeKlantDialog(

							KlantManager, maatschappijManager, Klant_Table.getModel().getValueAt(Klant_Table.getSelectedRow(), 1).toString());

					changeKlantDialog.setVisible(true);
				} else {
					System.out.println("geen klant geselecteerd");
				}
			}
		});
		btnChangeKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnChangeKlant.setMinimumSize(new Dimension(0, 0));
		Klant_Table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						boolean rowsAreSelected = Klant_Table.getSelectedRowCount() > 0;
						btnChangeKlant.setEnabled(rowsAreSelected);
					}
				});

		/*
		 * Facturatieknop wordt toegevoegd, en gekoppeld aan een actionlistener
		 */
		btnFacturatie = new JButton("facturatie");
		rechts.add(btnFacturatie);
		btnFacturatie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KlantenPanel.setVisible(false);
				FacturatiePanel.add(FacturatieGUI.FacturatieGUI());
				FacturatiePanel.setVisible(true);
			}
		});

		lblFacturatiesysteem = new JLabel("FacturatieSysteem");
		lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
		lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 26));
		Header.setLayout(new BorderLayout(5, 5));
		Header.add(lblFacturatiesysteem, BorderLayout.WEST);

		btnVerzekeringmaatschapij = new JButton("Verzekeringmaatschapij");
		Header_Button.add(btnVerzekeringmaatschapij, BorderLayout.EAST);
		btnVerzekeringmaatschapij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlantenPanel.setVisible(false);
				VerzekeringPanel.setVisible(false);
				VerzekeringsMaatschappijPanel.setVisible(true);
				FacturatiePanel.setVisible(false);
			}
		});
		btnVerzekeringmaatschapij.setBackground(SystemColor.inactiveCaption);

		btnVerzekeringbeheer = new JButton("VerzekeringBeheer");
		Header_Button.add(btnVerzekeringbeheer, BorderLayout.WEST);
		btnVerzekeringbeheer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlantenPanel.setVisible(false);
				VerzekeringsMaatschappijPanel.setVisible(false);
				VerzekeringPanel.setVisible(true);
				FacturatiePanel.setVisible(false);
			}
		});
		btnVerzekeringbeheer.setBackground(SystemColor.inactiveCaption);

		btnKlantenbeheer = new JButton("KlantenBeheer");
		Header_Button.add(btnKlantenbeheer, BorderLayout.CENTER);
		btnKlantenbeheer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VerzekeringPanel.setVisible(false);
				VerzekeringsMaatschappijPanel.setVisible(false);
				KlantenPanel.setVisible(true);
				FacturatiePanel.setVisible(false);
			}
		});
		btnKlantenbeheer.setBackground(SystemColor.inactiveCaption);
		
		VerzekeringPanel.setVisible(false);
		VerzekeringsMaatschappijPanel.setVisible(false);
		KlantenPanel.setVisible(true);
		FacturatiePanel.setVisible(false);
		
		frame.setVisible(true);

	}
}