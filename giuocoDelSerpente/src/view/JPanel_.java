package view;

import model.SnakeMultiplayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class JPanel_ extends JPanel {
	private static Image[] APPLEIMG = { new ImageIcon(JPanel_.class.getResource("/view/AppleBitten.png")).getImage(),
			new ImageIcon(JPanel_.class.getResource("/view/AppleNoBite.png")).getImage() };
	private SnakeMultiplayer snake;
	private JLabel lblPunti;
	private JLabel lblGameOver;
	private JButton btnRestart;

	private static final long serialVersionUID = 1L;

	public JLabel getLblPunti() {
		return lblPunti;
	}

	public JLabel getLblGameOver() {
		return lblGameOver;
	}
	

	public JButton getBtnRestart() {
		return btnRestart;
	}
	public void updatePrefSize() {
		if(snake.isMultiplayer()) {
			setPreferredSize(new Dimension((snake.getFieldSize()*2)*snake.getCellSize() + snake.getCellSize(), snake.getFieldSize()*snake.getCellSize() + snake.getCellSize()));
		}
		else {
			setPreferredSize(new Dimension(snake.getFieldSize()*snake.getCellSize() + snake.getCellSize(), snake.getFieldSize()*snake.getCellSize() + snake.getCellSize()));
		}
		revalidate();
	}

	public JPanel_(SnakeMultiplayer snake) {
		lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(JLabel.CENTER);
		lblGameOver.setFont(new Font("Unispace", Font.PLAIN, 77));
		lblGameOver.setForeground(new Color(255, 255, 255));
		lblGameOver.setLocation(0, 158);
		lblGameOver.setSize(600, 193);
		lblGameOver.setVisible(false);
		add(lblGameOver);
		this.snake = snake;
		setLayout(null);
		setPreferredSize(new Dimension(snake.getFieldSize()*snake.getCellSize() + snake.getCellSize(), snake.getFieldSize()*snake.getCellSize() + snake.getCellSize()));
		setBackground(Color.BLACK);
		lblPunti = new JLabel("SCORE: 0");
		lblPunti.setVisible(false);
		lblPunti.setFont(new Font("Unispace", Font.PLAIN, 18));
		lblPunti.setHorizontalAlignment(JLabel.CENTER);
		lblPunti.setForeground(new Color(255, 255, 255));
		lblPunti.setBounds(10, 11, 107, 25);
		add(lblPunti);
		btnRestart = new JButton("RESTART");
		btnRestart.setOpaque(false);
		btnRestart.setBackground(new Color(255, 0, 0));
		btnRestart.setFocusPainted(false);
		btnRestart.setForeground(new Color(255, 0, 0));
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 77));
		btnRestart.setBounds(68, 389, 468, 157);
		btnRestart.setBorder(null);
		add(btnRestart);

	}

	@Override
	protected void paintComponent(Graphics g) {
		if (snake.isStart()) {
			super.paintComponent(g);
			/*for (int i = 0; i <= (snake.FIELD_SIZE + 1) * snake.UNIT_SIZE; i += snake.UNIT_SIZE) {
				g.drawLine(i, 0, i, snake.FIELD_SIZE * snake.UNIT_SIZE + snake.UNIT_SIZE);
				g.drawLine(0, i, snake.FIELD_SIZE * snake.UNIT_SIZE + snake.UNIT_SIZE, i);

			}*/
			for (int i = 0; i < snake.getLunghezza(0); i++) {
				if (i == 0) {
					g.setColor(Color.red);
				} else {
					g.setColor(Color.green);
				}
				g.fillRect(snake.getCoordinate(0,i).x, snake.getCoordinate(0,i).y, snake.getCellSize(), snake.getCellSize());
				g.setColor(Color.red);
				g.fillRect(snake.getCoordinate(0,0).x, snake.getCoordinate(0,0).y, snake.getCellSize(), snake.getCellSize());
			}
				if(snake.isMultiplayer()) {
					for(int j = 0; j < snake.getLunghezza(1); j++) {
						if (j == 0) {
							g.setColor(Color.blue);
						} else {
							g.setColor(Color.cyan);
						}
						g.fillRect(snake.getCoordinate(1,j).x, snake.getCoordinate(1,j).y, snake.getCellSize(), snake.getCellSize());
						g.setColor(Color.blue);
						g.fillRect(snake.getCoordinate(1,0).x, snake.getCoordinate(1,0).y, snake.getCellSize(), snake.getCellSize());
					}
				}
			}
			g.drawImage(APPLEIMG[1], snake.getApplePos(1).x, snake.getApplePos(1).y, snake.getCellSize(), snake.getCellSize(),
				this);
			if ((snake.getLunghezza(0)+snake.getLunghezza(1)) % 2 == 0) {
				g.drawImage(APPLEIMG[1], snake.getApplePos(0).x, snake.getApplePos(0).y, snake.getCellSize(), snake.getCellSize(),
						this);
			} else {
				g.drawImage(APPLEIMG[0], snake.getApplePos(0).x, snake.getApplePos(0).y, snake.getCellSize(), snake.getCellSize(),
						this);
			}
		}

	}
