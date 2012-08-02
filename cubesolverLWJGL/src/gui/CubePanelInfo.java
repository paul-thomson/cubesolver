package gui;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class CubePanelInfo {
	public static Font titleFont = new Font("Tahoma", Font.PLAIN, 26);
	private JLabel title;
	private JLabel summaryIcon;
	private JTextArea summary;
	private JLabel bottomTextBox;
	private ArrayList<Hint> hints;

	public CubePanelInfo(String titleText, String summaryIconPath, String summaryText) {
		title = new JLabel(titleText);
		title.setFont(titleFont);
		title.setHorizontalAlignment(SwingConstants.CENTER);

		summaryIcon = new JLabel("");
		summaryIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource(summaryIconPath)));

		summary = new JTextArea(summaryText);
		summary.setLineWrap(true);
		summary.setWrapStyleWord(true);
		summary.setOpaque(false);

		bottomTextBox = new JLabel("ALGORITHM GOES HERE");
		bottomTextBox.setFont(titleFont);
		bottomTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		
		hints = new ArrayList<Hint>();
	}
	
	public void addHint(String algorithm, String imagePath, String text) {
		hints.add(new Hint(algorithm, imagePath, text));
	}

	public static Font getTitleFont() {
		return titleFont;
	}

	public JLabel getTitle() {
		return title;
	}

	public JLabel getSummaryIcon() {
		return summaryIcon;
	}

	public JTextArea getSummary() {
		return summary;
	}

	public JLabel getBottomTextBox() {
		return bottomTextBox;
	}

	public ArrayList<Hint> getHints() {
		return hints;
	}
}
