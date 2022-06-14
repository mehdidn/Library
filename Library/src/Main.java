import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
	}
}

class Admin {
	private static JSONObject adminObject;

	// set admin's json object
	public Admin(JSONObject object) {
		adminObject = object;

		if (adminObject == null) {
			adminObject = new JSONObject();
		}
	}

	public Admin() throws Exception {
		try {
			// read admin file
			JSONParser parser = new JSONParser();
			Object adminObj = parser.parse(new FileReader("C:\\java\\admin.json"));
			String adminInString = new Gson().toJson(adminObj);
			adminObject = new JSONObject(adminInString);
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (Exception e) {
			// first time
			adminObject = new JSONObject();
		}
	}

	// get adminObject
	public static JSONObject getAdminObject() {
		return adminObject;
	}

	// get information and add to admin file
	public static void add(String id, String password) throws Exception {

		adminObject.put("id", id);
		adminObject.put("password", password);

		try { // Writing on admin file
			File file = new File("c:\\java\\admin.json");
			FileWriter fileWriter = new FileWriter(file);

			fileWriter.write(adminObject.toString(4));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Librarian {
	private static JSONObject librarianObject;

	// set librarian's jsonobject and array
	public Librarian(JSONObject object) {
		librarianObject = object;

		if (librarianObject == null) {
			librarianObject = new JSONObject();
		}
	}

	public Librarian() throws Exception {
		try {
			// read librarians file
			JSONParser parser = new JSONParser();
			Object librarianObj = parser.parse(new FileReader("C:\\java\\librarians.json"));
			String librarianInString = new Gson().toJson(librarianObj);
			librarianObject = new JSONObject(librarianInString);
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (Exception e) {
			// first time
			librarianObject = new JSONObject();
		}
	}

	public static void sort() throws JSONException {
		JSONArray array = librarianObject.getJSONArray("librarians");
		JSONObject sortedObject = new JSONObject();
		JSONArray sortedArray = new JSONArray();

		while (array.length() > 0) { // selection sort
			int minIndex = 0;
			for (int i = 0; i < array.length(); ++i) {
				if (((String) (array.getJSONObject(minIndex).get("first name")))
						.compareTo(((String) (array.getJSONObject(i).get("first name")))) > 0) {
					minIndex = i;
				} else if (((String) (array.getJSONObject(minIndex).get("first name")))
						.compareTo(((String) (array.getJSONObject(i).get("first name")))) == 0
						&& ((String) (array.getJSONObject(minIndex).get("last name")))
								.compareTo(((String) (array.getJSONObject(i).get("last name")))) > 0) {
					minIndex = i;
				}
			}

			// swap
			sortedArray.put(array.getJSONObject(minIndex));
			array.remove(minIndex);
		}

		// add to sortedObject
		sortedObject.put("librarians", sortedArray);
		librarianObject = sortedObject;
	}

	// writing on librarians file
	public static void writeFile() throws JSONException {
		try { // Writing to librarians file
			File file = new File("c:\\java\\librarians.json");
			FileWriter fileWriter = new FileWriter(file);

			sort();
			fileWriter.write(librarianObject.toString(4));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add librarian
	public static void add(Map map) throws JSONException, InvalidId {
		synchronized ((String) map.get("id")) {

			try { // if file isnt empty
				JSONArray lArray = librarianObject.getJSONArray("librarians");

				for (int i = 0; i < lArray.length(); ++i) {
					if (lArray.getJSONObject(i).get("id").equals(map.get("id"))) {
						System.out.println("invalid id!");
						throw new InvalidId();
					}
				}
				librarianObject.getJSONArray("librarians").put(map);
			} catch (JSONException e) { // if file is empty

				JSONObject add = new JSONObject(map);
				JSONArray librarianArray = new JSONArray();
				librarianArray.put(add);
				librarianObject.put("librarians", librarianArray);

			}
		}

		writeFile();
		// System.out.println("librarian added successfully");
	}

	// change information of librarians
	public static void changeInformation(String id, String wantedChange, String changeValue)
			throws JSONException, MemberNotFound {
		synchronized (id) {
			JSONArray array = librarianObject.getJSONArray("librarians");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					array.getJSONObject(i).put(wantedChange, changeValue); // replace with new information
					writeFile();
					// System.out.println("librarian information changed successfully");
					return;
				}
			}
		}

		// System.out.println("Nothing Found");
		throw new MemberNotFound();

	}

	// remove librarian
	public static void remove(String id) throws JSONException {
		synchronized (id) {
			JSONArray memberArray = librarianObject.getJSONArray("librarians");

			for (int i = 0; i < memberArray.length(); ++i) {
				if (memberArray.getJSONObject(i).get("id").equals(id)) {
					librarianObject.getJSONArray("librarians").remove(i);
					writeFile();
					// System.out.println("librarian removed successfully");
					return;
				}
			}
		}

		// System.out.println("Nothing Found");
		throw new JSONException("");
	}

	// search for librarian with id
	public static JSONObject search(String id) throws JSONException {
		synchronized (id) {
			JSONArray array = librarianObject.getJSONArray("librarians");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					return array.getJSONObject(i);
				}
			}
		}

		throw new JSONException("");
	}

	// get librarianObject
	public static JSONObject getLibrarianObject() {
		return librarianObject;
	}
}

class Member {
	// static features for all members
	private static JSONObject memberObject;
	private static JSONArray memberArray;

	// features for each member
	private int nbBorrow = 0;
	private int nbReserve = 0;
	private long[] borrowedTime = new long[1000];
	private long[] reservedTime = new long[1000];

	// set member's json object
	public Member(JSONObject object) {
		memberObject = object;

		if (memberObject == null) {
			memberObject = new JSONObject();
			memberArray = new JSONArray();
		}
	}

	public Member() {
		try {
			// read members file
			JSONParser parser = new JSONParser();
			Object memberObj = parser.parse(new FileReader("C:\\java\\members.json"));
			String memberInString = new Gson().toJson(memberObj);
			memberObject = new JSONObject(memberInString);
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (Exception e) {
			// first time
			memberObject = new JSONObject();
		}
	}

	public static void sort() throws JSONException {
		JSONArray array = memberObject.getJSONArray("members");
		JSONObject sortedObject = new JSONObject();
		JSONArray sortedArray = new JSONArray();

		while (array.length() > 0) {
			int minIndex = 0;
			for (int i = 0; i < array.length(); ++i) {
				if (((String) (array.getJSONObject(minIndex).get("first name")))
						.compareTo(((String) (array.getJSONObject(i).get("first name")))) > 0) {
					minIndex = i;
				} else if (((String) (array.getJSONObject(minIndex).get("first name")))
						.compareTo(((String) (array.getJSONObject(i).get("first name")))) == 0
						&& ((String) (array.getJSONObject(minIndex).get("last name")))
								.compareTo(((String) (array.getJSONObject(i).get("last name")))) > 0) {
					minIndex = i;
				}
			}

			sortedArray.put(array.getJSONObject(minIndex));
			array.remove(minIndex);
		}

		sortedObject.put("members", sortedArray);
		memberObject = sortedObject;
	}

	// writing on members file
	static void writeFile() throws JSONException {
		try { // Writing to members file
			File file = new File("c:\\java\\members.json");
			FileWriter fileWriter = new FileWriter(file);

			sort();
			fileWriter.write(memberObject.toString(4));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add member
	public static void add(Map map) throws JSONException, InvalidId {
		synchronized ((String) map.get("id")) {
			try { // if file isn`t empty
				JSONArray mArray = memberObject.getJSONArray("members");

				for (int i = 0; i < mArray.length(); ++i) {
					if (mArray.getJSONObject(i).get("id").equals(map.get("id"))) {
						System.out.println("invalid id!");
						throw new InvalidId();
					}
				}

				memberObject.getJSONArray("members").put(map);

			} catch (JSONException e) { // if file is empty
				JSONObject add = new JSONObject(map);
				JSONArray memberArray = new JSONArray();
				memberArray.put(add);
				memberObject.put("members", memberArray);
			}
		}

		writeFile();
		// System.out.println("member added successfully");
	}

	// change information of members
	static void changeInformation(String id, String wantedChange, String changeValue) throws JSONException {
		synchronized (id) {
			JSONArray array = memberObject.getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					array.getJSONObject(i).put(wantedChange, changeValue); // replace with new information
					writeFile();
					// System.out.println("member information changed successfully");
					return;
				}
			}
		}

		// System.out.println("Nothing Found");
		throw new JSONException("");
	}

	// remove member
	static void remove(String id) throws JSONException, MemberNotFound {
		synchronized (id) {
			JSONArray memberArray = memberObject.getJSONArray("members");

			for (int i = 0; i < memberArray.length(); ++i) {
				if (memberArray.getJSONObject(i).get("id").equals(id)) {
					JSONArray bookArray = Book.getBookObject().getJSONArray("books");
					// remove borrow and reserve of this member from book
					for (int j = 0; j < bookArray.length(); ++j) {
						try {
							if (bookArray.getJSONObject(j).get("reserved").equals(id)) {
								bookArray.getJSONObject(j).remove("reserved");
							}
						} catch (JSONException e) {
						}

						try {
							if (bookArray.getJSONObject(j).get("borrowed").equals(id)) {
								bookArray.getJSONObject(j).remove("borrowed");
							}
						} catch (JSONException e) {
						}
					}

					Book.writeFile();
					memberObject.getJSONArray("members").remove(i);
					writeFile();
					// System.out.println("member removed successfully");
					return;
				}
			}
		}

		// System.out.println("Nothing Found");
		throw new MemberNotFound();
	}

	// search member with id
	static JSONObject search(String id) throws JSONException {
		synchronized (id) {
			JSONArray array = memberObject.getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					return array.getJSONObject(i);
				}
			}
		}

		throw new JSONException("");
	}

	void reserve(String memberId, String bookId)
			throws JSONException, MemberPenalty, MemberNotFound, BookIsReserved, BookIsBorrowedByHimSelf, BookNotFound {
		JSONObject currentReserve = null;
		boolean memberFound = false;
		boolean isBorrowedBySomeoneElse = false;

		synchronized (bookId) {
			JSONArray array = memberObject.getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(memberId)) {
					// if member penalty is true he cant reserve new book
					try {
						array.getJSONObject(i).get("penalty");
						// System.out.println("you must pay your penalty");
						throw new MemberPenalty();
					} catch (JSONException e) {
					}

					// reserve book
					try {
						Book.reserve(memberId, bookId);
					} catch (BookIsReserved e) {
						// System.out.println("this book is reserved by someone else");
						throw new BookIsReserved();
					} catch (BookIsBorrowedByHimSelf e) {
						// System.out.println("this book is borrowed by yourself");
						throw new BookIsBorrowedByHimSelf();
					} catch (BookNotFound e) {
						// System.out.println("book not found");
						throw new BookNotFound();
					} catch (BookIsBorrowed e) {
						isBorrowedBySomeoneElse = true;
					}

					// reserve for member
					memberFound = true;
					nbReserve++;

					currentReserve = array.getJSONObject(i);
					array.getJSONObject(i).put("reserved" + nbReserve, bookId);
				}
			}

			if (!memberFound) {
				// System.out.println("member not found");
				throw new MemberNotFound();
			}

			// put reservedTime and number of reserve for this book
			reservedTime[nbReserve] = System.currentTimeMillis();
			currentReserve.put("reservedTime" + nbReserve, reservedTime[nbReserve]);
			currentReserve.put("nbReserve", nbReserve);
			writeFile();
		}
		System.out.println("reserved successfully");
	}

