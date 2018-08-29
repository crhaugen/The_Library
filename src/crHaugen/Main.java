package crHaugen;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Chyanne Haugen
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//BigDecimal fees = new BigDecimal(0.0);
		Scanner input = new Scanner(System.in);
		
	   Library library = new Library();
	  	   
	   library.addBooks("books.txt");
	   library.addPatrons("patrons.txt");
	       
 
		   
		   System.out.println("Hello, Welcome to the library");
		   System.out.println("Press 1 to check out books");
		   System.out.println("Press 2 to check in books");
		   System.out.println("Press 3 to add books to the library");
		   System.out.println("Press 4 to add patrons to the library");
		   System.out.println("Press 5 to remove books from the library");
		   System.out.println("Press 6 to remove patrons from the library");
		   System.out.println("Press 7 to look up patrons with fees");
		   System.out.println("Press 8 to pay patron fees");
		   System.out.println("Press 9 to look up patron by card number");
		   System.out.println("Press 10 to look up patron by name");
		   System.out.println("Press 11 to look up books by an author");
		   System.out.println("Press 12 to look up book by ISBN");
		   System.out.println("Press 13 to look all books in a genre");
		   System.out.println("Press 14 to look up all transactions");
		   System.out.println("Press 15 to look up trasaction by data");
		  
		   int choice = input.nextInt();
		   input.nextLine(); //consume newline left over
		   
		   switch(choice)
		   {
		   case 1:
			   System.out.println("Please enter your card number: ");
			   String cardNum = input.nextLine();
			   System.out.println("Please enter ISBN of book: ");
			   String ISBN = input.nextLine();
			   library.checkOutBooks(cardNum, ISBN);
			   break;
		   case 2:
			   System.out.println("Please enter your card number: ");
			   String cardNum2 = input.nextLine();
			   System.out.println("Please enter ISBN of book: ");
			   String ISBN2 = input.nextLine();
			   library.checkInBooks(cardNum2, ISBN2);
			   break;
		   case 3:
			   System.out.println("Please enter your first name: ");
			   String firstName = input.nextLine();
			   System.out.println("Please enter your last name: ");
			   String lastName = input.nextLine();
			   System.out.println("Please enter card number for new patron: ");
			   String newNUM = input.nextLine();
			   BigDecimal fees = new BigDecimal(0.0);
			   Patron p = new Patron(firstName, lastName, newNUM, fees);
			   library.addPatron(p);
			   break;
		   case 4:
			   System.out.println("Please enter genre of book: ");
			   BookGenre genre =  BookGenre.valueOf(input.nextLine());
			   System.out.println("Please enter ISBN of book ");
			   String newISBN = input.nextLine();
			   System.out.println("Please enter author of book ");
			   String author = input.nextLine();
			   System.out.println("Please enter title of book ");
			   String title = input.nextLine();
			   System.out.println("Please enter data ");
			   String data = input.nextLine();
			   
			   Book b = new Book(genre, newISBN, author, title, data, false, "n/a");
			   library.addBook(b);
			   break;
		   case 5:
			   System.out.println("Please enter ISBN of book ");
			   String removeISBN = input.nextLine();
			   library.removeBook(removeISBN);
			   break;
		   case 6:
			   System.out.println("Please enter card number for new patron: ");
			   String removeCard = input.nextLine();
			   library.removePatron(removeCard);
			   break;
		   case 7:
			   for (Patron pa : library.oweFees()) {
					System.out.println(pa.toString());
				}
			   break;
		   case 8:
			   System.out.println("Please enter your card number:");
			   String card = input.nextLine();
			   library.PayFees(card);
			   break;
		   case 9:
			   System.out.println("Please enter card number for patron: ");
			   String cardNumLook = input.nextLine();
			  library.lookUpPatronCardNum(cardNumLook);
			  break;
		   case 10:
			   System.out.println("Please enter last name of patron: ");
			   String lastNameLook = input.nextLine();
			  library.lookUpPatron(lastNameLook);
			  break;
		   case 11:
			   System.out.println("Please enter author of book "); 
			   String authorLook = input.nextLine();
			   
			   for (Book bo : library.findAuthor(authorLook)) {
					System.out.println(bo.toString());
				}
			 
			   break;
		   case 12:
			   System.out.println("Please enter ISBN of book ");
			   String ISBNLook = input.nextLine();
			   library.lookUpBook(ISBNLook);
			   break;
		   case 13:
			   System.out.println("Please enter genre of book: ");
			   BookGenre genreLook =  BookGenre.valueOf(input.nextLine());
			   library.findGenre(genreLook);
			   for (Book bg : library.findGenre(genreLook)) {
					System.out.println(bg.toString());
				}
			   break;
		   case 14:
			   library.printListOfTransactions();
			   break;
		   case 15:
			   System.out.println("Please enter data of transactions you want");
			   String transactionData = input.nextLine();
			   library.findTransactions(transactionData);
			   break;
		   default:
				   System.out.println("Error invalid input");
		   }
		  
		    
	   library.readBooksToFile();
	   library.readPatronsToFile(); 
	   
	  input.close(); 
	   
	}
}

