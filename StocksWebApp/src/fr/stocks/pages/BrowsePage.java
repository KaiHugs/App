package fr.stocks.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.stocks.api.YahooAPI;
import fr.stocks.auth.DatabaseHandler;
import fr.stocks.core.MainFrame;
import fr.stocks.graph.Graph;
import fr.stocks.guiSharedElements.NavigationBar;
import fr.stocks.style.Style;
import fr.stocks.tools.PropertyLoader;

public class BrowsePage extends AbstractPage {

	private static final long serialVersionUID = 1L;
	
	private NavigationBar navigationPanel;
	private JScrollPane jsp;
	private JLabel title;
	private JPanel rightPanel, leftPanel, topPanel, bottomPanel, centerPanel, content;
	private Graph[] graphs;

	public BrowsePage(Dimension _size, MainFrame _mf) {
		super(_size, _mf);
		name = "Browse Page";
		navigationPanel = new NavigationBar(_mf, true);

		leftPanel 		= new JPanel();
		rightPanel 		= new JPanel();
		topPanel 		= new JPanel();
		bottomPanel 	= new JPanel();
		centerPanel		= new JPanel();
		content			= new JPanel();
		jsp 			= new JScrollPane(content);
		
		leftPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/7 , (int)size.getHeight()   ));
		rightPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/7 , (int)size.getHeight()   ));
		topPanel.setPreferredSize(new Dimension(	(int)size.getWidth()    , (int)size.getHeight()/10));
		bottomPanel.setPreferredSize(new Dimension(	(int)size.getWidth()    , (int)size.getHeight()/8 ));
		
		//CENTER
		//content.setPreferredSize(new Dimension(100, 10000));
		content.setBackground(Style.FRAME_BACKGROUND);
		
		centerPanel.setLayout(new GridLayout(1, 1));
		//centerPanel.setBackground(Style.FRAME_BACKGROUND);
		jsp.setBackground(Style.FRAME_BACKGROUND);
		jsp.getVerticalScrollBar().setUnitIncrement(12);
		centerPanel.add(jsp);
		
		//TOP
		topPanel.setBackground(Style.FRAME_BACKGROUND);
		
		//BOTTOM
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.add(navigationPanel);
		
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
		
		String[] sName = PropertyLoader.getInfo(PropertyLoader.FAV);
		
		HashMap<String,List<List<Double>>> d = YahooAPI.getStockHistory("1d", "1mo", sName[1]);
		graphs = new Graph[d.size()];


		//Change in time intervals


		int counter = 0;
		for (Entry<String, List<List<Double>>> entry : d.entrySet()) {
		    String key = entry.getKey();
		    List<List<Double>> value = entry.getValue();
		    System.out.println(key+" "+value);
		    graphs[counter] = new Graph(key, value);
		    counter++;
		}
		
		content.setLayout(new GridLayout(counter/3, 3));
		content.setPreferredSize(new Dimension(100, (counter/3)*250));
		for(int i = 0; i < counter; i++) {
			content.add(graphs[i]);
		}
	}
	
	public void loadContent() {
		
	}
	

}