	void borrow(String memberId, String bookId)
			throws JSONException, MemberPenalty, BookIsNotReserved, BookIsReserved, BookIsBorrowed, BookNotFound {
		JSONObject currentBorrow = null;
		boolean memberFound = false;

		synchronized (bookId) {
			JSONArray array = memberObject.getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(memberId)) {
					// if member penalty is true he cant borrow new book
					try {
						array.getJSONObject(i).get("penalty");
						// System.out.println("you must pay your penalty");
						throw new MemberPenalty();
					} catch (JSONException e) {
					}

					// borrow book
					try {
						Book.borrow(memberId, bookId);
					} catch (BookIsNotReserved e) {
						// System.out.println("you didn't reserve this book or your reserve time has
						// ended");
						throw new BookIsNotReserved();
					} catch (BookIsReserved e) {
						// System.out.println("this book is reserved by someone else");
						throw new BookIsReserved();
					} catch (BookIsBorrowed e) {
						// System.out.println("this book is borrowed");
						throw new BookIsBorrowed();
					} catch (BookNotFound e) {
						// System.out.println("book not found");
						throw new BookNotFound();
					}

					// borrow for member
					memberFound = true;
					currentBorrow = array.getJSONObject(i);
					nbBorrow++;

					array.getJSONObject(i).put("borrowed" + nbBorrow, bookId);

					// remove reserve and reserve time for members
					for (int j = 1; j <= nbReserve; ++j) {
						try {
							if (array.getJSONObject(i).get("reserved" + j).equals(bookId)) {
								array.getJSONObject(i).remove("reserved" + j);
								array.getJSONObject(i).remove("reservedTime" + j);
							}
						} catch (JSONException e) {
						}
					}
				}
			}

