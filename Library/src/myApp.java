import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class myApp {
	static String id[] = new String[1000];
	static Member[] members = new Member[1000];
	static int memberIndex = 0;
	static int nbMembers = 0;
	static String MemberInputId;

	static JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		FlatDarculaLaf.setup();

		Admin admin = new Admin();
		Librarian librarian = new Librarian();
		Member member = new Member();
		Book book = new Book();
		JSONObject adminObject = Admin.getAdminObject();
		JSONArray librarianArray = null;
		JSONArray memberArray = null;
		JSONArray bookArray = null;

		//

		try {

			Member.updateBorrow();
			Member.updateReserve();
		} catch (Exception e) {
		}

		//
		// member.reserve("6", "7");
		// member.borrow("6", "7");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myApp window = new myApp();
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
	public myApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login to system");


		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(69, 47, 63, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(69, 104, 63, 14);
		frame.getContentPane().add(lblPassword);

		username = new JTextField();
		username.setBounds(142, 44, 108, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);

		password = new JPasswordField();
		password.setBounds(142, 101, 108, 20);
		frame.getContentPane().add(password);

		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(69, 147, 63, 23);
		frame.getContentPane().add(rdbtnAdmin);

		JRadioButton rdbtnLibrarian = new JRadioButton("Librarian");
		rdbtnLibrarian.setBounds(153, 147, 81, 23);
		frame.getContentPane().add(rdbtnLibrarian);

		JRadioButton rdbtnMember = new JRadioButton("Member");
		rdbtnMember.setBounds(249, 147, 109, 23);
		frame.getContentPane().add(rdbtnMember);

		ButtonGroup Selections = new ButtonGroup();
		Selections.add(rdbtnMember);
		Selections.add(rdbtnLibrarian);
		Selections.add(rdbtnAdmin);

		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String ucheck = username.getText();
				String pcheck = password.getText();

				if (rdbtnAdmin.isSelected()) {
					try {
						if (ucheck.equals((String) Admin.getAdminObject().get("id"))
								&& pcheck.equals((String) Admin.getAdminObject().get("password"))) {

							JOptionPane.showMessageDialog(null, "Login Successful", "Good",
									JOptionPane.INFORMATION_MESSAGE);
							AdminApp a = new AdminApp();
							frame.setVisible(false);
							a.main(null);

						} else
							JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
									JOptionPane.ERROR_MESSAGE);
					} catch (JSONException e) {
						JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				else if (rdbtnLibrarian.isSelected()) {

					try {
						JSONArray librarianArray = Librarian.getLibrarianObject().getJSONArray("librarians");
						boolean librarianFound = false;

						for (int i = 0; i < librarianArray.length(); ++i) {
							if (ucheck.equals((String) librarianArray.getJSONObject(i).get("id"))
									&& pcheck.equals((String) librarianArray.getJSONObject(i).get("password"))) {

								JOptionPane.showMessageDialog(null, "Login Successful", "Good",
										JOptionPane.INFORMATION_MESSAGE);
								librarianFound = true;
								frame.setVisible(false);
								LibrarianApp b = new LibrarianApp();
								b.main(null);
							}
						}

						if (!librarianFound) {
							JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (JSONException e) {
						JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
								JOptionPane.ERROR_MESSAGE);
					}

				}

				else if (rdbtnMember.isSelected()) {

					try {
						JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
						boolean memberFound = false;

						for (int i = 0; i < memberArray.length(); ++i) {
							if (ucheck.equals((String) memberArray.getJSONObject(i).get("id"))
									&& pcheck.equals((String) memberArray.getJSONObject(i).get("password"))) {

								JOptionPane.showMessageDialog(null, "Login Successful", "Good",
										JOptionPane.INFORMATION_MESSAGE);
								memberFound = true;
								MemberApp.main(null);
								frame.setVisible(false);
								MemberInputId = (String) memberArray.getJSONObject(i).get("id");
							}
						}

						if (!memberFound) {
							JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (JSONException e) {
						JOptionPane.showMessageDialog(null, "Invalid username or password", "Login error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		login.setBounds(69, 207, 89, 23);
		frame.getContentPane().add(login);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				password.setText(null);
				username.setText(null);

			}
		});
		btnReset.setBounds(187, 207, 89, 23);
		frame.getContentPane().add(btnReset);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(311, 207, 89, 23);
		frame.getContentPane().add(btnExit);

	}
}