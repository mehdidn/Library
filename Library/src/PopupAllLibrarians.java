import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class PopupAllLibrarians {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupAllLibrarians window = new PopupAllLibrarians();
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
	public PopupAllLibrarians() {
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

		JTextArea resultAllLibrarians = new JTextArea();
		resultAllLibrarians.setBounds(10, 11, 573, 412);
		frame.getContentPane().add(resultAllLibrarians);

		resultAllLibrarians.setText(Librarian.getLibrarianObject().toString());
		resultAllLibrarians.setLineWrap(true);

	}

}