			if (!memberFound) {
				System.out.println("member not found");
				return;
			}

			// put borrow features for this book
			borrowedTime[nbBorrow] = System.currentTimeMillis();
			currentBorrow.put("borrowedTime" + nbBorrow, borrowedTime[nbBorrow]);
			currentBorrow.put("remaining borrow" + nbBorrow, 10 * 24 * 60 * 60 * 1000);
			currentBorrow.put("nbBorrow", nbBorrow);
			writeFile();
		}
		System.out.println("borrowed successfully");
	}

	void giveBack(String memberId, String bookId)
			throws JSONException, BookIsBorrowed, BookIsNotBorrowed, BookNotFound, MemberNotFound {
		synchronized (bookId) {
			JSONArray array = memberObject.getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(memberId)) {

					// give back book
					try {
						Book.giveBack(memberId, bookId);
					} catch (BookIsBorrowed e) {
						// System.out.println("someone else borrowed this book");
						throw new BookIsBorrowed();
					} catch (BookIsNotBorrowed e) {
						// System.out.println("you didn't borrow this book");
						throw new BookIsNotBorrowed();
					} catch (BookNotFound e) {
						// System.out.println("book is not found");
						throw new BookNotFound();
					} catch (BookIsReserved e) { // book gave back so we can start another reserve on this book
						boolean out = false;

						for (int j = 0; j < array.length(); ++j) { // check all members
							for (int k = 1; k <= array.getJSONObject(j).getInt("nbReserve"); ++k) { // check all member
																									// reserves
								try {
									if (array.getJSONObject(j).get("reserved" + k).equals(bookId)) {
										out = true;
										array.getJSONObject(j).put("reservedTime" + k, System.currentTimeMillis());
										break;
									}
								} catch (JSONException e1) {
								}
							}

							if (out) {
								break;
							}
						}
					}

					// remove borrow features for member
					for (int j = 1; j <= nbBorrow; ++j) {
						try {
							if (array.getJSONObject(i).get("borrowed" + j).equals(bookId)) {
								array.getJSONObject(i).remove("borrowed" + j);
								array.getJSONObject(i).remove("borrowedTime" + j);
								array.getJSONObject(i).remove("remaining borrow" + j);
								array.getJSONObject(i).remove("extend(days) for" + j);
								break;
							}
						} catch (JSONException e) {
						}
					}
					writeFile();
					// System.out.println("gave back successfully");
					return;
				}
			}
		}

		// System.out.println("member not found");
		throw new MemberNotFound();
	}

	void updateNbReserve(String memberId) throws JSONException {
		JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
		for (int i = 0; i < memberArray.length(); ++i) {
			if (memberArray.getJSONObject(i).get("id").equals(memberId)) {
				try {
					nbReserve = memberArray.getJSONObject(i).getInt("nbReserve"); // get nbReserve from file to update
																					// it
				} catch (JSONException e) {
					nbReserve = 0;
					return;
				}
			}
		}
	}

	void updateNbBorrow(String memberId) throws JSONException {
		JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
		for (int i = 0; i < memberArray.length(); ++i) {
			if (memberArray.getJSONObject(i).get("id").equals(memberId)) {
				try {
					nbBorrow = memberArray.getJSONObject(i).getInt("nbBorrow"); // get nbBorrow from file to update it
				} catch (JSONException e) {
					nbBorrow = 0;
					return;
				}
			}
		}
	}

	static void updateReserve() throws JSONException {
		JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
		JSONArray bookArray = Book.getBookObject().getJSONArray("books");

		for (int i = 0; i < memberArray.length(); ++i) {
			JSONObject currentMember = memberArray.getJSONObject(i);
			try {
				for (int j = 1; j <= currentMember.getInt("nbReserve"); ++j) {
					boolean borrowedByAnother = false; // set boolean for check borrow by some one else to not play
														// reserveTime
					try {
						for (int k = 0; k < bookArray.length(); ++k) {
							JSONObject currentBook = bookArray.getJSONObject(k);
							try {
								if (currentMember.get("reserved" + j).equals(currentBook.get("id"))) {
									currentBook.get("borrowed"); // if someone else borrowed this book
									borrowedByAnother = true;
								}
							} catch (JSONException e) {
								break;
							}
						}

						long updateTime = System.currentTimeMillis() - currentMember.getLong("reservedTime" + j);
						if (!borrowedByAnother && updateTime > 120 * 60 * 1000) { // 2h resrveTime ended so we can
																					// remove reserve features
							currentMember.remove("reserved" + j);
							currentMember.remove("reservedTime" + j);
						}
					} catch (JSONException e) {
					}
				}
			} catch (JSONException e) {
			}
		}

		writeFile();
	}

	static void updateBorrow() throws JSONException {
		JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
		JSONArray bookArray = Book.getBookObject().getJSONArray("books");

		for (int i = 0; i < memberArray.length(); ++i) {
			JSONObject currentMember = memberArray.getJSONObject(i);
			try {
				for (int j = 1; j <= currentMember.getInt("nbBorrow"); ++j) {
					int extendDays = 0;
					try {
						extendDays = currentMember.getInt("extend(days) for" + j); // try for getting extension for
																					// current borrowed book
					} catch (JSONException e) {
					}

					try {
						long updateTime = System.currentTimeMillis() - currentMember.getLong("borrowedTime" + j);

						// if 10day+extension has ended we can set penalty for member
						if (updateTime > 10 * 24 * 60 * 60 * 1000 + extendDays * 24 * 60 * 60 * 1000) {
							currentMember.put("penalty", "true");
						} else { // update remaining time
							long remainingTime = (10 * 24 * 60 * 60 * 1000 - updateTime)
									+ extendDays * 24 * 60 * 60 * 1000;
							currentMember.put("remaining borrow" + j, remainingTime);
						}
					} catch (JSONException e) {
					}
				}
			} catch (JSONException e) {
			}
		}

		writeFile();
	}

	void showBorrow(String memberId) throws JSONException {
		Member.updateBorrow();
		JSONObject thisMember = null;

		synchronized (memberId) {
			JSONArray bookArray = Book.getBookObject().getJSONArray("books");
			JSONArray memberArray = Member.getMemberObject().getJSONArray("members");

			// get crrent member cell of meberArray
			for (int i = 0; i < memberArray.length(); ++i) {
				if (memberArray.getJSONObject(i).get("id").equals(memberId)) {
					thisMember = memberArray.getJSONObject(i);
					break;
				}
			}

			for (int i = 0; i < bookArray.length(); ++i) {
				try {
					// search for borrowed books by thisMember
					if (bookArray.getJSONObject(i).get("borrowed").equals(memberId)) {
						System.out.println(bookArray.getJSONObject(i).toString(4));
					}

					// get borrowedTime of borrowed books
					for (int j = 1; j <= nbBorrow; ++j) {
						try {
							if (thisMember.get("borrowed" + j).equals(bookArray.getJSONObject(i).get("id"))) {
								System.out.print("remaining borrow time: "); // get remaining time from file
								System.out.println(thisMember.get("remaining borrow" + j));
								break;
							}
						} catch (JSONException e) {
						}
					}
				} catch (JSONException e) {
				}
			}
		}
	}

	void showReserve(String memberId) throws JSONException {
		Member.updateReserve();
		JSONObject thisMember = null;

		synchronized (memberId) {
			JSONArray bookArray = Book.getBookObject().getJSONArray("books");
			JSONArray memberArray = Member.getMemberObject().getJSONArray("members");

			// get crrent member cell of meberArray
			for (int i = 0; i < memberArray.length(); ++i) {
				if (memberArray.getJSONObject(i).get("id").equals(memberId)) {
					thisMember = memberArray.getJSONObject(i);
					break;
				}
			}

			for (int i = 0; i < bookArray.length(); ++i) {
				try {
					// search for reserved books by thisMember
					if (bookArray.getJSONObject(i).get("reserved").equals(memberId)) {
						System.out.println(bookArray.getJSONObject(i).toString(4));
					}

					// get reservedTime of reserved books
					for (int j = 1; j <= nbReserve; ++j) {
						try {
							if (thisMember.get("reserved" + j).equals(bookArray.getJSONObject(i).get("id"))) {
								try { // if borrowed by someone else
									bookArray.getJSONObject(i).get("borrowed");
									System.out.print("remaining reserve time: ");
									System.out.println(120 * 60 * 1000);
									break;
								} catch (JSONException e) {
									System.out.print("remaining reserve time: "); // calculating remaining reserve time
									System.out.println(120 * 60 * 1000
											- (System.currentTimeMillis() - thisMember.getLong("reservedTime" + j)));
									break;
								}
							}
						} catch (JSONException e) {
						}
					}
				} catch (JSONException e) {
				}
			}
		}
	}

	void extension(String memberId, String bookId, int extendDay) throws JSONException, BookIsNotBorrowed, BookIsReserved, BookNotFound, MemberNotFound, ExtensionOk {
		// check for not reserved by someone else
		synchronized (bookId) {
            JSONArray bookArray = Book.getBookObject().getJSONArray("books");
            boolean bookFound = false;

            //check for not reserved by someone else
            for (int i = 0; i < bookArray.length(); ++i) {
                if (bookArray.getJSONObject(i).get("id").equals(bookId)) {
                    bookFound = true;

                    try {   //try for getting reserved
                        bookArray.getJSONObject(i).get("reserved");
                        //System.out.println("this book is reserved by someone else and cant extend");
                        throw new BookIsReserved();
                    } catch (JSONException e) {     //not reserved
                        break;
                    }
                }
            }

            if (!bookFound) {
                //System.out.println("book not found");
                throw new BookNotFound();
            }
		}

		synchronized (memberId) {
            JSONArray array = memberObject.getJSONArray("members");
            boolean memberFound = false;

            for (int i = 0; i < array.length(); ++i) {
                if (array.getJSONObject(i).get("id").equals(memberId)) {
                    memberFound = true;

                    for (int j = 1; j <= nbBorrow; ++j) {
                        if (array.getJSONObject(i).get("borrowed" + j).equals(bookId)) {
                            try {       //if another extension occured on this book
                                long exsistExtend = array.getJSONObject(i).getInt("extend(days) for" + j);
                                array.getJSONObject(i).put("extend(days) for" + j, exsistExtend + extendDay);
                                writeFile();
                                //System.out.println("extension successfully");
                                throw new ExtensionOk();
                            } catch (JSONException e) {     //else we can just put new extend time
                                array.getJSONObject(i).put("extend(days) for" + j, extendDay);
                                writeFile();
                                //System.out.println("extension successfully");
                                throw new ExtensionOk();
                            }
                        }
                    }
                }
            }

            if (!memberFound) {
                //System.out.println("member not found");
                throw new MemberNotFound();
            }
        }

		throw new BookIsNotBorrowed();
	}

	static void payPenalty(String memberId) throws JSONException, PenaltyPaid, PenaltyNotFound {
		synchronized (memberId) {
			JSONArray array = Member.getMemberObject().getJSONArray("members");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(memberId)) {
					try { // if member has penalty remove it
						array.getJSONObject(i).get("penalty");
						array.getJSONObject(i).remove("penalty");
						System.out.println("you paid your penalty");
						throw new PenaltyPaid();
					} catch (JSONException e) { // else he doesnt have penalty
						System.out.println("you dont have penalty");
						throw new PenaltyNotFound();
					}
				}
			}
		}
	}

	public static JSONObject getMemberObject() {
		return memberObject;
	}

	public static void setMemberObject(JSONObject inputMemberObject) {
		memberObject = inputMemberObject;
	}

	public static JSONArray getMemberArray() {
		return memberArray;
	}

	public static void setgetMemberArray(JSONArray inputMemberArray) {
		memberArray = inputMemberArray;
	}

	public int getNbBorrow() {
		return nbBorrow;
	}

	public int getNbReserve() {
		return nbReserve;
	}

	public long[] getborrowedTime() {
		return borrowedTime;
	}

	public long[] getreservedTime() {
		return reservedTime;
	}
}

