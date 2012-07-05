package gui;
import de.matthiasmann.twl.DialogLayout;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.ResizableFrame;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.ToggleButton;
import de.matthiasmann.twl.Widget;


public class UserInterface extends Widget {
	
	private final ToggleButton testButton;
	private final TextArea textArea;
	private final DialogLayout dLayout;
	private final ResizableFrame resizableFrame; 
	
	public UserInterface() {
		
		testButton = new ToggleButton();
		testButton.setTheme("togglebutton");
        add(testButton);	
        
        textArea = new TextArea();
        textArea.setTheme("textarea");
        add(textArea);
        
        dLayout = new DialogLayout();
        dLayout.setTheme("dialoglayout");
        add(dLayout);
        
        resizableFrame = new ResizableFrame();
        resizableFrame.setTheme("resizableframe");
        add(resizableFrame);
	}
	
	@Override
    protected void layout() {
		
//		textArea.adjustSize();
//		textArea.setSize(100, 100);
//		textArea.setPosition(400,400);
		
//		dLayout.adjustSize();
//		dLayout.setPosition(400,400);
		
		resizableFrame.setSize(300,600);
		resizableFrame.setPosition(500,0);
		
//		resizableFrame.add(testButton);
//		testButton.adjustSize();
//		testButton.setText("LOLOLOLOLOLOL");
//		testButton.setPosition(0, 0);
	}
	
	@Override
    protected boolean handleEvent(Event evt) {
		
		return false;
	}

}
