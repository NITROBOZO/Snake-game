package model;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class Snake {
	private ArrayList<Point> coordinateCorpo;
	private int punteggio;
	private char lastChar;
	public Snake()
	{	
		lastChar='p';
		this.coordinateCorpo = new ArrayList<Point>();
		this.punteggio=0;
	}
	public void setLunghezzaIniziale(int n,int cellSize,int fieldSize,boolean p2) {//imposta lunghezza e posizione in base al player
		this.coordinateCorpo.clear();
		this.coordinateCorpo.trimToSize();
		if(!p2) {
			for (int i = 0; i < n; i++) {
				this.coordinateCorpo.add(new Point(i * cellSize, 0));
				
			}
			Collections.reverse(coordinateCorpo);
		}
		else {
			for (int i = (fieldSize*2+1)-n; i < (fieldSize*2+1); i++) {
				this.coordinateCorpo.add(new Point(i * cellSize, 0));
				
			}
		}
		}
		


	public void init()
	{
		this.punteggio=0;
	}
	


	public Point getCoordinate(int i) {//ritorna un punto specifico
		return this.coordinateCorpo.get(i);
	}


	public int getLunghezza() {//lunghezza serpente
		return this.coordinateCorpo.size();
	}

	public boolean addPunto(int x, int y) {
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

	public void move(char c,int fieldSize,int cellSize,boolean m) {
				if(c!=' ') {
					for (int i = this.coordinateCorpo.size() - 1; i > 0; i--) {//sposta il corpo del serpente dietro la testa
						Point punto = this.coordinateCorpo.get(i - 1);
						this.coordinateCorpo.get(i).setLocation(punto.x, punto.y);
					}
				}
			int xSize=fieldSize;//per modalità multiplayer, estende la direzione orizzontale
			if(m) {
				xSize*=2;
			}
			switch (c) {
			case 'W': {
				if (this.coordinateCorpo.get(0).y != 0 ) {
					this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y - cellSize;
				}
				break;
			}
			case 'A': {
				if (this.coordinateCorpo.get(0).x != 0 ) {
					this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x - cellSize;
				}
				break;
			}
			case 'S': {
				if (this.coordinateCorpo.get(0).y != fieldSize * cellSize) {
					this.coordinateCorpo.get(0).y = coordinateCorpo.get(0).y + cellSize;
				}
				break;
			}
			case 'D': {
				if (this.coordinateCorpo.get(0).x != xSize * cellSize ) {
					this.coordinateCorpo.get(0).x = coordinateCorpo.get(0).x + cellSize;
				}
				break;
			}
			}
			lastChar=c;
		}
	public boolean isAppleCollision(Point apple) {
		if(this.coordinateCorpo.get(0).equals(apple)) {
			return true;
		}
		return false;
	}
	public int getPunteggio() {
		return punteggio;
	}
	public void incrementaPt() {
		punteggio++;
	}
	public void incrementaLunghezza() {
		this.coordinateCorpo.add(new Point(999999,999999));
	}
	
	}
