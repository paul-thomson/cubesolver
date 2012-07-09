package gui;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.Label;
import de.matthiasmann.twl.Widget;

public abstract class Hint extends Widget {
	
	//TODO getters/setters?
	protected Label hintText;
	protected HintImage image;
	
	public Hint() {
		setImageTheme();
		setHintText();
	}
	
	public abstract void setImageTheme();
	
	public abstract void setHintText();
	
	
	class HintImage extends Button {
		@Override
		public int getPreferredInnerHeight(){
			return 50; //TODO
		}
		
		@Override
		public int getPreferredInnerWidth() {
			return 50; //TODO;
		}
	}
}
