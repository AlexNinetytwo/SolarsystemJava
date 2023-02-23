package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.util.Random;

import entities.Planet;
import utilz.LoadSave;



public class Simulation implements Runnable{
	
	
	private SimPanel simPanel;
	private Thread simThread;
	private final int FPS_SET = 60;
	private final int UPS_SET = 90;
	private Planet[] planets;
	private final byte planetsAmount = 10;
	private BufferedImage background = LoadSave.GetSpriteAtlas("background.png");
	
	public static final int SIM_WIDTH = 1000;
	public static final int SIM_HEIGHT = 1000;
	

	public Simulation() {
		
		initClasses();
		simPanel = new SimPanel(this);
		new SimWindow(simPanel);
		simPanel.requestFocus();		
		startGameLoop();
	}
	
	
	// Initialize
		
	private void initClasses() {
		
		creatPlanets();
		creatCircles();
	}
	
	private void creatPlanets() {
		
		planets = new Planet[planetsAmount];
		
		//BufferedImage img, int tilesX, int tilesY, int size, int aniSpeed, float x, float y, float scale
		Planet sun = new Planet(loadImage("sun.png"),5, 7, 128, 2, 500, 500, 1),
		   mercury = new Planet(loadImage("mercury.png"),1, 43, 100, 2, 500, 500, 0.1F),
			 venus = new Planet(loadImage("venus.png"),7, 9, 120, 2, 500, 500, 0.25F),
			 earth = new Planet(loadImage("earth.png"),11, 2, 120, 2, 500, 500, 0.25F),
			  mars = new Planet(loadImage("mars_200_x6_y10.png"), 6, 10, 200, 2, 500, 500, 0.15F),
		   jupiter = new Planet(loadImage("jupiter_200_x9_y8.png"), 9, 8, 200, 2, 500, 500, 0.5F),
		    saturn = new Planet(loadImage("saturn_214_x10_y6.png"), 10, 6, 214, 2, 500, 500, 0.9F),
		    uranus = new Planet(loadImage("uranus_200_x6_y4.png"), 6, 4, 199, 2, 500, 500, 0.25F),
		   neptune = new Planet(loadImage("neptune_184_x8_y6.png"), 8, 6, 184, 2, 500, 500, 0.32F),
		     pluto = new Planet(loadImage("pluto_100_x5_y8.png"), 5, 8, 100, 2, 500, 500, 0.1F);
		   
			 
		
		planets[0] = sun;
		planets[1] = mercury;
		planets[2] = venus;
		planets[3] = earth;
		planets[4] = mars;
		planets[5] = jupiter;
		planets[6] = saturn;
		planets[7] = uranus;
		planets[8] = neptune;
		planets[9] = pluto;
	}
	
	private void creatCircles() {
		planets[1].createCircle(100, Color.gray);
		planets[2].createCircle(150, Color.orange);
		planets[3].createCircle(200, Color.blue);
		planets[4].createCircle(250, Color.red);
		planets[5].createCircle(300, Color.yellow);
		planets[6].createCircle(350, Color.green);
		planets[7].createCircle(400, Color.blue);
		planets[8].createCircle(450, Color.blue);
		planets[9].createCircle(500, Color.blue);
	}
	
	private BufferedImage loadImage(String fileName) {
		
		BufferedImage img = LoadSave.GetSpriteAtlas(fileName);
		return img;
	}

	private void startGameLoop() {
		
		simThread = new Thread(this);
		simThread.start();
	}
	
	
	// Stuff for the loop
	
	public void update() {
		
		planets[1].revolveAround(planets[0], false, 88, 100);
		planets[2].revolveAround(planets[0], false, 255, 150);
		planets[3].revolveAround(planets[0], false, 365, 200);
		planets[4].revolveAround(planets[0], false, 365*1.9F, 250);
		planets[5].revolveAround(planets[0], false, 365*11.9F, 300);
		planets[6].revolveAround(planets[0], false, 365*29.5F, 350);
		planets[7].revolveAround(planets[0], false, 365*84, 400);
		planets[8].revolveAround(planets[0], false, 365*165, 450);
		planets[9].revolveAround(planets[0], false, 365*284, 500);
		
		for (int i = 0; i < planets.length; i++) {
			planets[i].update();
		}
	}
	
	public void render(Graphics g) {
		
		renderBackground(g);
		for (int i = 0; i < planets.length; i++) {
			planets[i].render(g);
		}	
	}
	
	private void renderBackground(Graphics g) {
		
		g.drawImage(background, 0, 0, null);
	}
	

	
	
	
	// Start the loop
	
	@Override
	public void run() {
		new RunCode(this);
		
	}
	
	
	
	// All getters
	
	public SimPanel getPanel() {
		return simPanel;
	}
	
	public int getFPS() {
		return FPS_SET;
	}
	
	public int getUPS() {
		return UPS_SET;
	}
	
	public Planet[] getPlanets() {
		
		return planets;
	}
	
	
	
	
	
	
	// Not used yet
	
//	public int getRandomNumber(int min, int max) {
//	    return (int) ((Math.random() * (max - min)) + min);
//	}
	

}


