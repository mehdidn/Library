import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class PopupAllMember {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupAllMember window = new PopupAllMember();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PopupAllMember() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 609, 473);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea resultAllMembers = new JTextArea();
		resultAllMembers.setBounds(10, 11, 573, 412);
		frame.getContentPane().add(resultAllMembers);

		resultAllMembers.setText(Member.getMemberObject().toString());
		resultAllMembers.setLineWrap(true);

	}
}
