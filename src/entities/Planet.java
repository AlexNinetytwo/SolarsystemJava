package entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class Planet extends Entity{

	private BufferedImage[][] animation;
	private int aniTick;
	private int aniX = 0;
	private int aniY = 0;
	private float angle = 0;
	private Circle circle;
	private boolean circleCreated = false;

	
	public Planet(BufferedImage img, int tilesX, int tilesY, int size, int aniSpeed, float x, float y, float scale) {
		
		super(img,tilesX,tilesY,size,aniSpeed,x,y,scale);
		loadAnimation(img);
	}
	
	public void revolveAround(Planet center, boolean clockwise, float speed, float distance) {
		
		calcNewPos(center, distance, clockwise);
		neutralizePos();
		nextRotateAngle(speed);
	
	}

	public void calcNewPos(Planet center, float distance, boolean clockwise) {
		if (clockwise) {
			this.x = (float) (distance * Math.cos(angle) + center.x);
			this.y = (float) (distance * Math.sin(angle) + center.y);
		}
		else {
			this.x = (float) (distance * Math.sin(angle) + center.x);
			this.y = (float) (distance * Math.cos(angle) + center.y);
		}
	}
	
	public void nextRotateAngle(float speed) {
		
		angle += 4 / speed;
		if (angle >= 6.3)
			angle -= 6.3;
	}
	
	private void loadAnimation(BufferedImage img) {
				
		animation = new BufferedImage[tilesY][tilesX];
		for (int j=0; j<animation.length; j++)
			for (int i=0; i<animation[j].length; i++)
				animation[j][i] = img.getSubimage(i*size, j*size, size, size);	
	}
	
	private void updateAnimationTick() {
		
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			nextImageIndex();
		}
	}
	
	private void nextImageIndex() {
		
		if (aniX < animation[0].length-1) {
			aniX++;
		}
		else if (aniY < animation.length-1) {
			aniX = 0;
			aniY++;
		}
		else {
			aniX = 0;
			aniY = 0;
		}
		
	}
	
	public void createCircle(float radius, Color color) {
		
		if (!circleCreated) {
			this.circle = new Circle((int)this.x, (int)this.y, (int)radius, color);
			circleCreated = true;
		}
		
	}
	
	public void render(Graphics g) {
		
		if (circleCreated) {
			this.circle.render(g);
		}
		
		g.drawImage(animation[aniY][aniX], (int) neutralX, (int) neutralY, (int) scale, (int) scale, null);
	}
	
	public void update() {
		
		updateAnimationTick();
	}
	
}
