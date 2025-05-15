package model;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;
import java.util.Random;

public class SnakeModel {
	public final int UNIT_SIZE;
	public final int INITIAL_SIZE;
	public final int FIELD_SIZE;
	private ArrayList<Point> cordinateCorpo;
	private Point applePos;
	private boolean giocoFinito;
	private int punteggio;
	private boolean start;

	public SnakeModel()
	{
		this.cordinateCorpo = new ArrayList<Point>(0);
		this.UNIT_SIZE = 30;
		this.INITIAL_SIZE = 2;
		this.FIELD_SIZE = 20;
		this.applePos = new Point(0, 0);
		this.giocoFinito = false;
		punteggio=0;
		start=false;
		for (int i = 0; i < INITIAL_SIZE; i++) {
			this.cordinateCorpo.add(new Point(i * UNIT_SIZE, 0));
		}
		Collections.reverse(cordinateCorpo);
	}

	public SnakeModel(int uSize, int iSize, int fSize) {
		this.cordinateCorpo = new ArrayList<Point>();
		this.UNIT_SIZE = uSize;
		this.INITIAL_SIZE = iSize;
		this.FIELD_SIZE = fSize;
		this.applePos = new Point(0, 0);
		this.giocoFinito = false;
		for (int i = 0; i < INITIAL_SIZE; i++) {
			this.cordinateCorpo.add(new Point(i * UNIT_SIZE, 0));
		}
	}
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public Point getCoordinate(int i) {
		return this.cordinateCorpo.get(i);
	}
//
	public Point getApplePos() {
		return this.applePos;
	}

	public void genApple() {
		Random random = new Random();
		int x = random.nextInt(FIELD_SIZE) * UNIT_SIZE;
		int y = random.nextInt(FIELD_SIZE) * UNIT_SIZE;
		this.applePos.setLocation(x, y);
	}

	public int getLunghezza() {
		return this.cordinateCorpo.size();
	}

	public boolean addPunto(int x, int y) {
		try {
			this.cordinateCorpo.add(new Point(x, y));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isGiocoFinito() {
		return giocoFinito;
	}

	public boolean addPunto(Point punto) {
		try {
			this.cordinateCorpo.add(punto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void move(char c) {
		if (!this.giocoFinito) {
			if(c != ' ') {
				for (int i = this.cordinateCorpo.size() - 1; i > 0; i--) {
					Point punto = this.cordinateCorpo.get(i - 1);
					this.cordinateCorpo.get(i).setLocation(punto.x, punto.y);
				}
			}
			

			switch (c) {
			case 'W': {
				if (this.cordinateCorpo.get(0).y != 0) {
					this.cordinateCorpo.get(0).y = cordinateCorpo.get(0).y - UNIT_SIZE;
				}
				break;
			}
			case 'A': {
				if (this.cordinateCorpo.get(0).x != 0) {
					this.cordinateCorpo.get(0).x = cordinateCorpo.get(0).x - UNIT_SIZE;
				}
				break;
			}
			case 'S': {
				if (this.cordinateCorpo.get(0).y != FIELD_SIZE * UNIT_SIZE) {
					this.cordinateCorpo.get(0).y = cordinateCorpo.get(0).y + UNIT_SIZE;
				}
				break;
			}
			case 'D': {
				if (this.cordinateCorpo.get(0).x != FIELD_SIZE * UNIT_SIZE) {
					this.cordinateCorpo.get(0).x = cordinateCorpo.get(0).x + UNIT_SIZE;
				}
				break;
			}
			}

		}

	}
	public int getPunteggio() {
		return punteggio;
	}

	public boolean appleCollision() {
		if (this.cordinateCorpo.get(0).equals(this.applePos)) {
			punteggio++;
			this.cordinateCorpo.add(new Point(999999999,999999999));
			genApple();
			return true;
		}
		return false;
	}

	// CONTROLLA SE IL CORPO DEL SERPETE SI SOVREPPONE. RESTITUISCE '0' SE IL CORPO
	// NON SI SCONTRA CON SE STESSO. RESTITUISCE '1' SE IL CORPO SI SCONTRA CON SE
	// STESSO. RESTITUISCE '-1' SE C'E' STATO UN ERRORE NEL CONTROLLO.
	public int controlloConflittoCorpo()
	{
		try
		{
			for (int i = cordinateCorpo.size()-1; i>0 ; i--) {
				Point p = this.cordinateCorpo.get(i);
				if ((p.x == this.cordinateCorpo.get(0).x && p.y == this.cordinateCorpo.get(0).y)) {
					// Se il primo punto del corpo coincide con un altro punto del corpo, il gioco è
					// finito
					giocoFinito = true;
					return 1;
			}
			
			
		}
			return 0;
		}
		
		catch(Exception e)
		{
			return -1;
		}
	}
	}
