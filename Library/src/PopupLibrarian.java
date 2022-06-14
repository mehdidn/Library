import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PopupLibrarian {

	private JFrame frame;
	private JTextField librarianId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupLibrarian window = new PopupLibrarian();
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
	public PopupLibrarian() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLibrarianId = new JLabel("Librarian id");
		lblLibrarianId.setBounds(106, 34, 62, 33);
		frame.getContentPane().add(lblLibrarianId);

		librarianId = new JTextField();
		librarianId.setBounds(178, 40, 86, 20);
		frame.getContentPane().add(librarianId);
		librarianId.setColumns(10);

		JButton btnSubmit = new JButton("Submit");

		JTextArea soutLib = new JTextArea();
		soutLib.setBounds(10, 124, 414, 126);
		frame.getContentPane().add(soutLib);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					soutLib.setText("");
					String id = librarianId.getText();
					soutLib.setText(Librarian.search(id).toString());
					soutLib.setLineWrap(true);

					JOptionPane.showMessageDialog(null, "Librarian found");

				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, "Librarian not found");
				}
			}
		});
		btnSubmit.setBounds(150, 88, 89, 23);
		frame.getContentPane().add(btnSubmit);

	}
}
