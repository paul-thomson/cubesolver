import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


public class CubePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CubePanel() {
		
		JLabel lblStageOne = new JLabel("Stage One - Cross");
		lblStageOne.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblStageOne.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CubePanel.class.getResource("firststageicon.png")));
		
		JTextArea lblToGetThe = new JTextArea("To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		lblToGetThe.setLineWrap(true);
		lblToGetThe.setWrapStyleWord(true);
		
		JLabel lblAlgorithmGoesHere = new JLabel("ALGORITHM GOES HERE");
		lblAlgorithmGoesHere.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAlgorithmGoesHere.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblURF = new JLabel("U' R' F R");
		lblURF.setIcon(new ImageIcon(CubePanel.class.getResource("s1hint2.png")));
		
		JLabel lblf = new JLabel("2F");
		lblf.setIcon(new ImageIcon(CubePanel.class.getResource("s1hint1.png")));
		lblf.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlgorithmGoesHere, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addComponent(lblStageOne, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblToGetThe, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label))
						.addComponent(lblURF)
						.addComponent(lblf))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblStageOne)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(lblToGetThe, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
					.addComponent(lblf)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblURF)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAlgorithmGoesHere)
					.addContainerGap())
		);
		setLayout(groupLayout);
	
	}

}
