package fr.stocks.core;

import java.awt.Dimension;

import javax.swing.JFrame;

import fr.stocks.pages.AbstractPage;
import fr.stocks.pages.BrowsePage;
import fr.stocks.pages.HomePage;
import fr.stocks.pages.SignInPage;
import fr.stocks.style.Style;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public  Dimension frameSize;
	public HomePage homePage;
	public BrowsePage browsePage;
	private AbstractPage currentPage;
	
	
	public MainFrame(Dimension _frameSize) {
		frameSize 	= _frameSize;
		currentPage	= new SignInPage(frameSize, this);
		//currentPage = new BrowsePage(frameSize, this)/*new HomePage(frameSize, this)*/;
		
		this.setTitle(currentPage.getName());
		this.setSize(frameSize);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(currentPage);
		this.getContentPane().setBackground(Style.FRAME_BACKGROUND);
		
		homePage 	= new HomePage(this.frameSize, this);
		browsePage 	= new BrowsePage(this.frameSize, this);
	}
	
	public void changePage(AbstractPage p) {
		this.getContentPane().remove(currentPage);
		this.add(p);
		this.getContentPane().setBackground(Style.FRAME_BACKGROUND);
		currentPage = p;
		this.setTitle(currentPage.getName());
		this.revalidate();
		this.repaint();
	}
	
	
	
	
	public void showMe(boolean b) { this.setVisible(b); }
	
}
