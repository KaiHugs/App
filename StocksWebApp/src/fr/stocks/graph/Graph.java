package fr.stocks.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

import javax.swing.JPanel;

public class Graph extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<List<Double>> data;
	private List<Double> values;
	private Double min, max;
	private int dataSize;
	
	public Graph(String _name, List<List<Double>> _data) {
		name 	 = _name;
		data 	 = _data;
		
		values 	 = (List<Double>)data.get(1);
		
		min 	 = (Double)values.get(0);
		max 	 = (Double)values.get(0);
		dataSize = values.size();
		for(int i = 1; i < values.size(); i++) {
			if(values.get(i) > max) {
				max = values.get(i);
			} 
			if(values.get(i) < min) {
				min = values.get(i);
			}
		}
	}
	
	public Graph() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		int w = (int)(this.getWidth()-this.getWidth()*0.1);
		int h = (int)(this.getHeight()-this.getHeight()*0.2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, (int)(this.getHeight()*0.3), w, h);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("1 month", 10, 90);
		g.drawString(name, w-50, 90);
		
		//Changes in timelapse


		int spacing = w / dataSize;
		Polygon p = new Polygon();
		for(int i = 0; i < dataSize; i++) {
			double a = 100 / (max - min);
			int v = h-(int)((values.get(i).intValue() - min.intValue())*a);
			p.addPoint(spacing*i, v);
		}
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}

}
