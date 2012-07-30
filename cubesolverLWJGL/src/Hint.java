import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class Hint {
	/** The button containing the image and the algorithm*/
	private JButton hint1Button;
	/** The text containing a summary of the algorithm */
	private JTextArea hint1Text;

	/**
	 * The class which uses this should remember to add an ActionListener
	 * @param algorithm to be displayed on the button
	 * @param imagePath
	 * @param text to be displayed next to the button
	 */
	public Hint(String algorithm, String imagePath, String text) {
		hint1Button = new JButton(algorithm);
		hint1Button.setIcon(new ImageIcon(CubePanelOld.class.getResource(imagePath)));
		hint1Button.setVerticalTextPosition(SwingConstants.BOTTOM);
		hint1Button.setHorizontalTextPosition(SwingConstants.CENTER);
		
		hint1Text = new JTextArea(text);
		hint1Text.setLineWrap(true);
		hint1Text.setWrapStyleWord(true);
		hint1Text.setOpaque(false);
	}
	
	public JButton getButton() {
		return hint1Button;
	}
	
	public JTextArea getText() {
		return hint1Text;
	}
}
