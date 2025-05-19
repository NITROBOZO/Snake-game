package model;

import java.awt.Point;
public class SnakeMultiplayer {
	/*classe per gestire la logica tra serpenti, mele
	 *e le variabili di gioco principali
  	 */
	static public final int VEL_LIMIT = 6;
	private Apple[] apples;
	private Snake[] snakes;
	private boolean giocoFinito;
	private int cellSize;
	private int lunghezzaInit = 1;
	private int fieldSize;
	private boolean start;
	private int velocita;
	private boolean multiplayer;
	private int lastEaten;

	public SnakeMultiplayer () {
		lastEaten = 2;
		apples = new Apple[2];
		snakes = new Snake[2];
		
		for (int i = 0; i < 2; i++) {
			apples[i] = new Apple();
			snakes[i] = new Snake();
		}
		
		this.giocoFinito=false;
		this.start=false;
	}
	
	public Point getApplePos (int i) {
		return apples[i].getApplePos();
	}
	
	public boolean isMultiplayer () {
		return multiplayer;
	}
	
	public int getLunghezzaInit () {
		return lunghezzaInit;
	}
	
	public void setLunghezzaIniziale (int lunghezzaInit) {
		this.lunghezzaInit = lunghezzaInit;
		
		if (multiplayer) {
			snakes[0].setLunghezzaIniziale(lunghezzaInit, cellSize, fieldSize,false);
			snakes[1].setLunghezzaIniziale(lunghezzaInit, cellSize, fieldSize,true);
		}
		else {
			snakes[0].setLunghezzaIniziale(lunghezzaInit, cellSize, fieldSize, false);
		}
	}
	
	public void setMultiplayer (boolean multiplayer) {
		this.multiplayer = multiplayer;
	}
	
	public boolean isStart () {
		return this.start;
	}

	public void setStart (boolean start) {
		this.start = start;
	}
	
	public int getCellSize () {
		return cellSize;
	}
	

	public int getFieldSize () {
		return fieldSize;
	}

	public void setFieldSize (int fieldSize) {
		this.fieldSize = fieldSize;
	}

	public void setCellSize (int cellSize) {
		this.cellSize = cellSize;
	}
	public void setVelocita (int n) {
		this.velocita=(VEL_LIMIT-n)*25;
	}
	public int getVelocita () {
		return this.velocita;
	}

	public int getLunghezza (int i) {
		return snakes[i].getLunghezza();
	}
	
	public boolean isGiocoFinito () {
		return giocoFinito;
	}

	// GESTIONE COLLISIONE CON LA MELA
	public boolean appleCollision() {
		if (multiplayer) {
			//P1 tocca la mela
			if (snakes[0].isAppleCollision(apples[0].getApplePos()) || snakes[0].isAppleCollision(apples[1].getApplePos())) {
				snakes[0].incrementaPt();
				snakes[0].incrementaLunghezza();
				
				//controllo per capire quale delle 2 mele ha toccato
				if (snakes[0].isAppleCollision(apples[0].getApplePos())) {
					lastEaten=0;
					apples[0].genApple(fieldSize, cellSize, multiplayer);
				}
				else {
					lastEaten=1;
					apples[1].genApple(fieldSize, cellSize, multiplayer);
				}
				return true;
				
			}
			//P2 tocca la mela
			else if (snakes[1].isAppleCollision(apples[0].getApplePos()) || snakes[1].isAppleCollision(apples[1].getApplePos())) {
				snakes[1].incrementaPt();
				snakes[1].incrementaLunghezza();
				
				//controllo per capire quale delle 2 mele ha toccato
				if(snakes[1].isAppleCollision(apples[0].getApplePos())) {
					lastEaten = 0;
					apples[0].genApple(fieldSize, cellSize, multiplayer);
				}
				else {
					lastEaten = 1;
					apples[1].genApple(fieldSize, cellSize, multiplayer);
				}
				
				return true;
			}
			
		}
		//controllo se P1 tocca la mela
		else if (snakes[0].isAppleCollision(apples[0].getApplePos())) {
			lastEaten = 0;
			snakes[0].incrementaPt();
			snakes[0].incrementaLunghezza();
			apples[0].genApple(fieldSize, cellSize, multiplayer);
			
			return true;
		}
		
		return false;
	}
	
	public boolean isAppleCollision () {
		//gestione collisioni con mela
		if (multiplayer) {
			if (snakes[0].isAppleCollision(apples[0].getApplePos()) || snakes[0].isAppleCollision(apples[1].getApplePos())) {//P1 tocca la mela
				return true;
			}
			else if (snakes[1].isAppleCollision(apples[0].getApplePos()) || snakes[1].isAppleCollision(apples[1].getApplePos())) {//P2 tocca la mela
				return true;
			}
		}

		//controllo se P1 tocca la mela
		else if (snakes[0].isAppleCollision(apples[0].getApplePos())) {
			return true;
		}
		
		return false;
	}
		
	public int getLastEaten () {
		return lastEaten;
	}
	
	public int controlloConflittoCorpo()
	{
		try
		{	
			Point head1 = snakes[0].getCoordinate(0);
			if (multiplayer) {
				Point head2 = snakes[1].getCoordinate(0);
				if (head1.equals(head2)) {
		            		giocoFinito = true;
		            		return 0; //P1 colpisce P2 e viceversa
		        	}
				
				for (int i = 1; i < snakes[1].getLunghezza(); i++) {
		            		if (snakes[1].getCoordinate(i).equals(head1)) {
		                		giocoFinito = true;
		                		return 1; // P1 colpisce P2
		            		}
		        	}
				
				for (int i = 1; i < snakes[1].getLunghezza(); i++) {
		            		if (snakes[1].getCoordinate(i).equals(head2)) {
		                		giocoFinito = true;
		                		return 2; // P2 si colpisce da solo
		           		}
		        	}
				
				for (int i = 1; i < snakes[0].getLunghezza(); i++) {
		            		if (snakes[0].getCoordinate(i).equals(head2)) {
		                		giocoFinito = true;
		                		return 2; // P2 colpisce P1
		            		}
		        	}
			}
	        

	        	for (int i = 1; i < snakes[0].getLunghezza(); i++) {
	            		if (snakes[0].getCoordinate(i).equals(head1)) {
	                		giocoFinito = true;
	                		return 1; // P1 si colpisce da solo
	            		}
	        	}
	        
	        	return 3; // nessuna collisione
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public void move(char c, char c2) {//movimento
		snakes[0].move(c, fieldSize, cellSize, multiplayer);
		snakes[1].move(c2, fieldSize, cellSize, multiplayer);
	}
	
	public void genApple() {
		apples[0].genApple(fieldSize, cellSize, multiplayer);
		if (multiplayer) {
			apples[1].genApple(fieldSize, cellSize, multiplayer);
		}
	}
	
	public int getPunteggio(int i) {
		return snakes[i].getPunteggio();
	}
	
	public Point getCoordinate(int i, int corpo) {
		return snakes[i].getCoordinate(corpo);
	}
	
	public void reset() {
		lastEaten = 2;
		apples[0].init();
		apples[1].init();
		snakes[0].init();
		snakes[1].init();
		this.giocoFinito = false;
		this.start = false;
		}


}
