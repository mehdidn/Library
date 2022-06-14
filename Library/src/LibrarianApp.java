import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.json.JSONArray;
import org.json.JSONException;

public class LibrarianApp {

	private JFrame frame;
	private JTextField bookIdSearch;
	private JTextField memberIdForBorrow;
	private JTextField bookIdForBorrow;
	private JTextField bookIdForGiveBack;
	private JTextField memberIdForGiveBack;
	private JTextField memberIdForAllBook;
	private JTextField memberIdForExtend;
	private JTextField bookIdForExtend;
	private JTextField daysForExtend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianApp window = new LibrarianApp();
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
	public LibrarianApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Librarian App");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultLookAndFeelDecorated(true);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Search in books with BookId");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(25, 45, 140, 47);
		frame.getContentPane().add(lblNewLabel);

		bookIdSearch = new JTextField();
		bookIdSearch.setBounds(175, 58, 86, 20);
		frame.getContentPane().add(bookIdSearch);
		bookIdSearch.setColumns(10);

		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JTextArea resutlBookSearch = new JTextArea();
		resutlBookSearch.setBounds(35, 103, 474, 172);
		frame.getContentPane().add(resutlBookSearch);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resutlBookSearch.setText("");

				try {

					String bookIdSearchStr = bookIdSearch.getText();
					resutlBookSearch.setText(Book.search(bookIdSearchStr).toString());
					resutlBookSearch.setLineWrap(true);

				} catch (Exception a) {

					JOptionPane.showMessageDialog(null, "Book not found");

				}
			}
		});
		btnNewButton.setBounds(286, 57, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(625, 11, 12, 264);
		frame.getContentPane().add(separator);

		JTextArea resultShowAllBook = new JTextArea();
		resultShowAllBook.setBounds(817, 27, 706, 236);
		frame.getContentPane().add(resultShowAllBook);

		JButton btnShowAllBooks = new JButton("Show all books");
		btnShowAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultShowAllBook.setText("");
				resultShowAllBook.setText(Book.getBookObject().toString());
				resultShowAllBook.setLineWrap(true);

			}
		});
		btnShowAllBooks.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllBooks.setBounds(658, 111, 125, 36);
		frame.getContentPane().add(btnShowAllBooks);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(25, 297, 1879, 11);
		frame.getContentPane().add(separator_1);

		JLabel lblShowBooksWith = new JLabel("Member Id");
		lblShowBooksWith.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowBooksWith.setBounds(25, 319, 125, 47);
		frame.getContentPane().add(lblShowBooksWith);

		JTextArea resultShowBooksWithMember = new JTextArea();
		resultShowBooksWithMember.setBounds(25, 377, 484, 249);
		frame.getContentPane().add(resultShowBooksWithMember);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(625, 319, 12, 360);
		frame.getContentPane().add(separator_2);

		JLabel lblBorrowThisBook = new JLabel("Lend this book to this member");
		lblBorrowThisBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBorrowThisBook.setBounds(635, 297, 175, 61);
		frame.getContentPane().add(lblBorrowThisBook);

		memberIdForBorrow = new JTextField();
		memberIdForBorrow.setBounds(724, 379, 86, 20);
		frame.getContentPane().add(memberIdForBorrow);
		memberIdForBorrow.setColumns(10);

		bookIdForBorrow = new JTextField();
		bookIdForBorrow.setColumns(10);
		bookIdForBorrow.setBounds(724, 402, 86, 20);
		frame.getContentPane().add(bookIdForBorrow);

		JLabel lblMemberId = new JLabel("Member Id");
		lblMemberId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberId.setBounds(658, 377, 86, 14);
		frame.getContentPane().add(lblMemberId);

		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBookId.setBounds(658, 407, 46, 14);
		frame.getContentPane().add(lblBookId);

		JButton btnBorrow = new JButton("Borrow !");
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberIdForBorrowStr = memberIdForBorrow.getText();
				String bookIdForBorrowStr = bookIdForBorrow.getText();

				try {

					boolean memberFound = false;
					for (int i = 1; i <= myApp.nbMembers; ++i) {
						if (myApp.id[i].equals(memberIdForBorrowStr)) {
							myApp.memberIndex = i;
							memberFound = true;
							break;
						}
					}

					if (!memberFound) {
						myApp.nbMembers++;
						myApp.id[myApp.nbMembers] = memberIdForBorrowStr;
						myApp.members[myApp.nbMembers] = new Member();
						myApp.members[myApp.nbMembers].updateNbReserve(memberIdForBorrowStr);
						myApp.members[myApp.nbMembers].updateNbBorrow(memberIdForBorrowStr);
						myApp.memberIndex = myApp.nbMembers;
					}

					myApp.members[myApp.memberIndex].borrow(memberIdForBorrowStr, bookIdForBorrowStr);
					JOptionPane.showMessageDialog(null, "Book Borrrowed Successfully");
					Member.setgetMemberArray(Member.getMemberObject().getJSONArray("members"));
					Book.setBookArray(Book.getBookObject().getJSONArray("books"));

				} catch (BookIsReserved e1) {
					JOptionPane.showMessageDialog(null, "Book is reserved already!");
				} catch (BookIsBorrowed e2) {

					JOptionPane.showMessageDialog(null, "Book is Borrowed already!");

				} catch (BookNotFound e3) {

					JOptionPane.showMessageDialog(null, "Book Not Found !");

				} catch (MemberPenalty e4) {
					JOptionPane.showMessageDialog(null, "You must pay the bill.");

				} catch (BookIsNotReserved e5) {
					JOptionPane.showMessageDialog(null, "This book not reserved to borrow or you have it already");

				}

			}
		});
		btnBorrow.setBounds(694, 452, 89, 23);
		frame.getContentPane().add(btnBorrow);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(860, 307, 12, 372);
		frame.getContentPane().add(separator_3);

		JLabel lblGiveBackBook = new JLabel("Give Back Book");
		lblGiveBackBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGiveBackBook.setBounds(876, 320, 111, 20);
		frame.getContentPane().add(lblGiveBackBook);

		bookIdForGiveBack = new JTextField();
		bookIdForGiveBack.setColumns(10);
		bookIdForGiveBack.setBounds(967, 402, 86, 20);
		frame.getContentPane().add(bookIdForGiveBack);

		JLabel label = new JLabel("Book Id");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(901, 407, 46, 14);
		frame.getContentPane().add(label);

		JLabel label_1 = new JLabel("Member Id");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(901, 377, 86, 14);
		frame.getContentPane().add(label_1);

		memberIdForGiveBack = new JTextField();
		memberIdForGiveBack.setColumns(10);
		memberIdForGiveBack.setBounds(967, 379, 86, 20);
		frame.getContentPane().add(memberIdForGiveBack);

		JButton btnGiveBackBook = new JButton("Give Back Book");
		btnGiveBackBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGiveBackBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookIdForGiveBackStr = bookIdForGiveBack.getText();
				String memberIdForGiveBackStr = memberIdForGiveBack.getText();

				try {

					boolean memberFound = false;
					for (int i = 1; i <= myApp.nbMembers; ++i) {
						if (myApp.id[i].equals(memberIdForGiveBackStr)) {
							myApp.memberIndex = i;
							memberFound = true;
							break;
						}
					}

					if (!memberFound) {
						myApp.nbMembers++;
						myApp.id[myApp.nbMembers] = memberIdForGiveBackStr;
						myApp.members[myApp.nbMembers] = new Member();
						myApp.members[myApp.nbMembers].updateNbReserve(memberIdForGiveBackStr);
						myApp.members[myApp.nbMembers].updateNbBorrow(memberIdForGiveBackStr);
						myApp.memberIndex = myApp.nbMembers;
					}

					myApp.members[myApp.memberIndex].giveBack(memberIdForGiveBackStr, bookIdForGiveBackStr);
					JOptionPane.showMessageDialog(null, "Book gaved back successfully.");
					Member.setgetMemberArray(Member.getMemberObject().getJSONArray("members"));
					Book.setBookArray(Book.getBookObject().getJSONArray("books"));

				} catch (BookIsNotBorrowed e1) {
					JOptionPane.showMessageDialog(null, "book is not found");
				} catch (BookIsBorrowed e2) {

					JOptionPane.showMessageDialog(null, "Book is Borrowed already!");

				} catch (BookNotFound e3) {

					JOptionPane.showMessageDialog(null, "Book Not Found !");

				} catch (MemberNotFound e4) {
					JOptionPane.showMessageDialog(null, "Member not found!");

				}

			}
		});
		btnGiveBackBook.setBounds(913, 455, 116, 36);
		frame.getContentPane().add(btnGiveBackBook);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(1088, 307, 12, 372);
		frame.getContentPane().add(separator_4);

		JTextArea showBorrowedBooks = new JTextArea();
		showBorrowedBooks.setBounds(1294, 319, 588, 236);
		frame.getContentPane().add(showBorrowedBooks);

		JButton btnShowAllBorrowed = new JButton("Show all borrowed books");
		btnShowAllBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showBorrowedBooks.setText("");

				JSONArray array = Book.getBookObject().getJSONArray("books");

				for (int i = 0; i < array.length(); ++i) {
					try {
						array.getJSONObject(i).get("borrowed"); // if borrowed by someone
						showBorrowedBooks.append((array.getJSONObject(i).toString() + "\n"));
						;

					} catch (JSONException e6) {
					}
				}

			}
		});
		btnShowAllBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllBorrowed.setBounds(1110, 422, 158, 36);
		frame.getContentPane().add(btnShowAllBorrowed);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 690, 1904, 11);
		frame.getContentPane().add(separator_5);

		memberIdForAllBook = new JTextField();
		memberIdForAllBook.setBounds(156, 332, 86, 20);
		frame.getContentPane().add(memberIdForAllBook);
		memberIdForAllBook.setColumns(10);

		JButton buttonShowAllBbooks = new JButton("Show all borrowed books");
		buttonShowAllBbooks.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonShowAllBbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberIdForAllBookStr = memberIdForAllBook.getText();
				resultShowBooksWithMember.setText("");
				boolean error = true;

				JSONArray array = Book.getBookObject().getJSONArray("books");

				for (int i = 0; i < array.length(); ++i) {
					try {
						if (array.getJSONObject(i).get("borrowed").equals(memberIdForAllBookStr)) { // books that
																									// borroed value
																									// equals member id

							resultShowBooksWithMember.append(array.getJSONObject(i).toString() + "\n");
							error = false;

						}
					} catch (JSONException e7) {
					}
				}
				
				if(error)
					JOptionPane.showMessageDialog(null, "there isnt borrowed book for this member id or wrong member id");

			}
		});
		buttonShowAllBbooks.setBounds(286, 331, 175, 23);
		frame.getContentPane().add(buttonShowAllBbooks);

		JLabel lblExtendBorrowedBook = new JLabel("Extend borrowed book for some day");
		lblExtendBorrowedBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExtendBorrowedBook.setBounds(25, 714, 204, 20);
		frame.getContentPane().add(lblExtendBorrowedBook);

		JLabel lblMemberId_1 = new JLabel("Member Id");
		lblMemberId_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMemberId_1.setBounds(35, 775, 63, 14);
		frame.getContentPane().add(lblMemberId_1);

		JLabel lblBookId_1 = new JLabel("Book Id");
		lblBookId_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBookId_1.setBounds(36, 813, 46, 14);
		frame.getContentPane().add(lblBookId_1);

		JLabel lblExtendDays = new JLabel("Extend days ?");
		lblExtendDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExtendDays.setBounds(35, 851, 74, 14);
		frame.getContentPane().add(lblExtendDays);

		memberIdForExtend = new JTextField();
		memberIdForExtend.setBounds(108, 772, 86, 20);
		frame.getContentPane().add(memberIdForExtend);
		memberIdForExtend.setColumns(10);

		bookIdForExtend = new JTextField();
		bookIdForExtend.setBounds(108, 810, 86, 20);
		frame.getContentPane().add(bookIdForExtend);
		bookIdForExtend.setColumns(10);

		daysForExtend = new JTextField();
		daysForExtend.setBounds(108, 848, 86, 20);
		frame.getContentPane().add(daysForExtend);
		daysForExtend.setColumns(10);

		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String memberIdForExtendStr = memberIdForExtend.getText();
				String bookIdForExtendStr = bookIdForExtend.getText();
				int daysForExtendInt = Integer.parseInt(daysForExtend.getText());

				boolean memberFound = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(memberIdForExtendStr)) {
						myApp.memberIndex = i;
						memberFound = true;
						break;
					}
				}

				if (!memberFound) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = memberIdForExtendStr;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(memberIdForExtendStr);
					myApp.members[myApp.nbMembers].updateNbBorrow(memberIdForExtendStr);
					myApp.memberIndex = myApp.nbMembers;
				}

				try {

					try {
						myApp.members[myApp.memberIndex].extension(memberIdForExtendStr, bookIdForExtendStr,
								daysForExtendInt);
					} catch (ExtensionOk ex) {
						ex.printStackTrace();
					}

				} catch (BookIsNotBorrowed e8) {
					JOptionPane.showMessageDialog(null, "Book is not reserved");
				} catch (BookNotFound k1) {

					JOptionPane.showMessageDialog(null, "Book not found!");

				} catch (BookIsReserved k1) {

					JOptionPane.showMessageDialog(null, "this book is reserved by someone else and cant extend");

				} catch (MemberNotFound k1) {

					JOptionPane.showMessageDialog(null, "There isnt member with this id");

				}

			}
		});
		btnNewButton_1.setBounds(229, 809, 89, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				myApp.frame.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(1766, 958, 125, 36);
		frame.getContentPane().add(btnNewButton_2);

	}
}
