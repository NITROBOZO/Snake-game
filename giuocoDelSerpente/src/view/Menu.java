package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Menu extends JFrame implements ActionListener{
	private static final ImageIcon[] IMG = {new ImageIcon(Menu.class.getResource("/images/btnPlayStatic.png")),
											new ImageIcon(Menu.class.getResource("/images/btnPngM.png")),
											new ImageIcon(Menu.class.getResource("/images/btnPngS.png"))};
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JLabel lblLunghezzaSnake;
	private ButtonGroup buttonGroup;
	private JButton btnPlay;
	private JRadioButton rdbt1Player;
	private JRadioButton rdbt2Player;
	private JComboBox<String> comboBoxVel;
	private JTextField areaField;
	private JButton btnColori;
	private JCheckBox boxMuri;
	private FrameColori windowC;
	private Color[] colors;
	private JCheckBox chckbxFullscreen;
	private JComboBox<String> comboBoxResolution;
	private JTextField textFieldN1;
	private JTextField textFieldN2;
	private JLabel lblNomeP2;
	private JTextArea textAreaS;
	private JTextArea textAreaM;
	
	
	

	public JTextArea getTextAreaS() {
		return textAreaS;
	}
	public JTextArea getTextAreaM() {
		return textAreaM;
	}
	public JTextField getTextFieldN1() {
		return textFieldN1;
	}
	public JTextField getTextFieldN2() {
		return textFieldN2;
	}
	public Color[] getColori() {
		return colors;
	}
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public JComboBox<String> getComboBoxVel() {
		return comboBoxVel;
	}
	public Point getSelectedResolution() {
		if(chckbxFullscreen.isSelected()) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			return new Point(screenSize.width,screenSize.height);
		}
		String str = (String) comboBoxResolution.getSelectedItem();
		String[] strs = str.split("x");
		int[] n = new int[2];
		for(int i=0;i<2;i++) {
			n[i]=Integer.valueOf(strs[i]);
		}
		return new Point(n[0],n[1]);
	}

	public JButton getBtnPlay() {
		return btnPlay;
	}

	public JRadioButton getRdbt1Player() {
		return rdbt1Player;
	}

	public JRadioButton getRdbt2Player() {
		return rdbt2Player;
	}
	public JTextField getAreaField() {
		return areaField;
	}
	public JButton getBtnColori() {
		return btnColori;
	}
	public JCheckBox getBoxMuri() {
		return boxMuri;
	}
	public JCheckBox getChckbxFullscreen() {
		return chckbxFullscreen;
	}
	/**
	 * Create the frame.
	 */
	public Menu() {
		setResizable(false);
		colors = new Color[4];
		colors[0]=Color.red;
		colors[1]=Color.blue;
		colors[2]=Color.green;
		colors[3]=Color.cyan;
		windowC = new FrameColori();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 444);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbt1Player = new JRadioButton("1 Player");
		rdbt1Player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbt1Player.isSelected()) {
					btnPlay.setRolloverIcon(IMG[2]);
					textFieldN2.setVisible(false);
					lblNomeP2.setVisible(false);
					textFieldN2.setText("P2");
				}
			}
		});
		rdbt1Player.setForeground(new Color(255, 255, 255));
		rdbt1Player.setOpaque(false);
		rdbt1Player.setFont(new Font("Unispace", Font.PLAIN, 20));
		rdbt1Player.setBounds(78, 137, 121, 21);
		rdbt1Player.setFocusPainted(false);
		rdbt1Player.setSelected(true);
		contentPane.add(rdbt1Player);
		
		rdbt2Player = new JRadioButton("2 Player");
		rdbt2Player.setForeground(new Color(255, 255, 255));
		rdbt2Player.setOpaque(false);
		rdbt2Player.setFont(new Font("Unispace", Font.PLAIN, 20));
		rdbt2Player.setBounds(78, 167, 121, 21);
		rdbt2Player.setFocusPainted(false);
		rdbt2Player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbt2Player.isSelected()) {
					btnPlay.setRolloverIcon(IMG[1]);
					textFieldN2.setVisible(true);
					lblNomeP2.setVisible(true);
					
				}
			}
		});
		contentPane.add(rdbt2Player);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbt1Player);
		buttonGroup.add(rdbt2Player);
		
		btnPlay = new JButton("");
		btnPlay.setOpaque(false);
		btnPlay.setFont(new Font("Unispace", Font.PLAIN, 50));
		btnPlay.setBounds(154, 254, 200, 80);
		btnPlay.setFocusPainted(false);
		btnPlay.setIcon(IMG[0]);
		btnPlay.setRolloverIcon(IMG[2]);
		btnPlay.setOpaque(false);
		btnPlay.setRolloverEnabled(true);
		contentPane.add(btnPlay);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"}));
		comboBox.setSelectedIndex(2);
		comboBox.setBounds(225, 168, 39, 27);
		contentPane.add(comboBox);
		
		lblLunghezzaSnake = new JLabel("Lunghezza");
		lblLunghezzaSnake.setForeground(new Color(255, 255, 255));
		lblLunghezzaSnake.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblLunghezzaSnake.setBounds(215, 131, 79, 27);
		contentPane.add(lblLunghezzaSnake);
		
		JLabel lblVelocita = new JLabel("Velocità");
		lblVelocita.setForeground(Color.WHITE);
		lblVelocita.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblVelocita.setBounds(301, 131, 65, 27);
		contentPane.add(lblVelocita);
		
		comboBoxVel = new JComboBox<String>();
		comboBoxVel.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		comboBoxVel.setSelectedIndex(3);
		comboBoxVel.setBounds(305, 168, 39, 27);
		contentPane.add(comboBoxVel);
		
		
		JLabel lblRisoluzione = new JLabel("<html><center>Risoluzione</center></html>");
		lblRisoluzione.setForeground(Color.WHITE);
		lblRisoluzione.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblRisoluzione.setBounds(376, 124, 97, 39);
		contentPane.add(lblRisoluzione);
		
		btnColori = new JButton("COLORI");
		btnColori.setContentAreaFilled(true);
		btnColori.setBackground(new Color(255, 255, 255));
		btnColori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowC.setVisible(true);
				setVisible(false);
				
			}
		});
		btnColori.setOpaque(true);
		btnColori.setFocusPainted(false);
		btnColori.setBounds(410, 284, 89, 23);
		contentPane.add(btnColori);
		
		areaField = new JTextField(); //a
		areaField.setText("20");
		areaField.setColumns(10);
		areaField.setBounds(470, 171, 33, 20);
		contentPane.add(areaField);
		
		JLabel lblTitolo = new JLabel("SNAKE");
		lblTitolo.setHorizontalAlignment(JLabel.CENTER);
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Dialog", Font.BOLD, 38));
		lblTitolo.setBounds(0, 28, 155, 39);
		contentPane.add(lblTitolo);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setForeground(Color.WHITE);
		lblArea.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblArea.setBounds(470, 131, 46, 27);
		contentPane.add(lblArea);
		
		boxMuri = new JCheckBox("Muri");
		boxMuri.setForeground(new Color(255, 255, 255));
		boxMuri.setOpaque(false);
		boxMuri.setFont(new Font("Unispace", Font.PLAIN, 20));
		boxMuri.setBounds(78, 111, 97, 23);
		boxMuri.setFocusPainted(false);
		contentPane.add(boxMuri);
		
		chckbxFullscreen = new JCheckBox("Fullscreen");
		chckbxFullscreen.setOpaque(false);
		chckbxFullscreen.setForeground(Color.WHITE);
		chckbxFullscreen.setFont(new Font("Unispace", Font.PLAIN, 20));
		chckbxFullscreen.setFocusPainted(false);
		chckbxFullscreen.setBounds(78, 85, 155, 23);
		contentPane.add(chckbxFullscreen);
		
		comboBoxResolution = new JComboBox<String>();
		comboBoxResolution.setModel(new DefaultComboBoxModel<String>(new String[] {"1920x1080", "1600x900", "1280x720", "960x540"}));
		comboBoxResolution.setBounds(376, 167, 64, 28);
		contentPane.add(comboBoxResolution);
		
		textFieldN1 = new JTextField("P1");
		textFieldN1.setBounds(22, 221, 86, 20);
		contentPane.add(textFieldN1);
		textFieldN1.setColumns(10);
		
		textFieldN2 = new JTextField("P2");
		textFieldN2.setColumns(10);
		textFieldN2.setVisible(false);
		textFieldN2.setBounds(118, 221, 86, 20);
		contentPane.add(textFieldN2);
		
		JLabel lblNomeP1 = new JLabel("Nick P1");
		lblNomeP1.setForeground(Color.WHITE);
		lblNomeP1.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNomeP1.setBounds(22, 195, 79, 27);
		contentPane.add(lblNomeP1);
		
		lblNomeP2 = new JLabel("Nick P2");
		lblNomeP2.setForeground(Color.WHITE);
		lblNomeP2.setVisible(false);
		lblNomeP2.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNomeP2.setBounds(120, 195, 79, 27);
		contentPane.add(lblNomeP2);
		
		JLabel lblNewLabel = new JLabel("RECORD");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(199, 11, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVsMode = new JLabel("VS MODE");
		lblVsMode.setForeground(Color.WHITE);
		lblVsMode.setBackground(Color.WHITE);
		lblVsMode.setBounds(376, 11, 63, 14);
		contentPane.add(lblVsMode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(192, 28, 152, 106);
		contentPane.add(scrollPane);
		
		textAreaS = new JTextArea();
		scrollPane.setViewportView(textAreaS);
		textAreaS.setEditable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(361, 28, 155, 106);
		contentPane.add(scrollPane_1);
		
		textAreaM = new JTextArea();
		textAreaM.setEditable(false);
		scrollPane_1.setViewportView(textAreaM);
		windowC.getOkButton().addActionListener(this);
		windowC.getCancelButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==windowC.getOkButton()) {
			colors = windowC.getColors();
		}
		setVisible(true);
	}
}
