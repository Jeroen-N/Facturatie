package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.text.DefaultCaret;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;

import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManager;
import facturatieSysteem.FacturatieSubsysteem.BusinessLayer.FacturatieManagerImpl;
import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.PresentationLayer.VerzekeringsmaatschappijGUI;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.AddKlantDialog;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.AddVerzekeringPolisDialog;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.ChangeKlantDialog;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.ChangeVerzekeringPolisDialog;
import static java.awt.Frame.MAXIMIZED_BOTH;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

public class MainGUI {
	private Integer row;
	private JFrame frmFacturatiesysteem;
	private JTable Klant_Table;
	private JPanel Header, Footer, MainPanel, FacturatiePanel, KlantenPanel,
			VerzekeringsMaatschappijPanel, knoppen, Klant_info,
			VerzekeringPanel, Header_Button, PanelEast, Klant_zoeken;
	private JScrollPane KlantenTablePanel;
	private KlantManager KlantManager;
	private JLabel lblCreatedByInfosys, lblFacturatiesysteem;
	private JTextArea Uitgebreide_Info;
	private JFormattedTextField textFieldZoeken;
	private JButton btnAddKlant, btnChangeKlant, btnFacturatie,
			btnVerzekeringmaatschapij, btnKlantenbeheer,
			btnAddPolis, btnZoekKlant;
	private FacturatieManager facturatieManager;
	private VerzekeringsmaatschappijManager maatschappijManager;
	private JTextArea PolisInfo;
	private JPanel Info_Polis;
	private JScrollPane scroll;

	// The datamodel to be displayed in the JTable.
	private DataTableModel dataTableModel;
	private ArrayList<Klant> memberList = null;

	// Get a logger instance for the current class
	static Logger logger = Logger.getLogger(MainGUI.class);
	private JButton btnChangePolis;
	private JButton btnReset;

	public MainGUI(KlantManager klantManager,
			VerzekeringsmaatschappijManager verzekeringsmaatschappijmanager, FacturatieManager facturatieManager) {
		logger.debug("Constructor");
		this.KlantManager = klantManager;
		this.maatschappijManager = verzekeringsmaatschappijmanager;
		this.facturatieManager = facturatieManager;
		dataTableModel = new DataTableModel();
		makeFrame();
	}

