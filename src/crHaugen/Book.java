package crHaugen;



/**
 * @author Chyanne Haugen
 *
 */
public class Book {

	private BookGenre genre;
	private String ISBN = " ";
	private String Author = " ";
	private String title = " ";
	private String data = " ";
	private boolean checkedOut = false;
	private String checkOutData = " ";
	
	
	public Book(BookGenre genre, String ISBN, String Author,  String title, String data, boolean checkedOut, String checkOutData)
	{
		this.genre = genre;
		this.ISBN = ISBN;
		this.Author = Author;
		this.title = title;
		this.data = data;
		this.checkedOut = checkedOut;
		this.checkOutData = checkOutData;
	}
	
	/*
	 * @return the genre
	 */
	public BookGenre getGenre() {
		return genre;
	}
	/*
	 * @param genre sets the genre
	 */
	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}
	/*
	 * @return the ISBN
	 */
	public String getISBN() {
		return ISBN;
	}
	/*
	 * @param ISBN sets the ISBN
	 */
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	/*
	 * @return author name
	 */
	public String getAuthor() {
		return Author;
	}
	/*
	 * @param author sets the author
	 */
	public void setAuthor(String author) {
		this.Author = author;
	}

	/*
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * @param title sets the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/*
	 * @return if checked out
	 */
	public boolean isCheckedOut() {
		return checkedOut;
	}
	/*
	 * @param checkedOut see is user has checked out a book
	 */
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Book [Genre = " + genre + ", ISBN = " + ISBN + ", Author = " + Author + ", Title = " + title + ", Data = " + data
				+ ", checkedOut=" + checkedOut + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Author == null) ? 0 : Author.hashCode());
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + (checkedOut ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (Author == null) {
			if (other.Author != null)
				return false;
		} else if (!Author.equals(other.Author))
			return false;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (checkedOut != other.checkedOut)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (genre != other.genre)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String getCheckOutData() {
		return checkOutData;
	}

	public void setCheckOutData(String checkOutData) {
		this.checkOutData = checkOutData;
	}
}
