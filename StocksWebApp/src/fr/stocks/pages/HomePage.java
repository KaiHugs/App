package fr.stocks.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.stocks.api.YahooAPI;
import fr.stocks.core.MainFrame;
import fr.stocks.graph.Graph;
import fr.stocks.guiSharedElements.NavigationBar;
import fr.stocks.style.Style;
import fr.stocks.tools.InfoPanel;
import fr.stocks.tools.PropertyLoader;

public class HomePage extends AbstractPage {

	private static final long serialVersionUID = 1L;
	
	private Graph[] favGraphs = new Graph[5];
	private NavigationBar navigationPanel;
	private JLabel title;
	private JPanel rightPanel, leftPanel, topPanel, bottomPanel, centerPanel;
	
	private Font font = new Font("serif", Font.PLAIN, 24);
	
	public HomePage(Dimension _size, MainFrame _mf) {
		
		super(_size, _mf);
		
		name = "Home Page";

		navigationPanel = new NavigationBar(_mf, true);
		
		title			= new JLabel("Favorite Stocks");
		
		leftPanel 		= new JPanel();
		rightPanel 		= new JPanel();
		topPanel 		= new JPanel();
		bottomPanel 	= new JPanel();
		centerPanel		= new JPanel();
		
		leftPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/5 , (int)size.getHeight()  ));
		rightPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/5 , (int)size.getHeight()  ));
		topPanel.setPreferredSize(new Dimension(	(int)size.getWidth()   , (int)size.getHeight()/10));
		bottomPanel.setPreferredSize(new Dimension(	(int)size.getWidth()   , (int)size.getHeight()/4));
		
		
		//CENTER
		centerPanel.setLayout(new GridLayout(2, 3));
		centerPanel.setBackground(Style.FRAME_BACKGROUND);
		
		String[] sName = PropertyLoader.getInfo(PropertyLoader.FAV);
		
		//graphs
		HashMap<String,List<List<Double>>> d = YahooAPI.getStockHistory("1d", "1mo", sName[0]);


		//CHANGE IN TIME INTERVALS

		
		int iHash = 0;
		double[] means 		= new double[5];
		double[] variances 	= new double[5];
		double[] avReturn 	= new double[5];
		double[] covari 	= new double[5];
		
		for (Entry<String, List<List<Double>>> entry : d.entrySet()) {
		    String key = entry.getKey();
		    List<List<Double>> value = entry.getValue();
		    for(int i = 0; i < value.get(1).size(); i++) {
		    	means[iHash] += value.get(1).get(i);
		    }
		    means[iHash] = means[iHash] / value.get(1).size();
		    
		    for(int i = 0; i < value.get(1).size(); i++) {
		    	variances[iHash] += (value.get(1).get(i)-means[iHash])*(value.get(1).get(i)-means[iHash]);
		    	if(i > 0) {
		    		avReturn[iHash] += value.get(1).get(i) / value.get(1).get(i-1);
		    		
		    	}
		    }
		    variances[iHash] = (variances[iHash] / value.get(1).size())/5;
		    avReturn[iHash]  = avReturn[iHash] / value.get(1).size();
		    
		 
			/*String key = entry.getKey();
		  List<List<Double>> value = entry.getValue();
		  for(int i=0; i<value.get(1); i++){
			means[iHash += value.get(1).get(i)]
		  }
		    
		    */
		    
		    		
		    		
		    System.out.println(variances[iHash]);
		    
		    favGraphs[iHash] = new Graph(key, value);
		    
		    centerPanel.add(favGraphs[iHash]);
		    iHash++;
		}
		iHash = 0;
		double res = 0 ;
		
		List<List<Double>> vv = new ArrayList<List<Double>>();
		for (Entry<String, List<List<Double>>> entry : d.entrySet()) {
			vv.add(entry.getValue().get(1));
		}
		
		for(int i = 0; i < vv.size(); i++) {
			res += 	(vv.get(0).get(i) - avReturn[0]) *
					(vv.get(1).get(i) - avReturn[1]) *
					(vv.get(2).get(i) - avReturn[2]) *
					(vv.get(3).get(i) - avReturn[3]) *
					(vv.get(4).get(i) - avReturn[4]);
		}
		res = res / 30;
		
		
		
		
		
		
		centerPanel.add(new InfoPanel(variances, res));
		
		
		//BOTTOM
		bottomPanel.setLayout(new GridLayout(2, 1));
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Style.FRAME_BACKGROUND);
		bottomPanel.add(spacer1);
		bottomPanel.add(navigationPanel);
		
		//TOP
		topPanel.setBackground(Style.FRAME_BACKGROUND);
		topPanel.setLayout(new GridBagLayout());
		title.setFont(font);
		topPanel.add(title);
		
		//SIDES
		leftPanel.setBackground(Style.FRAME_BACKGROUND);
		rightPanel.setBackground(Style.FRAME_BACKGROUND);
		
		this.setSize(size);
		this.setOpaque(true);
		this.setBackground(Color.red);
		this.setLayout(new BorderLayout());
		
		
		this.add(leftPanel		, BorderLayout.LINE_START);
		this.add(rightPanel		, BorderLayout.LINE_END);
		this.add(topPanel		, BorderLayout.PAGE_START);
		this.add(bottomPanel	, BorderLayout.PAGE_END);
		this.add(centerPanel	, BorderLayout.CENTER);
		
		
	}


	
}


