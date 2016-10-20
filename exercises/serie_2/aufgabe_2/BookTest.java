// Lorenzo Wipfli 13-933-262
// Michael Senn, 16-126-880

import java.util.GregorianCalendar;

public class BookTest
{
	public static void main(String[] args) {
		Book book = new Book();

		book.setId(123);
		System.out.println("Book has ID: " + book.getId());

		book.setTitle("A story of two mice");
		System.out.println("Book has title: " + book.getTitle());

		book.setAuthor("Tom Briggs");
		System.out.println("Author of the book is: " + book.getAuthor());

		book.setDateOfPublication(new GregorianCalendar(2015, java.util.Calendar.JANUARY, 3).getTime());
		System.out.println("Book was published on: " + book.getDateOfPublication());

		System.out.println("Book was published " + book.age() + " days ago");

		System.out.println(book);

		System.out.println("Now modify at will :)");
		book.input();
		System.out.println(book);
	}
}
