package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Menu extends JFrame {
	private static final ImageIcon[] IMG = {new ImageIcon(Menu.class.getResource("/images/btnPlayStatic.png")),
											new ImageIcon(Menu.class.getResource("/images/btnPngM.png")),
											new ImageIcon(Menu.class.getResource("/images/btnPngS.png"))};
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JLabel lblLunghezzaSnake;
	private ButtonGroup buttonGroup;
	private JButton btnPlay;
	private JRadioButton rdbt1Player;
	private JRadioButton rdbt2Player;
	private JComboBox comboBoxVel;
	private JTextField cellField;
	private JTextField areaField;
	private JButton btnColori;
	

	public JComboBox getComboBox() {
		return comboBox;
	}
	public JComboBox getComboBoxVel() {
		return comboBoxVel;
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
	public JTextField getCellField() {
		return cellField;
	}
	public JTextField getAreaField() {
		return areaField;
	}
	/**
	 * Create the frame.
	 */
	public Menu() {
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
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
		
		comboBoxVel = new JComboBox();
		comboBoxVel.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBoxVel.setSelectedIndex(3);
		comboBoxVel.setBounds(305, 168, 39, 27);
		contentPane.add(comboBoxVel);
		
		cellField = new JTextField();
		cellField.setText("20");
		cellField.setBounds(377, 171, 33, 20);
		cellField.setColumns(10);
		contentPane.add(cellField);
		
		
		JLabel lblGrandezzaCelle = new JLabel("<html><center>Grandezza celle</center></html>");
		lblGrandezzaCelle.setForeground(Color.WHITE);
		lblGrandezzaCelle.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblGrandezzaCelle.setBounds(354, 133, 85, 39);
		contentPane.add(lblGrandezzaCelle);
		
		btnColori = new JButton("COLORI");
		btnColori.setOpaque(false);
		btnColori.setFocusPainted(false);
		btnColori.setBounds(410, 284, 89, 23);
		contentPane.add(btnColori);
		
		areaField = new JTextField();
		areaField.setText("20");
		areaField.setColumns(10);
		areaField.setBounds(450, 171, 33, 20);
		contentPane.add(areaField);
		
		JLabel lblTitolo = new JLabel("SNAKE");
		lblTitolo.setHorizontalAlignment(JLabel.CENTER);
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Dialog", Font.BOLD, 38));
		lblTitolo.setBounds(10, 28, 516, 39);
		contentPane.add(lblTitolo);
		
		JLabel lblArea = new JLabel("Area");
		lblArea.setForeground(Color.WHITE);
		lblArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblArea.setBounds(447, 131, 79, 27);
		contentPane.add(lblArea);
	}
}
