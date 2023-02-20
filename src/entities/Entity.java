package entities;

import java.awt.image.BufferedImage;

public abstract class Entity {

	protected float x,y;
	protected int size;
	protected double scale;
	protected float neutralX;
	protected float neutralY;
	protected BufferedImage img;
	protected int tilesX;
	protected int tilesY;
	protected int aniSpeed;
	public Entity(BufferedImage img, int tilesX, int tilesY, int size, int aniSpeed, float x, float y, double scale) {
		this.size = size;
		this.scale = scale * size;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.x = x;
		this.y = y;
		this.img = img;
		this.aniSpeed = aniSpeed;
		
		neutralizePos();
	}
	
		public void neutralizePos() {
			this.neutralX = x - ( (float) this.scale/2);
			this.neutralY = y - ( (float) this.scale/2);
	}
}
