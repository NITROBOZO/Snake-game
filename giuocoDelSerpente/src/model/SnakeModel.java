package model;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;
import java.util.Random;

public class SnakeModel {
	static public final int VEL_LIMIT = 6;
	public final int UNIT_SIZE;
	private int lunghezzaInit;
	public final int FIELD_SIZE;
	private ArrayList<Point> cordinateCorpo1;
	private ArrayList<Point> cordinateCorpo2;
	private Point applePos;
	private boolean giocoFinito;
	private int punteggio;
	private boolean start;
	private int velocita;
	private boolean multiplayer;
	private boolean moved;
	private boolean movedP2;
	public SnakeModel(boolean p)
	{	
		movedP2=false;
		moved=false;
		this.cordinateCorpo1 = new ArrayList<Point>();
		this.cordinateCorpo2 = new ArrayList<Point>();
		this.UNIT_SIZE = 30;
		this.lunghezzaInit = 2;
		this.FIELD_SIZE = 20;
		this.applePos = new Point(99999999, 99999999);
		this.giocoFinito = false;
		this.punteggio=0;
		this.velocita=25*1;
		start=false;
		multiplayer=p;
	}

	/*public SnakeModel(int uSize, int iSize, int fSize) {
		this.cordinateCorpo = new ArrayList<Point>();
		this.UNIT_SIZE = uSize;
		this.lunghezzaInit = iSize;
		this.FIELD_SIZE = fSize;
		this.applePos = new Point(99999999, 99999999);
		this.giocoFinito = false;
		this.punteggio=0;
		this.velocita=25*1;
		start=false;
	}*/
	
	public void setLunghezza(int n) {
		this.lunghezzaInit=n;
		this.cordinateCorpo1.clear();
		for (int i = 0; i < lunghezzaInit; i++) {
			this.cordinateCorpo1.add(new Point(i * UNIT_SIZE, 0));
		}
		Collections.reverse(cordinateCorpo1);
		if(multiplayer) {
			this.cordinateCorpo2.clear();
			for (int i = FIELD_SIZE-lunghezzaInit; i < FIELD_SIZE; i++) {
				this.cordinateCorpo2.add(new Point(i * UNIT_SIZE, 0));
			}
		}
	}
	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setVelocita(int n) {
		this.velocita=(VEL_LIMIT-n)*25;
	}
	public int getVelocita() {
		return this.velocita;
	}
	public void restart()
	{
		this.cordinateCorpo1= new ArrayList<Point>();
		this.cordinateCorpo2= new ArrayList<Point>();
		this.giocoFinito = false;
		this.start=false;
		this.punteggio=0;
		moved=false;
		movedP2=false;
		for (int i = 0; i < lunghezzaInit; i++) {
			this.cordinateCorpo1.add(new Point(i * UNIT_SIZE, 0));
		}
		Collections.reverse(cordinateCorpo1);
	}
	
	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public Point getCoordinate(int i) {
		return this.cordinateCorpo1.get(i);
	}
	public Point getCoordinateP2(int i) {
		return this.cordinateCorpo2.get(i);
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
		return this.cordinateCorpo1.size();
	}
	public int getLunghezzaP2() {
		return this.cordinateCorpo2.size();
	}

