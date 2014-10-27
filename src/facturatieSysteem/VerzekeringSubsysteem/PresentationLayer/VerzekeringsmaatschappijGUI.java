package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import facturatieSysteem.KlantenSubsysteem.PresentationLayer.ChangeKlantDialog;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.*;
import facturatieSysteem.main.*;

public class VerzekeringsmaatschappijGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JPanel VerzekeringPanel, zoekpaneel, tabelpaneel,
			infopaneel, knoppenPaneel, linkerpaneel, rechterpaneel;
	private static JTextField zoekVeld;
	private static JButton zoekKnop, resetKnop, btnWijzigen, btnVerwijderen,
			btnToevoegen;
	private static JTable Verzekering_Table;
	private static Integer row;
	private static String naam;
	private static JTextArea Uitgebreide_Info;
	


	// The datamodel to be displayed in the JTable.
	private static DataTableModelVerzekeringen dataTableModelVerzekeringen;
	private static ArrayList<Verzekeringsmaatschappij> verzekeringList = null;

	// Get a logger instance for the current class
	Logger logger = Logger.getLogger(MainGUI.class);

	@SuppressWarnings({ "serial", "unused" })
	public static JPanel VerzekeringsGUI(VerzekeringsmaatschappijManager manager) {
		tabelpaneel = new JPanel();
		knoppenPaneel = new JPanel();
		zoekpaneel = new JPanel();
		infopaneel = new JPanel();
		linkerpaneel = new JPanel();
		rechterpaneel = new JPanel();
		dataTableModelVerzekeringen = new DataTableModelVerzekeringen();
		btnWijzigen = new JButton("");
		btnVerwijderen = new JButton("");
		btnToevoegen = new JButton("");
		VerzekeringPanel = new JPanel();
		VerzekeringPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		VerzekeringPanel.setLayout(new BorderLayout(0, 0));
		infopaneel.setLayout(new BorderLayout(5, 5));
		zoekpaneel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnWijzigen.setEnabled(false);
		btnVerwijderen.setEnabled(false);
		Uitgebreide_Info = new JTextArea();
		
		
		

		// / Zoekveld en knoppen
		VerzekeringPanel.add(infopaneel, BorderLayout.EAST);
		infopaneel.add(zoekpaneel, BorderLayout.NORTH);
		zoekpaneel.setLayout(new BorderLayout(0, 0));
		zoekpaneel.add(linkerpaneel, BorderLayout.WEST);
		zoekpaneel.add(rechterpaneel, BorderLayout.EAST);
		JLabel zoekLabel = new JLabel("Verzekering zoeken: ");
		zoekLabel.setPreferredSize(new Dimension(120, 16));
		zoekLabel.setMinimumSize(new Dimension(120, 16));
		zoekLabel.setMaximumSize(new Dimension(120, 16));
		zoekLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		zoekLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		zoekLabel.setVerticalAlignment(SwingConstants.CENTER);
		zoekKnop = new JButton("Zoeken");
		resetKnop = new JButton("Reset");
		zoekVeld = new JTextField();
		zoekVeld.setSize(new Dimension(6, 20));
		zoekVeld.setMaximumSize(new Dimension(6, 20));
		zoekKnop.setAlignmentY(TOP_ALIGNMENT);
		zoekVeld.setColumns(15);
		
		linkerpaneel.add(zoekLabel, BorderLayout.WEST);
		linkerpaneel.add(zoekVeld, BorderLayout.EAST);
		rechterpaneel.add(zoekKnop, BorderLayout.WEST);
		rechterpaneel.add(resetKnop, BorderLayout.EAST);
		

		// / Tabel Paneel
		VerzekeringPanel.add(tabelpaneel, BorderLayout.CENTER);
		tabelpaneel.setLayout(new BorderLayout(0, 0));

		// / Totaal lijst
		JScrollPane totaalLijst = new JScrollPane();
		totaalLijst.setAlignmentY(CENTER_ALIGNMENT);
		totaalLijst.setAlignmentX(CENTER_ALIGNMENT);
		tabelpaneel.add(totaalLijst);

		knoppenPaneel.setPreferredSize(new Dimension(250, 70));
		knoppenPaneel.setMaximumSize(new Dimension(250, 250));
		infopaneel.add(knoppenPaneel, BorderLayout.SOUTH);
		btnToevoegen.setIcon(new ImageIcon("Pictures/new-polis-xsmall.png"));
		btnWijzigen.setIcon(new ImageIcon("Pictures/change-polis-xsmall.png"));
		btnVerwijderen.setIcon(new ImageIcon("Pictures/delete-polis-xsmall.png"));
		btnToevoegen.setMargin(new Insets(0, 0, 0, 0));
		btnWijzigen.setMargin(new Insets(0, 0, 0, 0));
		btnVerwijderen.setMargin(new Insets(0, 0, 0, 0));
		knoppenPaneel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		knoppenPaneel.add(btnToevoegen);
		knoppenPaneel.add(btnWijzigen);
		knoppenPaneel.add(btnVerwijderen);
		
		infopaneel.add(Uitgebreide_Info, BorderLayout.CENTER);
		Uitgebreide_Info.setColumns(40);
		Uitgebreide_Info.setEditable(false);
		
		//kijk of iets wordt geselecteerd
		Verzekering_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Verzekering_Table.getSelectedRow() >= 0){
				row = Verzekering_Table.getSelectedRow();
				fillField(row);
				}
			}
		});

		
		// / CRUD Toevoegen

		btnToevoegen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddVerzekeringDialog AddVerzekeringDialog = new AddVerzekeringDialog(
						manager);
				AddVerzekeringDialog
						.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				AddVerzekeringDialog.setModal(true);
				AddVerzekeringDialog.setVisible(true);
				AddVerzekeringDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						Verzekering_Table.removeAll();
						fillTable(manager);
						Uitgebreide_Info.setText("");
						
					}
				});
			}
		});
		
		
		/// CRUD WIJZIGEN
		btnWijzigen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent d) {
				// "Wijzigen verzekering wordt geklikt"
				if (btnWijzigen.isEnabled()) {
					 System.out.println("verzekering geselecteerd!");
					ChangeVerzekeringDialog changeVerzekeringDialog = new ChangeVerzekeringDialog(
							manager, Verzekering_Table.getModel().getValueAt(Verzekering_Table.getSelectedRow(), 0)
									.toString());
					changeVerzekeringDialog
							.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					changeVerzekeringDialog.setModal(true);
					changeVerzekeringDialog.setVisible(true);
					changeVerzekeringDialog.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent e) {
							// System.out.println("window is closed");
							Verzekering_Table.removeAll();
							fillTable(manager);
							Verzekering_Table.setRowSelectionInterval(row, row);
							//fillField(row);
						}
					});
				} else {
					System.out.println("geen verzekering geselecteerd");
				}
			}
		});
			///CRUD VERWIJDEREN
		
		btnVerwijderen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerwijderen.setActionCommand("Verwijderen");
		btnVerwijderen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 Component frame = null;
				 int n = JOptionPane.showConfirmDialog(
					    frame,
					    "Weet uw zeker dat u deze verzekering wilt verwijderen",
					    "Verwijderen",
					    JOptionPane.YES_NO_OPTION);
				if(n == 0){
					String nummer = Verzekering_Table.getModel().getValueAt(Verzekering_Table.getSelectedRow(), 0)
					.toString();
					Verzekeringsmaatschappij m1 = manager.getVerzekeringsmaatschappij(nummer);
					
					manager.deleteVerzekeringsmaatschappij(m1);
					fillTable(manager);
					
				}
					
			}
				
		});
		

		// / TABEL VULLEN
		Verzekering_Table = new JTable(dataTableModelVerzekeringen) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};

		String[] headers = new String[] { "Nummer", "Naam", "Adres", "Postcode",
				"Plaats", "KVK", "RekeningNr" };
		dataTableModelVerzekeringen.setTableHeader(headers);
		String[][] initialValues = new String[][] { { "", "", "", "" } };

		TableColumn column = Verzekering_Table.getColumnModel().getColumn(0);
		column.setPreferredWidth(6);

		// Handle row selection, only one row can be selected
		Verzekering_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Verzekering_Table.getTableHeader().setReorderingAllowed(false);
		Verzekering_Table.getTableHeader().setResizingAllowed(false);

		fillTable(manager);

		totaalLijst = new JScrollPane(Verzekering_Table);

		totaalLijst.setViewportView(Verzekering_Table);
		Verzekering_Table.setFillsViewportHeight(true);
		totaalLijst.setBorder(new TitledBorder(new LineBorder(
				new Color(0, 0, 0)), "Verzekeringenlijst",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		VerzekeringPanel.add(totaalLijst, BorderLayout.CENTER);
		
		Verzekering_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Verzekering_Table.getSelectedRow() >= 0){
				row = Verzekering_Table.getSelectedRow();
				}
			}
		}); 
		
		return VerzekeringPanel;
			}

	protected static void fillField(Integer row) {
		String maatschappijnr = Verzekering_Table.getModel().getValueAt(row, 0)
				.toString();
		Uitgebreide_Info.setText("TESTEN: " + maatschappijnr);
	}

	public static void fillTable(VerzekeringsmaatschappijManager manager) {
		verzekeringList = manager.getVerzekeringsmaatschappijen();
		System.out.println(verzekeringList.size());
		int count = (verzekeringList == null) ? 0 : verzekeringList.size();

		if (count > 0) {
			dataTableModelVerzekeringen.setValues(verzekeringList);
		}
	
	
	
	Verzekering_Table.getSelectionModel().addListSelectionListener(
			new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					boolean rowsAreSelected = Verzekering_Table
							.getSelectedRowCount() > 0;
					btnWijzigen.setEnabled(rowsAreSelected);
					btnVerwijderen.setEnabled(rowsAreSelected);
					
				}
				
			});
	
	

		
		
	}
}

