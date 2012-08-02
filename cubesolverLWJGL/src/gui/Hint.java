package gui;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class Hint {
	/** The button containing the image and the algorithm*/
	private JButton hintButton;
	/** The text containing a summary of the algorithm */
	private JTextArea hintText;

	/**
	 * The class which uses this should remember to add an ActionListener
	 * @param algorithm to be displayed on the button
	 * @param imagePath
	 * @param text to be displayed next to the button
	 */
	public Hint(String algorithm, String imagePath, String text) {
		hintButton = new JButton(algorithm);
		hintButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource(imagePath)));
		hintButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		hintButton.setHorizontalTextPosition(SwingConstants.CENTER);
		
		hintText = new JTextArea(text);
		hintText.setLineWrap(true);
		hintText.setWrapStyleWord(true);
		hintText.setOpaque(false);
	}
	
	public JButton getButton() {
		return hintButton;
	}
	
	public JTextArea getText() {
		return hintText;
	}
}
