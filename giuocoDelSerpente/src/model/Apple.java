package model;

import java.awt.Point;
import java.util.Random;

public class Apple {
	private final static Random random = new Random();
	private Point applePos = new Point(99999,99999);
	
	public Point genApple (int fieldSize,int cellSize,boolean multiplayer) {
		int x=0;
		int y=0;
		x = (multiplayer ? (random.nextInt((int)((fieldSize * 1.7) * cellSize))) : (random.nextInt(fieldSize) * cellSize));
		y = random.nextInt(fieldSize) * cellSize;
		applePos.setLocation(x, y);
		System.out.println(x+":"+y);
		return applePos.getLocation();
	}
	
	public Point getApplePos() {
		return applePos;
	}
	
	public void init() {
		applePos.setLocation(999999,999999);
	}
}
