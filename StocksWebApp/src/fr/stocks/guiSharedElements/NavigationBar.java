package fr.stocks.guiSharedElements;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.stocks.core.MainFrame;
import fr.stocks.pages.AbstractPage;
import fr.stocks.pages.BrowsePage;
import fr.stocks.pages.HomePage;
import fr.stocks.style.Style;

public class NavigationBar extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JButton homeButton, browseButton, accountButton;

	public NavigationBar(MainFrame f, boolean isConnected) {
		homeButton		= new JButton("Home");
		browseButton	= new JButton("Browse");
		accountButton	= new JButton("Account");
		
		homeButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		browseButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		accountButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		
		homeButton.setBorder(null);
		browseButton.setBorder(null);
		accountButton.setBorder(null);
		
		homeButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(isConnected) {
					System.out.println("HOME");
					//f.changePage(new HomePage(f.frameSize, f));
					f.changePage(f.homePage);
					//f.dispose();
				}
			} 
		});
		
		browseButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				if(isConnected) {
					System.out.println("BROWSE");
					//f.changePage(new BrowsePage(f.frameSize, f));
					f.changePage(f.browsePage);
					
				}
			} 
		});
		
		accountButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("ACCOUNT");
				//f.changePage(new AccountPage(f.frameSize, f));
			} 
		});
		
		this.setBackground(Style.NAVBAR_BACKGROUND);
		this.setLayout(new GridLayout(1, 7));
		
		this.add(new JLabel());
		this.add(homeButton);
		this.add(new JLabel());
		this.add(browseButton);
		this.add(new JLabel());
		this.add(accountButton);
		this.add(new JLabel());
		
	}

	
}
