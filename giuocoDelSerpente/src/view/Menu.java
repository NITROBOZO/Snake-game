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

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JLabel lblLunghezzaSnake;
	private ButtonGroup buttonGroup;
	private JButton btnPlay;
	private JRadioButton rdbt1Player;
	private JRadioButton rdbt2Player;
	
	

	public JComboBox getComboBox() {
		return comboBox;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("SNAKE");
		lblTitolo.setForeground(new Color(255, 255, 255));
		
		lblTitolo.setHorizontalAlignment(JLabel.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblTitolo.setBounds(0, 30, 528, 70);
		contentPane.add(lblTitolo);
		
		rdbt1Player = new JRadioButton("1 Player");
		rdbt1Player.setForeground(new Color(255, 255, 255));
		rdbt1Player.setOpaque(false);
		rdbt1Player.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbt1Player.setBounds(96, 137, 103, 21);
		rdbt1Player.setFocusPainted(false);
		contentPane.add(rdbt1Player);
		
		rdbt2Player = new JRadioButton("2 Player");
		rdbt2Player.setForeground(new Color(255, 255, 255));
		rdbt2Player.setOpaque(false);
		rdbt2Player.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbt2Player.setBounds(96, 167, 103, 21);
		rdbt2Player.setFocusPainted(false);
		contentPane.add(rdbt2Player);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbt1Player);
		buttonGroup.add(rdbt2Player);
		
		btnPlay = new JButton("PLAY");
		btnPlay.setFocusPainted(false);
		btnPlay.setOpaque(false);
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnPlay.setBounds(142, 263, 212, 63);
		contentPane.add(btnPlay);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setBounds(270, 168, 39, 27);
		contentPane.add(comboBox);
		
		lblLunghezzaSnake = new JLabel("Lunghezza Snake");
		lblLunghezzaSnake.setForeground(new Color(255, 255, 255));
		lblLunghezzaSnake.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblLunghezzaSnake.setBounds(222, 131, 132, 27);
		contentPane.add(lblLunghezzaSnake);
	}
}
