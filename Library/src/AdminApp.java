import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONException;

import javax.swing.JTextArea;

public class AdminApp {

	private JFrame frmAdminapp;
	private JTextField inputId;
	private JPasswordField inputPassword;
	private JTextField libName;
	private JTextField libFamily;
	private JTextField libBrithDay;
	private JTextField libGender;
	private JTextField libIdRemove;
	private JTextField libIdChange;
	private JTextField libWantChange;
	private JTextField libFinalData;
	private JTextField memberId;
	private JPasswordField memberPassword;
	private JTextField memberFamily;
	private JTextField memberName;
	private JTextField memberGender;
	private JTextField memberBrithDay;
	private JTextField memberIdRemove;
	private JTextField memberIdChange;
	private JTextField memberWantChange;
	private JTextField memberFinalData;
	private JTextField bookIdSearch;
	private JTextField memberIdForBorrowed;
	private JTextField removeBookId;
	private JTextField idForAddBook;
	private JTextField WnameForAddBook;
	private JTextField releaseAddBook;
	private JTextField nameForAddBook;
	private JTextField wNameAddBook;
	private JTextField bookFinalData;
	private JTextField bookWantedChange;
	private JTextField bookIdChange;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminApp window = new AdminApp();
					window.frmAdminapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminapp = new JFrame();
		frmAdminapp.setTitle("AdminApp");
		frmAdminapp.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmAdminapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminapp.getContentPane().setLayout(null);
		frmAdminapp.setDefaultLookAndFeelDecorated(true);

		JButton btnNewButton_1 = new JButton("Add librarian");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String librarianId = inputId.getText();
				String liblarianPassword = inputPassword.getText();
				String libNameStr = libName.getText();
				String libFamilyStr = libFamily.getText();
				String libGenderStr = libGender.getText();
				String libBirthDayStr = libBrithDay.getText();

				Map<String, String> libHash = new HashMap<>();
				libHash.put("first name", libNameStr);
				libHash.put("last name", libFamilyStr);
				libHash.put("id", librarianId);
				libHash.put("password", liblarianPassword);
				libHash.put("gender", libGenderStr);
				libHash.put("birth date", libBirthDayStr);