class Book {
	private static JSONObject bookObject;
	private static JSONArray bookArray;
	private static Member member = new Member();

	public Book() throws Exception {
		try {
			// read books file
			JSONParser parser = new JSONParser();
			Object bookObj = parser.parse(new FileReader("C:\\java\\books.json"));
			String bookInString = new Gson().toJson(bookObj);
			bookObject = new JSONObject(bookInString);
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (Exception e) {
			// first time
			bookObject = new JSONObject();
		}
	}

	// set book's jsonobject
	public static void read(JSONObject object) {
		bookObject = object;

		if (bookObject == null) {
			bookObject = new JSONObject();
			bookArray = new JSONArray();
		}
	}

	// selection sort for book like sorting librarian
	public static void sort() throws JSONException {
		JSONArray array = bookObject.getJSONArray("books");
		JSONObject sortedObject = new JSONObject();
		JSONArray sortedArray = new JSONArray();

		while (array.length() > 0) {
			int minIndex = 0;
			for (int i = 0; i < array.length(); ++i) {
				if (((String) (array.getJSONObject(minIndex).get("name")))
						.compareTo(((String) (array.getJSONObject(i).get("name")))) > 0) {
					minIndex = i;
				}
			}

			sortedArray.put(array.getJSONObject(minIndex));
			array.remove(minIndex);
		}

		sortedObject.put("books", sortedArray);
		bookObject = sortedObject;
	}

	// writing on books file
	static void writeFile() throws JSONException {
		try { // Writing to books file
			File file = new File("c:\\java\\books.json");
			FileWriter fileWriter = new FileWriter(file);

			sort();
			fileWriter.write(bookObject.toString(4));
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// add book
	static void add(Map map) throws JSONException, InvalidId {
		synchronized ((String) map.get("id")) {
			try { // if file isnt empty
				JSONArray bArray = bookObject.getJSONArray("books");

				for (int i = 0; i < bArray.length(); ++i) {
					if (bArray.getJSONObject(i).get("id").equals(map.get("id"))) {
						System.out.println("invalid id!");
						throw new InvalidId();
					}
				}

				bookObject.getJSONArray("books").put(map);
			} catch (JSONException e) { // if file is empty
				JSONObject add = new JSONObject(map);
				JSONArray bookArray = new JSONArray();
				bookArray.put(add);
				bookObject.put("books", bookArray);
			}

			writeFile();
		}
		System.out.println("book added successfully");
	}

	// change information of books
	static void changeInformation(String id, String wantedChange, String changeValue)
			throws JSONException, MemberNotFound {
		synchronized (id) {
			JSONArray array = bookObject.getJSONArray("books");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					array.getJSONObject(i).put(wantedChange, changeValue); // replace with new information
					writeFile();
					System.out.println("book information changed successfully");
					return;
				}
			}
		}

		// System.out.println("Nothing Found");
		// hamin tori in error ro zadam
		throw new MemberNotFound();
	}

	// remove book
	static void remove(String id) throws JSONException, BookNotFound {
		synchronized (id) {
			JSONArray bookArray = Book.getBookObject().getJSONArray("books");

			for (int i = 0; i < bookArray.length(); ++i) {
				if (bookArray.getJSONObject(i).get("id").equals(id)) {
					JSONArray memberArray = Member.getMemberObject().getJSONArray("members");
					for (int j = 0; j < memberArray.length(); ++j) {
						// check for members who reserved this book and remove reserve featres
						try {
							for (int k = 1; k <= memberArray.getJSONObject(j).getInt("nbReserve"); ++k) {
								try {
									if (memberArray.getJSONObject(j).get("reserved" + k).equals(id)) {
										memberArray.getJSONObject(j).remove("reserved" + k);
										memberArray.getJSONObject(j).remove("reservedTime" + k);
									}
								} catch (JSONException e) {
								}
							}
						} catch (JSONException e) {
						}

						// check for member who borrowed this book and remove borrow featres
						try {
							for (int k = 1; k <= memberArray.getJSONObject(j).getInt("nbBorrow"); ++k) {
								try {
									if (memberArray.getJSONObject(j).get("borrowed" + k).equals(id)) {
										memberArray.getJSONObject(j).remove("borrowed" + k);
										memberArray.getJSONObject(j).remove("borrowedTime" + k);
										memberArray.getJSONObject(j).remove("remaining borrow" + k);
										memberArray.getJSONObject(j).remove("extend(days) for" + k);
									}
								} catch (JSONException e) {
								}
							}
						} catch (JSONException e) {
						}
					}

					Member.writeFile();
					bookObject.getJSONArray("books").remove(i);
					writeFile();
					System.out.println("book removed successfully");
					return;
				}
			}
		}

		throw new BookNotFound();
	}

	// search book with id
	static JSONObject search(String id) throws JSONException {
		synchronized (id) {
			JSONArray array = bookObject.getJSONArray("books");

			for (int i = 0; i < array.length(); ++i) {
				if (array.getJSONObject(i).get("id").equals(id)) {
					return array.getJSONObject(i);
				}
			}
		}

		return null;
	}

	static void reserve(String memberId, String bookId)
			throws JSONException, BookIsReserved, BookIsBorrowed, BookNotFound, BookIsBorrowedByHimSelf {
		JSONArray array = bookObject.getJSONArray("books");

		for (int i = 0; i < array.length(); ++i) {
			if (array.getJSONObject(i).get("id").equals(bookId)) {
				// check for reserve
				try {
					array.getJSONObject(i).get("reserved");
					throw new BookIsReserved(); // if reserved
				} catch (JSONException e) { // if not reserved
					// check for borrow
					try {
						if (array.getJSONObject(i).get("borrowed").equals(memberId)) {
							throw new BookIsBorrowedByHimSelf(); // if borrowed by himself
						}
					} catch (JSONException e1) {
					}

					// if not borrowed by himself
					try { // check for someone else borrow
						array.getJSONObject(i).get("borrowed");
						array.getJSONObject(i).put("reserved", memberId); // put reserve
						writeFile();
						throw new BookIsBorrowed();
					} catch (JSONException e1) { // if not borrowed at all
						array.getJSONObject(i).put("reserved", memberId); // put reserve
						writeFile();
						return;
					}
				}
			}
		}

		throw new BookNotFound();
	}

	static void borrow(String memberId, String bookId)
			throws JSONException, BookIsBorrowed, BookIsReserved, BookIsNotReserved, BookNotFound {
		JSONArray array = bookObject.getJSONArray("books");

		for (int i = 0; i < array.length(); ++i) {
			if (array.getJSONObject(i).get("id").equals(bookId)) {
				try {
					if (!array.getJSONObject(i).get("reserved").equals(memberId)) { // check for reserving for someone
																					// else
						throw new BookIsReserved();
					}
				} catch (JSONException e) { // if not reserved at all
					throw new BookIsNotReserved();
				}
				try { // check for borrow
					array.getJSONObject(i).get("borrowed");
					throw new BookIsBorrowed();
				} catch (JSONException e) { // if not borrowed
					array.getJSONObject(i).put("borrowed", memberId);
					array.getJSONObject(i).remove("reserved");
					writeFile();
					return;
				}
			}
		}

		throw new BookNotFound();
	}

	static void giveBack(String memberId, String bookId)
			throws JSONException, BookIsBorrowed, BookIsNotBorrowed, BookNotFound, BookIsReserved {
		JSONArray array = bookObject.getJSONArray("books");

		for (int i = 0; i < array.length(); ++i) {
			if (array.getJSONObject(i).get("id").equals(bookId)) {
				try {
					if (!array.getJSONObject(i).get("borrowed").equals(memberId)) { // check for borrowing for someone
																					// else
						throw new BookIsBorrowed();
					}
				} catch (JSONException e) { // if not borrowed at all
					throw new BookIsNotBorrowed();
				}

				try { // check for reserve for someone else to notify its reserving thread in
						// member.giveBack
					array.getJSONObject(i).get("reserved");
					array.getJSONObject(i).remove("borrowed");
					writeFile();
					throw new BookIsReserved();
				} catch (JSONException e) {
					array.getJSONObject(i).remove("borrowed");
					writeFile();
					return;
				}
			}
		}

		throw new BookNotFound();
	}

	static void showMemberBorrowedBooks(String memberId) throws JSONException {
		synchronized (memberId) {
			JSONArray array = bookObject.getJSONArray("books");

			for (int i = 0; i < array.length(); ++i) {
				try {
					if (array.getJSONObject(i).get("borrowed").equals(memberId)) { // books that borroed value equals
																					// member
						// id
						System.out.println(array.getJSONObject(i).toString(4));
					}
				} catch (JSONException e) {
				}
			}
		}
	}

	static void showBorrowedBooks() throws JSONException {
		JSONArray array = bookObject.getJSONArray("books");

		for (int i = 0; i < array.length(); ++i) {
			try {
				array.getJSONObject(i).get("borrowed"); // if borrowed by someone
				System.out.println(array.getJSONObject(i).toString(4));

			} catch (JSONException e) {
			}
		}
	}

	static void showReservedBooks() throws JSONException {
		JSONArray array = bookObject.getJSONArray("books");

		for (int i = 0; i < array.length(); ++i) {
			try {
				array.getJSONObject(i).get("reserved"); // if reserved by someone
				System.out.println(array.getJSONObject(i).toString(4));
			} catch (JSONException e) {
			}
		}
	}

	public static JSONObject getBookObject() {
		return bookObject;
	}

	public static JSONArray getBookArray() {
		return bookArray;
	}

	public static void setBookArray(JSONArray inputBookArr) {
		bookArray = inputBookArr;
	}
}

class InvalidId extends Exception {
}

class BookNotFound extends Exception {
}

class BookIsReserved extends Exception {
}

class BookIsNotReserved extends Exception {
}

class BookIsBorrowed extends Exception {
}

class BookIsBorrowedByHimSelf extends Exception {
}

class BookIsNotBorrowed extends Exception {
}

class MemberNotFound extends Exception {
}

class MemberPenalty extends Exception {
}

class PenaltyNotFound extends Exception {
}

class PenaltyPaid extends Exception {
}

class ExtensionOk extends Exception {
}
