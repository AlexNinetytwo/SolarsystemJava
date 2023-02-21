package main;

public class RunCode {

	private long previousTime = System.nanoTime(),
					lastCheck = System.currentTimeMillis();
	
	private double deltaU, deltaF = 0;
	
	private int frames, updates = 0;
	
	
	public RunCode(Simulation sim) {
		
		final double timePerFrame = 1_000_000_000.0 / sim.getFPS(),
					timePerUpdate = 1_000_000_000.0 / sim.getUPS();
		
		while(true) {
			
			calcTime(timePerFrame, timePerUpdate);
			
			updateSimAndBalanceUpdates(sim);
			
			repaintSimAndBalanceFrames(sim);
			
			print_FPS_and_UPS();
		}
	}
	
	private void calcTime(double timePerFrame, double timePerUpdate) {
		
		long currentTime = System.nanoTime();	
		deltaU += (currentTime - previousTime) / timePerUpdate;
		deltaF += (currentTime - previousTime) / timePerFrame;
		previousTime = currentTime;
		
	}

	private void updateSimAndBalanceUpdates(Simulation sim) {
		if(deltaU >= 1) {
			sim.update();
			updates++;
			deltaU--;
		}
	}
	
	private void repaintSimAndBalanceFrames(Simulation sim) {
		if(deltaF >= 1) {
			sim.getPanel().repaint();
			frames++;
			deltaF--;
		}
	}
	
	private void print_FPS_and_UPS() {
		
		if(System.currentTimeMillis() - lastCheck >= 1000) {
			lastCheck = System.currentTimeMillis();
			System.out.println("FPS: " + frames + " | UPS: " + updates);
			frames = 0;
			updates = 0;
		}
	}
	
}
