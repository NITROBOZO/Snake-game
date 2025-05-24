package view;


import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.SnakeMultiplayer;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel_ p;
	

	/**
	 * Launch the application.
	 */


	public JPanel_ getPanel() {
		return p;
	}


	/**
	 * Create the frame.
	 */
	public MyFrame(SnakeMultiplayer snake) {
		setResizable(false);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setLocationRelativeTo(null);
				getPanel().getLblGameOver().setBounds(0,getSize().height/6,getSize().width,getSize().height/3);
				getPanel().getLblGameOver().setFont(new Font("Tahoma", Font.PLAIN, getSize().height/10));
				getPanel().getBtnRestart().setBounds((int)(getSize().width/8.8),(int)(getSize().height/1.5),(int)(getSize().width/1.3),getSize().height/8);
				getPanel().getBtnRestart().setFont(new Font("Tahoma", Font.PLAIN, getSize().height/7));
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p= new JPanel_(snake);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
		pack();
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}

}
