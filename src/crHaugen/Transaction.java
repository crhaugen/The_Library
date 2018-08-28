package crHaugen;


/**
 * @author Chyanne Haugen
 *
 */

public class Transaction {
	
	private String data = " ";
	private Book book;
	private Patron patron;
	private TransactionType transactionType;
    
	
	public Transaction(String data, Book book, Patron patron, TransactionType transactionType)
	{
		this.data = data;
		this.book = book;
		this.patron = patron;	
		this.setTransactionType(transactionType);
	}
 
	public Transaction(String data, Patron patron, TransactionType transactionType)
	{
		this.data = data;
		//this.book = book;
		this.patron = patron;	
		this.setTransactionType(transactionType);
	}

	/*
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/*
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	
	/*
	 * @return the patron
//	 */
	public Patron getPatron() {
		return patron;
	}



	@Override
	public String toString() {
		return "Transaction [data=" + data + ", book=" + book + ", patron=" + patron + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
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
		Transaction other = (Transaction) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (patron == null) {
			if (other.patron != null)
				return false;
		} else if (!patron.equals(other.patron))
			return false;
		return true;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}


}
    

