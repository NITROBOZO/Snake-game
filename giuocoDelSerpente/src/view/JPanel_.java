package view;

import model.SnakeMultiplayer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class JPanel_ extends JPanel {
	private static Image[] APPLEIMG = { new ImageIcon(JPanel_.class.getResource("/images/AppleBitten.png")).getImage(),
			new ImageIcon(JPanel_.class.getResource("/images/AppleNoBite.png")).getImage() };
	private SnakeMultiplayer snake;
	private JLabel lblPunti;
	private JLabel lblGameOver;
	private JButton btnRestart;
	private int[] nIMG;
	private Color[] colors;
	private Point point;
	private boolean grid;
	private static final long serialVersionUID = 1L;

	public void setColors(Color[] c) {
		colors = c;
	}

	public JLabel getLblPunti() {
		return lblPunti;
	}

	public int[] getNIMG() {
		return nIMG;
	}

	public void setGrid(boolean grid) {
		this.grid = grid;
	}

	public JLabel getLblGameOver() {
		return lblGameOver;
	}

	public void setPoint(Point p) {
		point = p;
	}

	public JButton getBtnRestart() {
		return btnRestart;
	}

	public void updatePrefSize() {
		if (snake.isMultiplayer()) {
			setPreferredSize(
					new Dimension((int) (snake.getFieldSize() * 1.7) * snake.getCellSize() + snake.getCellSize(),
							snake.getFieldSize() * snake.getCellSize() + snake.getCellSize()));
		} else {
			setPreferredSize(new Dimension(snake.getFieldSize() * snake.getCellSize() + snake.getCellSize(),
					snake.getFieldSize() * snake.getCellSize() + snake.getCellSize()));
		}
		revalidate();
	}

	public JPanel_(SnakeMultiplayer snake) {
		grid = false;
		point = new Point(0, 0);
		colors = new Color[4];
		colors[0] = Color.red;
		colors[1] = Color.green;
		colors[2] = Color.blue;
		colors[3] = Color.cyan;
		nIMG = new int[2];
		nIMG[0] = 0;
		nIMG[1] = 0;
		lblGameOver = new JLabel("GAME OVER");
		lblGameOver.setHorizontalAlignment(JLabel.CENTER);
		lblGameOver.setFont(new Font("Unispace", Font.PLAIN, 80));
		lblGameOver.setForeground(new Color(255, 255, 255));
		lblGameOver.setLocation(0, 100);
		lblGameOver.setSize(600, 190);
		lblGameOver.setVisible(false);
		add(lblGameOver);
		this.snake = snake;
		setLayout(null);
		setPreferredSize(new Dimension(snake.getFieldSize() * snake.getCellSize() + snake.getCellSize(),
				snake.getFieldSize() * snake.getCellSize() + snake.getCellSize()));
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
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 80));
		btnRestart.setBounds(68, 389, 468, 150);
		btnRestart.setBorder(null);
		add(btnRestart);

	}

	@Override
	protected void paintComponent(Graphics g) {
		Point p = point;
		int c = snake.getCellSize();
		int totSize = (int) (snake.getFieldSize() * SnakeMultiplayer.MPCONST) * c + c;
		int partSize = snake.getFieldSize() * c + c;
		if (snake.isStart()) {
			super.paintComponent(g);
			// griglia

			if (snake.isMultiplayer()) {
				if (grid) {
					// disegna griglia completa
					for (int i = 0; i <= (int) ((snake.getFieldSize() + 1) * SnakeMultiplayer.MPCONST) * c; i += c) {
						g.drawLine(i + p.x, 0 + p.y, i + p.x, snake.getFieldSize() * c + c + p.y);
						if (i <= (int) ((snake.getFieldSize() + 1)) * c) {
							g.drawLine(0 + p.x, i + p.y, totSize + p.x, i + p.y);
						}
					}
				} else { // Disegna bordi
					g.drawLine(0 + p.x, 0 + p.y, totSize + p.x, 0 + p.y);
					g.drawLine(0 + p.x, partSize + p.y, totSize + p.x, partSize + p.y);
					g.drawLine(0 + p.x, 0 + p.y, 0 + p.x, partSize + p.y);
					g.drawLine(totSize + p.x, 0 + p.y, totSize + p.x, partSize + p.y);
				}
			} else {
				if (grid) {
					// disegna griglia completa
					for (int i = 0; i <= (snake.getFieldSize() + 1) * c; i += c) {
						g.drawLine(i + p.x, 0 + p.y, i + p.x, partSize + p.y);
						g.drawLine(0 + p.x, i + p.y, partSize + p.x, i + p.y);
					}
				} else { // disegna bordi
					g.drawLine(0 + p.x, 0 + p.y, partSize + p.x, 0 + p.y);
					g.drawLine(0 + p.x, partSize + p.y, partSize + p.x, partSize + p.y);
					g.drawLine(0 + p.x, 0 + p.y, 0 + p.x, partSize + p.y);
					g.drawLine(partSize + p.x, 0 + p.y, partSize + p.x, partSize + p.y);
				}
			}
			// disegna serpenti
			for (int i = 0; i < snake.getLunghezza(0); i++) {
				if (i == 0) {
					g.setColor(colors[0]);
				} else {
					g.setColor(colors[2]);
				}
				g.fillRect(snake.getCoordinate(0, i).x + p.x, snake.getCoordinate(0, i).y + p.y, c, c);
			}
			if (snake.isMultiplayer()) {
				for (int j = 0; j < snake.getLunghezza(1); j++) {
					if (j == 0) {
						g.setColor(colors[1]);
					} else {
						g.setColor(colors[3]);
					}
					g.fillRect(snake.getCoordinate(1, j).x + p.x, snake.getCoordinate(1, j).y + p.y, c, c);

				}
			}
		}

		// disegna mele
		// se mangio mela 1, aggiorno solo mela 1, altrimenti aggiorno mela 2
		g.drawImage(APPLEIMG[nIMG[0]], snake.getApplePos(0).x + p.x, snake.getApplePos(0).y + p.y, c, c, this);
		g.drawImage(APPLEIMG[nIMG[1]], snake.getApplePos(1).x + p.x, snake.getApplePos(1).y + p.y, c, c, this);
	}

}
