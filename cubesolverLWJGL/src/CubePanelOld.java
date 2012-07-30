import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import world.World;


public class CubePanelOld extends JPanel implements ActionListener {
	World world;

	/**
	 * Create the panel.
	 */
	public CubePanelOld(World world) {
		this.world = world;
		
		JLabel title = new JLabel("Stage One - Cross");
		title.setFont(new Font("Tahoma", Font.PLAIN, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel summaryIcon = new JLabel("");
		summaryIcon.setIcon(new ImageIcon(CubePanelOld.class.getResource("firststageicon.png")));
		
		JTextArea summary = new JTextArea("To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		summary.setLineWrap(true);
		summary.setWrapStyleWord(true);
		summary.setOpaque(false);
		
		JLabel bottomTextBox = new JLabel("ALGORITHM GOES HERE");
		bottomTextBox.setFont(new Font("Tahoma", Font.PLAIN, 26));
		bottomTextBox.setHorizontalAlignment(SwingConstants.CENTER);
		
		Hint hint1 = new Hint("F F", "s1hint1.png", "The edge is now oriented correctly so swivel it around into the correct position");
		JButton hint1Button = hint1.getButton();
		JTextArea hint1Text = hint1.getText();
//		JButton hint1Button = new JButton("F F");
//		hint1Button.setIcon(new ImageIcon(CubePanelOld.class.getResource("s1hint1.png")));
//		hint1Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//		hint1Button.setHorizontalTextPosition(SwingConstants.CENTER);
//		JTextArea hint1Text = new JTextArea("The edge is now oriented correctly so swivel it around into the correct position");
//		hint1Text.setWrapStyleWord(true);
//		hint1Text.setOpaque(false);
//		hint1Text.setLineWrap(true);
		
		Hint hint2 = new Hint("U' R' F R", "s1hint2.png", "The edge is not oriented correctly so we position and orient it at the same time. Notice the second R ensures we do not ruin other cross pieces");
		JButton hint2Button = hint2.getButton();
		JTextArea hint2Text = hint2.getText();
//		JButton hint2Button = new JButton("U' R' F R");
//		hint2Button.setIcon(new ImageIcon(CubePanelOld.class.getResource("s1hint2.png")));
//		hint2Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//		hint2Button.setHorizontalTextPosition(SwingConstants.CENTER);
//		JTextArea hint2Text = new JTextArea("The edge is not oriented correctly so we position and orient it at the same time. Notice the second R ensures we do not ruin other cross pieces");
//		hint2Text.setWrapStyleWord(true);
//		hint2Text.setOpaque(false);
//		hint2Text.setLineWrap(true);

		Hint hint3 = new Hint("L' U' L", "s1hint3.png", "If you need to move an edge piece which will then affect and correct cross piece remember to put the cross piece back");
		JButton hint3Button = hint3.getButton();
		JTextArea hint3Text = hint3.getText();
//		JButton hint3Button = new JButton("L' U' L");
//		hint3Button.setIcon(new ImageIcon(CubePanelOld.class.getResource("s1hint3.png")));
//		hint3Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//		hint3Button.setHorizontalTextPosition(SwingConstants.CENTER);
//		JTextArea hint3Text = new JTextArea("If you need to move an edge piece which will then affect and correct cross piece remember to put the cross piece back");
//		hint3Text.setWrapStyleWord(true);
//		hint3Text.setOpaque(false);
//		hint3Text.setLineWrap(true);
		
		hint1Button.setActionCommand(hint1Button.getText());
		hint1Button.addActionListener(this);
		hint2Button.setActionCommand(hint2Button.getText());
		hint2Button.addActionListener(this);
		hint3Button.setActionCommand(hint3Button.getText());
		hint3Button.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		
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
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(hint3Button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(hint2Button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(hint1Button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(hint3Text, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
								.addComponent(hint2Text, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
								.addComponent(hint1Text, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(summaryIcon)
						.addComponent(summary, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(hint1Button)
						.addComponent(hint1Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(hint2Button)
						.addComponent(hint2Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(hint3Button)
						.addComponent(hint3Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottomTextBox)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	public void actionPerformed(ActionEvent e) {
		world.performTurns(World.parseTurnsFromString(e.getActionCommand()));
	}
}
