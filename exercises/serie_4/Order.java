// Lorenzo Wipfli 13-933-262
// Michael Senn, 16-126-880

import java.util.*;
public class Order {
	private static int currentId;


	private int id;
	private String customerName;
	private String customerAddress;
	ArrayList<IArticle> articles = new ArrayList<IArticle>();

	public Order()
	{
		currentId++;
		id = currentId;

	}

	public int getId() {
		return this.id;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName=customerName;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress=customerAddress;
	}

	public Iterable<IArticle> getOrderedArticles() {
		return this.articles;
	}

	public String toString()
	{
		String s = "Order id: "+id+", Customer: "+customerName+", "+ customerAddress+"\n";
		for(int i = 0; i<articles.size();i++)
		{
			s=s+articles.get(i).toString()+"\n";
		}
		s = s+"Total price: "+getTotalPrice()+" CHF \n";
		return s;
	}

	public void add(IArticle article)
	{
		articles.add(article);
	}

	public int getTotalPrice()
	{
		int prize=0;
		for(int i = 0; i<articles.size();i++)
		{
			prize=prize+articles.get(i).getPrice();
		}
		return prize;
	}
}
