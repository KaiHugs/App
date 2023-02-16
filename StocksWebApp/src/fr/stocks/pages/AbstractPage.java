package fr.stocks.pages;

import java.awt.Dimension;

import javax.swing.JPanel;

import fr.stocks.core.MainFrame;

/*
 * AbstractPage will be used as a mean to represent any page in MainFrame
 */
public abstract class AbstractPage extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected Dimension size;
	protected MainFrame mf;
	
	public AbstractPage(Dimension _size, MainFrame _mf) {
		size = _size;
		mf	 = _mf;
	}
	
	public String getName() { return name; }


}
