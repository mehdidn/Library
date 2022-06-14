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
import org.json.JSONObject;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MemberApp {

	private JFrame frame;
	private JTextField bookIdForReserver;
	private JTextField bookIdForBorrow;
	private JTextField bookIdForGiveBack;
	private JTextField bookIdForExtend;
	private JTextField daysForExtend;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberApp window = new MemberApp();
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
	public MemberApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setTitle("MemberApp");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultLookAndFeelDecorated(true);
		frame.getContentPane().setLayout(null);

		JLabel lblShowAllBooks = new JLabel("Show all books");
		lblShowAllBooks.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowAllBooks.setBounds(10, 11, 77, 14);
		frame.getContentPane().add(lblShowAllBooks);

		JTextArea showAllBook = new JTextArea();
		showAllBook.setBounds(119, 38, 451, 188);
		frame.getContentPane().add(showAllBook);

		JButton btnShowAllBook = new JButton("Show all book");
		btnShowAllBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				showAllBook.setText("");
				showAllBook.setText(Book.getBookObject().toString());
				showAllBook.setLineWrap(true);

			}
		});
		btnShowAllBook.setBounds(10, 107, 99, 23);
		frame.getContentPane().add(btnShowAllBook);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(640, 11, 2, 215);
		frame.getContentPane().add(separator);

		JLabel lblReserveBook = new JLabel("Reserve Book");
		lblReserveBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblReserveBook.setBounds(664, 11, 77, 14);
		frame.getContentPane().add(lblReserveBook);

		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBookId.setBounds(664, 68, 46, 14);
		frame.getContentPane().add(lblBookId);

		bookIdForReserver = new JTextField();
		bookIdForReserver.setBounds(720, 65, 86, 20);
		frame.getContentPane().add(bookIdForReserver);
		bookIdForReserver.setColumns(10);

		JButton btnReserve = new JButton("Reserve !");
		btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookIdForReserverStr = bookIdForReserver.getText();

				boolean found = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(myApp.MemberInputId)) {
						myApp.memberIndex = i;
						found = true;
						break;
					}
				}

				if (!found) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = myApp.MemberInputId;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
					myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
					myApp.memberIndex = myApp.nbMembers;
				}

				try {

					myApp.members[myApp.memberIndex].reserve(myApp.MemberInputId, bookIdForReserverStr);
					JOptionPane.showMessageDialog(null, "Book reserved successfully.");

				} catch (MemberPenalty d1) {

					JOptionPane.showMessageDialog(null, "You have penalty .. you must pay the bill first");
				} catch (MemberNotFound d2) {

				} catch (BookNotFound d2) {

					JOptionPane.showMessageDialog(null, "Book Not Found");

				} catch (BookIsBorrowedByHimSelf d3) {

					JOptionPane.showMessageDialog(null, "This book borrowed by your self already");
				} catch (BookIsReserved d3) {

					JOptionPane.showMessageDialog(null, "This book is reserved by someone else or you");
				}

				Member.setgetMemberArray(Member.getMemberObject().getJSONArray("members"));
				Book.setBookArray(Book.getBookObject().getJSONArray("books"));
			}
		});
		btnReserve.setBounds(825, 64, 89, 23);
		frame.getContentPane().add(btnReserve);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(924, 11, 2, 215);
		frame.getContentPane().add(separator_1);

		JLabel label = new JLabel("Book Id");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(948, 68, 46, 14);
		frame.getContentPane().add(label);

		JLabel lblBorrowBook = new JLabel("Borrow Book");
		lblBorrowBook.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBorrowBook.setBounds(951, 11, 77, 14);
		frame.getContentPane().add(lblBorrowBook);

		bookIdForBorrow = new JTextField();
		bookIdForBorrow.setColumns(10);
		bookIdForBorrow.setBounds(1004, 65, 86, 20);
		frame.getContentPane().add(bookIdForBorrow);

		JButton btnBorrow = new JButton("Borrow !");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookIdForBorrowStr = bookIdForBorrow.getText();

				boolean found = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(myApp.MemberInputId)) {
						myApp.memberIndex = i;
						found = true;
						break;
					}
				}

				if (!found) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = myApp.MemberInputId;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
					myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
					myApp.memberIndex = myApp.nbMembers;
				}

				try {

					myApp.members[myApp.memberIndex].borrow(myApp.MemberInputId, bookIdForBorrowStr);
					JOptionPane.showMessageDialog(null, "borrowed successfully");

				} catch (MemberPenalty h1) {

					JOptionPane.showMessageDialog(null, "You have penalty .. you must pay the bill");

				} catch (BookIsNotReserved h2) {

					JOptionPane.showMessageDialog(null,
							"you didn't reserve this book or your reserve time has ended or you borrowed already");

				} catch (BookIsReserved h3) {

					JOptionPane.showMessageDialog(null, "this book is reserved by someone else");

				} catch (BookIsBorrowed h4) {

					JOptionPane.showMessageDialog(null, "This book is borrowed");
				} catch (BookNotFound h5) {

					JOptionPane.showMessageDialog(null, "Book not found");
				}

				Member.setgetMemberArray(Member.getMemberObject().getJSONArray("members"));
				Book.setBookArray(Book.getBookObject().getJSONArray("books"));

			}
		});
		btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBorrow.setBounds(1110, 64, 89, 23);
		frame.getContentPane().add(btnBorrow);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(1232, 11, 2, 215);
		frame.getContentPane().add(separator_2);

		JLabel lblGiveBack = new JLabel("Give back");
		lblGiveBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGiveBack.setBounds(1244, 11, 77, 14);
		frame.getContentPane().add(lblGiveBack);

		JLabel label_2 = new JLabel("Book Id");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(1244, 68, 46, 14);
		frame.getContentPane().add(label_2);

		bookIdForGiveBack = new JTextField();
		bookIdForGiveBack.setColumns(10);
		bookIdForGiveBack.setBounds(1300, 65, 86, 20);
		frame.getContentPane().add(bookIdForGiveBack);

		JButton btnGiveBack = new JButton("Give back");
		btnGiveBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookIdForGiveBackStr = bookIdForGiveBack.getText();

				boolean found = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(myApp.MemberInputId)) {
						myApp.memberIndex = i;
						found = true;
						break;
					}
				}

				if (!found) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = myApp.MemberInputId;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
					myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
					myApp.memberIndex = myApp.nbMembers;
				}

				try {

					myApp.members[myApp.memberIndex].giveBack(myApp.MemberInputId, bookIdForGiveBackStr);
					JOptionPane.showMessageDialog(null, "the book backed successfuly...");

				} catch (BookIsBorrowed j1) {

					JOptionPane.showMessageDialog(null, "someone else borrowed this book");
				} catch (BookIsNotBorrowed j2) {

					JOptionPane.showMessageDialog(null, "you didn't borrow this book");
				} catch (BookNotFound j3) {

					JOptionPane.showMessageDialog(null, "book is not found");
				} catch (MemberNotFound j4) {
				}

				Member.setgetMemberArray(Member.getMemberObject().getJSONArray("members"));
				Book.setBookArray(Book.getBookObject().getJSONArray("books"));

			}
		});
		btnGiveBack.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGiveBack.setBounds(1406, 64, 89, 23);
		frame.getContentPane().add(btnGiveBack);

		JLabel lblExtendBorrow = new JLabel("Extend Borrow");
		lblExtendBorrow.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblExtendBorrow.setBounds(1560, 11, 77, 14);
		frame.getContentPane().add(lblExtendBorrow);

		JLabel label_3 = new JLabel("Book Id");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(1587, 68, 46, 14);
		frame.getContentPane().add(label_3);

		bookIdForExtend = new JTextField();
		bookIdForExtend.setColumns(10);
		bookIdForExtend.setBounds(1643, 65, 86, 20);
		frame.getContentPane().add(bookIdForExtend);

		JLabel lblDays = new JLabel("Days");
		lblDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDays.setBounds(1587, 96, 46, 14);
		frame.getContentPane().add(lblDays);

		daysForExtend = new JTextField();
		daysForExtend.setColumns(10);
		daysForExtend.setBounds(1643, 93, 86, 20);
		frame.getContentPane().add(daysForExtend);

		JButton btnGetExtendDays = new JButton("Get extend days");
		btnGetExtendDays.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String bookIdForExtendStr = bookIdForExtend.getText();
				int daysForExtendInt = Integer.parseInt(daysForExtend.getText());
				JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
				JSONArray bookArray = Book.getBookObject().getJSONArray("books");

				boolean found = false;
                for (int i = 1; i <= myApp.nbMembers; ++i) {
                    if (myApp.id[i].equals(myApp.MemberInputId)) {
                    	myApp.memberIndex = i;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                	myApp.nbMembers++;
                	myApp.id[myApp.nbMembers] = myApp.MemberInputId;
                	myApp.members[myApp.nbMembers] = new Member();
                	myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
                	myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
                	myApp.memberIndex = myApp.nbMembers;
                }

                boolean out = false;
                for (int i = 0; i < memberArray.length(); ++i) {
                    if (memberArray.getJSONObject(i).get("id").equals(myApp.MemberInputId)) {
                        for (int j = 1; j <= myApp.members[myApp.memberIndex].getNbBorrow(); ++j) {
                            if (memberArray.getJSONObject(i).get("borrowed"+j).equals(bookIdForExtendStr)) {
                            	try {
									myApp.members[myApp.memberIndex].extension(myApp.MemberInputId, bookIdForExtendStr, daysForExtendInt);
								} catch (BookIsNotBorrowed k1)
                            	{
									JOptionPane.showMessageDialog(null, "Book is not borrowed");
								}catch (BookIsReserved k1)
                            	{
									JOptionPane.showMessageDialog(null, "Book is reserved");
								}catch (BookNotFound k1)
                            	{
									JOptionPane.showMessageDialog(null, "Book not found");
								}catch (MemberNotFound k1)
                            	{
									
								}catch (JSONException k1)
                            	{
									
								}catch (ExtensionOk k1)
                            	{
									JOptionPane.showMessageDialog(null, "Extension Successfull.");
								}
                                out = true;
                                break;
                            }
                        }
                    }

                    if (out) {
                        break;
                    }
                }

                if (!out) {
                	JOptionPane.showMessageDialog(null, "You didnt borrow this book");
                }}});
		btnGetExtendDays.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGetExtendDays.setBounds(1748, 68, 139, 34);
		frame.getContentPane().add(btnGetExtendDays);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(1532, 11, 2, 215);
		frame.getContentPane().add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(0, 257, 1910, 2);
		frame.getContentPane().add(separator_4);

		JTextArea resultAllBorrowedBooks = new JTextArea();
		resultAllBorrowedBooks.setBounds(179, 318, 531, 188);
		frame.getContentPane().add(resultAllBorrowedBooks);

		JButton btnShowAllBorrowed = new JButton("Show all borrowed");
		btnShowAllBorrowed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONArray bookArray = Book.getBookObject().getJSONArray("books");
				resultAllBorrowedBooks.setText("");

				for (int i = 0; i < bookArray.length(); ++i) {
					try {
						bookArray.getJSONObject(i).get("borrowed"); // if borrowed by someone
						// System.out.println(bookArray.getJSONObject(i).toString(4));
						resultAllBorrowedBooks.append(bookArray.getJSONObject(i).toString() + "\n");

					} catch (JSONException e6) {
					}
				}

			}
		});
		btnShowAllBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllBorrowed.setBounds(10, 380, 139, 34);
		frame.getContentPane().add(btnShowAllBorrowed);

		JLabel lblShowAllBorrowed = new JLabel("Show all borrowed books");
		lblShowAllBorrowed.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowAllBorrowed.setBounds(10, 295, 139, 14);
		frame.getContentPane().add(lblShowAllBorrowed);

		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(841, 293, 2, 248);
		frame.getContentPane().add(separator_5);

		JTextArea resultAllReserverBook = new JTextArea();
		resultAllReserverBook.setBounds(1186, 295, 601, 211);
		frame.getContentPane().add(resultAllReserverBook);

		JButton btnShowAllReserved = new JButton("Show all reserved");
		btnShowAllReserved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JSONArray bookArray = Book.getBookObject().getJSONArray("books");
				resultAllReserverBook.setText("");

				for (int i = 0; i < bookArray.length(); ++i) {
					try {
						bookArray.getJSONObject(i).get("reserved"); // if borrowed by someone
						// System.out.println(bookArray.getJSONObject(i).toString(4));
						resultAllReserverBook.append(bookArray.getJSONObject(i).toString() + "\n");

					} catch (JSONException e6) {
					}
				}

			}
		});
		btnShowAllReserved.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllReserved.setBounds(1017, 380, 139, 34);
		frame.getContentPane().add(btnShowAllReserved);

		JLabel lblShowAllReserved = new JLabel("Show all reserved books");
		lblShowAllReserved.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowAllReserved.setBounds(1017, 295, 139, 14);
		frame.getContentPane().add(lblShowAllReserved);

		JTextArea resutlAllYourBorrowed = new JTextArea();
		resutlAllYourBorrowed.setBounds(218, 604, 523, 209);
		frame.getContentPane().add(resutlAllYourBorrowed);

		JButton btnShowAllYour = new JButton("Show all your borrowed");
		btnShowAllYour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resutlAllYourBorrowed.setText("");

				boolean found = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(myApp.MemberInputId)) {
						myApp.memberIndex = i;
						found = true;
						break;
					}
				}

				if (!found) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = myApp.MemberInputId;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
					myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
					myApp.memberIndex = myApp.nbMembers;
				}

				// this same method in main (ShowBorrowedBooksMember)

				Member.updateBorrow();
				JSONArray bookArray = Book.getBookObject().getJSONArray("books");
				JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
				JSONObject thisMember = null;

				// get current member cell of meberArray
				for (int i = 0; i < memberArray.length(); ++i) {
					if (memberArray.getJSONObject(i).get("id").equals(myApp.MemberInputId)) {
						thisMember = memberArray.getJSONObject(i);
						break;
					}
				}

				for (int i = 0; i < bookArray.length(); ++i) {
					try {
						// search for borrowed books by thisMember
						if (bookArray.getJSONObject(i).get("borrowed").equals(myApp.MemberInputId)) {
							resutlAllYourBorrowed.append(bookArray.getJSONObject(i).toString());
						}

						// get borrowedTime of borrowed books
						for (int j = 1; j <= myApp.members[myApp.memberIndex].getNbBorrow(); ++j) {
							try {
								if (thisMember.get("borrowed" + j).equals(bookArray.getJSONObject(i).get("id"))) {
									resutlAllYourBorrowed.append("\n" + "remaining borrow time: "); // get remaining
																									// time from file
									resutlAllYourBorrowed
											.append(thisMember.get("remaining borrow" + j).toString() + "\n");
									break;
								}
							} catch (JSONException e8) {
							}
						}
					} catch (JSONException e9) {
					}
				}

			}
		});
		btnShowAllYour.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllYour.setBounds(10, 666, 159, 34);
		frame.getContentPane().add(btnShowAllYour);

		JLabel lblShowAllYour = new JLabel("Show all your borrowed books");
		lblShowAllYour.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowAllYour.setBounds(10, 581, 173, 14);
		frame.getContentPane().add(lblShowAllYour);

		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 552, 1910, 2);
		frame.getContentPane().add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(841, 565, 2, 248);
		frame.getContentPane().add(separator_7);

		JTextArea resultForAllReserved = new JTextArea();
		resultForAllReserved.setBounds(1186, 604, 601, 209);
		frame.getContentPane().add(resultForAllReserved);

		JButton btnShowAllYour_1 = new JButton("Show all your reserved");
		btnShowAllYour_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				resultForAllReserved.setText("");

				boolean found = false;
				for (int i = 1; i <= myApp.nbMembers; ++i) {
					if (myApp.id[i].equals(myApp.MemberInputId)) {
						myApp.memberIndex = i;
						found = true;
						break;
					}
				}

				if (!found) {
					myApp.nbMembers++;
					myApp.id[myApp.nbMembers] = myApp.MemberInputId;
					myApp.members[myApp.nbMembers] = new Member();
					myApp.members[myApp.nbMembers].updateNbReserve(myApp.MemberInputId);
					myApp.members[myApp.nbMembers].updateNbBorrow(myApp.MemberInputId);
					myApp.memberIndex = myApp.nbMembers;
				}

				// this same method for show all reserved

				Member.updateReserve();
				JSONArray bookArray = Book.getBookObject().getJSONArray("books");
				JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
				JSONObject thisMember = null;

				// get crrent member cell of meberArray
				for (int i = 0; i < memberArray.length(); ++i) {
					if (memberArray.getJSONObject(i).get("id").equals(myApp.MemberInputId)) {
						thisMember = memberArray.getJSONObject(i);
						break;
					}
				}

				for (int i = 0; i < bookArray.length(); ++i) {
					try {
						// search for reserved books by thisMember
						if (bookArray.getJSONObject(i).get("reserved").equals(myApp.MemberInputId)) {
							resultForAllReserved.append(bookArray.getJSONObject(i).toString());
						}

						// get reservedTime of reserved books
						for (int j = 1; j <= myApp.members[myApp.memberIndex].getNbReserve(); ++j) {
							try {
								if (thisMember.get("reserved" + j).equals(bookArray.getJSONObject(i).get("id"))) {
									try { // if borrowed by someone else
										bookArray.getJSONObject(i).get("borrowed");
										resultForAllReserved.append("\n" + "remaining reserve time: ");
										resultForAllReserved.append("7200000");
										break;
									} catch (JSONException e9) {
										resultForAllReserved.append("\n" + "remaining reserve time: "); // calculating
																										// remaining
																										// reserve time
										resultForAllReserved.append(
												Integer.toString((int) (120 * 60 * 1000 - (System.currentTimeMillis()
														- thisMember.getLong("reservedTime" + j)))));
										break;
									}
								}
							} catch (JSONException e3) {
							}
						}
					} catch (JSONException e1) {
					}
				}

			}
		});
		btnShowAllYour_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnShowAllYour_1.setBounds(997, 680, 159, 34);
		frame.getContentPane().add(btnShowAllYour_1);

		JLabel lblShowAllYour_1 = new JLabel("Show all your reserved books");
		lblShowAllYour_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblShowAllYour_1.setBounds(885, 581, 173, 14);
		frame.getContentPane().add(lblShowAllYour_1);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(10, 858, 1910, 2);
		frame.getContentPane().add(separator_8);

		JButton btnNewButton = new JButton("Pay Penalty");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					Member.payPenalty(myApp.MemberInputId);

				} catch (PenaltyNotFound e) {

					JOptionPane.showMessageDialog(null, "You dont have penalty to pay.");

				} catch (PenaltyPaid e) {

					JOptionPane.showMessageDialog(null, "You paid your penalty");

				}

			}
		});
		btnNewButton.setBounds(39, 923, 139, 47);
		frame.getContentPane().add(btnNewButton);

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				myApp.frame.setVisible(true);

			}
		});
		btnLogOut.setBounds(1748, 923, 139, 47);
		frame.getContentPane().add(btnLogOut);

		JLabel lblPayPenaltyAnd = new JLabel("Pay Penalty and unlock your account");
		lblPayPenaltyAnd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPayPenaltyAnd.setBounds(30, 882, 214, 14);
		frame.getContentPane().add(lblPayPenaltyAnd);

	}
}
