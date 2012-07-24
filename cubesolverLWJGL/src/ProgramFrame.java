import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;


public class ProgramFrame extends JFrame {

	private JPanel contentPane;
	final Canvas canvas = new Canvas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramFrame frame = new ProgramFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblStage = new JLabel("Stage 1 - Cross");
		lblStage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStage.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(ProgramFrame.class.getResource("/gui/firststageicon.png")));
		
		JTextArea txtrToGetThe = new JTextArea();
		txtrToGetThe.setWrapStyleWord(true);
		txtrToGetThe.setText("To get the cross you turn the top layer to get a bottom layer piece above the correct position. Then perform the correct algorithm below:");
		txtrToGetThe.setLineWrap(true);
		
		JLabel lblAlgorithmGoesHere = new JLabel("ALGORITHM GOES HERE");
		lblAlgorithmGoesHere.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAlgorithmGoesHere.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("U' R' F R");
		label.setIcon(new ImageIcon(ProgramFrame.class.getResource("/gui/s1hint2.png")));
		
		JLabel lblf = new JLabel("2F");
		lblf.setIcon(new ImageIcon(ProgramFrame.class.getResource("/gui/s1hint1.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAlgorithmGoesHere, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.addComponent(lblStage, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblf)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(txtrToGetThe, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
						.addComponent(label))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblStage, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(24)
							.addComponent(txtrToGetThe, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
					.addComponent(lblf)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAlgorithmGoesHere)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
