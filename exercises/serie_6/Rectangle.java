/* ************************************************************************** *\
*                     Programmierung 1 HS 2016                                 *
*                            Aufgabe 6-2                                       *
\* ************************************************************************** */

public class Rectangle implements Comparable<Rectangle> {
	private int length;
	private int width;
	
	public Rectangle (int length, int width){
		this.length = length;
		this.width = width;
	}

	public int getArea(){
		return this.length*this.width;
	}
	
	public String toString(){
		return length+" x "+width+", area: "+this.getArea();
	}
	
	public int compareTo(Rectangle other){
		return this.getArea() - other.getArea();
	}
}
