package control;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import model.SnakeMultiplayer;
import view.MyFrame;
import view.Menu;

public class Controller implements ActionListener {
	public static void main(String[] args) {
		System.out.println("Hello World");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Controller();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Timer timer;
	private Menu menu;
	private char direction = ' ';
	private char directionP2 = ' ';
	public SnakeMultiplayer snake;
	private MyFrame frame;

	public Controller() {
		snake = new SnakeMultiplayer();
		menu = new Menu();
		frame = new MyFrame(snake);
		menu.setVisible(true);
		frame.setVisible(false);
		menu.getBtnColori().addActionListener(this);
		menu.getBtnPlay().addActionListener(this);
		menu.getCellField().addActionListener(this);
		frame.getPanel().getBtnRestart().addActionListener(this);
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = Character.toUpperCase(e.getKeyChar());
				
				if (snake.isMultiplayer()) {//scegli a quale giocatore assegnare i tasti, singleplayer, tutti a p1, 
					switch (e.getKeyCode()) {//multiplayer WASD a p1, IJKL e le frecciette a p2
					case KeyEvent.VK_DOWN: {
						c = 'K';
					}
						break;
					case KeyEvent.VK_UP: {
						c = 'I';
					}
						break;
					case KeyEvent.VK_LEFT: {
						c = 'J';
					}
						break;
					case KeyEvent.VK_RIGHT: {
						c = 'L';
					}
						break;
					default:
						break;
					}
				} else {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_DOWN:
					case 'K': {
						c = 'S';
					}
						break;
					case KeyEvent.VK_UP:
					case 'I': {
						c = 'W';
					}
						break;
					case KeyEvent.VK_LEFT:
					case 'J': {
						c = 'A';
					}
						break;
					case KeyEvent.VK_RIGHT:
					case 'L': {
						c = 'D';
					}
						break;
					default:
						break;
					}
				}
				if (c == 'W' || c == 'A' || c == 'S' || c == 'D') {
					direction = c;
				}
				if (c == 'I' || c == 'J' || c == 'K' || c == 'L') {
					switch (c) {
					case 'I':
						c = 'W';
						break;
					case 'J':
						c = 'A';
						break;
					case 'K':
						c = 'S';
						break;
					case 'L':
						c = 'D';
						break;
					}
					directionP2 = c;
				}
			}
		});
		timer = new Timer(50, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==menu.getBtnPlay()) {
		    // imposta parametri principali
		    if(menu.getRdbt2Player().isSelected()) {
		        snake.setMultiplayer(true);
		        frame.getPanel().getLblPunti().setVisible(false);
		    } else {
		    	frame.getPanel().getLblPunti().setVisible(true);
		        snake.setMultiplayer(false);
		    }
		    snake.setVelocita(Integer.valueOf((String)menu.getComboBoxVel().getSelectedItem()));
		    timer.setDelay(snake.getVelocita());//regola velocità di gioco
		    
		    // Set field and cell size before snake creation
		    try {
		        String str = menu.getAreaField().getText();
		        int n = Integer.valueOf(str.replaceAll("[^0-9]", ""));
		        snake.setFieldSize(n > 40 || n < 10 ? 40 : n);
		    } catch (NumberFormatException e1) {
		        snake.setFieldSize(40);
		    }
		    try {
		        String str = menu.getCellField().getText();
		        int n = Integer.valueOf(str.replaceAll("[^0-9]", ""));
		        snake.setCellSize(n > 40 || n < 10 ? 40 : n);
		    } catch (NumberFormatException e1) {
		        snake.setCellSize(40);
		    }

		    // inizializza il serpente
		    snake.reset();
		    snake.setLunghezzaIniziale(Integer.valueOf((String)menu.getComboBox().getSelectedItem()));
		    snake.setStart(true);
		    
		    // aggiorna GUI
		    frame.getPanel().updatePrefSize();
		    frame.pack();
		    frame.getPanel().setFocusable(true);
		    frame.getPanel().requestFocusInWindow();
		    menu.setVisible(false);
		    frame.setVisible(true);
		    
		    // genera la mela/e
		    snake.genApple();
		    frame.getPanel().getBtnRestart().setVisible(false);
		    direction = ' ';
		    directionP2 = ' ';
		    timer.restart();
		} else if (e.getSource() == frame.getPanel().getBtnRestart()) {
			menu.setVisible(true);
			frame.setVisible(false);
			frame.getPanel().getLblGameOver().setVisible(false);
			frame.getPanel().getBtnRestart().setVisible(false);
			frame.getPanel().getLblPunti().setText("SCORE: " + 0);
		} else {
			//inizio logica del gioco
			snake.move(direction, directionP2);
			frame.getContentPane().repaint();
			if (snake.appleCollision()) {
				int eaten = snake.getLastEaten();
				int[] nIMG = frame.getPanel().getNIMG();
					if(eaten==0||eaten==2) {
						nIMG[0] = (int)(Math.random()+0.5);
					}
					else {
						nIMG[1] = (int)(Math.random()+0.5);
					}
				frame.getPanel().getLblPunti().setText("SCORE: " + (snake.getPunteggio(0) + snake.getPunteggio(1)));
			}
			int n = snake.controlloConflittoCorpo();//restituisce il numero del giocatore che perde,0 se è pareggio

			if (snake.isGiocoFinito()) {
				//messaggi di vincita
				frame.getPanel().getLblGameOver().setVisible(true);
				frame.getPanel().getBtnRestart().setVisible(true);
				if (snake.isMultiplayer()) {
					switch (n) {
					case 1: {
						frame.getPanel().getLblGameOver().setText("<html><center>VINCE GIOCATORE 2<center></html>");
					}
						break;
					case 2: {
						frame.getPanel().getLblGameOver().setText("<html><center>VINCE GIOCATORE 1<center></html>");
					}
						break;
					case 0: {
						frame.getPanel().getLblGameOver().setText("PAREGGIO");
					}
						break;
					}

				} else {
					System.out.println("Game Over");
				}
				timer.stop();
			}

		}

	}

}
