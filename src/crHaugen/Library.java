package crHaugen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
//import java.util.Scanner;

/**
 * @author Chyanne Haugen
 *
 */

public class Library {

	private ArrayList<Patron> patrons;
	private ArrayList<Book> books;
	private ArrayList<Transaction> transactions;

	public Library() {
		this.patrons = new ArrayList<Patron>();
		this.books = new ArrayList<Book>();
		this.transactions = new ArrayList<Transaction>();
	}

	public Library(String bookFile, String patronFile) {
		books = new ArrayList<Book>();
		patrons = new ArrayList<Patron>();
		transactions = new ArrayList<Transaction>();
	}
	/**
	 * 
	 * @param fileName, file which bookd will br added to 
	 * @return boolean whether the books were added successfully
	 */

	public boolean addBooks(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = " ";

			while ((line = br.readLine()) != null) {
				// System.out.println(line);

				// line = br.readLine();
				String[] info = line.split(",");

				String author = info[0];
				String title = info[1];
				String ISBN = info[2];
				String data = info[3];
				BookGenre genre = BookGenre.valueOf(info[4]);

				Book b = new Book(genre, ISBN, author, title, data, false, "n/a");
				//System.out.println(b);
				books.add(b);
			}


		} catch (FileNotFoundException e) 
		{
			System.out.print(fileName + " File not found");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/**
	 * 
	 * @param fileName, file of patrons to be added to library
	 * @return boolean to patrons where added or not
	 */
	public boolean addPatrons(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = " ";

			while ((line = br.readLine()) != null) {

				String[] info = line.split(",");

				String firstName = info[0];
				String lastName = info[1];
				String cardNumber = info[2];
				BigDecimal fee = new BigDecimal(info[3]);

				Patron p = new Patron(firstName, lastName, cardNumber, fee);
				//System.out.println(p);
				patrons.add(p);
			}
  
		} catch (FileNotFoundException e) {
			System.out.print(fileName + " File not found");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
      
		return true;
	}


	public void readBooksToFile() {

		try {
			writeFileLinesBook("newBooks.txt", books);
		} catch (IOException ex) {
			System.out.println("IO Exception caught");
		}
	}

	public void readPatronsToFile() {

		try {
			writeFileLinesPatron("newPatrons.txt", patrons);
		} catch (IOException ex) {
			System.out.println("IO Exception caught");
		}
	}

	/**
	 * 
	 * @param fileToPrint, file which patrons will be written to
	 * @param patrons2, arraylist of patrons to write to file
	 * @throws IOException
	 */
	private void writeFileLinesPatron(String fileToPrint, ArrayList<Patron> patrons2) throws IOException
	{
		FileWriter file = new FileWriter(fileToPrint);
		for(Patron write : patrons ) {
			file.write(String.format("%s%n",write));
			}
		file.close();
		
	}

	/**
	 * 
	 * @param fileToPrint, file which books will be written to 
	 * @param book, arraylist of book to be written to file
	 * @throws IOException
	 */
	private void writeFileLinesBook(String fileToPrint, ArrayList<Book> book) throws IOException
	{
		FileWriter file = new FileWriter(fileToPrint);
		for(Book write : book ) {
			file.write(String.format("%s%n",write));
			}
		file.close();
	}
	

	public void printListOfBooks()
	{
		for (Book b : books) {
			System.out.println(b.toString());
		}
	}
	
	
	public void printListOfTransactions()
	{
				
		for (Transaction t : transactions) {
			System.out.println(t.toString());
		}
	}
	
	/**
	 * 
	 * @param book, book being added
	 */

	public void addBook(Book book) {
		books.add(book);
	}
	
	/**
	 * 
	 * @param ISBN book being removed 
	 */
	public void removeBook(String ISBN)
	{
		for (int i = 0; i < books.size(); i++) {
			if (ISBN.equals(books.get(i).getISBN())) {
				books.remove(books.get(i));
			}
		}
	}

	/**
	 * 
	 * @param patron, adding patron to library
	 */
	public void addPatron(Patron patron) {
		patrons.add(patron);  
	}
	
	/**
	 * 
	 * @param cardNumber, patron being removed from library
	 */
	public void removePatron(String cardNumber)
	{
		for (int i = 0; i < patrons.size(); i++) {
			if (cardNumber.equals(patrons.get(i).getCardNumber())) {
				patrons.remove(patrons.get(i));
			}
		}
	}

	/**
	 * 
	 * @param cardNumber, allows a valid patron to check out a book
	 * @param ISBN, book being checked out
	 * @return boolean depending on if check out was successful
	 */
	public boolean checkOutBooks(String cardNumber, String ISBN) {
		boolean bookOut = false;
		boolean validCard = false;
		boolean validBook = false;
		boolean userFees = false;
		int bookIndex = 0;
		int patronIndex = 0;

		for (int i = 0; i < patrons.size(); i++) {
			if (cardNumber.equals(patrons.get(i).getCardNumber())) {
				validCard = true;
				userFees = patrons.get(i).hasFees();
				bookIndex = i;
			}
		}

		for (int i = 0; i < books.size(); i++) {
			if (ISBN.equals(books.get(i).getISBN())) {
				validBook = true;
				patronIndex = i;
			}
		}

		if (validCard == true && validBook == true && userFees == false) {
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the data: ");
			String data = input.nextLine();
			
			Transaction t1 = new Transaction(data, books.get(bookIndex), patrons.get(patronIndex), TransactionType.CHECKOUT);

			books.get(bookIndex).setCheckedOut(true);
			books.get(bookIndex).setCheckOutData(data);
			bookOut = true;

			transactions.add(t1);
			
			input.close();
		}

		return bookOut;
	}
	/**
	 * 
	 * @param cardNumber, allows patron to checked in a book 
	 * @param ISBN, book being checked in 
	 * @return boolean depending on if check in was successful 
	 */
	
	public boolean checkInBooks(String cardNumber, String ISBN) {
		boolean bookIn = false;
		boolean validCard = false;
		boolean validBook = false;
		int bookIndex = 0;
		int patronIndex = 0;

		for (int i = 0; i < patrons.size(); i++) {
			if (cardNumber.equals(patrons.get(i).getCardNumber())) {
				validCard = true;
				bookIndex = i;
			}
		}

		for (int i = 0; i < books.size(); i++) {
			if (ISBN.equals(books.get(i).getISBN())) {
				validBook = true;
				patronIndex = i;
			}
		}

		if (validCard == true && validBook == true) {
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the data: ");
			String data = input.nextLine();
			System.out.println("Enter how long you've had the book: ");
			int daysHad = input.nextInt();
			
			if(daysHad > 7)
			{
				daysHad -= 7;
				
				while(daysHad > 0)
				{
					BigDecimal fees = new BigDecimal(0.25);
					patrons.get(patronIndex).setFees(fees);
					daysHad--;
				}
					
			}
			
			Transaction t1 = new Transaction(data, books.get(bookIndex), patrons.get(patronIndex), TransactionType.CHECKIN);

			books.get(bookIndex).setCheckedOut(false);
			books.get(bookIndex).setCheckOutData("n/a");
			bookIn = true;

			transactions.add(t1);
			
			input.close();
		}

		return bookIn;
	}

	/**
	 * 
	 * @return arraylist of all patrons who have fees 
	 */
	public ArrayList<Patron> oweFees() {
		ArrayList<Patron> oweFees = new ArrayList<Patron>();

		for (int i = 0; i < patrons.size(); i++) {
			if (patrons.get(i).hasFees() == true) {
				oweFees.add(patrons.get(i));
			}
		}
		return (oweFees);
	}
	
	/**
	 * 
	 * @param cardNumber, allows a patron to pay any fees given a valid card number
	 */
	public void PayFees(String cardNumber) {
		
		
		for (int i = 0; i < patrons.size(); i++) {
			if (cardNumber.equals(patrons.get(i).getCardNumber())) 
			{
				if (patrons.get(i).hasFees() == true) {
					System.out.println("You have : " + patrons.get(i).getFees() + " in fees");
					BigDecimal fees = new BigDecimal(0.0);
					patrons.get(i).setFees(fees);
					Scanner inp = new Scanner(System.in);
					System.out.println("Enter the data: ");
					String data = inp.nextLine();
					Transaction t = new Transaction(data, patrons.get(i), TransactionType.PAYFEE);
					
					transactions.add(t);
					inp.close();
				}
			}
		}
	}
	
	/**
	 * 
	 * @param lastName, prints out patron from valid last name
	 */
	public void lookUpPatron(String lastName)
	{
		for (int i = 0; i < books.size(); i++) {
			if (patrons.get(i).getLastName().equals(lastName)) {
				System.out.println(patrons.get(i));
			}
		}
	}
	
	/**
	 * 
	 * @param cardNumber, prints out patron from valid card number
	 */
	public void lookUpPatronCardNum(String cardNumber)
	{
		for (int i = 0; i < books.size(); i++) {
			if (patrons.get(i).getCardNumber().equals(cardNumber)) {
				System.out.println(patrons.get(i));
			}
		}
	}
	
	/**
	 * 
	 * @param ISBN, prints out book with given ISBN
	 */
	public void lookUpBook(String ISBN)
	{
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getISBN().equals(ISBN)) {
				System.out.println(books.get(i).getISBN());
			}
		}
	}

	/**
	 * 
	 * @param author, finds all book with given author
	 * @return arraylist with all book from author
	 */
	public ArrayList<Book> findAuthor(String author) {
		ArrayList<Book> authorBook = new ArrayList<Book>();

		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor().equals(author)) {
				authorBook.add(books.get(i));
			}
		}
		return (authorBook);
	}
	
	   /**
	    * 
	    * @param data, method will find all books from given year
	    * @return arraylist of all transaction with a given date 
	    */
		public ArrayList<Transaction> findTransactions(String data) {
			ArrayList<Transaction> dataTransaction = new ArrayList<Transaction>();

			for (int i = 0; i < transactions.size(); i++) {
				if (transactions.get(i).getData().equals(data)) {
					dataTransaction.add(transactions.get(i));
				}
			}
			return (dataTransaction);
		}
		
		/**
		 * 
		 * @param genre, method will find all boon with given genre 
		 * @return arraylist of book with given genre
		 */
		public ArrayList<Book> findGenre(BookGenre genre) {
			ArrayList<Book> bookGenre = new ArrayList<Book>();

			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getGenre().equals(genre)) {
					bookGenre.add(books.get(i));
				}
			}
			return (bookGenre);
		}
		

	@Override
	public String toString() {
		return "Library [Patrons = " + patrons + ", Books = " + books + ", Transactions = " + transactions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((patrons == null) ? 0 : patrons.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Library other = (Library) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books)) 
			return false;
		if (patrons == null) {
			if (other.patrons != null)
				return false;
		} else if (!patrons.equals(other.patrons))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		return true;
	}
	
	
	
}


