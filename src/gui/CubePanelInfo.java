package gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import main.God;

import solver.Solver;

public class CubePanelInfo implements ActionListener {
	public static Font titleFont = new Font("Tahoma", Font.PLAIN, 26);
	private JLabel title;
	private JLabel summaryIcon;
	private JTextArea summary;
	private JButton helperButton;
	private JButton previousButton;
	private JButton nextButton;
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

		helperButton = new JButton("Need help?");
		helperButton.setFont(titleFont);
		helperButton.setHorizontalAlignment(SwingConstants.CENTER);
		helperButton.addActionListener(new Solver());

		previousButton = new JButton("<-");
		previousButton.setFont(titleFont);
		previousButton.addActionListener(this);
		
		nextButton = new JButton("->");
		nextButton.setFont(titleFont);
		nextButton.addActionListener(this);
		
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

	public JButton getBottomTextBox() {
		return helperButton;
	}

	public ArrayList<Hint> getHints() {
		return hints;
	}
	
	public JButton getPreviousButton() {
		return previousButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == previousButton) {
			God.previousStage();
		} else {
			God.nextStage();
		}
		
	}
}
