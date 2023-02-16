package fr.stocks.core;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import fr.stocks.pages.BrowsePage;
import fr.stocks.pages.HomePage;
import fr.stocks.pages.SignInPage;

public class Main {

	public static void main(String[] args) {
		
		Dimension frameSize = new Dimension(1200, 800);
		
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            MainFrame mf = new MainFrame(frameSize/*new BrowsePage(frameSize)new HomePage(frameSize)*/);
	            mf.showMe(true);
	        	
	        	
	        	
	        	
	        }
	    });       
	
		
		
		
		
	}

}
