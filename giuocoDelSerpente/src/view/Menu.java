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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class Menu extends JFrame {

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
		
		JLabel lblTitolo = new JLabel("SNAKE");
		lblTitolo.setForeground(new Color(255, 255, 255));
		
		lblTitolo.setHorizontalAlignment(JLabel.CENTER);
		lblTitolo.setFont(new Font("Unispace", Font.PLAIN, 48));
		lblTitolo.setBounds(0, 30, 528, 70);
		contentPane.add(lblTitolo);
		
		rdbt1Player = new JRadioButton("1 Player");
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
		contentPane.add(rdbt2Player);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbt1Player);
		buttonGroup.add(rdbt2Player);
		
		btnPlay = new JButton("PLAY");
		btnPlay.setOpaque(false);
		btnPlay.setFont(new Font("Unispace", Font.PLAIN, 50));
		btnPlay.setBounds(154, 263, 212, 63);
		btnPlay.setFocusPainted(false);
		contentPane.add(btnPlay);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setSelectedIndex(2);
		comboBox.setBounds(270, 168, 39, 27);
		contentPane.add(comboBox);
		
		lblLunghezzaSnake = new JLabel("Lunghezza");
		lblLunghezzaSnake.setForeground(new Color(255, 255, 255));
		lblLunghezzaSnake.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblLunghezzaSnake.setBounds(251, 131, 79, 27);
		contentPane.add(lblLunghezzaSnake);
		
		JLabel lblVelocita = new JLabel("Velocità");
		lblVelocita.setForeground(Color.WHITE);
		lblVelocita.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblVelocita.setBounds(331, 131, 65, 27);
		contentPane.add(lblVelocita);
		
		comboBoxVel = new JComboBox();
		comboBoxVel.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBoxVel.setSelectedIndex(3);
		comboBoxVel.setBounds(344, 168, 39, 27);
		contentPane.add(comboBoxVel);
		
		cellField = new JTextField();
		cellField.setBounds(434, 175, 33, 20);
		cellField.setColumns(10);
		contentPane.add(cellField);
		
		JLabel lblGrandezzaCelle = new JLabel("<html><center>Grandezza celle</center></html>");
		lblGrandezzaCelle.setForeground(Color.WHITE);
		lblGrandezzaCelle.setFont(new Font("Unispace", Font.PLAIN, 13));
		lblGrandezzaCelle.setBounds(414, 131, 85, 39);
		contentPane.add(lblGrandezzaCelle);
		
		btnColori = new JButton("COLORI");
		btnColori.setOpaque(false);
		btnColori.setFocusPainted(false);
		btnColori.setBounds(410, 284, 89, 23);
		contentPane.add(btnColori);
	}
}
