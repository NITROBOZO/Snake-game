package control;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

import model.SnakeModel;
import view.MyFrame;
import view.Menu;
public class Controller implements ActionListener {
	public static void main(String[] args) {
		System.out.println("Hello World");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller frame = new Controller();
					System.out.println(""+frame.snake.getCoordinate(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private Timer timer;
	private Menu menu;
	private char direction = ' ';
	public SnakeModel snake;
	private MyFrame frame;
	public Controller() {
		snake=new SnakeModel();
		menu= new Menu();
		frame=new MyFrame(snake);
		menu.setVisible(true);
		frame.setVisible(false);
		menu.getBtnPlay().addActionListener(this);
		frame.getPanel().getBtnRestart().addActionListener(this);
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent e) {
				char c = Character.toUpperCase(e.getKeyChar());
				if(c=='W'||c=='A'||c=='S'||c=='D') {
					direction = c;
				}
	        }
		});
		snake.genApple();
		timer = new Timer(50, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu.getBtnPlay()) {
			frame.getPanel().setFocusable(true);
			frame.getPanel().requestFocusInWindow();
			menu.setVisible(false); 
			frame.setVisible(true);
			snake.setStart(true);
			timer.restart();
			frame.getPanel().getBtnRestart().setVisible(false);
		}
		else if(e.getSource() == frame.getPanel().getBtnRestart())
		{
			snake.restart();
			menu.setVisible(true); 
			frame.setVisible(false);
			frame.getPanel().getLblGameOver().setVisible(false);
			frame.getPanel().getBtnRestart().setVisible(false);
		}
		else{
			snake.move(direction);
			frame.getContentPane().repaint();
			if(snake.appleCollision()) {
			frame.getPanel().getLblPunti().setText("SCORE: "+snake.getPunteggio());
			}
		    snake.controlloConflittoCorpo();
		    
		    if (snake.isGiocoFinito()) {
				frame.getPanel().getLblGameOver().setVisible(true);
				frame.getPanel().getBtnRestart().setVisible(true);
				System.out.println("Game Over");
				direction = ' ';
				timer.stop();
			}
			
		}
		
		
	}
	
	}

	    