	@SuppressWarnings({"serial" })
	public void makeFrame() {

		/*
		 * Create the frame
		 */
		frmFacturatiesysteem = new JFrame();
		frmFacturatiesysteem.setBackground(new Color(255, 255, 255));
		frmFacturatiesysteem.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmFacturatiesysteem.setIconImage(Toolkit.getDefaultToolkit().getImage("Pictures/verzekering-xsmall.png"));
		frmFacturatiesysteem.setTitle("Facturatiesysteem");
		frmFacturatiesysteem.setName("Facturatiesysteem");
		frmFacturatiesysteem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFacturatiesysteem.setExtendedState(MAXIMIZED_BOTH);
		frmFacturatiesysteem.getContentPane().setLayout(new BorderLayout(0, 0));

		/*
		 * Create the Header
		 */
		Header = new JPanel();
		Header.setPreferredSize(new Dimension(10, 75));
		Header.setMinimumSize(new Dimension(10, 15));
		frmFacturatiesysteem.getContentPane().add(Header, BorderLayout.NORTH);
		Header.setBackground(new Color(30, 144, 255));

		/*
		 * Add JLabel in header + add to header
		 */
		lblFacturatiesysteem = new JLabel("FacturatieSysteem");
		lblFacturatiesysteem.setForeground(new Color(255, 255, 255));
		lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
		lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 26));
		Header.setLayout(new BorderLayout(5, 5));
		Header.add(lblFacturatiesysteem, BorderLayout.WEST);

		/*
		 * Create the Panel in the header for the buttons + add to header
		 */
		Header_Button = new JPanel();
		Header_Button.setPreferredSize(new Dimension(500, 50));
		Header_Button.setBackground(new Color(30, 144, 255));
		Header_Button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Header.add(Header_Button, BorderLayout.EAST);

		Header_Button.setLayout(new BorderLayout(0, 50));
		JPanel gui = new VerzekeringsmaatschappijGUI().VerzekeringsGUI(maatschappijManager);
		Header_Button.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnKlantenbeheer = new JButton("");
		btnKlantenbeheer.setToolTipText("Deze knop brengt u naar het klantenbeheer");
		btnKlantenbeheer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnKlantenbeheer.setMargin(new Insets(0, 0, 0, 0));
		btnKlantenbeheer.setIcon(new ImageIcon("Pictures/contact-administration-xsmall.png"));
		btnKlantenbeheer.setPreferredSize(new Dimension(50, 50));
		Header_Button.add(btnKlantenbeheer);
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
		btnVerzekeringmaatschapij = new JButton("");
		btnVerzekeringmaatschapij.setToolTipText("Deze knop brengt u naar het verzekeringsmaatschappij beheer");
		btnVerzekeringmaatschapij.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVerzekeringmaatschapij.setMargin(new Insets(0, 0, 0, 0));
		btnVerzekeringmaatschapij.setIcon(new ImageIcon("Pictures/verzekering-xsmall.png"));
		btnVerzekeringmaatschapij.setPreferredSize(new Dimension(50, 50));
		Header_Button.add(btnVerzekeringmaatschapij);
		btnVerzekeringmaatschapij.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlantenPanel.setVisible(false);
				VerzekeringPanel.setVisible(false);
				VerzekeringsMaatschappijPanel.add(gui);
				VerzekeringsMaatschappijPanel.setVisible(true);
				FacturatiePanel.setVisible(false);
			}
		});
		btnVerzekeringmaatschapij.setBackground(SystemColor.inactiveCaption);

		/*
		 * Create the Klantenbeheer button + add button to header_button
		 */

		/*
		 * Create the Main Panel
		 */
		MainPanel = new JPanel();
		frmFacturatiesysteem.getContentPane().add(MainPanel);
		MainPanel.setLayout(new CardLayout(0, 0));

		/*
		 * Create panel + add to main panel
		 */
		KlantenPanel = new JPanel();
		MainPanel.add(KlantenPanel, "name_11236108644850");
		KlantenPanel.setLayout(new BorderLayout(0, 0));

		/*
		 * fill the table
		 */
		Klant_Table = new JTable(dataTableModel) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		String[] headers = new String[] { "Naam", "BSN", "Geboortedatum",
				"Adres" };
		dataTableModel.setTableHeader(headers);

		TableColumn column = Klant_Table.getColumnModel().getColumn(0);
		column.setPreferredWidth(6);

		// Handle row selection, only one row can be selected
		Klant_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		fillTable();
		
		KlantenTablePanel = new JScrollPane(Klant_Table);
		Klant_Table.setFillsViewportHeight(true);
		KlantenTablePanel.setBorder(new TitledBorder(new LineBorder(new Color(
				0, 0, 0)), "Clientenlijst", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		Klant_Table.getTableHeader().setReorderingAllowed(false);
		Klant_Table.getTableHeader().setResizingAllowed(false);
		KlantenPanel.add(KlantenTablePanel, BorderLayout.CENTER);

		PanelEast = new JPanel();
		PanelEast.setPreferredSize(new Dimension(500, 10));
		KlantenPanel.add(PanelEast, BorderLayout.EAST);
		PanelEast.setLayout(new BorderLayout(0, 0));
		
		Klant_zoeken = new JPanel();
		Klant_zoeken.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(30, 144, 255)));
		Klant_zoeken.setBackground(new Color(255, 255, 255));
		PanelEast.add(Klant_zoeken, BorderLayout.NORTH);
		Klant_zoeken.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JSplitPane splitPaneZoeken = new JSplitPane();
		splitPaneZoeken.setBackground(new Color(255, 255, 255));
		splitPaneZoeken .setPreferredSize(new Dimension(300,30));
		splitPaneZoeken .setMinimumSize(new Dimension(300,30));
		splitPaneZoeken .setMaximumSize(new Dimension(300,30));
		splitPaneZoeken .setDividerSize(0);
		splitPaneZoeken .setBorder(null);
		Klant_zoeken.add(splitPaneZoeken,BorderLayout.WEST );
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("##-##-####");
			formatter.setPlaceholder("dd-mm-yyyy");
		} catch (ParseException e1) {
			showConfirmationWindow("Zorg ervoor dat u het volgens het goede format invult: dag - maand - jaar");
		}
		
		textFieldZoeken = new JFormattedTextField(formatter);
		textFieldZoeken.setColumns(15);
		textFieldZoeken.setText("dd-mm-yyyy");
		textFieldZoeken.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
				  textFieldZoeken.setText("");
			  }
			});
		splitPaneZoeken.setRightComponent(textFieldZoeken);
		
		JLabel lblZoeken = new JLabel("Klant zoeken: ");
		lblZoeken.setBackground(new Color(255, 255, 255));
		lblZoeken.setPreferredSize(new Dimension(120,16));
		lblZoeken.setMinimumSize(new Dimension(120, 16));
		lblZoeken.setMaximumSize(new Dimension(120, 16));
		lblZoeken.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblZoeken.setHorizontalAlignment(SwingConstants.RIGHT);
		splitPaneZoeken.setLeftComponent(lblZoeken);
		
		btnZoekKlant = new JButton("Zoeken");
		btnZoekKlant.setToolTipText("Zoek naar de ingevulde geboortedatum");
		btnZoekKlant.setIconTextGap(0);
		btnZoekKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnZoekKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (textFieldZoeken.getText().matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")){
					fillTableZoekresultaat(textFieldZoeken.getText());		
				}else{
					showConfirmationWindow("Geen geldige zoekwaarde");
				}
			}
		});
		Klant_zoeken.add(btnZoekKlant,BorderLayout.EAST );
		
		btnReset = new JButton("Reset");
		btnReset.setToolTipText("Reset de tabel.");
		btnReset.setIconTextGap(0);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Klant_Table.removeAll();
				fillTable();
				Uitgebreide_Info.setText("");
				PolisInfo.setText("");
				textFieldZoeken.setText("dd-mm-yyyy");
			}
		});
		Klant_zoeken.add(btnReset);

		/*
		 * Create panel for more information about klant
		 */
		Klant_info = new JPanel();
		Klant_info.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(30, 144, 255)));
		PanelEast.add(Klant_info, BorderLayout.CENTER);
		Klant_info.setLayout(new BorderLayout(0, 0));

		/*
		 * Add function to see more information
		 */
		Uitgebreide_Info = new JTextArea();
		Uitgebreide_Info.setRows(25);
		Uitgebreide_Info.setColumns(30);
		Uitgebreide_Info.setEditable(false);
		Klant_info.add(Uitgebreide_Info, BorderLayout.NORTH);
		Klant_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Klant_Table.getSelectedRow() >= 0){
				row = Klant_Table.getSelectedRow();
				fillField(row);
				}
			}
		});
		Klant_Table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						boolean rowsAreSelected = Klant_Table
								.getSelectedRowCount() > 0;
						btnChangeKlant.setEnabled(rowsAreSelected);
						btnAddPolis.setEnabled(rowsAreSelected);
						btnChangePolis.setEnabled(rowsAreSelected);
						btnFacturatie.setEnabled(rowsAreSelected);
					}
				});

		/*
		 * Polis informatie panel wordt aangemaakt en gevuld
		 */
		Info_Polis = new JPanel();
		Info_Polis.setMinimumSize(new Dimension(10, 50));
		Info_Polis.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(30, 144, 255)));
		Klant_info.add(Info_Polis, BorderLayout.CENTER);
		Info_Polis.setLayout(new BorderLayout(0, 0));
		PolisInfo = new JTextArea();
		PolisInfo.setColumns(40);
		PolisInfo.setPreferredSize(new Dimension(0, 50));
		PolisInfo.setEditable(false);
		scroll = new JScrollPane(PolisInfo);
		scroll.setPreferredSize(new Dimension(4, 50));
		Info_Polis.add(scroll);
		DefaultCaret caret = (DefaultCaret) PolisInfo.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

		/*
		 * add panel for buttons underneath more information panel
		 */
		knoppen = new JPanel();
		knoppen.setBackground(new Color(255, 255, 255));
		Klant_info.add(knoppen, BorderLayout.SOUTH);

		knoppen.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnChangePolis = new JButton("");
		btnChangePolis.setToolTipText("Deze knop maakt het mogelijk de polissen van deze klant te bekijken, en te bewerken.");
		btnChangePolis.setMargin(new Insets(0, 0, 0, 0));
		btnChangePolis.setIcon(new ImageIcon("Pictures/change-polis-xsmall.png"));
		btnChangePolis.setEnabled(false);
		btnChangePolis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// "Wijzigen klant wordt geklikt"
				if (btnChangePolis.isEnabled()) {
					// System.out.println("klant geselecteerd!");
					ChangeVerzekeringPolisDialog changeKlantDialog = new ChangeVerzekeringPolisDialog(KlantManager,maatschappijManager,Klant_Table.getModel().getValueAt(Klant_Table.getSelectedRow(), 1).toString());
					changeKlantDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					changeKlantDialog.setModal(true);
					changeKlantDialog.setVisible(true);
					changeKlantDialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							// System.out.println("window is closed");
							Klant_Table.removeAll();
							fillTable();
							Klant_Table.setRowSelectionInterval(row, row);
							Uitgebreide_Info.setText("");
							fillField(row);
						}
					});
				} else {
					System.out.println("geen klant geselecteerd");
				}
			}
		});
		
		btnFacturatie = new JButton("");
		btnFacturatie.setToolTipText("Hiermee gaat u naar het facturatiesysteem, en kan u facturen maken en printen voor de in de tabel geselecteerde klant.");
		btnFacturatie.setEnabled(false);
		btnFacturatie.setMargin(new Insets(0, 0, 0, 0));
		btnFacturatie.setIcon(new ImageIcon("Pictures/factureer-xsmall.png"));
		btnFacturatie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnChangeKlant.isEnabled()) {
					KlantenPanel.setVisible(false);
					Klant fact = KlantManager.getKlant(Klant_Table.getModel().getValueAt(Klant_Table.getSelectedRow(), 1).toString());
					FacturatiePanel.add(FacturatieGUI.FacturatieGUI(facturatieManager, fact, maatschappijManager));
					FacturatiePanel.setVisible(true);
				}
			}
		});		
		
		btnAddKlant = new JButton("");
		btnAddKlant.setToolTipText("Door gebruik van deze knop, kan u een klant toevoegen");
		btnAddKlant.setMargin(new Insets(0, 0, 0, 0));
		btnAddKlant.setIconTextGap(0);
		btnAddKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAddKlant.setMinimumSize(new Dimension(0, 0));
		btnAddKlant.setIcon(new ImageIcon("Pictures/add-contact-icon-xsmall.png"));
		btnAddKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddKlantDialog addKlantDialog = new AddKlantDialog(
						KlantManager, maatschappijManager);
				addKlantDialog
						.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addKlantDialog.setModal(true);
				addKlantDialog.setVisible(true);
				addKlantDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						Klant_Table.removeAll();
						fillTable();
						Uitgebreide_Info.setText("");
						PolisInfo.setText("");
					}
				});
			}
		});
		
		btnChangeKlant = new JButton("");
		btnChangeKlant.setToolTipText("Deze knop zorgt ervoor dat u de geselecteerde klant kan bewerken.");
		btnChangeKlant.setMargin(new Insets(0, 0, 0, 0));
		btnChangeKlant.setIcon(new ImageIcon("Pictures/change-contact-icon-xsmal.png"));
		btnChangeKlant.setEnabled(false);
		btnChangeKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnChangeKlant.setMinimumSize(new Dimension(0, 0));
		btnChangeKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// "Wijzigen klant wordt geklikt"
				if (btnChangeKlant.isEnabled()) {
					// System.out.println("klant geselecteerd!");
					ChangeKlantDialog changeKlantDialog = new ChangeKlantDialog(
							KlantManager,
							maatschappijManager,
							Klant_Table
									.getModel()
									.getValueAt(Klant_Table.getSelectedRow(), 1)
									.toString());
					changeKlantDialog
							.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					changeKlantDialog.setModal(true);
					changeKlantDialog.setVisible(true);
					changeKlantDialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							// System.out.println("window is closed");
							Klant_Table.removeAll();
							fillTable();
							Klant_Table.setRowSelectionInterval(row, row);
							Uitgebreide_Info.setText("");
							fillField(row);
						}
					});
				} else {
					System.out.println("geen klant geselecteerd");
				}
			}
		});
		
		btnAddPolis = new JButton("");
		btnAddPolis.setToolTipText("Deze knop maakt het mogelijk om een polis toe te voegen aan een geselecteerde klant.");
		btnAddPolis.setMargin(new Insets(0, 0, 0, 0));
		btnAddPolis.setIcon(new ImageIcon("Pictures/new-polis-xsmall.png"));
		btnAddPolis.setEnabled(false);
		btnAddPolis.setAlignmentY(Component.TOP_ALIGNMENT);
		btnAddPolis.setMinimumSize(new Dimension(0, 0));
		btnAddPolis.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (btnChangeKlant.isEnabled()) {
				AddVerzekeringPolisDialog addVerzekeringPolisDialog = new AddVerzekeringPolisDialog(KlantManager, maatschappijManager, Klant_Table.getModel().getValueAt(Klant_Table.getSelectedRow(), 1).toString());
				addVerzekeringPolisDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addVerzekeringPolisDialog.setModal(true);
				addVerzekeringPolisDialog.setVisible(true);
				addVerzekeringPolisDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						Klant_Table.removeAll();
						fillTable();
						Klant_Table.setRowSelectionInterval(row, row);
						Uitgebreide_Info.setText("");
						fillField(row);									
						}
					});
				}
			}
		});

		knoppen.add(btnAddKlant);		
		knoppen.add(btnChangeKlant);
		knoppen.add(btnAddPolis);
		knoppen.add(btnChangePolis);
		knoppen.add(btnFacturatie);		

		/*
		 * Create the footer
		 */
		Footer = new JPanel();
		Footer.setMinimumSize(new Dimension(10, 50));
		Footer.setPreferredSize(new Dimension(10, 30));
		frmFacturatiesysteem.getContentPane().add(Footer, BorderLayout.SOUTH);
		Footer.setBackground(new Color(30, 144, 255));
		Footer.getLayout();

		/*
		 * Set text in the footer
		 */
		lblCreatedByInfosys = new JLabel("Created by InfoSys");
		lblCreatedByInfosys.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCreatedByInfosys.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatedByInfosys.setForeground(new Color(255, 255, 255));
		lblCreatedByInfosys.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 12));
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
		frmFacturatiesysteem.setVisible(true);
	}
	/*
	 * Methode om de table te kunnen vullen en updaten
	 */
	public void fillTable(){
		memberList = KlantManager.getKlanten();
		int count = (memberList == null) ? 0 : memberList.size();
		
		if(count > 0){
		dataTableModel.setValues(memberList);
		}
	}
	
	public void fillTableZoekresultaat(String gebDatum){
		memberList = KlantManager.findKlant(gebDatum);
		
		int count = (memberList == null) ? 0 : memberList.size();
			
		if(count > 0){
			Klant_Table.removeAll();
			Uitgebreide_Info.setText("");
			PolisInfo.setText("");
			dataTableModel.setValues(memberList);
		}else{
			showConfirmationWindow("Geen klanten gevonden");
		}
	}
	
	/*
	 * Methode om het informatie veld te kunnen vullen en updaten
	 */
	public void fillField(int row){
		String b_s_n = Klant_Table.getModel().getValueAt(row, 1)
				.toString();
		Uitgebreide_Info.setText(KlantManager.toonKlant(b_s_n));
		PolisInfo.setText("");
		for (String s : KlantManager.toonPolis(b_s_n)) {
			PolisInfo.append(s + System.getProperty("line.separator"));
		}
	}
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}