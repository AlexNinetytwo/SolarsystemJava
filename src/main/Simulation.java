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
	private final byte planetsAmount = 4;
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
			 earth = new Planet(loadImage("earth.png"),11, 2, 120, 2, 500, 500, 0.5F),
		   mercury = new Planet(loadImage("mercury.png"),1, 43, 100, 2, 500, 500, 0.25F),
			  moon = new Planet(loadImage("sun.png"),5, 7, 128, 20, 500, 500, 0.1F);
		
		planets[0] = sun;
		planets[1] = earth;
		planets[2] = mercury;
		planets[3] = moon;
	}
	
	private void creatCircles() {
		planets[1].createCircle(250, Color.blue);
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
		
		planets[1].revolveAround(planets[0], false, 2, 250);
		planets[2].revolveAround(planets[0], false, 4, 150);
		planets[3].revolveAround(planets[1], false, 30, 50);
		for (int i = 0; i < planets.length; i++) {
			planets[i].update();
		}
	}
	
	public void render(Graphics g) {
		
		renderBackground(g);
		planets[1].circle.render(g);
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


