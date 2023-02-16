package fr.stocks.pages;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.stocks.auth.DatabaseHandler;
import fr.stocks.core.MainFrame;
import fr.stocks.guiSharedElements.NavigationBar;
import fr.stocks.style.RoundedBorder;
import fr.stocks.style.Style;

public class SignInPage extends AbstractPage {

	private static final long serialVersionUID = 1L; 
	
	private JLabel usernameLabel, passwordLabel, heightSpacing;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton loginButton, signUpButton, contactButton, aboutButton;
	private JPanel loginFormPanel, rightPanel, leftPanel, topPanel, bottomPanel;
	private NavigationBar navigationPanel;
	private DatabaseHandler dbHandler;
	
	public SignInPage(Dimension _size, MainFrame _mf) {
		super(_size, _mf);
		name = "Sign In Page";
		
		dbHandler 		= new DatabaseHandler();
		
		navigationPanel = new NavigationBar(_mf, DatabaseHandler.isCo);
		
		loginFormPanel 	= new JPanel();
		leftPanel 		= new JPanel();
		rightPanel 		= new JPanel();
		topPanel 		= new JPanel();
		bottomPanel 	= new JPanel();
		
		loginButton		= new JButton("Sign In");
		
		usernameLabel 	= new JLabel("Username:");
		passwordLabel 	= new JLabel("Password:");
		heightSpacing	= new JLabel();
		
		usernameInput 	= new JTextField();
		passwordInput 	= new JPasswordField();
		
		loginFormPanel.setLayout(new GridLayout(6, 1));
		loginFormPanel.setBackground(Style.FRAME_BACKGROUND);
		
		usernameLabel.setFont(Style.FONT_INPUT_LABEL);
		passwordLabel.setFont(Style.FONT_INPUT_LABEL);
		
		leftPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/8 , (int)size.getHeight()  ));
		rightPanel.setPreferredSize(new Dimension(	(int)size.getWidth()/3 , (int)size.getHeight()  ));
		topPanel.setPreferredSize(new Dimension(	(int)size.getWidth()   , (int)size.getHeight()/14));
		bottomPanel.setPreferredSize(new Dimension(	(int)size.getWidth()   , (int)size.getHeight()/4));
		leftPanel.setBackground(Style.FRAME_BACKGROUND);
		rightPanel.setBackground(Style.FRAME_BACKGROUND);
		topPanel.setBackground(Style.FRAME_BACKGROUND);	
		
		//BOTTOM
		bottomPanel.setLayout(new GridLayout(2, 1));
		JPanel spacer1 = new JPanel();
		spacer1.setBackground(Style.FRAME_BACKGROUND);
		bottomPanel.add(spacer1);
		bottomPanel.add(navigationPanel);
		
		//RIGHT
		rightPanel.setLayout(new GridLayout(7, 2));
		signUpButton	= new JButton("Sign Up");
		contactButton	= new JButton("Contact");
		aboutButton		= new JButton("About us");
		
		signUpButton.setBorder(null);
		signUpButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		contactButton.setBorder(null);
		contactButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		aboutButton.setBorder(null);
		aboutButton.setFont(Style.FONT_INPUT_LABEL_SMALLER);
		
		rightPanel.add(new JLabel());
		rightPanel.add(signUpButton);
		rightPanel.add(new JLabel());
		rightPanel.add(contactButton);
		rightPanel.add(new JLabel());
		rightPanel.add(aboutButton);
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		rightPanel.add(new JLabel());
		
		//INPUTS
		usernameInput.setBorder(new RoundedBorder(20));
		usernameInput.setFont(Style.FONT_INPUT_LABEL);
		usernameInput.setBackground(Style.FRAME_BACKGROUND);
		passwordInput.setBorder(new RoundedBorder(20));
		passwordInput.setFont(Style.FONT_INPUT_LABEL);
		passwordInput.setBackground(Style.FRAME_BACKGROUND);
		
		loginButton.setBorder(new RoundedBorder(40));
		loginButton.setFont(Style.FONT_INPUT_LABEL);
		loginButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				//System.out.println(usernameInput.getText()+" "+String.valueOf(passwordInput.getPassword()));
				if(dbHandler.authUser(usernameInput.getText(), String.valueOf(passwordInput.getPassword()))) {
					mf.changePage(mf.homePage);
				}
			}  
		});  
		
		contactButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
					URI mailto;
					try {
						mailto = new URI("mailto:kaifhughes4@gmail.com?subject=Support");
						desktop.mail(mailto);
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
				  throw new RuntimeException("desktop doesn't support mail to manually send an email to 'kaifhughes4@gmail.com'");
				}
			}  
		});  

		loginFormPanel.add(usernameLabel);
		loginFormPanel.add(usernameInput);
		loginFormPanel.add(passwordLabel);
		loginFormPanel.add(passwordInput);
		loginFormPanel.add(heightSpacing);
		loginFormPanel.add(loginButton);
		
		
		this.setSize(size);
		this.setOpaque(true);
		this.setBackground(Style.FRAME_BACKGROUND);
		this.setLayout(new BorderLayout());
		
		this.add(loginFormPanel	, BorderLayout.CENTER);
		this.add(leftPanel		, BorderLayout.LINE_START);
		this.add(rightPanel		, BorderLayout.LINE_END);
		this.add(topPanel		, BorderLayout.PAGE_START);
		this.add(bottomPanel	, BorderLayout.PAGE_END);
	}

}