				try {
					Librarian.add(libHash);
					JOptionPane.showMessageDialog(null, "Librarian added successfully.");
				} catch (InvalidId i) {
					JOptionPane.showMessageDialog(null, "This Librarian is already exists with that id", "Invalid Librarian Id",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(556, 48, 127, 48);
		frmAdminapp.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_6 = new JButton("Search Labrarian");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PopupLibrarian.main(null);

			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_6.setBounds(1145, 50, 128, 45);
		frmAdminapp.getContentPane().add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("Show all members");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PopupAllMember.main(null);
			}
		});
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_7.setBounds(1796, 153, 128, 43);
		frmAdminapp.getContentPane().add(btnNewButton_7);

		JSeparator separator = new JSeparator();
		separator.setBounds(23, 413, 450, -52);
		frmAdminapp.getContentPane().add(separator);

		JLabel lblMembers = new JLabel("Librarian: ");
		lblMembers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMembers.setBounds(23, 11, 91, 20);
		frmAdminapp.getContentPane().add(lblMembers);

		inputId = new JTextField();
		inputId.setBounds(115, 45, 86, 20);
		frmAdminapp.getContentPane().add(inputId);
		inputId.setColumns(10);

		inputPassword = new JPasswordField();
		inputPassword.setBounds(115, 76, 86, 20);
		frmAdminapp.getContentPane().add(inputPassword);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblId.setBounds(69, 45, 18, 20);
		frmAdminapp.getContentPane().add(lblId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(53, 79, 46, 14);
		frmAdminapp.getContentPane().add(lblPassword);

		JLabel lblGendre = new JLabel("Gender ");
		lblGendre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGendre.setBounds(385, 79, 46, 14);
		frmAdminapp.getContentPane().add(lblGendre);

		JLabel lblName = new JLabel("first name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setBounds(211, 48, 58, 14);
		frmAdminapp.getContentPane().add(lblName);

		JLabel lblFamily = new JLabel("last name:");
		lblFamily.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFamily.setBounds(211, 79, 58, 14);
		frmAdminapp.getContentPane().add(lblFamily);

		libName = new JTextField();
		libName.setBounds(279, 45, 86, 20);
		frmAdminapp.getContentPane().add(libName);
		libName.setColumns(10);

		libFamily = new JTextField();
		libFamily.setBounds(279, 76, 86, 20);
		frmAdminapp.getContentPane().add(libFamily);
		libFamily.setColumns(10);

		JLabel lblNewLabel = new JLabel("BrithDay");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(385, 48, 46, 14);
		frmAdminapp.getContentPane().add(lblNewLabel);

		libBrithDay = new JTextField();
		libBrithDay.setBounds(441, 45, 86, 20);
		frmAdminapp.getContentPane().add(libBrithDay);
		libBrithDay.setColumns(10);

		libGender = new JTextField();
		libGender.setBounds(441, 76, 86, 20);
		frmAdminapp.getContentPane().add(libGender);
		libGender.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(693, 11, 18, 107);
		frmAdminapp.getContentPane().add(separator_1);

		JLabel lblIdForRemove = new JLabel("Id for remove librarian");
		lblIdForRemove.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIdForRemove.setBounds(704, 57, 120, 34);
		frmAdminapp.getContentPane().add(lblIdForRemove);

		libIdRemove = new JTextField();
		libIdRemove.setBounds(835, 64, 86, 20);
		frmAdminapp.getContentPane().add(libIdRemove);
		libIdRemove.setColumns(10);

		JButton btnRemoveLibrarian = new JButton("Remove librarian");
		btnRemoveLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRemoveLibrarian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String libIdRemoveStr = libIdRemove.getText();
					Librarian.remove(libIdRemoveStr);
					JOptionPane.showMessageDialog(null, "Librarian has been deleted successfuly.");

				} catch (Exception b) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "There isn`t librarain with this id.");
				}

			}
		});
		btnRemoveLibrarian.setBounds(947, 52, 148, 45);
		frmAdminapp.getContentPane().add(btnRemoveLibrarian);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(1123, 11, 18, 107);
		frmAdminapp.getContentPane().add(separator_2);

		JLabel lblMembers_1 = new JLabel("Members: ");
		lblMembers_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMembers_1.setBounds(23, 144, 50, 14);
		frmAdminapp.getContentPane().add(lblMembers_1);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 120, 1914, 2);
		frmAdminapp.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(1299, 11, 2, 100);
		frmAdminapp.getContentPane().add(separator_4);

		JLabel lblLibrarianIdFor = new JLabel("Librarian id for change information :");
		lblLibrarianIdFor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLibrarianIdFor.setBounds(1322, 14, 191, 14);
		frmAdminapp.getContentPane().add(lblLibrarianIdFor);

		libIdChange = new JTextField();
		libIdChange.setBounds(1525, 11, 86, 20);
		frmAdminapp.getContentPane().add(libIdChange);
		libIdChange.setColumns(10);

		JLabel lblWhatYouWant = new JLabel("What you want to change ? ");
		lblWhatYouWant.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWhatYouWant.setBounds(1322, 48, 191, 14);
		frmAdminapp.getContentPane().add(lblWhatYouWant);

		libWantChange = new JTextField();
		libWantChange.setBounds(1525, 45, 86, 20);
		frmAdminapp.getContentPane().add(libWantChange);
		libWantChange.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("This is new data that you want :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(1322, 82, 178, 14);
		frmAdminapp.getContentPane().add(lblNewLabel_1);

		libFinalData = new JTextField();
		libFinalData.setBounds(1525, 79, 86, 20);
		frmAdminapp.getContentPane().add(libFinalData);
		libFinalData.setColumns(10);

		JButton btnNewButton_11 = new JButton("Change Value");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String libFinalDataStr = libFinalData.getText();
					String libIdChangeStr = libIdChange.getText();
					String libWantChangeStr = libWantChange.getText();

					Librarian.changeInformation(libIdChangeStr, libWantChangeStr, libFinalDataStr);

					JOptionPane.showMessageDialog(null, "Data has been changed");

				} catch (Exception a) {

					JOptionPane.showMessageDialog(null, "Nothing Found");

				}
			}
		});
		btnNewButton_11.setBounds(1640, 34, 127, 43);
		frmAdminapp.getContentPane().add(btnNewButton_11);

		JLabel label = new JLabel("Id");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(69, 185, 18, 20);
		frmAdminapp.getContentPane().add(label);

		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(53, 219, 46, 14);
		frmAdminapp.getContentPane().add(label_1);

		JLabel lblLastName = new JLabel("last name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLastName.setBounds(211, 219, 58, 14);
		frmAdminapp.getContentPane().add(lblLastName);

		JLabel lblFirstName = new JLabel("first name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFirstName.setBounds(211, 188, 58, 14);
		frmAdminapp.getContentPane().add(lblFirstName);

		JLabel label_4 = new JLabel("Gender ");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(385, 219, 46, 14);
		frmAdminapp.getContentPane().add(label_4);

		JLabel label_5 = new JLabel("BrithDay");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(385, 188, 46, 14);
		frmAdminapp.getContentPane().add(label_5);

		memberId = new JTextField();
		memberId.setColumns(10);
		memberId.setBounds(115, 182, 86, 20);
		frmAdminapp.getContentPane().add(memberId);

		memberPassword = new JPasswordField();
		memberPassword.setBounds(115, 213, 86, 20);
		frmAdminapp.getContentPane().add(memberPassword);

		memberFamily = new JTextField();
		memberFamily.setColumns(10);
		memberFamily.setBounds(279, 213, 86, 20);
		frmAdminapp.getContentPane().add(memberFamily);

		memberName = new JTextField();
		memberName.setColumns(10);
		memberName.setBounds(279, 182, 86, 20);
		frmAdminapp.getContentPane().add(memberName);

		memberGender = new JTextField();
		memberGender.setColumns(10);
		memberGender.setBounds(441, 213, 86, 20);
		frmAdminapp.getContentPane().add(memberGender);

		memberBrithDay = new JTextField();
		memberBrithDay.setColumns(10);
		memberBrithDay.setBounds(441, 182, 86, 20);
		frmAdminapp.getContentPane().add(memberBrithDay);

		JButton btnAddMember = new JButton("Add member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberIdStr = memberId.getText();
				String memberPasswordStr = memberPassword.getText();
				String memberNameStr = memberName.getText();
				String memberFamilyStr = memberFamily.getText();
				String memberGenderStr = memberGender.getText();
				String memberBrithDayStr = memberBrithDay.getText();

				Map<String, String> memberHash = new HashMap<>();
				memberHash.put("first name", memberNameStr);
				memberHash.put("last name", memberFamilyStr);
				memberHash.put("id", memberIdStr);
				memberHash.put("password", memberPasswordStr);
				memberHash.put("gender", memberGenderStr);
				memberHash.put("brith", memberBrithDayStr);

				try {
					Member.add(memberHash);
					JOptionPane.showMessageDialog(null, "Member added successfully.");
				} catch (InvalidId i) {
					JOptionPane.showMessageDialog(null, "This Member is already exists with that id", "Invalid Member Id",
							JOptionPane.ERROR_MESSAGE);
				}

				// myApp.nbMembers++;

			}
		});
		btnAddMember.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddMember.setBounds(556, 185, 127, 48);
		frmAdminapp.getContentPane().add(btnAddMember);

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(693, 143, 18, 107);
		frmAdminapp.getContentPane().add(separator_5);

		JButton btnRemoveMember = new JButton("Remove member");

		memberIdRemove = new JTextField();
		memberIdRemove.setColumns(10);
		memberIdRemove.setBounds(835, 188, 86, 20);
		frmAdminapp.getContentPane().add(memberIdRemove);

		btnRemoveMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String memebrRemoveIdStr = memberIdRemove.getText();
					Member.remove(memebrRemoveIdStr);
					JOptionPane.showMessageDialog(null, "Member removed succesfully.");

				} catch (MemberNotFound c) {

					JOptionPane.showMessageDialog(null, "There isn`t member with this id");

				}

			}
		});
		btnRemoveMember.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRemoveMember.setBounds(947, 173, 148, 45);
		frmAdminapp.getContentPane().add(btnRemoveMember);

		JLabel lblIdForRemove_1 = new JLabel("Id for remove member");
		lblIdForRemove_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIdForRemove_1.setBounds(704, 185, 120, 34);
		frmAdminapp.getContentPane().add(lblIdForRemove_1);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(1123, 126, 18, 107);
		frmAdminapp.getContentPane().add(separator_6);

		JButton btnShowMember = new JButton("Search member");
		btnShowMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PopupMember.main(null);
			}
		});
		btnShowMember.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowMember.setBounds(1145, 173, 128, 45);
		frmAdminapp.getContentPane().add(btnShowMember);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(1299, 120, 2, 100);
		frmAdminapp.getContentPane().add(separator_7);

		JLabel lblMemberIdFor = new JLabel("Member id for change information :");
		lblMemberIdFor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberIdFor.setBounds(1322, 133, 191, 14);
		frmAdminapp.getContentPane().add(lblMemberIdFor);

		memberIdChange = new JTextField();
		memberIdChange.setColumns(10);
		memberIdChange.setBounds(1525, 130, 86, 20);
		frmAdminapp.getContentPane().add(memberIdChange);

		memberWantChange = new JTextField();
		memberWantChange.setColumns(10);
		memberWantChange.setBounds(1525, 164, 86, 20);
		frmAdminapp.getContentPane().add(memberWantChange);

		JLabel lblWhatYouWant_1 = new JLabel("What you want to change ?");
		lblWhatYouWant_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWhatYouWant_1.setBounds(1322, 167, 191, 14);
		frmAdminapp.getContentPane().add(lblWhatYouWant_1);

		JLabel label_8 = new JLabel("This is new data that you want :");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(1322, 201, 178, 14);
		frmAdminapp.getContentPane().add(label_8);

		memberFinalData = new JTextField();
		memberFinalData.setColumns(10);
		memberFinalData.setBounds(1525, 198, 86, 20);
		frmAdminapp.getContentPane().add(memberFinalData);

		JButton button = new JButton("Change Value");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String memberIdChangeStr = memberIdChange.getText();
					String memberWantChangeStr = memberWantChange.getText();
					String memberFinalDataStr = memberFinalData.getText();

					Member.changeInformation(memberIdChangeStr, memberWantChangeStr, memberFinalDataStr);
					JOptionPane.showMessageDialog(null, "Data has been changed successfuly.");

				} catch (Exception JSONException) {
					JOptionPane.showMessageDialog(null, "Nothing Found");
				}
			}
		});
		button.setBounds(1640, 153, 127, 43);
		frmAdminapp.getContentPane().add(button);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(10, 264, 1914, 2);
		frmAdminapp.getContentPane().add(separator_8);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBounds(1784, 11, 2, 100);
		frmAdminapp.getContentPane().add(separator_9);

		JSeparator separator_10 = new JSeparator();
		separator_10.setOrientation(SwingConstants.VERTICAL);
		separator_10.setBounds(1784, 133, 2, 100);
		frmAdminapp.getContentPane().add(separator_10);

		JButton btnShowAllLibrarians = new JButton("Show all librarians");
		btnShowAllLibrarians.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PopupAllLibrarians.main(null);

			}
		});
		btnShowAllLibrarians.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllLibrarians.setBounds(1796, 34, 128, 43);
		frmAdminapp.getContentPane().add(btnShowAllLibrarians);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBounds(10, 537, 1914, 2);
		frmAdminapp.getContentPane().add(separator_11);

		JLabel lblBooks = new JLabel("Books :");
		lblBooks.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBooks.setBounds(27, 282, 46, 14);
		frmAdminapp.getContentPane().add(lblBooks);

		JLabel lblSearchBookWith = new JLabel("Search book with id");
		lblSearchBookWith.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSearchBookWith.setBounds(1013, 586, 127, 34);
		frmAdminapp.getContentPane().add(lblSearchBookWith);

		bookIdSearch = new JTextField();
		bookIdSearch.setBounds(1140, 593, 86, 20);
		frmAdminapp.getContentPane().add(bookIdSearch);
		bookIdSearch.setColumns(10);

		JButton btnNewButton_12 = new JButton("Search");

		JTextArea bookSearchResult = new JTextArea();
		bookSearchResult.setBounds(1343, 568, 464, 249);
		frmAdminapp.getContentPane().add(bookSearchResult);

		JLabel lblResult = new JLabel("Result :");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblResult.setBounds(1269, 568, 46, 14);
		frmAdminapp.getContentPane().add(lblResult);

		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bookSearchResult.setText("");

				try {
					String bookIdSearchStr = bookIdSearch.getText();

					bookSearchResult.setText(Book.search(bookIdSearchStr).toString());
					bookSearchResult.setLineWrap(true);
				} catch (JSONException d) {

					JOptionPane.showMessageDialog(null, "Book Not Found");

				}catch (NullPointerException d) {

					JOptionPane.showMessageDialog(null, "Book id wrong");

				}


			}
		});
		btnNewButton_12.setBounds(1094, 631, 89, 23);
		frmAdminapp.getContentPane().add(btnNewButton_12);

		JSeparator separator_12 = new JSeparator();
		separator_12.setOrientation(SwingConstants.VERTICAL);
		separator_12.setBounds(693, 277, 18, 249);
		frmAdminapp.getContentPane().add(separator_12);

		JButton btnShowAllBooks = new JButton("Show all books");

		btnShowAllBooks.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllBooks.setBounds(160, 909, 120, 61);
		frmAdminapp.getContentPane().add(btnShowAllBooks);

		JTextArea resultAllBook = new JTextArea();
		resultAllBook.setBounds(378, 848, 695, 158);
		frmAdminapp.getContentPane().add(resultAllBook);

		JLabel lblResult_1 = new JLabel("Result :");
		lblResult_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblResult_1.setBounds(319, 893, 46, 92);
		frmAdminapp.getContentPane().add(lblResult_1);

		JLabel lblNewLabel_2 = new JLabel("Show borrowed books with member id :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(23, 550, 224, 92);
		frmAdminapp.getContentPane().add(lblNewLabel_2);

		memberIdForBorrowed = new JTextField();
		memberIdForBorrowed.setColumns(10);
		memberIdForBorrowed.setBounds(220, 586, 86, 20);
		frmAdminapp.getContentPane().add(memberIdForBorrowed);

		JButton button_1 = new JButton("Search");

		JTextArea resultBorrowedBooks = new JTextArea();
		resultBorrowedBooks.setBounds(385, 550, 474, 273);
		frmAdminapp.getContentPane().add(resultBorrowedBooks);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberIdForBorrowedStr = memberIdForBorrowed.getText();
				resultBorrowedBooks.setText("");

				JSONArray arrayBorrowed = Book.getBookObject().getJSONArray("books");
				
				boolean error = true;

				for (int i = 0; i < arrayBorrowed.length(); ++i) {
					try {
						if (arrayBorrowed.getJSONObject(i).get("borrowed").equals(memberIdForBorrowedStr)) { // books
																												// that
																												// borrowed
																												// value
																												// equals
																												// member
																												// id
							// System.out.println(arrayBorrowed.getJSONObject(i).toString(4));
							resultBorrowedBooks.append(arrayBorrowed.getJSONObject(i).toString() + "\n");
							error = false;
						}
					} catch (JSONException e1) {}
				}
				
				if(error)
					JOptionPane.showMessageDialog(null, "Member id wrong");
			}
		});
		button_1.setBounds(158, 636, 89, 23);
		frmAdminapp.getContentPane().add(button_1);

		removeBookId = new JTextField();
		removeBookId.setColumns(10);
		removeBookId.setBounds(881, 331, 86, 20);
		frmAdminapp.getContentPane().add(removeBookId);

		JButton btnRemoveBook = new JButton("remove book");
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String removeBookIdStr = removeBookId.getText();
					Book.remove(removeBookIdStr);
					JOptionPane.showMessageDialog(null, "Book Removed Successfully.");
				} catch (BookNotFound b1) {
					JOptionPane.showMessageDialog(null, "Book Not Found");
				}

			}
		});
		btnRemoveBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnRemoveBook.setBounds(800, 375, 148, 34);
		frmAdminapp.getContentPane().add(btnRemoveBook);

		JSeparator separator_13 = new JSeparator();
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(949, 550, 18, 249);
		frmAdminapp.getContentPane().add(separator_13);

		JLabel lblIdForRemove_2 = new JLabel("Id for remove book");
		lblIdForRemove_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblIdForRemove_2.setBounds(770, 324, 120, 34);
		frmAdminapp.getContentPane().add(lblIdForRemove_2);

		idForAddBook = new JTextField();
		idForAddBook.setColumns(10);
		idForAddBook.setBounds(145, 324, 86, 20);
		frmAdminapp.getContentPane().add(idForAddBook);

		JLabel lblId_1 = new JLabel("Id :");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblId_1.setBounds(86, 324, 18, 20);
		frmAdminapp.getContentPane().add(lblId_1);

		releaseAddBook = new JTextField();
		releaseAddBook.setColumns(10);
		releaseAddBook.setBounds(378, 352, 86, 20);
		frmAdminapp.getContentPane().add(releaseAddBook);

		nameForAddBook = new JTextField();
		nameForAddBook.setColumns(10);
		nameForAddBook.setBounds(378, 324, 86, 20);
		frmAdminapp.getContentPane().add(nameForAddBook);

		JLabel label_10 = new JLabel("Name :");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_10.setBounds(276, 327, 46, 14);
		frmAdminapp.getContentPane().add(label_10);

		JLabel rDAddBOok = new JLabel("release date :");
		rDAddBOok.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rDAddBOok.setBounds(254, 355, 68, 14);
		frmAdminapp.getContentPane().add(rDAddBOok);

		JLabel lblWriterName = new JLabel("Writer name :");
		lblWriterName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWriterName.setBounds(56, 349, 68, 20);
		frmAdminapp.getContentPane().add(lblWriterName);

		wNameAddBook = new JTextField();
		wNameAddBook.setColumns(10);
		wNameAddBook.setBounds(145, 349, 86, 20);
		frmAdminapp.getContentPane().add(wNameAddBook);

		JButton btnAddBook = new JButton("Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String idForAddBookStr = idForAddBook.getText();
				String releaseAddBookStr = releaseAddBook.getText();
				String nameForAddBookStr = nameForAddBook.getText();
				String wNameAddBookStr = wNameAddBook.getText();

				Map<String, String> bookMap = new HashMap<>();
				bookMap.put("id", idForAddBookStr);
				bookMap.put("name", nameForAddBookStr);
				bookMap.put("writer name", wNameAddBookStr);
				bookMap.put("release date", releaseAddBookStr);

				try {
					Book.add(bookMap);
					JOptionPane.showMessageDialog(null, "Book added successfully.");
				} catch (InvalidId i) {
					JOptionPane.showMessageDialog(null, "This book is already exists with that id", "Invalid Book Id",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAddBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddBook.setBounds(517, 328, 127, 48);
		frmAdminapp.getContentPane().add(btnAddBook);

		JSeparator separator_15 = new JSeparator();
		separator_15.setBounds(10, 847, 1914, 2);
		frmAdminapp.getContentPane().add(separator_15);

		JLabel lblBookIdFor = new JLabel("Book id for change information :");
		lblBookIdFor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBookIdFor.setBounds(1094, 305, 191, 14);
		frmAdminapp.getContentPane().add(lblBookIdFor);

		JLabel label_9 = new JLabel("What you want to change ? Exp : name\r\n");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(1094, 339, 191, 14);
		frmAdminapp.getContentPane().add(label_9);

		JLabel label_11 = new JLabel("This is new data that you want :");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(1094, 373, 178, 14);
		frmAdminapp.getContentPane().add(label_11);

		bookFinalData = new JTextField();
		bookFinalData.setColumns(10);
		bookFinalData.setBounds(1297, 370, 86, 20);
		frmAdminapp.getContentPane().add(bookFinalData);

		bookWantedChange = new JTextField();
		bookWantedChange.setColumns(10);
		bookWantedChange.setBounds(1297, 336, 86, 20);
		frmAdminapp.getContentPane().add(bookWantedChange);

		bookIdChange = new JTextField();
		bookIdChange.setColumns(10);
		bookIdChange.setBounds(1297, 302, 86, 20);
		frmAdminapp.getContentPane().add(bookIdChange);

		JButton button_2 = new JButton("Change Value");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookFinalDataStr = bookFinalData.getText();
				String bookWantedChangeStr = bookWantedChange.getText();
				String bookIdChangeStr = bookIdChange.getText();

				try {

					Book.changeInformation(bookIdChangeStr, bookWantedChangeStr, bookFinalDataStr);
					JOptionPane.showMessageDialog(null, "book information changed successfully");

				} catch (MemberNotFound e1) {

					JOptionPane.showMessageDialog(null, "Invalind entered data ...");
				}

			}
		});
		button_2.setBounds(1412, 325, 127, 43);
		frmAdminapp.getContentPane().add(button_2);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmAdminapp.dispose();
				myApp.frame.setVisible(true);

			}
		});
		btnLogOut.setBounds(1770, 952, 127, 43);
		frmAdminapp.getContentPane().add(btnLogOut);

		JSeparator separator_16 = new JSeparator();
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setBounds(1055, 264, 18, 262);
		frmAdminapp.getContentPane().add(separator_16);

		btnShowAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultAllBook.setText("");
				resultAllBook.setText(Book.getBookObject().toString());
				resultAllBook.setLineWrap(true);

			}
		});

	}
}
