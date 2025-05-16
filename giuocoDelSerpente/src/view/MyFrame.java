package view;


import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.SnakeModel;

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
	public MyFrame(SnakeModel snake) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(snake.FIELD_SIZE*snake.UNIT_SIZE + snake.UNIT_SIZE*2, snake.FIELD_SIZE*snake.UNIT_SIZE + snake.UNIT_SIZE*3);
		setLocationRelativeTo(null);
		p= new JPanel_(snake);
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(p);
	}

}
