package facturatieSysteem.KlantenSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManager;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.VerzekeringPolis;
import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.main.DataTableModel;
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
		setTitle("Klant en verzekering beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 795, 509);
		getContentPane().setLayout(new BorderLayout());
		dataTableModelChangePolis = new DataTableModelChangePolis();
		final Klant klant = manager.getKlant(BSN);
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
						
						TableColumn column = column = polistable.getColumnModel().getColumn(0);
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
				}
				
				
			}
		
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			changeVerzekeringPolis.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Polis Toevoegen");
				okButton.setActionCommand("Polis Toevoegen");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
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
				buttonPane.add(cancelButton);
			}
		}}
}