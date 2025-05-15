package view;
import model.SnakeModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class JPanel_ extends JPanel {
	private static Image[] APPLEIMG = {
			new ImageIcon(JPanel_.class.getResource("/view/AppleBitten.png")).getImage(),
			new ImageIcon(JPanel_.class.getResource("/view/AppleNoBite.png")).getImage()
	};
	private SnakeModel snake;
	private JLabel lblPunti;
	private JLabel lblGameOver;


	private static final long serialVersionUID = 1L;
	

	public JLabel getLblPunti() {
		return lblPunti;
	}
	

	public JLabel getLblGameOver() {
		return lblGameOver;
	}
	public JPanel_(SnakeModel snake) {
		lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setFont(new Font("Unispace", Font.PLAIN, 35));
		lblGameOver.setForeground(new Color(255, 255, 255));
		lblGameOver.setLocation(218, 284);
		lblGameOver.setSize(195, 50);
		lblGameOver.setVisible(false);
		add(lblGameOver);
		this.snake = snake;
		setLayout(null);
		setSize(new Dimension(600,600));
		setFocusable(true);
		requestFocusInWindow();
		setBackground(Color.BLACK);
		lblPunti = new JLabel("SCORE: 0");
		lblPunti.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblPunti.setHorizontalAlignment(JLabel.CENTER);
		lblPunti.setForeground(new Color(255, 255, 255));
		lblPunti.setBounds(10, 11, 107, 25);
		add(lblPunti);
		
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i <= (snake.FIELD_SIZE+1)*snake.UNIT_SIZE; i += snake.UNIT_SIZE) {
			g.drawLine(i, 0, i, snake.FIELD_SIZE*snake.UNIT_SIZE + snake.UNIT_SIZE);
			g.drawLine(0, i, snake.FIELD_SIZE*snake.UNIT_SIZE + snake.UNIT_SIZE, i);
			
		}
		for(int i=0;i<snake.getLunghezza();i++) {
			if (i == 0) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.green);
			}
			g.fillRect(snake.getCoordinate(i).x, snake.getCoordinate(i).y, snake.UNIT_SIZE, snake.UNIT_SIZE);
			g.setColor(Color.red);
			g.fillRect(snake.getCoordinate(0).x, snake.getCoordinate(0).y, snake.UNIT_SIZE, snake.UNIT_SIZE);
		}
		if (snake.getLunghezza() % 2 == 0) {
			g.drawImage(APPLEIMG[1], snake.getApplePos().x, snake.getApplePos().y, snake.UNIT_SIZE, snake.UNIT_SIZE,
					this);
		} else {
			g.drawImage(APPLEIMG[0], snake.getApplePos().x, snake.getApplePos().y, snake.UNIT_SIZE, snake.UNIT_SIZE,
					this);
		}
		
		
		
		
	}
}
