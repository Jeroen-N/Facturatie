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

		/*
		 * Create the frame
		 */
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		/*
		 * Create the Header
		 */
		Header = new JPanel();
		frame.getContentPane().add(Header, BorderLayout.NORTH);
		Header.setBackground(Color.ORANGE);
		
		/*
		 * Add JLabel in header + add to header
		 */
		lblFacturatiesysteem = new JLabel("FacturatieSysteem");
		lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
		lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 26));
		Header.setLayout(new BorderLayout(5, 5));
		Header.add(lblFacturatiesysteem, BorderLayout.WEST);
		
		/*
		 * Create the Panel in the header for the buttons + add to header
		 */
		Header_Button = new JPanel();
		Header_Button.setBackground(Color.ORANGE);
		Header.add(Header_Button, BorderLayout.EAST);
		Header_Button.setLayout(new BorderLayout(0, 50));
		
		/*
		 * Create the verzekeringsmaatschappij button + add the button to header_button
		 */
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

		/*
		 * Create the verzekeringbeheer button + add button to header_button
		 */
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

		/*
		 * Create the Klantenbeheer button + add button to header_button
		 */
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
		
		/*
		 * Create the Main Panel
		 */
		MainPanel = new JPanel();
		frame.getContentPane().add(MainPanel);
		MainPanel.setLayout(new CardLayout(0, 0));
		
		/*
		 * Create panel + add to main panel
		 */
		KlantenPanel = new JPanel();
		MainPanel.add(KlantenPanel, "name_11236108644850");
		KlantenPanel.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Create panel for the table
		 */
		Klanten = new JPanel();
		KlantenPanel.add(Klanten, BorderLayout.CENTER);
		Klanten.setLayout(new BorderLayout(0, 0));
		
		/*
		 * fill the table
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
		Klant_Table = new JTable(data, columnNames){
			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
		Klanten.add(Klant_Table.getTableHeader(), BorderLayout.PAGE_START);
		Klanten.add(Klant_Table, BorderLayout.CENTER);
		
		/*
		 * Create panel for more information about klant
		 */
		Klant_info = new JPanel();
		KlantenPanel.add(Klant_info, BorderLayout.EAST);
		Klant_info.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Add function to see more information
		 */
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
		 * add panel for buttons underneath more information panel
		 */
		knoppen = new JPanel();
		Klant_info.add(knoppen, BorderLayout.SOUTH);
		knoppen.setLayout(new BorderLayout(0, 0));
		
		/*
		 * In the buttons panel, make 2 sides, left and right to create 2 rows of buttons
		 */
		links = new JPanel();
		knoppen.add(links, BorderLayout.WEST);
		
		rechts = new JPanel();
		knoppen.add(rechts, BorderLayout.EAST);
		rechts.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Create addKlant button + add to left panel
		 */
		btnAddKlant = new JButton("");
		btnAddKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				AddKlantDialog addKlantDialog = new AddKlantDialog(
						KlantManager, maatschappijManager);
				addKlantDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addKlantDialog.setModal(true);
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
		 * Create changeKlant button + add to left panel
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
					changeKlantDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					changeKlantDialog.setModal(true);
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
		 * Create facturatie button + add to right panel
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
		
		/*
		 * Create the footer
		 */
		Footer = new JPanel();
		frame.getContentPane().add(Footer, BorderLayout.SOUTH);
		Footer.setBackground(Color.ORANGE);
		FlowLayout fl_Footer = (FlowLayout) Footer.getLayout();
		
		/*
		 * Set text in the footer
		 */
		lblCreatedByInfosys = new JLabel("Created by InfoSys");
		lblCreatedByInfosys.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 12));
		Footer.add(lblCreatedByInfosys);
		
		/*
		 * Create facturatiePanel + add to mainpanel
		 */
		FacturatiePanel = new JPanel();
		MainPanel.add(FacturatiePanel, "name_11228791079497");
		FacturatiePanel.setLayout(new BorderLayout(0, 0));

		/*
		 * Create verzekeringPanel + add to mainpanel
		 */
		VerzekeringPanel = new JPanel();
		MainPanel.add(VerzekeringPanel, "name_11244230620371");
		VerzekeringPanel.setLayout(new BorderLayout(0, 0));

		/*
		 * create verzekeringsmaatschappijpanel + add to mainpanel
		 */
		VerzekeringsMaatschappijPanel = new JPanel();
		MainPanel.add(VerzekeringsMaatschappijPanel, "name_11248877742559");
		VerzekeringsMaatschappijPanel.setLayout(new BorderLayout(0, 0));

		/*
		 * Set the visibility of the panels
		 */
		VerzekeringPanel.setVisible(false);
		VerzekeringsMaatschappijPanel.setVisible(false);
		KlantenPanel.setVisible(true);
		FacturatiePanel.setVisible(false);
		
		/*
		 * Set visibility of the frame
		 */
		frame.setVisible(true);

	}
}