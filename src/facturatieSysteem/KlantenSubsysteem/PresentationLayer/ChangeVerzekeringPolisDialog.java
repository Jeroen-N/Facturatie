package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;
import facturatieSysteem.main.MainGUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;


public class ChangeVerzekeringPolisDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel changeVerzekeringPolis, changePolis_1, changePolis_2, buttonPane;
	private JTable polistable;
	private JScrollPane polisScrollPane;
	private JTextField textFieldPolisNummer;
	private JTextField textFieldStartDatum;
	private JTextField textFieldEindDatum;
	private JTextField textFieldEigenRisico;
	private Integer row;
	private JComboBox<String> comboBoxMaatschappij;
	private JComboBox<String> comboBoxVerzekeringsType;
	private VerzekeringsmaatschappijManager vermaatschappijManager;
	private DateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
	
	// The datamodel to be displayed in the JTable.
	private DataTableModelChangePolis dataTableModelChangePolis;
	private ArrayList<VerzekeringPolis> polissen = null;
	
	// Get a logger instance for the current class
	static Logger logger = Logger.getLogger(MainGUI.class);
	
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public ChangeVerzekeringPolisDialog(final KlantManager manager, final VerzekeringsmaatschappijManager vermaatschappijManager, final String BSN) {
		setBackground(Color.RED);
		setTitle("Klant en verzekering beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 509);
		getContentPane().setLayout(new BorderLayout());
		dataTableModelChangePolis = new DataTableModelChangePolis();
		Klant klant = manager.getKlant(BSN);
		this.vermaatschappijManager = vermaatschappijManager;
		{
			/*
			 * JTabbedPane wordt aangemaakt
			 */
			JTabbedPane klantManager = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(klantManager, BorderLayout.CENTER);
			{
				/*
				 * JPanel, de basispaneel, wordt aangemaakt
				 */
				changeVerzekeringPolis = new JPanel();
				klantManager.addTab("Polis Wijzigen", null, changeVerzekeringPolis, null);
				changeVerzekeringPolis.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					changeVerzekeringPolis.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					changePolis_1 = new JPanel();
					changeVerzekeringPolis.add(changePolis_1, BorderLayout.WEST);
					changePolis_1.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						changePolis_1.add(panel, BorderLayout.NORTH);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Polissen");
							lblKlant.setPreferredSize(new Dimension(100, 20));
							lblKlant.setMinimumSize(new Dimension(100, 20));
							lblKlant.setMaximumSize(new Dimension(100, 20));
							panel.add(lblKlant, BorderLayout.WEST);
						}
					}
					{
						polistable = new JTable(dataTableModelChangePolis) {
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
						String[] headers = new String[] { "Polisnummer", "Type", "Eigen risico", "Start datum", "Eind datum" };
						dataTableModelChangePolis.setTableHeader(headers);
						
						TableColumn column = polistable.getColumnModel().getColumn(0);
						column.setPreferredWidth(6);
						
						polistable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						polissen = klant.getVerzekeringPolissen();
						int count = (polissen == null) ? 0 : polissen.size();
						
						if(count > 0){
							dataTableModelChangePolis.setValues(polissen);
						}
						
						polisScrollPane = new JScrollPane(polistable);
						polistable.setFillsViewportHeight(true);
						polisScrollPane.setBorder(new TitledBorder(new LineBorder(new Color(
								0, 0, 0)), "Polislijst", TitledBorder.LEADING,
								TitledBorder.TOP, null, null));
						polistable.getTableHeader().setReorderingAllowed(false);
						polistable.getTableHeader().setResizingAllowed(false);
						changePolis_1.add(polisScrollPane, BorderLayout.CENTER);
						polistable.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								row = polistable.getSelectedRow();
								fillField(row);
								}
						});
						
						
						}
					}
				}
				
				{
					changePolis_2 = new JPanel();
					changeVerzekeringPolis.add(changePolis_2, BorderLayout.EAST);
					changePolis_2.setLayout(new BoxLayout(changePolis_2,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setAlignmentY(0.0f);
						panel.setAlignmentX(0.0f);
						changePolis_2.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Polis wijzigen");
							lblVerzekering.setPreferredSize(new Dimension(100,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(100, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(100, 20));
							panel.add(lblVerzekering, BorderLayout.NORTH);
						}
					}
					{
						JSplitPane splitPanePolisNummer = new JSplitPane();
						splitPanePolisNummer.setMaximumSize(new Dimension(300,
								30));
						splitPanePolisNummer.setMinimumSize(new Dimension(300,
								30));
						splitPanePolisNummer.setPreferredSize(new Dimension(
								300, 30));
						splitPanePolisNummer.setDividerSize(0);
						splitPanePolisNummer.setBorder(null);
						changePolis_2.add(splitPanePolisNummer);
						{
							JLabel lblPolisnummer = new JLabel("Polisnummer: ");
							lblPolisnummer.setPreferredSize(new Dimension(120,
									16));
							lblPolisnummer
									.setMinimumSize(new Dimension(120, 16));
							lblPolisnummer
									.setMaximumSize(new Dimension(120, 16));
							lblPolisnummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPolisnummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPanePolisNummer
									.setLeftComponent(lblPolisnummer);
						}
						{
							textFieldPolisNummer = new JTextField();
							textFieldPolisNummer.setColumns(15);
							splitPanePolisNummer
									.setRightComponent(textFieldPolisNummer);
							
							textFieldPolisNummer.setEditable(false);
						}
					}
					{
						JSplitPane splitPaneVerzekeringMaatschappij = new JSplitPane();
						splitPaneVerzekeringMaatschappij
								.setPreferredSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij
								.setMinimumSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij
								.setMaximumSize(new Dimension(300, 30));
						splitPaneVerzekeringMaatschappij.setDividerSize(0);
						splitPaneVerzekeringMaatschappij.setBorder(null);
						changePolis_2.add(splitPaneVerzekeringMaatschappij);
						{
							JLabel lblVerzekeringsmaatschappij = new JLabel(
									"Maatschappij: ");
							lblVerzekeringsmaatschappij
									.setPreferredSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setMinimumSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setMaximumSize(new Dimension(120, 16));
							lblVerzekeringsmaatschappij
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblVerzekeringsmaatschappij
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneVerzekeringMaatschappij
									.setLeftComponent(lblVerzekeringsmaatschappij);
						}
						{
							comboBoxMaatschappij = new JComboBox<String>();
							splitPaneVerzekeringMaatschappij.setRightComponent(comboBoxMaatschappij);
							comboBoxMaatschappij.setEnabled(false);							
							/*for (Verzekeringsmaatschappij maatschappij : vermaatschappijManager.getVerzekeringsmaatschappijen()) {
								comboBoxMaatschappij.addItem(maatschappij.getNaam());
							}
							comboBoxMaatschappij.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									comboBoxVerzekeringsType.removeAllItems();;
									textFieldEigenRisico.setText("");
									comboBoxVerzekeringsType.addItem("");
									if(comboBoxMaatschappij.getSelectedItem() != ""){
										Verzekeringsmaatschappij selectedMaatschappij = vermaatschappijManager.getVerzekeringsmaatschappij(comboBoxMaatschappij.getSelectedItem().toString());
										for (Verzekeringstype type : selectedMaatschappij.getTypes()) {
											comboBoxVerzekeringsType.addItem(type.getNaam());
										}
									}
								}
							});*/

						}
					}
					{
						JSplitPane splitPaneVerzekeringsType = new JSplitPane();
						splitPaneVerzekeringsType.setMaximumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType.setMinimumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType
								.setPreferredSize(new Dimension(300, 30));
						splitPaneVerzekeringsType.setDividerSize(0);
						splitPaneVerzekeringsType.setBorder(null);
						changePolis_2.add(splitPaneVerzekeringsType);
						{
							JLabel lblVerzekeringstype = new JLabel(
									"Verzekeringstype: ");
							lblVerzekeringstype.setPreferredSize(new Dimension(
									120, 16));
							lblVerzekeringstype.setMinimumSize(new Dimension(
									120, 16));
							lblVerzekeringstype.setMaximumSize(new Dimension(
									120, 16));
							lblVerzekeringstype
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblVerzekeringstype
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneVerzekeringsType
									.setLeftComponent(lblVerzekeringstype);
						}
						{
							comboBoxVerzekeringsType = new JComboBox<String>();
							splitPaneVerzekeringsType.setRightComponent(comboBoxVerzekeringsType);
							comboBoxVerzekeringsType.setEnabled(false);
							/*
							comboBoxVerzekeringsType.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									if(comboBoxMaatschappij.getSelectedItem() != "" && comboBoxMaatschappij.getSelectedItem() != null && comboBoxVerzekeringsType.getSelectedItem() != ""&& comboBoxVerzekeringsType.getSelectedItem() != null){
										textFieldEigenRisico.setText(
												Integer.toString(
														vermaatschappijManager.getVerzekeringstype(
																vermaatschappijManager.getVerzekeringsmaatschappij(
																		comboBoxMaatschappij.getSelectedItem().toString()),
																		comboBoxVerzekeringsType.getSelectedItem().toString())
																		.getEigenRisico()));
			
									}
								}
							});*/
						}
							
					}
					
					{
						JSplitPane splitPaneEigenRisico = new JSplitPane();
						splitPaneEigenRisico.setPreferredSize(new Dimension(
								300, 30));
						splitPaneEigenRisico.setMinimumSize(new Dimension(300,
								30));
						splitPaneEigenRisico.setMaximumSize(new Dimension(300,
								30));
						splitPaneEigenRisico.setDividerSize(0);
						splitPaneEigenRisico.setBorder(null);
						changePolis_2.add(splitPaneEigenRisico);
						{
							JLabel lblEigenRisico = new JLabel("Eigen Risico: ");
							lblEigenRisico.setPreferredSize(new Dimension(120,
									16));
							lblEigenRisico
									.setMinimumSize(new Dimension(120, 16));
							lblEigenRisico
									.setMaximumSize(new Dimension(120, 16));
							lblEigenRisico
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEigenRisico
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneEigenRisico
									.setLeftComponent(lblEigenRisico);
						}
						{
							textFieldEigenRisico = new JTextField();
							textFieldEigenRisico.setColumns(15);
							splitPaneEigenRisico.setRightComponent(textFieldEigenRisico);
							textFieldEigenRisico.setEditable(false);
						}
					}
					{
						JSplitPane splitPaneStartDatum = new JSplitPane();
						splitPaneStartDatum.setPreferredSize(new Dimension(300,
								30));
						splitPaneStartDatum.setMinimumSize(new Dimension(300,
								30));
						splitPaneStartDatum.setMaximumSize(new Dimension(300,
								30));
						splitPaneStartDatum.setDividerSize(0);
						splitPaneStartDatum.setBorder(null);
						changePolis_2.add(splitPaneStartDatum);
						{
							JLabel lblStartDatum = new JLabel("Start datum: ");
							lblStartDatum.setPreferredSize(new Dimension(120,
									16));
							lblStartDatum
									.setMinimumSize(new Dimension(120, 16));
							lblStartDatum
									.setMaximumSize(new Dimension(120, 16));
							lblStartDatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblStartDatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneStartDatum.setLeftComponent(lblStartDatum);
						}
						{
							textFieldStartDatum = new JTextField();
							textFieldStartDatum.setColumns(15);
							splitPaneStartDatum.setRightComponent(textFieldStartDatum);
						}
					}
					{
						JSplitPane splitPaneEindDatum = new JSplitPane();
						splitPaneEindDatum.setPreferredSize(new Dimension(300,
								30));
						splitPaneEindDatum
								.setMinimumSize(new Dimension(300, 30));
						splitPaneEindDatum
								.setMaximumSize(new Dimension(300, 30));
						splitPaneEindDatum.setDividerSize(0);
						splitPaneEindDatum.setBorder(null);
						changePolis_2.add(splitPaneEindDatum);
						{
							JLabel lblEindDatum = new JLabel("Eind datum: ");
							lblEindDatum
									.setPreferredSize(new Dimension(120, 16));
							lblEindDatum.setMinimumSize(new Dimension(120, 16));
							lblEindDatum.setMaximumSize(new Dimension(120, 16));
							lblEindDatum
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblEindDatum
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneEindDatum.setLeftComponent(lblEindDatum);
						}
						{
							textFieldEindDatum = new JTextField();
							textFieldEindDatum.setColumns(15);
							splitPaneEindDatum.setRightComponent(textFieldEindDatum);
						}
					}
				}
			}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			changeVerzekeringPolis.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Wijzig");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("Polis Toevoegen");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (!comboBoxMaatschappij.getSelectedItem().equals("")){
							String errorMessage =	manager.checkPolis(
									textFieldPolisNummer.getText(),
									comboBoxVerzekeringsType.getSelectedItem().toString(), 
									textFieldStartDatum.getText(), 
									textFieldEindDatum.getText());
							//System.out.println(errorMessage);
							if (!errorMessage.equals("")){
								showConfirmationWindow(errorMessage);
							}else{
								try {
									if(!manager.updateVerzekeringPolisXML(
											textFieldPolisNummer.getText(), 
											comboBoxVerzekeringsType.getSelectedItem().toString(), 
											Double.parseDouble(textFieldEigenRisico.getText()),
											df1.parse(textFieldStartDatum.getText()), 
											df1.parse(textFieldEindDatum.getText()))){
										showConfirmationWindow("Polis Toevoegen Mislukt");
									}else{
										dispose();
									}
								} catch (NumberFormatException | ParseException e1){
									e1.printStackTrace();
								}
							}
						}
						else{
							showConfirmationWindow("Geen verzekeringsmaatschappij gekozen");
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				{
					JButton btnPolisVerwijderen = new JButton("Verwijder");
					btnPolisVerwijderen.setActionCommand("Verwijderen");
					btnPolisVerwijderen.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(polistable.getRowCount() > 1){
								Component frame = null;
								int n = JOptionPane.showConfirmDialog(
									    frame,
									    "Weet uw zeker dat u de polis met nummer "+textFieldPolisNummer.getText()+" wilt verwijderen",
									    "Weet u het zeker?",
									    JOptionPane.YES_NO_OPTION);
								if(n == 0){
									manager.deleteVerzekeringPolisXML(textFieldPolisNummer.getText(), BSN);
									dispose();
								}
							} else {
								showConfirmationWindow("U kan deze polis niet verwijderen, want dan heeft de klant geen polis meer!");
							}
							
						}
					});
					buttonPane.add(btnPolisVerwijderen);
				}
				buttonPane.add(cancelButton);
			}
		}
	}
	public void fillField(int row){
		if(comboBoxMaatschappij.getItemCount() > 0 && comboBoxVerzekeringsType.getItemCount() > 0 && !textFieldEigenRisico.getText().equals("") || 
				comboBoxMaatschappij.getItemCount() > 0){
		comboBoxMaatschappij.setSelectedIndex(0);
		comboBoxVerzekeringsType.removeAllItems();
		textFieldEigenRisico.removeAll();
		}
		String PolisNr = polistable.getModel().getValueAt(row, 0).toString();
		String verType = polistable.getModel().getValueAt(row, 1).toString();
		String eigenRisico = polistable.getModel().getValueAt(row, 2).toString();
		String StartDatum = polistable.getModel().getValueAt(row, 3).toString();
		String EindDatum = polistable.getModel().getValueAt(row, 4).toString();
		textFieldPolisNummer.setText(PolisNr);
		comboBoxVerzekeringsType.addItem(verType);
		textFieldEigenRisico.setText(eigenRisico);
		textFieldStartDatum.setText(StartDatum);
		textFieldEindDatum.setText(EindDatum);
		
		SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		System.out.println(dt1.format(date));
		try {
			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(StartDatum);
			System.out.println( dt1.format(date1) );
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Verzekeringsmaatschappij maatschappij : vermaatschappijManager.getVerzekeringsmaatschappijen()) {
			for (Verzekeringstype type : maatschappij.getTypes()) {
				if(type.getNaam().equals(verType)){
					comboBoxMaatschappij.addItem( maatschappij.getNaam());
					break;
				}
			}
		}
	}
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);
	}
}