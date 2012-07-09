package gui;

import de.matthiasmann.twl.Label;

public class Hint1 extends Hint {
	
	private String themeName = "hint1";
	private String labelText = "This is the hint";
	
	public Hint1() {
		setImageTheme();
		setHintText();
	}
	
	@Override
	public void setImageTheme() {
		this.image = new HintImage();
		image.setTheme(themeName);
	}

	@Override
	public void setHintText() {
		this.hintText  = new Label(labelText);
	}
}
