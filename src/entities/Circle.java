package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circle {

	private int x, y, width, height;
	private Color color;
	
	public Circle(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.width = radius * 2;
		this.height = radius * 2;
		this.color = color;
		
		neutralizePos();
	}
	
	private void neutralizePos() {
		this.x -= this.width / 2;
		this.y -= this.height / 2;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(this.color);
		g2d.drawOval(this.x, this.y, this.width, this.height);
	}
}
