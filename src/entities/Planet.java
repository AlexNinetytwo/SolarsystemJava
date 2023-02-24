package entities;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class Planet extends Entity{

	private BufferedImage[][] animation;
	private double aniTick;
	private int aniX = 0;
	private int aniY = 0;
	private float angle;
	private Circle circle;
	private boolean circleCreated = false;
	public boolean reverseAnimation = false;
	private double revolveSpeed;

	
	public Planet(BufferedImage img, int tilesX, int tilesY, int size, float aniSpeed, float x, float y, float scale, double revolveSpeed) {
		
		super(img,tilesX,tilesY,size,aniSpeed,x,y,scale);
		this.revolveSpeed = revolveSpeed;
		loadAnimation(img);
	}
	
	public void revolveAround(Planet center, float speed, float distance, boolean clockwise) {
		
		calcNewPos(center, distance, clockwise);
		neutralizePos();
		nextRotateAngle();
	
	}
	
	public void revolveAround(Planet center, float speed, float distance) {
		
		calcNewPos(center, distance);
		neutralizePos();
		nextRotateAngle();
	
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
	
	public void calcNewPos(Planet center, float distance) {
		
		this.x = (float) (distance * Math.sin(angle) + center.x);
		this.y = (float) (distance * Math.cos(angle) + center.y);
	}
	
	
	public void nextRotateAngle() {
		
		angle += (4 / this.revolveSpeed) / 5;
		if (angle >= 6.3)
			angle -= 6.3;
	}
	
	// aniSpeed = 22
	// 90 UPS
	// 574,875 updates bis umrundet
	
	
	private void loadAnimation(BufferedImage img) {
				
		animation = new BufferedImage[tilesY][tilesX];
		for (int j=0; j<animation.length; j++)
			for (int i=0; i<animation[j].length; i++)
				animation[j][i] = img.getSubimage(i*size, j*size, size, size);	
	}
	
	private void updateAnimationTick() {
		
		aniTick+=0.2;
		if (aniTick >= (6.3 / (4 / this.revolveSpeed)) / aniSpeed) {
			aniTick = 0;
			nextImageIndex();
		}
	}
	
	private void nextImageIndex() {
		
		if (reverseAnimation) {
			
			if (aniX != 0) {
				aniX--;
			}
			else if (aniY != 0) {
				aniY--;
				aniX = animation[0].length-1;
			}
			else {
				aniX = animation[0].length-1;
				aniY = animation.length-1;
			}
		}
		
		else {
			
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
