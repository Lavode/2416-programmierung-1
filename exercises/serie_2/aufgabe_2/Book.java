/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 2-2                         * 
\* ************************************************************************* */

// Lorenzo Wipfli 13-933-262
// Michael Senn, 16-126-880

import java.util.Date;
import java.util.Scanner;
import java.text.*;

public class Book
{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	//--- constructors ---

	public Book() {
	}

	/** Returns the age of the book in days since publication */
	public int age() {
		Date now = new Date();

		long ageMilliSeconds = now.getTime() - dateOfPublication.getTime();

		return (int)(ageMilliSeconds/1000/60/60/24);
	}

	/** Returns a String representation of the book */
	public String toString() {
		String fmt = "[%s] '%s' (By: %s) Published: %s";
		return String.format(fmt, id, title, author, dateOfPublication);

	}

	/** Reads all book data from user input */
	public void input() {
		// This method hardly belongs into the class - Book shouldn't
		// have to know about input/output streams. ;)
		// At the very least, the to-be-used input stream should be
		// injected.

		Scanner scn = new Scanner(System.in);
		// Use newline as token separator - cleanest solution for
		// multi-word input like title or author.
		scn.useDelimiter(System.getProperty("line.separator"));

		System.out.print("Please enter a numeric ID: ");
		setId(scn.nextInt());

		System.out.print("Please enter a title: ");
		setTitle(scn.next());

		System.out.print("Please enter an author: ");
		setAuthor(scn.next());

		System.out.print("Please enter the publication date as dd.mm.yyyy: ");
		setDateOfPublication(stringToDate(scn.next()));
	}

	//--- Get-/Set-methods ---

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Date date) {
		dateOfPublication = date;
	}

	//--- helper methods -- DO NOT CHANGE ------------------------------------
	/** Converts the Date object d into a String object */
	public static String dateToString(Date d) {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	public static Date stringToDate(String s) {
		Date r = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
			r = fmt.parse(s);
		} catch (ParseException e){
			System.err.println(e);
			System.exit(1);
		}
		return r;
	}
}
