package entities;

import java.awt.image.BufferedImage;

public abstract class Entity {

	
	protected BufferedImage img;
	protected int size, tilesY, tilesX;
	protected float x, y, neutralX, neutralY, scale, aniSpeed;

	
	public Entity(BufferedImage img, int tilesX, int tilesY, int size, float aniSpeed, float x, float y, float scale) {
		this.size = size;
		this.scale = scale * size;
		this.tilesX = tilesX;
		this.tilesY = tilesY;
		this.x = x;
		this.y = y;
		this.img = img;
		this.aniSpeed = aniSpeed * (tilesX * tilesY);//frames;
		
		neutralizePos();
	}
	
		public void neutralizePos() {
			this.neutralX = x - ((float) this.scale/2);
			this.neutralY = y - ((float) this.scale/2);
	}
		
}
