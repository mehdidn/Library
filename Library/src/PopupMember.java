import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class PopupMember {

	private JFrame frame;
	private JTextField memberIdShow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopupMember window = new PopupMember();
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
	public PopupMember() {
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

		JLabel lblMemberId = new JLabel("Member id ");
		lblMemberId.setBounds(109, 42, 67, 14);
		frame.getContentPane().add(lblMemberId);

		memberIdShow = new JTextField();
		memberIdShow.setBounds(186, 39, 86, 20);
		frame.getContentPane().add(memberIdShow);
		memberIdShow.setColumns(10);

		JButton btnNewButton = new JButton("Submit");

		JTextArea soutMember = new JTextArea();
		soutMember.setBounds(10, 118, 414, 132);
		frame.getContentPane().add(soutMember);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					
					soutMember.setText("");
					String memberIdShowStr = memberIdShow.getText();
					soutMember.setText(Member.search(memberIdShowStr).toString());
					soutMember.setLineWrap(true);
					JOptionPane.showMessageDialog(null, "member found");

				} catch (JSONException f) {

					JOptionPane.showMessageDialog(null, "member not found");

				}

			}
		});
		btnNewButton.setBounds(161, 84, 89, 23);
		frame.getContentPane().add(btnNewButton);

	}

}
