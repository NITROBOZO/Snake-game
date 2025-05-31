package control;

import java.awt.Dimension;
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
import view.AudioPlayer;
import view.Menu;

public class Controller implements ActionListener {
	private AudioPlayer bgm;
	private AudioPlayer appleSfx;
	private ControllerInput controller;
	private Timer timer;
	private Menu menu;
	private int direction;
	private int directionP2;
	public SnakeMultiplayer snake;
	private MyFrame frame;
	private int key;

	public Controller() throws IOException {
		bgm = new AudioPlayer("/sfx/bgm.wav");
		appleSfx = new AudioPlayer("/sfx/eat.wav");
		controller = new ControllerInput();
		bgm.play();
		key = 255;
		direction = 255;
		directionP2 = 255;
		controller.start();
		snake = new SnakeMultiplayer();
		menu = new Menu();
		frame = new MyFrame(snake);
		menu.setVisible(true);
		snake.setWalls(true);
		frame.dispose();
		menu.getBtnColori().addActionListener(this);
		menu.getBtnPlay().addActionListener(this);
		frame.getPanel().getBtnRestart().addActionListener(this);
		snake.setLunghezzaIniziale(1);
		initFiles();
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				key = e.getKeyCode();
				/* gestione input */
				keyHandler();
			}
		});
		timer = new Timer(50, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* gestione input */
		padHandler();
		if (e.getSource() == menu.getBtnPlay()) {
			direction = 255;
			directionP2 = 255;
			// gestione variabili di gioco, posizionamento del campo di gioco e impostazione
			// della risoluzione
			snakeSetup();
			start();
		} else if (e.getSource() == frame.getPanel().getBtnRestart()) {
			restartPane();
		} else {
			// inizio logica del gioco
			update();
		}

	}

	// aggiorna serpente
	private void update() {
		snake.move(direction, directionP2);
		frame.getContentPane().repaint();
		if (snake.appleCollision()) {
			appleSfx.playSfx();
			int eaten = snake.getLastEaten();
			int[] nIMG = frame.getPanel().getNIMG();
			if (eaten == 0 || eaten == 2) {// immagine casuale per la mela
				nIMG[0] = (int) (Math.random() + 0.5);
			} else {
				nIMG[1] = (int) (Math.random() + 0.5);
			}
			frame.getPanel().getLblPunti().setText("SCORE: " + snake.getPunteggio(0));

		}
		int cPT = snake.getPunteggio(0);
		editMsg(cPT);
	}

	private void restartPane() {// reimposta la finestra di gioco
		menu.setVisible(true);
		frame.dispose();
		frame.getPanel().getLblGameOver().setVisible(false);
		frame.getPanel().getBtnRestart().setVisible(false);
		frame.getPanel().getLblPunti().setText("SCORE: " + 0);
	}

	/* gestione input */
	private void keyHandler() {
		if (snake.isMultiplayer()) {
			switch (key) {
			case KeyEvent.VK_DOWN: {
				key = KeyEvent.VK_K;
			}
				break;
			case KeyEvent.VK_UP: {
				key = KeyEvent.VK_I;
			}
				break;
			case KeyEvent.VK_LEFT: {
				key = KeyEvent.VK_J;
			}
				break;
			case KeyEvent.VK_RIGHT: {
				key = KeyEvent.VK_L;
				;
			}
				break;
			default:
				break;
			}
		} else {
			switch (key) {
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_K: {
				key = KeyEvent.VK_S;
			}
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_I: {
				key = KeyEvent.VK_W;
			}
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_J: {
				key = KeyEvent.VK_A;
			}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_L: {
				key = KeyEvent.VK_D;
			}
				break;
			default:
				break;
			}
		}
		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_A:
		case KeyEvent.VK_S:
		case KeyEvent.VK_D:
			direction = key;
			break;
		}

		if (key == KeyEvent.VK_I || key == KeyEvent.VK_J || key == KeyEvent.VK_K || key == KeyEvent.VK_L) {
			switch (key) {
			case KeyEvent.VK_I:
				key = KeyEvent.VK_W;
				break;
			case KeyEvent.VK_J:
				key = KeyEvent.VK_A;
				break;
			case KeyEvent.VK_K:
				key = KeyEvent.VK_S;
				break;
			case KeyEvent.VK_L:
				key = KeyEvent.VK_D;
				break;

			}
			directionP2 = key;

		}
	}

	private void padHandler() {
		if (snake.isMultiplayer()) {
			if (controller.isP1DpadDown()) {
				directionP2 = KeyEvent.VK_S;
			}
			if (controller.isP1DpadUp()) {
				directionP2 = KeyEvent.VK_W;
			}
			if (controller.isP1DpadLeft()) {
				directionP2 = KeyEvent.VK_A;
			}
			if (controller.isP1DpadRight()) {
				directionP2 = KeyEvent.VK_D;
			}

		} else {
			if (controller.isP1DpadDown()) {
				direction = KeyEvent.VK_S;
			}
			if (controller.isP1DpadUp()) {
				direction = KeyEvent.VK_W;
			}
			if (controller.isP1DpadLeft()) {
				direction = KeyEvent.VK_A;
			}
			if (controller.isP1DpadRight()) {
				direction = KeyEvent.VK_D;
			}
		}
	}

	private void start() {
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
		timer.restart();
	}

	private void snakeSetup() {// setup finestra e attributi
		snake.setWalls(menu.getBoxMuri().isSelected());
		frame.getPanel().setGrid(menu.getChckbxGrid().isSelected());
		snake.setCellSize(
				(int) (menu.getSelectedResolution().y / 1.05) / (Integer.valueOf(menu.getAreaField().getText()) + 1));
		int yOffset = 0;
		frame.setUndecorated(true);
		if (menu.getChckbxFullscreen().isSelected()) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			yOffset = (int) (screenSize.height / 30);
			frame.setAlwaysOnTop(true);
			frame.setUndecorated(true);
			frame.setPreferredSize(screenSize);

			if (menu.getRdbt1Player().isSelected()) {
				frame.getPanel().setPoint(new Point((int) (screenSize.width / 4.3), yOffset));
			} else {
				yOffset = yOffset - snake.getCellSize() / 12;
				frame.getPanel().setPoint(new Point(screenSize.width / 20, yOffset));
			}

		} else {
			frame.setAlwaysOnTop(false);
			frame.setUndecorated(false);
			int yD = (int) (menu.getSelectedResolution().y / 0.9755);
			yOffset = (int) (menu.getSelectedResolution().y / 50);
			if (menu.getRdbt2Player().isSelected()) {
				frame.getPanel().setPoint(new Point((int) (menu.getSelectedResolution().x / 30), yOffset));
				frame.setPreferredSize(new Dimension((int) (menu.getSelectedResolution().x / 1.05), yD));
			} else {
				frame.getPanel().setPoint(new Point((int) (menu.getSelectedResolution().x / 80), yOffset));
				frame.setPreferredSize(new Dimension((int) (menu.getSelectedResolution().x / 1.78), yD));
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
		try {
			String str = menu.getAreaField().getText();
			int n = Integer.valueOf(str.replaceAll("[^0-9]", ""));
			snake.setFieldSize(n > 60 || n < 10 ? 40 : n);
		} catch (NumberFormatException e1) {
			snake.setFieldSize(40);
		}
		// inizializza il serpente
		snake.reset();

	}

	private void editMsg(int cPT) {
		int n = snake.controlloConflittoCorpo();// restituisce il numero del giocatore che perde,0 se è pareggio, ho
												// dovuto salvarlo poichè cambiava il valore da solo
		if (snake.isGiocoFinito()) {
			timer.stop();
			// messaggi di vincita
			frame.getPanel().getLblGameOver().setVisible(true);
			frame.getPanel().getBtnRestart().setVisible(true);
			if (snake.isMultiplayer()) {
				/* parte di salvataggio delle statistiche partita */
				saveAndUpdateM(menu.getTextFieldN1().getText(), menu.getTextFieldN2().getText(), n);

				switch (n) {// messaggio di vincita
				case 1: {
					frame.getPanel().getLblGameOver()
							.setText("<html><center>VINCE " + menu.getTextFieldN2().getText() + "<center></html>");
				}
					break;
				case 2: {
					frame.getPanel().getLblGameOver()
							.setText("<html><center>VINCE " + menu.getTextFieldN1().getText() + "<center></html>");
				}
					break;
				case 0: {
					frame.getPanel().getLblGameOver().setText("PAREGGIO");
				}
					break;
				}
			} else {
				try {
					// messaggio di vincita solo se è un nuovo record
					if (Integer.valueOf(ScoreSaver.get(false).get(0)[3]) < cPT) {
						frame.getPanel().getLblGameOver().setText("NUOVO RECORD!");
					} else {
						frame.getPanel().getLblGameOver().setText("GAME OVER");
					}

					saveAndUpdateS(menu.getTextFieldN1().getText(), snake.getLunghezzaInit(),cPT, snake.getFieldSize(),
							menu.getComboBoxVel().getSelectedIndex()+1, snake.isWalls());
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();

				}
			}
		}
	}

	private void updateS() {// aggiorna record nel menu
		try {
			ArrayList<String[]> ptS;
			ptS = ScoreSaver.get(false);
			String full = "";
			for (String[] strs : ptS) {
				full += strs[0] + " score: " + strs[3] + " vel: " + strs[2] + " w: " + strs[4] + "\nL init: " + strs[1]
						+ " area: " + strs[5] + "\n";
				menu.getTextAreaS().setText(full);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateM() {
		try {
			ArrayList<String[]> ptS = ScoreSaver.get(true);
			;
			String full = "";
			for (String[] strs : ptS) {
				full += strs[0] + " VS " + strs[1] + " -> " + strs[2] + "\n";
				menu.getTextAreaM().setText(full);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// salva il punteggio con le statistiche della partita necessarie
	private void saveAndUpdateS(String str, int l, int c, int fSize, int vel, boolean w) {
		try {

			ScoreSaver.salvaS(str, l, c, fSize, vel, w);
			updateS();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void saveAndUpdateM(String p1, String p2, int n) {
		try {
			ScoreSaver.salvaM(p1, p2, n);
			updateM();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// inizializza file
	private void initFiles() {
		try {
			if (!ScoreSaver.hasFileM()) {
				ScoreSaver.salvaM("P1", "P2", 2);
			}
			if (!ScoreSaver.hasFileS()) {
				ScoreSaver.salvaS("Player", 3, 5, 20, 4, true);
			}
			for (int i = 0; i < 2; i++) {
				if (i == 0) {
					updateS();
				} else {
					updateM();
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
