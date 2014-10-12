package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;
import facturatieSysteem.VerzekeringSubsysteem.PresentationLayer.VerzekeringstypeGUI;
import facturatieSysteem.VerzekeringSubsysteem.PresentationLayer.VerzekeringsmaatschappijGUI;
import facturatieSysteem.KlantenSubsysteem.BusinessLayer.KlantManagerImpl;
import facturatieSysteem.KlantenSubsysteem.EntityLayer.Klant;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.KlantGUI;
import static java.awt.Frame.MAXIMIZED_BOTH;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI {
	private JFrame frame;
	private JTable Klant_Table;
	private JPanel Header;
	private KlantManagerImpl KlantManager = new KlantManagerImpl();
	private JTextArea Uitgebreide_Info;

	public MainGUI() {

		makeFrame();
	}

	public void makeFrame() {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setExtendedState(MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		final JPanel VerzekeringPanel = new JPanel();
		frame.getContentPane().add(VerzekeringPanel, "name_30497855602569");
		//VerzekeringPanel.add(VerzekeringstypeGUI.VerzekeringstypeGUI());
		VerzekeringPanel.setVisible(false);

		final JPanel VerzekeringsMaatschappijPanel = new JPanel();
		frame.getContentPane().add(VerzekeringsMaatschappijPanel,
				"name_30497881246570");

		final JPanel KlantenPanel = new JPanel();
		frame.getContentPane().add(KlantenPanel, "name_31629163661906");
		KlantenPanel.setLayout(new BorderLayout(0, 0));

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
		Uitgebreide_Info.setColumns(30);
		Klant_info.add(Uitgebreide_Info);
		Klant_Table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = Klant_Table.getSelectedRow();
				String b_s_n = Klant_Table.getModel().getValueAt(row, 1).toString();
				Uitgebreide_Info.setText(KlantManager.toonKlant(b_s_n));
			}
		});

		JPanel footer = new JPanel();
		footer.setBackground(Color.ORANGE);
		FlowLayout flowLayout = (FlowLayout) footer.getLayout();
		KlantenPanel.add(footer, BorderLayout.SOUTH);
		
		JLabel lblCreatedByInfosys = new JLabel("Created by InfoSys");
		lblCreatedByInfosys.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 12));
		footer.add(lblCreatedByInfosys);
		
		VerzekeringPanel.setVisible(false);
		KlantenPanel.setVisible(true);

		frame.setVisible(true);

	}
}