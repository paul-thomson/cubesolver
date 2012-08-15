package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.God;
import world.EventHandler;


public class Hint implements ActionListener {
	/** The button containing the image and the algorithm*/
	private JButton button;
	/** The text containing a summary of the algorithm */
	private JTextArea hintText;

	/**
	 * The class which uses this should remember to add an ActionListener
	 * @param algorithm to be displayed on the button
	 * @param imagePath
	 * @param text to be displayed next to the button
	 */
	public Hint(String algorithm, String imagePath, String text) {
		button = new JButton(algorithm);
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource(imagePath)));
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setActionCommand(button.getText());
		button.addActionListener(this);
		
		hintText = new JTextArea(text);
		hintText.setLineWrap(true);
		hintText.setWrapStyleWord(true);
		hintText.setOpaque(false);
	}
	
	public JButton getButton() {
		return button;
	}
	
	public JTextArea getText() {
		return hintText;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		God.performTurns(EventHandler.parseTurnsFromString(e.getActionCommand()));	
	}
}
