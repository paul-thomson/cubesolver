//package gui;
//import de.matthiasmann.twl.Button;
//import de.matthiasmann.twl.DialogLayout;
//import de.matthiasmann.twl.Event;
//import de.matthiasmann.twl.Label;
//import de.matthiasmann.twl.ResizableFrame;
//import de.matthiasmann.twl.TextArea;
//import de.matthiasmann.twl.Widget;
//import de.matthiasmann.twl.textarea.SimpleTextAreaModel;
//
//
//public class UserInterface extends Widget {
//
//	private final ResizableFrame resizableFrame;
//
//	private final DialogLayout layout;
////	private final FirstStageIcon icon;
//	private final Label title;
//	private final TextArea description;
//
//	private final Button hint1Image;
//	private final Label hint1Text;
//
//	private final Button hint2Image;
//	private final Label hint2Text;
//
//	private final String crossDescription = "Orient and position the first layer edges. Filler " +
//			"text blah blah lolly lol";
//
//	public UserInterface() {
//
//		layout = new DialogLayout();
//		layout.setTheme("dialoglayout");
//
//		resizableFrame = new ResizableFrame();
//		resizableFrame.setTheme("resizableframe");
//		add(resizableFrame);
//		
////		icon = new FirstStageIcon();
////		icon.setTheme("firststageicon");
//		//        add(icon);
//
//		title = new Label("Stage 1");
//		title.setTheme("label");
//		//        add(title);
//
//		SimpleTextAreaModel stam = new SimpleTextAreaModel();
//		stam.setText(crossDescription,false);
//		description = new TextArea(stam);
//		description.setTheme("label");
//		//        add(description);
//
//		hint1Image = new Button();
//		hint1Image.setTheme("firststageicon");
//		//        add(hint1Image);
//
//		hint1Text = new Label("This is the first hint");
//		hint1Text.setTheme("label");
//		//        add(hint1Text);
//
//		hint2Image = new Button();
//		hint2Image.setTheme("firststageicon");
//		//        add(hint2Image);
//
//		hint2Text = new Label("This is the second hint");
//		hint2Text.setTheme("label");
//		//        add(hint2Text);
//
//		DialogLayout.Group hTitle = layout.createSequentialGroup()
//								.addGap()
//				.addWidget(title);
//		DialogLayout.Group hSummary = layout.createSequentialGroup().addWidget(description)
//																	.addGap()
//																	.addWidget(icon);
//		DialogLayout.Group hTip1 = layout.createSequentialGroup(hint1Image,hint1Text);
//		DialogLayout.Group hTip2 = layout.createSequentialGroup(hint2Image,hint2Text);
//		layout.setHorizontalGroup(layout.createParallelGroup(hTitle,hSummary,hTip1,hTip2));
//
//		DialogLayout.Group vDescription = layout.createSequentialGroup(description).addGap();
//		DialogLayout.Group vIcon = layout.createSequentialGroup(icon).addGap();
//		
//		layout.setVerticalGroup(layout.createSequentialGroup(
//				layout.createParallelGroup()
//				.addWidget(title),
//				layout.createParallelGroup().addGroup(vDescription).addGroup(vIcon),
//				layout.createParallelGroup(hint1Text,hint1Image),
//				layout.createParallelGroup(hint2Text,hint2Image)
//				));
////		add(layout);
//		resizableFrame.add(layout);
//		
//	}
//
//	@Override
//	protected void layout() {
////		layout.adjustSize();
////		layout.setPosition(500, 0);
//		
//				resizableFrame.setSize(300,600);
//				resizableFrame.setPosition(500,0);
//		//		
////				icon.adjustSize();
////				icon.setSize(200, 200);
//		//		icon.setPosition(680,60);
//		//		
////				title.adjustSize();
////				title.setPosition(690, 30);
//		//		
////				description.adjustSize();
////				description.setSize(100,100);
//		//		description.setPosition(520, 60);
//		//		
////				hint1Image.adjustSize();
////				hint1Image.setSize(50,50);
//		//		hint1Image.setPosition(520,300);
//		//
////				hint1Text.adjustSize();
//		//		hint1Text.setPosition(580,300);
//		//
////				hint2Text.adjustSize();
//		//		hint2Text.setPosition(580,370);
//		//		
////				hint2Image.adjustSize();
////				hint2Image.setSize(50,50);
////				hint2Image.setPosition(100,100);
//
//	}
//
//	@Override
//	protected boolean handleEvent(Event evt) {
//
//		return false;
//	}
//
//}
