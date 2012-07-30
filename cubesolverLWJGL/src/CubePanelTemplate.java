import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import world.World;


public class CubePanelTemplate extends JPanel implements ActionListener {
	World world;
	JLabel title;
	JLabel bottomTextBox;
	JTextArea summary;
	JLabel summaryIcon;
	ArrayList<Hint> hints;
	
	public CubePanelTemplate(World world, CubePanelInfo panelInfo) {
		this.world = world;
		title = panelInfo.getTitle();
		bottomTextBox = panelInfo.getBottomTextBox();
		summary = panelInfo.getSummary();
		summaryIcon = panelInfo.getSummaryIcon();
		hints = panelInfo.getHints();

		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		
		ParallelGroup horizontalHintGroup = groupLayout.createParallelGroup(Alignment.TRAILING,false);
		ParallelGroup horizontalHintTextGroup = groupLayout.createParallelGroup(Alignment.LEADING);
		//sequential group is a bit more difficult
		SequentialGroup verticalGroup = groupLayout.createSequentialGroup()
				.addContainerGap()
				.addComponent(title)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(summaryIcon)
					.addComponent(summary, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
				
		for (int i = 0; i < hints.size(); i++) {
			JButton button = hints.get(i).getButton();
			JTextArea hintText = hints.get(i).getText();
			button.setActionCommand(button.getText());
			button.addActionListener(this);
			
			horizontalHintGroup.addComponent(button, Alignment.LEADING,GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			horizontalHintTextGroup.addComponent(hintText, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE);
			
			verticalGroup.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addComponent(button)
					.addComponent(hintText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(ComponentPlacement.RELATED);
		
		}
		verticalGroup.addComponent(bottomTextBox);
		verticalGroup.addContainerGap();
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(bottomTextBox, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(summary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
							.addComponent(summaryIcon))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(horizontalHintGroup)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(horizontalHintTextGroup)
							)
						)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(verticalGroup));
		setLayout(groupLayout);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		world.performTurns(World.parseTurnsFromString(e.getActionCommand()));
		
	}

}
