package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.BehandelDAO;
import facturatieSysteem.VerzekeringSubsysteem.DataStoreLayer.BehandelDAOImpl;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;
import facturatieSysteem.main.MainGUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;


public class ChangeVerzekeringsTypeDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private  JPanel changeVerzekeringType, changeType_1, changeType_2, buttonPane;
	private  JTable typetable;
	private  JScrollPane typeScrollPane;
	private  JTextField textFieldTypeNr;
	private  JTextField textFieldNaam;
	private  JTextField textFieldEigenRisico;
	private  Integer row;
	private  JComboBox<String> comboBoxBehandelCode, comboBoxBehandelCodeAdd;
	private VerzekeringsmaatschappijManager manager;
	private Verzekeringsmaatschappij maatschappij;
	private String TypeNr;
	
	// The datamodel to be displayed in the JTable.
	private DataTableModelChangeType dateTableModelChangeType;
	private ArrayList<Verzekeringstype> typen = null;
	
	// Get a logger instance for the current class
	 Logger logger = Logger.getLogger(MainGUI.class);
	
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public ChangeVerzekeringsTypeDialog(VerzekeringsmaatschappijManager manager, String nummer) {
		this.manager = manager;
		setBackground(Color.RED);
		setTitle("Verzekeringstype beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 509);
		getContentPane().setLayout(new BorderLayout());
		dateTableModelChangeType = new DataTableModelChangeType();
		maatschappij = manager.getVerzekeringsmaatschappij(nummer);
		
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
				changeVerzekeringType = new JPanel();
				klantManager.addTab("Type Wijzigen", null, changeVerzekeringType, null);
				changeVerzekeringType.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					changeVerzekeringType.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					changeType_1 = new JPanel();
					changeVerzekeringType.add(changeType_1, BorderLayout.WEST);
					changeType_1.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						changeType_1.add(panel, BorderLayout.NORTH);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Naam");
							lblKlant.setPreferredSize(new Dimension(100, 20));
							lblKlant.setMinimumSize(new Dimension(100, 20));
							lblKlant.setMaximumSize(new Dimension(100, 20));
							panel.add(lblKlant, BorderLayout.WEST);
						}
					}
					{
						typetable = new JTable(dateTableModelChangeType) {
							public boolean isCellEditable(int rowIndex, int mColIndex) {
								return false;
							}
						};
						String[] headers = new String[] { "Type Nr", "Naam", "Eigen risico"};
						dateTableModelChangeType.setTableHeader(headers);
						
						TableColumn column = typetable.getColumnModel().getColumn(0);
						column.setPreferredWidth(6);
						
						typetable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
						typen = maatschappij.getTypes();
						int count = (typen == null) ? 0 : typen.size();
						
						if(count > 0){
							dateTableModelChangeType.setValues(typen);
						}
						
						typeScrollPane = new JScrollPane(typetable);
						typetable.setFillsViewportHeight(true);
						typeScrollPane.setBorder(new TitledBorder(new LineBorder(new Color(
								0, 0, 0)), "Typelijst", TitledBorder.LEADING,
								TitledBorder.TOP, null, null));
						typetable.getTableHeader().setReorderingAllowed(false);
						typetable.getTableHeader().setResizingAllowed(false);
						changeType_1.add(typeScrollPane, BorderLayout.CENTER);
						typetable.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								row = typetable.getSelectedRow();
								fillField(row);
								}
						});
						
						
						}
					}
				}
				
				{
					changeType_2 = new JPanel();
					changeVerzekeringType.add(changeType_2, BorderLayout.EAST);
					changeType_2.setLayout(new BoxLayout(changeType_2,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setAlignmentY(0.0f);
						panel.setAlignmentX(0.0f);
						changeType_2.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Type wijzigen");
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
						JSplitPane splitPaneTypeNummer = new JSplitPane();
						splitPaneTypeNummer.setMaximumSize(new Dimension(300,
								30));
						splitPaneTypeNummer.setMinimumSize(new Dimension(300,
								30));
						splitPaneTypeNummer.setPreferredSize(new Dimension(
								300, 30));
						splitPaneTypeNummer.setDividerSize(0);
						splitPaneTypeNummer.setBorder(null);
						changeType_2.add(splitPaneTypeNummer);
						{
							JLabel lblTypeNummer = new JLabel("Type Nr: ");
							lblTypeNummer.setPreferredSize(new Dimension(120,
									16));
							lblTypeNummer
									.setMinimumSize(new Dimension(120, 16));
							lblTypeNummer
									.setMaximumSize(new Dimension(120, 16));
							lblTypeNummer
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblTypeNummer
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneTypeNummer
									.setLeftComponent(lblTypeNummer);
						}
						{
							textFieldTypeNr = new JTextField();
							textFieldTypeNr.setColumns(15);
							splitPaneTypeNummer.setRightComponent(textFieldTypeNr);							
							textFieldTypeNr.setEditable(false);
						}
					}
					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setPreferredSize(new Dimension(300,
								30));
						splitPaneNaam.setMinimumSize(new Dimension(300,
								30));
						splitPaneNaam.setMaximumSize(new Dimension(300,
								30));
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setBorder(null);
						changeType_2.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam: ");
							lblNaam.setPreferredSize(new Dimension(120,
									16));
							lblNaam
									.setMinimumSize(new Dimension(120, 16));
							lblNaam
									.setMaximumSize(new Dimension(120, 16));
							lblNaam
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNaam
									.setHorizontalAlignment(SwingConstants.RIGHT);
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam = new JTextField();
							textFieldNaam.setColumns(15);
							splitPaneNaam.setRightComponent(textFieldNaam);
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
						changeType_2.add(splitPaneEigenRisico);
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
							textFieldEigenRisico.setEditable(true);
						}
					}
					{
						JPanel panel = new JPanel();
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setAlignmentY(0.0f);
						panel.setAlignmentX(0.0f);
						changeType_2.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel("Behandelcode wijzigen");
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
						JSplitPane splitPaneVerzekeringsType = new JSplitPane();
						splitPaneVerzekeringsType.setMaximumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType.setMinimumSize(new Dimension(
								300, 30));
						splitPaneVerzekeringsType
								.setPreferredSize(new Dimension(300, 30));
						splitPaneVerzekeringsType.setDividerSize(0);
						splitPaneVerzekeringsType.setBorder(null);
						changeType_2.add(splitPaneVerzekeringsType);
						{
							comboBoxBehandelCodeAdd = new JComboBox<String>();
							
							comboBoxBehandelCodeAdd.setEnabled(true);
							comboBoxBehandelCodeAdd.setPreferredSize(new Dimension(
									120, 16));
							comboBoxBehandelCodeAdd.setMinimumSize(new Dimension(
									120, 16));
							comboBoxBehandelCodeAdd.setMaximumSize(new Dimension(
									120, 16));
							
							splitPaneVerzekeringsType.setLeftComponent(comboBoxBehandelCodeAdd);
						}
						{						
							JButton btnOk = new JButton("Toevoegen");
							splitPaneVerzekeringsType.setRightComponent(btnOk);
							btnOk.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									String behandelcode = comboBoxBehandelCodeAdd.getSelectedItem().toString();
									 Component frame = null;
										int n = JOptionPane.showConfirmDialog(
											    frame,
											    "Weet uw zeker dat u de behandelcode "+behandelcode+" wilt toevoegen",
											    "Weet u het zeker?",
											    JOptionPane.YES_NO_OPTION);
										if(n == 0){
											manager.addBehandelcode(maatschappij, manager.getVerzekeringstype(maatschappij,TypeNr), behandelcode);
										}
			
									
								}
							});
							
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
						changeType_2.add(splitPaneVerzekeringsType);
						{
							comboBoxBehandelCode = new JComboBox<String>();
							
							comboBoxBehandelCode.setEnabled(true);
							comboBoxBehandelCode.setPreferredSize(new Dimension(
									120, 16));
							comboBoxBehandelCode.setMinimumSize(new Dimension(
									120, 16));
							comboBoxBehandelCode.setMaximumSize(new Dimension(
									120, 16));
							
							splitPaneVerzekeringsType.setLeftComponent(comboBoxBehandelCode);
						}
						{						
							JButton btnOk = new JButton("Verwijderen");
							splitPaneVerzekeringsType.setRightComponent(btnOk);
							btnOk.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									String behandelcode = comboBoxBehandelCode.getSelectedItem().toString();
									 Component frame = null;
										int n = JOptionPane.showConfirmDialog(
											    frame,
											    "Weet uw zeker dat u de behandelcode "+behandelcode+" wilt verwijderen",
											    "Weet u het zeker?",
											    JOptionPane.YES_NO_OPTION);
												
										if(n == 0){
											manager.deleteBehandelcode(maatschappij, manager.getVerzekeringstype(maatschappij,TypeNr), behandelcode);
											
										}
										
											
								}
							});
							
						}
						
							
					}		
				}
			}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			changeVerzekeringType.add(buttonPane, BorderLayout.SOUTH);
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
						if(!textFieldTypeNr.getText().equals(TypeNr) || textFieldNaam.getText().equals("")){
							showConfirmationWindow("Geen type geselecteerd");
						} else {
							showConfirmationWindow("Type aangepast");
							Verzekeringstype type = new Verzekeringstype(TypeNr,Integer.parseInt(textFieldEigenRisico.getText()),textFieldNaam.getText());
							manager.updateVerzekeringstype(maatschappij, type);
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
							 Component frame = null;
								int n = JOptionPane.showConfirmDialog(
									    frame,
									    "Weet u zeker dat u het type met nummer "+textFieldTypeNr.getText()+" wilt verwijderen",
									    "Weet u het zeker?",
									    JOptionPane.YES_NO_OPTION);
								if(n == 0){
									manager.deleteVerzekeringstype(maatschappij, manager.getVerzekeringstype(maatschappij,TypeNr));
									
									
								}							
						}
					});
					buttonPane.add(btnPolisVerwijderen);
				}
				buttonPane.add(cancelButton);
			}
		}
	}
	public  void fillField(int row){
		comboBoxBehandelCode.removeAllItems();
		comboBoxBehandelCodeAdd.removeAllItems();
		TypeNr = typetable.getModel().getValueAt(row, 0).toString();
		String naam = typetable.getModel().getValueAt(row, 1).toString();
		String eigenRisico = typetable.getModel().getValueAt(row, 2).toString();
		Verzekeringstype type = manager.getVerzekeringstype(maatschappij, TypeNr);
		for(String behandelcode : type.getBehandelcodes()){
			comboBoxBehandelCode.addItem(behandelcode);
		}
		BehandelDAO behandel = new BehandelDAOImpl();
		for(String behandelcode : behandel.getBehandelcodes()){
			comboBoxBehandelCodeAdd.addItem(behandelcode);
		}
		textFieldTypeNr.setText(TypeNr);
		textFieldEigenRisico.setText(eigenRisico);
		textFieldNaam.setText(naam);			
	}
	public void showConfirmationWindow(String message) {
		 Component frame = null;
		JOptionPane.showMessageDialog(frame, message);

	}
}