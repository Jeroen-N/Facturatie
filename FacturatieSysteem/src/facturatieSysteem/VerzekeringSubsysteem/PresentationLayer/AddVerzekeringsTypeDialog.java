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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturatieSysteem.VerzekeringSubsysteem.BusinessLayer.VerzekeringsmaatschappijManager;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringsmaatschappij;
import facturatieSysteem.VerzekeringSubsysteem.EntityLayer.Verzekeringstype;

public class AddVerzekeringsTypeDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNaam;
	private JTextField textFieldEigenRisico;
	private JTextField textFieldTypeNr;

	/**
	 * Create the dialog.
	 */
	public AddVerzekeringsTypeDialog(
			final VerzekeringsmaatschappijManager manager,
			Verzekeringsmaatschappij maatschappij) {
		setTitle("Verzekeringstype beheer");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 632, 480);
		getContentPane().setLayout(new BorderLayout());
		{
			/*
			 * JTabbedPane wordt aangemaakt
			 */
			JTabbedPane VerzekeringsmaatschappijManager = new JTabbedPane(
					JTabbedPane.TOP);
			getContentPane().add(VerzekeringsmaatschappijManager,
					BorderLayout.CENTER);
			{
				/*
				 * JPanel, de basispaneel, wordt aangemaakt
				 */
				JPanel addVerzekering = new JPanel();
				VerzekeringsmaatschappijManager.addTab(
						"Verzekeringstype toevoegen", null, addVerzekering,
						null);
				addVerzekering.setLayout(new BorderLayout(0, 0));
				{
					/*
					 * Om de verzekering en de klant te kunnen scheiden is er
					 * gebruik gemaakt van een seperator
					 */
					JSeparator separator = new JSeparator();
					separator.setOrientation(SwingConstants.VERTICAL);
					addVerzekering.add(separator, BorderLayout.CENTER);
				}
				{
					/*
					 * Panel wordt aangemaakt om de klant gegevens in te kunnen
					 * vullen.
					 */
					JPanel addVerzekering_1 = new JPanel();
					JPanel addVerzekering_2 = new JPanel();
					addVerzekering.add(addVerzekering_1, BorderLayout.WEST);
					addVerzekering.add(addVerzekering_2, BorderLayout.EAST);
					addVerzekering_1.setLayout(new BoxLayout(addVerzekering_1,
							BoxLayout.Y_AXIS));
					{
						JPanel panel = new JPanel();
						panel.setMinimumSize(new Dimension(300, 20));
						panel.setMaximumSize(new Dimension(300, 20));
						panel.setPreferredSize(new Dimension(300, 20));
						panel.setAlignmentX(Component.LEFT_ALIGNMENT);
						panel.setAlignmentY(Component.TOP_ALIGNMENT);
						addVerzekering_1.add(panel);
						panel.setLayout(new BorderLayout(0, 0));
						{
							JLabel lblVerzekering = new JLabel(
									"Verzekeringstype");
							lblVerzekering.setPreferredSize(new Dimension(100,
									20));
							lblVerzekering
									.setMinimumSize(new Dimension(100, 20));
							lblVerzekering
									.setMaximumSize(new Dimension(100, 20));
							panel.add(lblVerzekering, BorderLayout.WEST);
						}
					}
					{
						JSplitPane splitPaneNr = new JSplitPane();
						splitPaneNr.setMinimumSize(new Dimension(300, 30));
						splitPaneNr.setMaximumSize(new Dimension(300, 30));
						splitPaneNr.setBorder(null);
						splitPaneNr.setDividerSize(0);
						splitPaneNr.setPreferredSize(new Dimension(300, 30));
						addVerzekering_1.add(splitPaneNr);
						{
							JLabel lblNr = new JLabel("Type Nr: ");
							lblNr.setHorizontalAlignment(SwingConstants.RIGHT);
							lblNr.setHorizontalTextPosition(SwingConstants.RIGHT);
							lblNr.setPreferredSize(new Dimension(120, 16));
							lblNr.setMinimumSize(new Dimension(120, 16));
							lblNr.setMaximumSize(new Dimension(120, 16));
							splitPaneNr.setLeftComponent(lblNr);
						}
						{
							textFieldTypeNr = new JTextField();
							splitPaneNr.setRightComponent(textFieldTypeNr);
							textFieldTypeNr.setColumns(15);
						}

						{
							JSplitPane splitPaneNaam = new JSplitPane();
							splitPaneNaam
									.setMinimumSize(new Dimension(300, 30));
							splitPaneNaam
									.setMaximumSize(new Dimension(300, 30));
							splitPaneNaam.setBorder(null);
							splitPaneNaam.setDividerSize(0);
							splitPaneNaam.setPreferredSize(new Dimension(300,
									30));
							addVerzekering_1.add(splitPaneNaam);
							{

								{
									JLabel lblNaam = new JLabel("Naam: ");
									lblNaam.setHorizontalAlignment(SwingConstants.RIGHT);
									lblNaam.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblNaam.setPreferredSize(new Dimension(120,
											16));
									lblNaam.setMinimumSize(new Dimension(120,
											16));
									lblNaam.setMaximumSize(new Dimension(120,
											16));
									splitPaneNaam.setLeftComponent(lblNaam);
								}
								{
									textFieldNaam = new JTextField();
									splitPaneNaam
											.setRightComponent(textFieldNaam);
									textFieldNaam.setColumns(15);
								}
							}
							{
								JSplitPane splitPaneAdres = new JSplitPane();
								splitPaneAdres.setMinimumSize(new Dimension(
										300, 30));
								splitPaneAdres.setMaximumSize(new Dimension(
										300, 30));
								splitPaneAdres.setBorder(null);
								splitPaneAdres.setDividerSize(0);
								splitPaneAdres.setPreferredSize(new Dimension(
										300, 30));
								addVerzekering_1.add(splitPaneAdres);
								{
									JLabel lblAdres = new JLabel(
											"Eigen Risico: ");
									lblAdres.setHorizontalTextPosition(SwingConstants.RIGHT);
									lblAdres.setHorizontalAlignment(SwingConstants.RIGHT);
									lblAdres.setPreferredSize(new Dimension(
											120, 16));
									lblAdres.setMinimumSize(new Dimension(120,
											16));
									lblAdres.setMaximumSize(new Dimension(120,
											16));
									splitPaneAdres.setLeftComponent(lblAdres);
								}
								{
									textFieldEigenRisico = new JTextField();
									splitPaneAdres
											.setRightComponent(textFieldEigenRisico);
									textFieldEigenRisico.setColumns(15);
								}
							}

							JPanel buttonPane = new JPanel();
							buttonPane.setLayout(new FlowLayout(
									FlowLayout.RIGHT));
							getContentPane()
									.add(buttonPane, BorderLayout.SOUTH);

							JButton okButton = new JButton("OK");
							okButton.setActionCommand("OK");
							buttonPane.add(okButton);
							getRootPane().setDefaultButton(okButton);

							okButton.addMouseListener(new MouseAdapter() {

								public void mouseClicked(MouseEvent e) {
									// TODO extra attribuut maatschappijnr
									String errorMessage = manager.checkType(
											textFieldTypeNr.getText(),
											textFieldEigenRisico.getText(),
											textFieldNaam.getText());

									if (!errorMessage.equals("")) {
										showConfirmationWindow(errorMessage);
									} else {
										Verzekeringstype type = new Verzekeringstype(
												textFieldTypeNr.getText(),
												Integer.parseInt(textFieldEigenRisico.getText()),
												textFieldNaam.getText());
										if(manager.addVerzekeringstype(
												maatschappij, type)){
											dispose();
											showConfirmationWindow("Type succesvol toegevoegd!");
										} else {
											showConfirmationWindow("Type niet succesvol toegevoegd!");
										}
									}
								}

							});

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
						
					}
				}
			}
		}
	}
	public void showConfirmationWindow(String message){
		Component frame = null;
		JOptionPane.showMessageDialog(frame, message);
	}
}
