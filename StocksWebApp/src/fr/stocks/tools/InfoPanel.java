package fr.stocks.tools;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {


	private static final long serialVersionUID = 1L;
	private String risk = "undefined";
	private double pVariance = -1f;

	
	public InfoPanel(double[] _variances, double cov) {
		for(int i = 0; i < _variances.length; i++) {
			pVariance += _variances[i];
		}
		pVariance += 5*cov;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int w = (int)(this.getWidth()-this.getWidth()*0.1);
		int h = (int)(this.getHeight()-this.getHeight()*0.2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, (int)(this.getHeight()*0.3), w, h);
		g.setColor(Color.DARK_GRAY);
		g.drawString("INFOS", w-50, 90);
		//g.drawString("RISK: "+risk, 20, 140);
		g.drawString("PORTFOLIO VARIANCE:", 20, 180);
		g.drawString(""+(float)pVariance, 20, 210);
	}
	
}
