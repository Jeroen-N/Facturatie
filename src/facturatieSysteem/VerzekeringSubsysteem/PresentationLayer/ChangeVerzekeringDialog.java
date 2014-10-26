package facturatieSysteem.VerzekeringSubsysteem.PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;


public class ChangeVerzekeringDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel changeVerzekering, changeVerzekering_1;
	private JTextField textFieldNaam;
	private JTextField textFieldAdres;
	private JTextField textFieldPostcode;
	private JTextField textFieldPlaats;
	private JTextField textFieldKVK;
	private JTextField textFieldRekeningNr;
	

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ChangeVerzekeringDialog(VerzekeringsmaatschappijManager manager, final String naam) {
		setTitle("Verzekering beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
		/*
		 * Klant wordt opgehaald
		 */
		final Verzekeringsmaatschappij verzekering = manager.getVerzekeringsmaatschappij(naam);
			/*
			 * JTabbedPane wordt aangemaakt
			 */
			JTabbedPane verzekeringsManager = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(verzekeringsManager, BorderLayout.CENTER);
			{
				/*
				 * JPanel, de basispaneel, wordt aangemaakt
				 */
				changeVerzekering = new JPanel();
				verzekeringsManager.addTab("Klant wijzigen", null, changeVerzekering, null);
				changeVerzekering.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de oude en nieuwe klant gegevens te scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					changeVerzekering.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de oude klant gegevens weer te geven
					 */
					changeVerzekering_1 = new JPanel();
					changeVerzekering.add(changeVerzekering_1, BorderLayout.WEST);
					changeVerzekering_1.setLayout(new BoxLayout(changeVerzekering_1,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						changeVerzekering_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblKlant = new JLabel("Klant Gegevens");
							lblKlant.setPreferredSize(new Dimension(100, 20));
							lblKlant.setMinimumSize(new Dimension(100, 20));
							lblKlant.setMaximumSize(new Dimension(100, 20));
							panel.add(lblKlant, BorderLayout.WEST);
						}
					}
					{
						JSplitPane splitPaneNaam = new JSplitPane();
						splitPaneNaam.setMinimumSize(new Dimension(300, 30));
						splitPaneNaam.setMaximumSize(new Dimension(300, 30));
						splitPaneNaam.setBorder(null);
						splitPaneNaam.setDividerSize(0);
						splitPaneNaam.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneNaam);
						{
							JLabel lblNaam = new JLabel("Naam: ");
							lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
							lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNaam.setPreferredSize(new Dimension(120, 16));
							lblNaam.setMinimumSize(new Dimension(120, 16));
							lblNaam.setMaximumSize(new Dimension(120, 16));
							splitPaneNaam.setLeftComponent(lblNaam);
						}
						{
							textFieldNaam = new JTextField();
							splitPaneNaam.setRightComponent(textFieldNaam);
							textFieldNaam.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneAdres = new JSplitPane();
						splitPaneAdres.setMinimumSize(new Dimension(300, 30));
						splitPaneAdres.setMaximumSize(new Dimension(300, 30));
						splitPaneAdres.setBorder(null);
						splitPaneAdres.setDividerSize(0);
						splitPaneAdres.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneAdres);
						{
							JLabel lblAdres = new JLabel("Adres: ");
							lblAdres.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblAdres.setHorizontalAlignment(SwingConstants.RIGHT);
							lblAdres.setPreferredSize(new Dimension(120, 16));
							lblAdres.setMinimumSize(new Dimension(120, 16));
							lblAdres.setMaximumSize(new Dimension(120, 16));
							splitPaneAdres.setLeftComponent(lblAdres);
						}
						{
							textFieldAdres = new JTextField();
							splitPaneAdres.setRightComponent(textFieldAdres);
							textFieldAdres.setColumns(15);
						}
					}
					{
						JSplitPane splitPanePostcode = new JSplitPane();
						splitPanePostcode
								.setMinimumSize(new Dimension(300, 30));
						splitPanePostcode
								.setMaximumSize(new Dimension(300, 30));
						splitPanePostcode.setBorder(null);
						splitPanePostcode.setDividerSize(0);
						splitPanePostcode.setPreferredSize(new Dimension(300,
								30));
						changeVerzekering_1.add(splitPanePostcode);
						{
							JLabel lblPostcode = new JLabel("Postcode: ");
							lblPostcode
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode
									.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPanePostcode.setLeftComponent(lblPostcode);
						}
						{
							textFieldPostcode = new JTextField();
							splitPanePostcode
									.setRightComponent(textFieldPostcode);
							textFieldPostcode.setColumns(15);

						}
					}
					{
						JSplitPane splitPanePlaats = new JSplitPane();
						splitPanePlaats.setMinimumSize(new Dimension(300, 30));
						splitPanePlaats.setMaximumSize(new Dimension(300, 30));
						splitPanePlaats.setBorder(null);
						splitPanePlaats.setDividerSize(0);
						splitPanePlaats
								.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPanePlaats);
						{
							JLabel lblPlaats = new JLabel("Plaats: ");
							lblPlaats
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPlaats
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPlaats.setPreferredSize(new Dimension(120, 16));
							lblPlaats.setMinimumSize(new Dimension(120, 16));
							lblPlaats.setMaximumSize(new Dimension(120, 16));
							splitPanePlaats.setLeftComponent(lblPlaats);
						}
						{
							textFieldPlaats = new JTextField();
							splitPanePlaats.setRightComponent(textFieldPlaats);
							textFieldPlaats.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneKVK = new JSplitPane();
						splitPaneKVK.setMinimumSize(new Dimension(300, 30));
						splitPaneKVK.setMaximumSize(new Dimension(300, 30));
						splitPaneKVK.setBorder(null);
						splitPaneKVK.setDividerSize(0);
						splitPaneKVK.setPreferredSize(new Dimension(300, 30));
						changeVerzekering_1.add(splitPaneKVK);
						{
							JLabel lblPostcode = new JLabel("Postcode: ");
							lblPostcode
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblPostcode
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblPostcode
									.setPreferredSize(new Dimension(120, 16));
							lblPostcode.setMinimumSize(new Dimension(120, 16));
							lblPostcode.setMaximumSize(new Dimension(120, 16));
							splitPaneKVK.setLeftComponent(lblPostcode);
						}
						{
							textFieldKVK = new JTextField();
							splitPaneKVK.setRightComponent(textFieldKVK);
							textFieldKVK.setColumns(15);
						}
					}
					{
						JSplitPane splitPaneRekeningNr = new JSplitPane();
						splitPaneRekeningNr.setMinimumSize(new Dimension(300,
								30));
						splitPaneRekeningNr.setMaximumSize(new Dimension(300,
								30));
						splitPaneRekeningNr.setBorder(null);
						splitPaneRekeningNr.setDividerSize(0);
						splitPaneRekeningNr.setPreferredSize(new Dimension(300,
								30));
						changeVerzekering_1.add(splitPaneRekeningNr);
						{
							JLabel lblRekeningNr = new JLabel("Rekening NR: ");
							lblRekeningNr
									.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblRekeningNr
									.setHorizontalAlignment(SwingConstants.RIGHT);
							lblRekeningNr
									.setMinimumSize(new Dimension(120, 16));
							lblRekeningNr
									.setMaximumSize(new Dimension(120, 16));
							lblRekeningNr.setPreferredSize(new Dimension(120,
									16));
							splitPaneRekeningNr.setLeftComponent(lblRekeningNr);
						}
						{
							textFieldRekeningNr = new JTextField();
							splitPaneRekeningNr
									.setRightComponent(textFieldRekeningNr);
							textFieldRekeningNr.setColumns(15);
						}						
					}					
				}
			}
			
			{
				JPanel buttonChangePane = new JPanel();
				buttonChangePane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				changeVerzekering.add(buttonChangePane, BorderLayout.SOUTH);
				{
					JButton wijzigButton = new JButton("Wijzigen");
					wijzigButton.setActionCommand("Wijzigen");
					buttonChangePane.add(wijzigButton);
					getRootPane().setDefaultButton(wijzigButton);
					
					/*
					wijzigButton.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
						manager.updateVerzekering(textFieldNaam.getText(), textFieldAdres.getText(), textFieldPostcode.getText(), textFieldPlaats.getText(), Integer.parseInt(textFieldKVK.getText()), Integer.parseInt(textFieldRekeningNr.getText()));
						dispose();
					}
					
					
				});*/
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					cancelButton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							dispose();
						}
					});
					buttonChangePane.add(cancelButton);
					
					
					{
	
			}
		}
			}
	}
	}
			