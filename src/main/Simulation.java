package main;
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
	private Planet sun;
	private Planet earth;
//	private Planet moon;
	private Planet[] planets;
	private final byte planetsAmount = 2;
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
	}
	
	private void creatPlanets() {
		
		planets = new Planet[planetsAmount];
		planets[0] = sun = new Planet(loadImage("sun.png"),5, 7, 128, 2, 500, 500, 1);
		planets[1] = earth = new Planet(loadImage("earth.png"),11, 2, 120, 2, 500, 500, 0.5F);
//		planets[2] = moon = new Planet(loadImage("sun.png"),5, 7, 128, 20, 500, 500, 0.1);
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
		
		earth.revolveAround(sun, false, 2, 250);
//		moon.rotate(earth, false, 4, 50);
		for (int i = 0; i < planets.length; i++) {
			planets[i].update();
		}
	}
	
	public void render(Graphics g) {
		
		renderBackground(g);
		renderCircle(g);
		for (int i = 0; i < planets.length; i++)
			planets[i].render(g);
		
	}
	
	private void renderBackground(Graphics g) {
		
		g.drawImage(background, 0, 0, null);
	}
	
	private void renderCircle(Graphics g) {
		
		simPanel.paint(g,250,250,500,500);
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


