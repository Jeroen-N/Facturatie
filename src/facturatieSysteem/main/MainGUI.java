package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

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

public class MainGUI {
	private JFrame frame;
	private JTable Klant_Table;
	private JPanel Header;
	private KlantManager KlantManager;
	private FacturatieManagerImpl facturatieManager = new FacturatieManagerImpl(); // aanmaken in de Main
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
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel VerzekeringPanel = new JPanel();
		frame.getContentPane().add(VerzekeringPanel, "name_30497855602569");
		// VerzekeringPanel.add(VerzekeringstypeGUI.VerzekeringstypeGUI());
		VerzekeringPanel.setVisible(false);

		final JPanel VerzekeringsMaatschappijPanel = new JPanel();
		frame.getContentPane().add(VerzekeringsMaatschappijPanel,
				"name_30497881246570");

		final JPanel KlantenPanel = new JPanel();
		frame.getContentPane().add(KlantenPanel, "name_31629163661906");
		KlantenPanel.setLayout(new BorderLayout(0, 0));

		final JPanel FacturatiePanel = new JPanel();
		frame.getContentPane().add(FacturatiePanel, "name_6096780048327");

		Header = new JPanel();
		KlantenPanel.add(Header, BorderLayout.NORTH);
		Header.setBackground(Color.ORANGE);

		JLabel lblFacturatiesysteem = new JLabel("FacturatieSysteem");
		lblFacturatiesysteem.setBackground(SystemColor.controlHighlight);
		lblFacturatiesysteem.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 26));
		Header.setLayout(new BorderLayout(5, 5));
		Header.add(lblFacturatiesysteem, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		Header.add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 50));

		JButton btnVerzekeringmaatschapij = new JButton(
				"Verzekeringmaatschapij");
		panel.add(btnVerzekeringmaatschapij, BorderLayout.EAST);
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

		JButton btnVerzekeringbeheer = new JButton("VerzekeringBeheer");
		panel.add(btnVerzekeringbeheer, BorderLayout.WEST);
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

		JButton btnKlantenbeheer = new JButton("KlantenBeheer");
		panel.add(btnKlantenbeheer, BorderLayout.CENTER);
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

		final JPanel Klanten = new JPanel();
		KlantenPanel.add(Klanten, BorderLayout.CENTER);

		ArrayList<Klant> klanten = KlantManager.getKlanten();

		String[][] data = new String[klanten.size()][4];

		int i = 0;

		for (Klant klant : klanten) {
			data[i][0] = klant.getNaam();
			data[i][1] = klant.getBSN();
			data[i][2] = klant.getGeboortedatum();
			data[i][3] = klant.getAdres();

			i++;
		}

		String[] columnNames = { "Naam", "BSN", "Geboortedatum", "Adres" };
		Klanten.setLayout(new BorderLayout(0, 0));

		Klant_Table = new JTable(data, columnNames);
		Klanten.add(Klant_Table.getTableHeader(), BorderLayout.PAGE_START);
		Klanten.add(Klant_Table, BorderLayout.CENTER);

		JPanel Klant_info = new JPanel();
		KlantenPanel.add(Klant_info, BorderLayout.EAST);
		Klant_info.setLayout(new BorderLayout(0, 0));

		final JTextArea Uitgebreide_Info = new JTextArea();
		Uitgebreide_Info.setColumns(40);
		Uitgebreide_Info.setEditable(false);
		Klant_info.add(Uitgebreide_Info);

		JPanel knoppen = new JPanel();
		Klant_info.add(knoppen, BorderLayout.SOUTH);
		knoppen.setLayout(new BorderLayout(0, 0));

		JPanel links = new JPanel();
		knoppen.add(links, BorderLayout.WEST);
		links.setLayout(new BoxLayout(links, BoxLayout.Y_AXIS));

		JButton button = new JButton("");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddKlantDialog addKlantDialog = new AddKlantDialog(
						KlantManager, maatschappijManager);
				addKlantDialog.setVisible(true);
			}
		});
		button.setAlignmentY(Component.TOP_ALIGNMENT);
		button.setMinimumSize(new Dimension(0, 0));
		button.setIcon(new ImageIcon(
				"Pictures/add-contact-icon-xsmall.png"));
		links.add(button);
		
		JButton btnChangeKlant = new JButton("Verander Klant");
		btnChangeKlant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	//TODO Fout melding als er geen klant is geselecteerd en er of "Wijzigen klant wordt geklikt"
				if (!Uitgebreide_Info.equals("")){
					ChangeKlantDialog changeKlantDialog = new ChangeKlantDialog(
							KlantManager, maatschappijManager, Klant_Table.getModel().getValueAt(Klant_Table.getSelectedRow(), 1).toString());
					changeKlantDialog.setVisible(true);
				}
				else{
					System.out.println("geen klant geselecteerd");
				}
			}
		});
		btnChangeKlant.setAlignmentY(Component.TOP_ALIGNMENT);
		btnChangeKlant.setMinimumSize(new Dimension(0, 0));
		knoppen.add(btnChangeKlant, BorderLayout.CENTER);
		
		JPanel rechts = new JPanel();
		knoppen.add(rechts, BorderLayout.EAST);

		JButton btnFacturatie = new JButton("facturatie");
		btnFacturatie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerzekeringPanel.setVisible(false);
				VerzekeringsMaatschappijPanel.setVisible(false);
				KlantenPanel.setVisible(false);
				// FacturatieGUI facgui = new FacturatieGUI(facturatieManager);
				FacturatiePanel.add(FacturatieGUI.FacturatieGUI());
				FacturatiePanel.setVisible(true);
			}
		});
		knoppen.add(btnFacturatie, BorderLayout.EAST);
		Klant_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = Klant_Table.getSelectedRow();
				String b_s_n = Klant_Table.getModel().getValueAt(row, 1)
						.toString();
				Uitgebreide_Info.setText(KlantManager.toonKlant(b_s_n));
			}
		});

		JPanel footer = new JPanel();
		footer.setBackground(Color.ORANGE);
		FlowLayout flowLayout = (FlowLayout) footer.getLayout();
		KlantenPanel.add(footer, BorderLayout.SOUTH);

		JLabel lblCreatedByInfosys = new JLabel("Created by InfoSys");
		lblCreatedByInfosys.setFont(new Font("Lucida Sans", Font.BOLD
				| Font.ITALIC, 12));
		footer.add(lblCreatedByInfosys);

		VerzekeringPanel.setVisible(false);
		KlantenPanel.setVisible(true);

		frame.setVisible(true);

	}
}