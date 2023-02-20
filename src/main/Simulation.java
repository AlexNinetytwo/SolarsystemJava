package main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

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
	
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	private void initClasses() {
		creatPlanets();
	}
	
	private void creatPlanets() {
		
		planets = new Planet[2];
		planets[0] = sun = new Planet(loadImage("sun.png"),5, 7, 128, 2, 500, 500, 1);
		planets[1] = earth = new Planet(loadImage("earth.png"),11, 2, 120, 2, 500, 500, 0.4);
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
	
	public void update() {	
		earth.rotate(sun, false, 2, 250);
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

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long previousTime = System.nanoTime();
		
		double deltaU = 0;
		double deltaF = 0;
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		
		
		while(true) {
			long currentTime = System.nanoTime();
			
			// deltaU neutralize lags for the FPS
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				simPanel.repaint();
				frames++;
				deltaF--;
			}
			
		
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		
	}
	
	public void windowFocusLost() {
//		player.resetDirBooleans();
	}
	
	public Planet[] getPlanets() {
		return planets;
	}
//		

}
