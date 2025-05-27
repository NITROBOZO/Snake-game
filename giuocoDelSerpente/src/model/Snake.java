package model;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Snake {
	public static final double MPCONST = 1.7;
	private ArrayList<Point> coordinateCorpo;
	private int punteggio;
	private int lastKey;
	
	public Snake ()
	{	
		lastKey = 255;
		this.coordinateCorpo = new ArrayList<Point>();
		this.punteggio = 0;
	}

	// OMPOSTA LUNGHEZZA E POSIZIONE IN BASE AL PLAYER
	public void setLunghezzaIniziale (int n,int cellSize,int fieldSize,boolean p2) {
		this.lastKey = 255;
		this.coordinateCorpo.clear();
		this.coordinateCorpo.trimToSize();
		
		if(!p2) {
			for (int i = 0; i < n; i++) {
				this.coordinateCorpo.add(new Point(i * cellSize, 0));
				
			}
			Collections.reverse(coordinateCorpo);
		}
		else {
			for (int i = (((int)(fieldSize * 1.7 + 1) - n)); i < ((int)(fieldSize * 1.7) + 1); i++) {
				this.coordinateCorpo.add(new Point(i * cellSize, 0));
				
			}
		}
	}

	public void init ()
	{
		this.punteggio=0;
	}

	public Point getCoordinate (int i) {//ritorna un punto specifico
		return this.coordinateCorpo.get(i);
	}
	
	public int getLunghezza () {//lunghezza serpente
		return this.coordinateCorpo.size();
	}

	public boolean addPunto (int x, int y) {
		try {
			this.coordinateCorpo.add(new Point(x, y));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addPunto(Point punto) {
		try {
			this.coordinateCorpo.add(punto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void move (int c, int fieldSize, int cellSize, boolean m,boolean walls) {
			if(c != 255) {
				for (int i = this.coordinateCorpo.size() - 1; i > 0; i--) {//sposta il corpo del serpente dietro la testa
					Point punto = this.coordinateCorpo.get(i - 1);
					this.coordinateCorpo.get(i).setLocation(punto.x, punto.y);
				}
			}
		
			int xSize = fieldSize;//per modalità multiplayer, estende la direzione orizzontale
		
			if (m) {
				xSize= (int)(xSize * MPCONST);
			}

			if ((c == KeyEvent.VK_W && this.lastKey != KeyEvent.VK_S) || 
					(c == KeyEvent.VK_S && this.lastKey != KeyEvent.VK_W) || 
					(c == KeyEvent.VK_A && this.lastKey != KeyEvent.VK_D) || 
					(c == KeyEvent.VK_D && this.lastKey != KeyEvent.VK_A) || 
					this.lastKey == 255) {
				this.lastKey = c;
			}
			if(walls) {
				switch (this.lastKey) {
				case KeyEvent.VK_W: {
					if (this.coordinateCorpo.get(0).y != 0 ) {
						this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y - cellSize;
					}
					else {
						this.coordinateCorpo.get(0).y = fieldSize * cellSize;
					}
					break;
				}
				case KeyEvent.VK_A: {
					if (this.coordinateCorpo.get(0).x != 0 ) {
						this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x - cellSize;
					}
					else {
						this.coordinateCorpo.get(0).x = xSize * cellSize;
					}
					break;
				}
				case KeyEvent.VK_S: {
					if (this.coordinateCorpo.get(0).y != fieldSize * cellSize) {
						this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y + cellSize;
					}
					else {
						this.coordinateCorpo.get(0).y = 0;
					}
					break;
				}
				case KeyEvent.VK_D: {
					if (this.coordinateCorpo.get(0).x != xSize * cellSize ) {
						this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x + cellSize;
					}
					else {
						this.coordinateCorpo.get(0).x = 0;
					}
					break;
				}
				}
			}
			else {
			switch (this.lastKey) {
			case KeyEvent.VK_W: {
				if (this.coordinateCorpo.get(0).y != 0 ) {
					this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y - cellSize;
				}
				break;
			}
			case KeyEvent.VK_A: {
				if (this.coordinateCorpo.get(0).x != 0 ) {
					this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x - cellSize;
				}
				break;
			}
			case KeyEvent.VK_S: {
				if (this.coordinateCorpo.get(0).y != fieldSize * cellSize) {
					this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y + cellSize;
				}
				break;
			}
			case KeyEvent.VK_D: {
				if (this.coordinateCorpo.get(0).x != xSize * cellSize ) {
					this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x + cellSize;
				}
				break;
			}
			}
			}
		}

	
	public boolean isAppleCollision (Point apple) {
		if(this.coordinateCorpo.get(0).equals(apple)) {
			return true;
		}
		return false;
	}
	
	public int getPunteggio () {
		return punteggio;
	}
	
	public void incrementaPt () {
		punteggio++;
	}
	
	public void incrementaLunghezza() {
		this.coordinateCorpo.add(new Point(999999,999999));
	}
	
	}
