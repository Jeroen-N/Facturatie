package facturatieSysteem.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import facturatieSysteem.FacturatieSubsysteem.PresentationLayer.FacturatieGUI;
import facturatieSysteem.KlantenSubsysteem.PresentationLayer.KlantGUI;

import java.util.*;

import static java.awt.Frame.MAXIMIZED_BOTH;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MainGUI {

	private JButton klant, verzekering, facturatie;
	private JFrame frame;
	private Container contentpane;
	private JPanel header, headerTop, headerBottom, homePaneel;
	private JLabel companyname;
	private String huidigPaneel;
	private JScrollPane scrollPane;

	public MainGUI() {
		
		
		makeFrame();
	}

	public void makeFrame() {

		frame = new JFrame();

        contentpane = frame.getContentPane();
        contentpane.setLayout(new BoxLayout(contentpane, BoxLayout.Y_AXIS));

        contentpane.add(header());

        scrollPane = new JScrollPane();

        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        setInhoudScrollPane(homePaneel());

        contentpane.add(scrollPane);

        // Add Bottom Panel to Panel
        contentpane.add(bottom());

        // Set Window to Full screen
        frame.setExtendedState(MAXIMIZED_BOTH);
        // Hide Frame title bar with close button
        //setUndecorated(true);  
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //laat het frame zien
        frame.pack();
        frame.setVisible(true);

	}

	private JPanel header() {
		header = new JPanel();

		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		header.setMaximumSize(new Dimension(999999, 50));

		// Create Top panel
		headerTop = new JPanel(new FlowLayout(FlowLayout.LEFT));

		companyname = new JLabel("Facturerings systeem");
		companyname.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		// Add Label to headerTop
		headerTop.add(companyname);

		headerBottom = new JPanel(new BorderLayout());
		headerBottom.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20));

		JButton backButton = new JButton();
		backButton.setText("< keer terug");

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setInhoudScrollPane(homePaneel());
			}
		});

		// Add Button to headerBottom
		headerBottom.add(backButton, BorderLayout.WEST);

		JButton refresh = new JButton("Vernieuw");

		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {

				JPanel paneel = new JPanel();

				switch (huidigPaneel) {
				case "KLANT":
					paneel = KlantGUI.KlantGUI();
					break;
				case "BAR":
					//paneel = FacturatieGUI.initComponents();
					break;
				case "BETALING":
					//paneel = betalingPaneel();
					break;
				default:
					paneel = homePaneel();
					break;
				}

				setInhoudScrollPane(paneel);
			}

		});

		headerBottom.add(refresh, BorderLayout.EAST);

		// Add Top Panel to Panel
		header.add(headerTop);
		header.add(headerBottom);

		return header;
	}
	
	private JPanel homePaneel(){
		JPanel paneel = new JPanel();
		paneel.setName("HOME");
		
		paneel.setLayout(new BoxLayout(paneel, BoxLayout.X_AXIS));
		
		Dimension dimension = new Dimension(200,200);
		
		klant = new JButton("Klanten");
		klant.setMaximumSize(dimension);
		
		klant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setInhoudScrollPane(KlantGUI.KlantGUI());
			}
		});
		
		paneel.add(klant);
		
		paneel.add(Box.createRigidArea(new Dimension(10,10)));
		
		verzekering = new JButton("Verzekeringen");
		verzekering.setMaximumSize(dimension);
		
		verzekering.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//setInhoudScrollPane();
			}
		});
		
		paneel.add(verzekering);
		
		paneel.add(Box.createRigidArea(new Dimension(10,10)));
		
		facturatie = new JButton("Facturatie");
		facturatie.setMaximumSize(dimension);
		
		facturatie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//setInhoudScrollPane(KlantGUI.KlantGUI());
			}
		});
		
		paneel.add(facturatie);
		
		paneel.add(Box.createRigidArea(new Dimension(10,10)));
		
		return paneel;
	}
	
	private JPanel bottom() {
        JPanel bottom = new JPanel();
        bottom.setAlignmentY(FlowLayout.CENTER);
        bottom.setMaximumSize(new Dimension(900, 50));

        JLabel poweredby = new JLabel("Powered by InfoSys");
        poweredby.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add Label to Bottom Panel
        bottom.add(poweredby);

        return bottom;
    }
	
	private void setInhoudScrollPane(JPanel paneel) {
        // Set het huidigePaneel
        huidigPaneel = paneel.getName();

        /*
         Er wordt een panel over het paneel toegevoegd met een girdbaglayout.
         Dit zorgt ervoor de het paneel gecentreerd wordt weergegeven.       
         */
        JPanel centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(20, 0, 0, 0);
        c.anchor = GridBagConstraints.NORTH;

        centerPanel.add(paneel, c);

        paneel.getName();

        scrollPane.setViewportView(centerPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }
}
