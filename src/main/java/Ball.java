import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	private int xCoor;
	private int yCoor;
	private int raza;
	
	public Ball(int xCoor, int yCoor, int raza) {
		this.xCoor=xCoor;
		this.yCoor=yCoor;
		this.raza=raza;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(xCoor, yCoor, raza, raza);
	}
	
	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}

	public int getRaza() {
		return raza;
	}

	public void setRaza(int raza) {
		this.raza = raza;
	}
	
	
}
