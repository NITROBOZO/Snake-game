package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import model.ScoreSaver;
import model.SnakeMultiplayer;
import view.MyFrame;
import view.Menu;

public class Controller implements ActionListener {


	private ControllerInput controller = new ControllerInput();
	private Timer timer;
	private Menu menu;
	private char direction = ' ';
	private char directionP2 = ' ';
	public SnakeMultiplayer snake;
	private MyFrame frame;
	public Controller() throws IOException {
		controller.start();
		snake = new SnakeMultiplayer();
		menu = new Menu();
		frame = new MyFrame(snake);
		menu.setVisible(true);
		frame.dispose();
		menu.getBtnColori().addActionListener(this);
		menu.getBtnPlay().addActionListener(this);
		frame.getPanel().getBtnRestart().addActionListener(this);
		snake.setLunghezzaIniziale(1);
		try {
			if(!ScoreSaver.hasFileM()) {
			ScoreSaver.salvaM("P1","P2",2);
			}
			ArrayList<String[]> ptS = ScoreSaver.get(true);
			System.out.println(ptS.size());
			String full ="";
			for(String[] strs : ptS) {
				full += strs[0]+" VS "+strs[1]+" -> "+strs[2]+"\n";
				menu.getTextAreaM().setText(full);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(!ScoreSaver.hasFileS()) {
			ScoreSaver.salvaS("SIGMA", 4, 6, 6);
			}
			ArrayList<String[]> ptS = ScoreSaver.get(false);
			System.out.println(ptS.size());
			String full ="";
			for(String[] strs : ptS) {
				full += strs[0]+" score: "+strs[2]+"\nLiniziale: "+strs[1]+" area: "+strs[3]+"\n";
				menu.getTextAreaS().setText(full);
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = Character.toUpperCase(e.getKeyChar());

				if (snake.isMultiplayer()) {
					switch (e.getKeyCode()) {// multiplayer WASD a p1, IJKL e le frecciette a p2
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
		if (snake.isMultiplayer()) {
			if (controller.isP1DpadDown()) {
				directionP2 = 'S';
			}
			if (controller.isP1DpadUp()) {
				directionP2 = 'W';
			}
			if (controller.isP1DpadLeft()) {
				directionP2 = 'A';
			}
			if (controller.isP1DpadRight()) {
				directionP2 = 'D';
			}
		} else {
			if (controller.isP1DpadDown()) {
				direction = 'S';
			}
			if (controller.isP1DpadUp()) {
				direction = 'W';
			}
			if (controller.isP1DpadLeft()) {
				direction = 'A';
			}
			if (controller.isP1DpadRight()) {
				direction = 'D';
			}
		}

		if (e.getSource() == menu.getBtnPlay()) {
			snake.setCellSize((int) menu.getSelectedResolution().y/(Integer.valueOf(menu.getAreaField().getText())+1));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int yOffset = (int)(screenSize.height/60);
			frame.setUndecorated(true);
			if(menu.getChckbxFullscreen().isSelected()) {
				
				frame.setPreferredSize(screenSize);
				
				if(menu.getRdbt1Player().isSelected()) {
					frame.getPanel().setPoint(new Point(screenSize.width/5,yOffset));
				}
				else {
					yOffset=yOffset-snake.getCellSize()/2;
					frame.getPanel().setPoint(new Point(screenSize.width/30,yOffset));
					frame.getPanel().setPoint(new Point(screenSize.width/29,yOffset));
				}
				
			}
			else {
				if(menu.getRdbt2Player().isSelected()) {
					frame.getPanel().setPoint(new Point((int)(menu.getSelectedResolution().x/85),yOffset));
					frame.setPreferredSize(new Dimension((int)(menu.getSelectedResolution().x/1.05),menu.getSelectedResolution().y));
				}
				else {
					frame.setPreferredSize(new Dimension((int)(menu.getSelectedResolution().x/1.79),(int)(menu.getSelectedResolution().y/1.01)));
				}
				
				
			}
			
			
			frame.getPanel().setColors(menu.getColori());
			frame.getPanel().getLblGameOver().setText("");
			// imposta parametri principali
			if (menu.getRdbt2Player().isSelected()) {
				snake.setMultiplayer(true);
				frame.getPanel().getLblPunti().setVisible(false);
			} else {
				frame.getPanel().getLblPunti().setVisible(true);
				snake.setMultiplayer(false);
			}
			snake.setVelocita(Integer.valueOf((String) menu.getComboBoxVel().getSelectedItem()));
			timer.setDelay(snake.getVelocita());// regola velocità di gioco

			// Set field and cell size before snake creation
			try {
				String str = menu.getAreaField().getText();
				int n = Integer.valueOf(str.replaceAll("[^0-9]", ""));
				snake.setFieldSize(n > 120 || n < 10 ? 40 : n);
			} catch (NumberFormatException e1) {
				snake.setFieldSize(40);
			}
				
			

			// inizializza il serpente
			snake.reset();
			snake.setLunghezzaIniziale(Integer.valueOf((String) menu.getComboBox().getSelectedItem()));
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
			frame.dispose();
			frame.getPanel().getLblGameOver().setVisible(false);
			frame.getPanel().getBtnRestart().setVisible(false);
			frame.getPanel().getLblPunti().setText("SCORE: " + 0);
		} else {
			// inizio logica del gioco
			snake.move(direction, directionP2);
			frame.getContentPane().repaint();
			if (snake.appleCollision()) {
				int eaten = snake.getLastEaten();
				int[] nIMG = frame.getPanel().getNIMG();
				if (eaten == 0 || eaten == 2) {
					nIMG[0] = (int) (Math.random() + 0.5);
				} else {
					nIMG[1] = (int) (Math.random() + 0.5);
				}
				frame.getPanel().getLblPunti().setText("SCORE: " + snake.getPunteggio(0));
			}
			int n = snake.controlloConflittoCorpo();
			int cPT=snake.getPunteggio(0);// restituisce il numero del giocatore che perde,0 se è pareggio
			if (snake.isGiocoFinito()) {
				timer.stop();
				// messaggi di vincita
				frame.getPanel().getLblGameOver().setVisible(true);
				frame.getPanel().getBtnRestart().setVisible(true);
				if (snake.isMultiplayer()) {
					try {
						ScoreSaver.salvaM(menu.getTextFieldN1().getText(),menu.getTextFieldN2().getText(),n);
						ArrayList<String[]> ptS = ScoreSaver.get(true);
						System.out.println(ptS.size());
						String full ="";
						for(String[] strs : ptS) {
							full += strs[0]+" VS "+strs[1]+" -> "+strs[2]+"\n";
							menu.getTextAreaM().setText(full);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					switch (n) {
					case 1: {
						frame.getPanel().getLblGameOver().setText("<html><center>VINCE "+menu.getTextFieldN2().getText()+"<center></html>");
					}
						break;
					case 2: {
						frame.getPanel().getLblGameOver().setText("<html><center>VINCE "+menu.getTextFieldN1().getText()+"<center></html>");
					}
						break;
					case 0: {
						frame.getPanel().getLblGameOver().setText("PAREGGIO");
					}
						break;
					}
				} else {
					try {
						
						if(Integer.valueOf(ScoreSaver.get(false).get(0)[2])<cPT) {
							frame.getPanel().getLblGameOver().setText("NUOVO RECORD!");
						}
						else {
							frame.getPanel().getLblGameOver().setText("GAME OVER");
						}
					} catch (NumberFormatException | IOException e1) {
						e1.printStackTrace();
					}
					try {
						ScoreSaver.salvaS(menu.getTextFieldN1().getText(), snake.getLunghezzaInit(), cPT, snake.getFieldSize());
						ArrayList<String[]> ptS = ScoreSaver.get(false);
						System.out.println(ptS.size());
						String full ="";
						for(String[] strs : ptS) {
							full += strs[0]+" score: "+strs[2]+"\nLiniziale: "+strs[1]+" area: "+strs[3]+"\n";
							menu.getTextAreaS().setText(full);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
				
				}
		}
	}
}
		
				

		


