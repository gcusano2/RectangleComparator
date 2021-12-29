package com.nuvalence.demo;
import java.util.Scanner;
import java.util.ArrayList;

public class RectangleComparator {
	
	static Scanner scan = new Scanner(System.in);

	public static void main(String args[]) {
		ArrayList<Integer> rect1 = new ArrayList<Integer>();
		ArrayList<Integer> rect2 = new ArrayList<Integer>();
		
		//Gets the coordinate of Rectangle 1 from user input.
		System.out.println("Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1: ");
		int rect1Itr = 0;
		while(rect1Itr<4){
			rect1.add(scan.nextInt());		
			rect1Itr++;
		}
		
		//If x1=x2 or y1=y2, it is not a rectangle.
		if (rect1.get(0) == rect1.get(2) || rect1.get(1) == rect1.get(3)) {
			throw new IllegalArgumentException ("x1/x2 and y1/y2 cannot have the same coordinate.");
		}
		
		if (rect1.size() != 4) {
			throw new IllegalArgumentException ("You must provide 4 integers.");
		}

		//Gets the coordinate of Rectangle 2 from user input.
		System.out.println("Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2: ");
		int rect2Itr = 0;
		while(rect2Itr<4) {
			rect2.add(scan.nextInt());	
			rect2Itr++;
		}
		scan.close();
		
		//If x1=x2 or y1=y2, it is not a rectangle.
		if (rect2.get(0) == rect2.get(2) || rect2.get(1) == rect2.get(3)) {
			throw new IllegalArgumentException ("x1/x2 and y1/y2 cannot have the same coordinate.");
		}
		
		if (rect2.size() != 4) {
			throw new IllegalArgumentException ("You must provide 4 integers.");
		}
		
		
		Rectangle r1 = new Rectangle(rect1.get(0),rect1.get(1),rect1.get(2),rect1.get(3));
		Rectangle r2 = new Rectangle(rect2.get(0),rect2.get(1),rect2.get(2),rect2.get(3));
		
		
		//First check if the rectangles are equal. If they are, there is not intersection, containment
		//or adjacency.
		if (r1.equals(r2)) {
			System.out.println("The rectangles are identical");
			System.exit(0);
		}
		
		//Next find the intersections of the rectangles. If they intersect, there is no containment
		//or adjacency.
		else if(Rectangle.hasIntersections(r1, r2)) {
			System.exit(0);
		}
		
		//Next find if the rectangles are adjacent. If they are, there is no containment.
		else if (Rectangle.hasAdjacency(r1, r2)){
			System.exit(0);
		}
		
		//Next find if one rectangle is contained within the other.
		else if (r1.contains(r2)) {
			System.out.println("Rectangle 1 contains Rectangle 2.");
			System.exit(0);
		}
		
		else if (r2.contains(r1)) {
			System.out.println("Rectangle 2 contains Rectangle 1.");
			System.exit(0);
		}
			
		//No containment
		else {
			System.out.println("Rectangles 1 and 2 do not contain one another.");
		}
		System.exit(0);
		
	}
}
