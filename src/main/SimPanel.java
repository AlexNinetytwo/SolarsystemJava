package main;
import static main.Simulation.SIM_HEIGHT;
import static main.Simulation.SIM_WIDTH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;



public class SimPanel extends JPanel{

	private Simulation simulation;

	public SimPanel(Simulation simulation) {
		this.simulation = simulation;
		setPanelSize();
		this.setBackground(Color.black);
	}
	

	

	private void setPanelSize() {
		Dimension size = new Dimension(SIM_WIDTH, SIM_HEIGHT);
		setPreferredSize(size);
//		System.out.println("size :" + SIM_WIDTH + " : " + SIM_HEIGHT);
		
	}
	
	
	public void paint(Graphics g, int x, int y, int x2, int y2) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setPaint(Color.blue);
		g2d.drawOval(x, y, x2, y2);
    }
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		simulation.render(g);	
	}
	
	
	
	public Simulation getSim() {
		return simulation;
	}
}