	public boolean addPunto(int x, int y) {
		try {
			this.cordinateCorpo1.add(new Point(x, y));
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
			this.cordinateCorpo1.add(punto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void move(char c,char cP2) {
		if (!this.giocoFinito && start) {
				if(moved) {
					for (int i = this.cordinateCorpo1.size() - 1; i > 0; i--) {
						Point punto = this.cordinateCorpo1.get(i - 1);
						this.cordinateCorpo1.get(i).setLocation(punto.x, punto.y);
					}
				}
				if(multiplayer && movedP2) {
					for (int i = this.cordinateCorpo2.size() - 1; i > 0; i--) {
						Point punto = this.cordinateCorpo2.get(i - 1);
						this.cordinateCorpo2.get(i).setLocation(punto.x, punto.y);
					}
				}
			}
			

			switch (c) {
			case 'W': {
				if (this.cordinateCorpo1.get(0).y != 0) {
					this.cordinateCorpo1.get(0).y = cordinateCorpo1.get(0).y - UNIT_SIZE;
					moved=true;
				}
				break;
			}
			case 'A': {
				if (this.cordinateCorpo1.get(0).x != 0) {
					this.cordinateCorpo1.get(0).x = cordinateCorpo1.get(0).x - UNIT_SIZE;
					moved=true;
				}
				break;
			}
			case 'S': {
				if (this.cordinateCorpo1.get(0).y != FIELD_SIZE * UNIT_SIZE) {
					this.cordinateCorpo1.get(0).y = cordinateCorpo1.get(0).y + UNIT_SIZE;
					moved=true;
				}
				break;
			}
			case 'D': {
				if (this.cordinateCorpo1.get(0).x != FIELD_SIZE * UNIT_SIZE) {
					this.cordinateCorpo1.get(0).x = cordinateCorpo1.get(0).x + UNIT_SIZE;
					moved=true;
				}
				break;
			}
			}
			if(multiplayer) {
				switch (cP2) {
				case 'I': {
					if (this.cordinateCorpo2.get(0).y != 0) {
						this.cordinateCorpo2.get(0).y = cordinateCorpo2.get(0).y - UNIT_SIZE;
						movedP2=true;
					}
					break;
				}
				case 'J': {
					if (this.cordinateCorpo2.get(0).x != 0) {
						this.cordinateCorpo2.get(0).x = cordinateCorpo2.get(0).x - UNIT_SIZE;
						movedP2=true;
					}
					break;
				}
				case 'K': {
					if (this.cordinateCorpo2.get(0).y != FIELD_SIZE * UNIT_SIZE) {
						this.cordinateCorpo2.get(0).y = cordinateCorpo2.get(0).y + UNIT_SIZE;
						movedP2=true;
					}
					break;
				}
				case 'L': {
					if (this.cordinateCorpo2.get(0).x != FIELD_SIZE * UNIT_SIZE) {
						this.cordinateCorpo2.get(0).x = cordinateCorpo2.get(0).x + UNIT_SIZE;
						movedP2=true;
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
		if (this.cordinateCorpo1.get(0).equals(this.applePos)) {
			punteggio++;
			this.cordinateCorpo1.add(new Point(999999999,999999999));
			genApple();
			return true;
		}
		else if(multiplayer && this.cordinateCorpo2.get(0).equals(this.applePos)) {
			this.cordinateCorpo2.add(new Point(999999999,999999999));
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
			Point head1 = cordinateCorpo1.get(0);
	        Point head2 = cordinateCorpo2.get(0);

	        // 0. Head-to-head collision
	        if (head1.equals(head2)) {
	            giocoFinito = true;
	            return 0;
	        }

	        // 1. Player 1 collides with its own body or player 2's body
	        for (int i = 1; i < cordinateCorpo1.size(); i++) {
	            if (cordinateCorpo1.get(i).equals(head1)) {
	                giocoFinito = true;
	                return 1; // P1 hit itself
	            }
	        }
	        for (int i = 1; i < cordinateCorpo2.size(); i++) {
	            if (cordinateCorpo2.get(i).equals(head1)) {
	                giocoFinito = true;
	                return 1; // P1 hit P2
	            }
	        }

	        // 2. Player 2 collides with its own body or player 1's body
	        for (int i = 1; i < cordinateCorpo2.size(); i++) {
	            if (cordinateCorpo2.get(i).equals(head2)) {
	                giocoFinito = true;
	                return 2; // P2 hit itself
	            }
	        }
	        for (int i = 1; i < cordinateCorpo1.size(); i++) {
	            if (cordinateCorpo1.get(i).equals(head2)) {
	                giocoFinito = true;
	                return 2; // P2 hit P1
	            }
	        }

	        return 3; // no collision
	}
		
		catch(Exception e)
		{
			return -1;
		}
	}
	}
