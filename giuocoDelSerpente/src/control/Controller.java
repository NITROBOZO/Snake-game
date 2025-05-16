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
	public SnakeModel snake;
	private MyFrame frame;
	public Controller() {
		snake=new SnakeModel(true);
		menu= new Menu();
		frame=new MyFrame(snake);
		menu.setVisible(true);
		frame.setVisible(false);
		menu.getBtnPlay().addActionListener(this);
		menu.getCellField().addActionListener(this);
		frame.getPanel().getBtnRestart().addActionListener(this);
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
	        public void keyPressed(KeyEvent e) {
				char c = Character.toUpperCase(e.getKeyChar());
				switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN:{
					c='K';
				}break;
				case KeyEvent.VK_UP:{
					c='I';
				}break;
				case KeyEvent.VK_LEFT:{
					c='J';
				}break;
				case KeyEvent.VK_RIGHT:{
					c='L';
				}break;
				default:
					break;
				}
					if(c=='W'||c=='A'||c=='S'||c=='D') {
						direction = c;
					}
					else if(c=='I'||c=='J'||c=='K'||c=='L') {
						directionP2 = c;
					}
				}
	        });
		snake.genApple();
		timer = new Timer(50, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menu.getBtnPlay()) {
			snake.setLunghezza(Integer.valueOf((String)menu.getComboBox().getSelectedItem()));
			snake.setVelocita(Integer.valueOf((String)menu.getComboBoxVel().getSelectedItem()));
			timer.setDelay(snake.getVelocita()); 
			frame.getPanel().setFocusable(true);
			frame.getPanel().requestFocusInWindow();
			menu.setVisible(false); 
			frame.setVisible(true);
			snake.setStart(true);
			frame.getPanel().getBtnRestart().setVisible(false);
			direction=' ';
			directionP2=' ';
			timer.restart();
		}
		else if(e.getSource() == frame.getPanel().getBtnRestart())
		{
			snake.restart();
			menu.setVisible(true); 
			frame.setVisible(false);
			frame.getPanel().getLblGameOver().setVisible(false);
			frame.getPanel().getBtnRestart().setVisible(false);
			frame.getPanel().getLblPunti().setText("SCORE: "+0);
		}
		else if(e.getSource()==menu.getCellField()) {
			int n=Integer.valueOf(menu.getCellField().getText().replaceAll("[^0-9]", ""));
			
			
		}
		else{
			snake.move(direction,directionP2);
			frame.getContentPane().repaint();
			if(snake.appleCollision()) {
			frame.getPanel().getLblPunti().setText("SCORE: "+snake.getPunteggio());
			}
		    snake.controlloConflittoCorpo();
		    
		    if (snake.isGiocoFinito()) {
				frame.getPanel().getLblGameOver().setVisible(true);
				frame.getPanel().getBtnRestart().setVisible(true);
				System.out.println("Game Over");
				timer.stop();
			}
			
		}
		
		
	}
	
	}

	    
