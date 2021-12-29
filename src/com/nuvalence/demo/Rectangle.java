package com.nuvalence.demo;
import java.math.*;
import java.awt.Point;
import java.util.ArrayList;

public class Rectangle {
	private int x1;
	private int x2;
	private int y1;
	private int y2;

	
	//Normalize Constructor so that x1 is always the left-most x coordinate
	// and y1 is always the bottom-most y coordinate.
	public Rectangle(int x1, int y1, int x2, int y2) {
		
		if (x1 > x2) { 
			this.x1 = x2;
			this.x2 = x1;
		}
		else {
			this.x1 = x1;
			this.x2 = x2;
		}
		
		if (y1 > y2) {
			this.y1 = y2;
			this.y2 = y1;
		}
		else {
			this.y1 = y1;
			this.y2 = y2;
		}

	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public int getY2() {
		return y2;
	}
	public void setY2(int y2) {
		this.y2 = y2;
	}


	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Rectangle)) {
			return false;
		}
		
		Rectangle r = (Rectangle) o;
		
		return x1 == r.x1 &&
				x2 == r.x2 &&
				y1 == r.y1 &&
				y2 == r.y2;
	}
	
	//Finds if there are any points of intersection and returns true. Returns false
	//if the rectangles have no intersections, are identical, one contains the other
	//or if one or both are null.
	public static boolean hasIntersections(Rectangle r1, Rectangle r2) {
		
		if (r1 == null || r2 == null) {
			System.out.println("One or both of the rectangles are null");
			return false;
		}
		
		if (r1.equals(r2)) {
			return false;
		}
		
		if (r1.contains(r2) || r2.contains(r1)) {
			System.out.println("The rectangles have no intersections.");
			return false;
		}
		
		int xMax = Math.max(r1.getX1(), r2.getX1());
		int yMax = Math.max(r1.getY1(), r2.getY1());
		int xMin = Math.min(r1.getX2(), r2.getX2());
		int yMin = Math.min(r1.getY2(), r2.getY2());
		
		//If the max value of x1 for both rectangles is greater than the min value
		//of x2 for both rectangles, then the rectangles do not intersect. The same goes
		//for y1 and y2.
		
		if (xMax >= xMin || yMax >= yMin) {
			System.out.println("The rectangles have no intersections.");
			return false;
		}
		
		//Using the max and min values of the x and y coordinates, we can determine
		//the 4 vertexes of the resultant intersecting rectangle.

		ArrayList<Point> intersections = new ArrayList<Point>();
		Point p1 = new Point(xMax,yMax); //bottom left corner
		Point p2 = new Point(xMax,yMin); //top left corner
		Point p3 = new Point(xMin,yMin); //top right corner
		Point p4 = new Point(xMin,yMax); //bottom right corner

		
		//The bottom left corner is a point of intersection if it does NOT have
		//the same coordinates as the top left corners of r1 or r2.
		
		if (!(p1.getX() == r1.getX1() && p1.getY() == r1.getY1()) && 
			!(p1.getX() == r2.getX1() && p1.getY() == r2.getY1())) {
			intersections.add(p1);
		}
		
		//The top left corner is a point of intersection if it does NOT have
		//the same coordinates as the top left corners of r1 or r2.	
		
		if (!(p2.getX() == r1.getX1() && p2.getY() == r1.getY2()) &&
			!(p2.getX() == r2.getX1() && p2.getY() == r2.getY2())) {
			
			intersections.add(p2);
		}
		
		//The top right corner is a point of intersection if it does NOT have
		//the same coordinates as the top right corners of r1 or r2.
		
		if (!(p3.getX() == r1.getX2() && p3.getY() == r1.getY2()) &&
			!(p3.getX() == r2.getX2() && p3.getY() == r2.getY2())) {
			
			intersections.add(p3);
		}
		
		//The bottom right corner is a point of intersection if it does NOT have
		//the same coordinates as the bottom right corners of r1 or r2.
		
		if (!(p4.getX() == r1.getX2() && p4.getY() == r1.getY1()) &&
			!(p4.getX() == r2.getX2() && p4.getY() == r2.getY1())) {
			
			intersections.add(p4);
		}
		
		System.out.println("The rectangles intersect at the following points:");
		for (Point p : intersections) {
			System.out.println("(" + p.getX() + "," + p.getY() + ")");
		}
		return true;
				
	}
	
	
	//Returns true if the rectangle contains Rectangle r
	public boolean contains(Rectangle r) {
		
		if (this.equals(r)) {
			return false;
		}
		
		// If r.x1 and r.x2 are between the subject rectangle's x1 and x2,
		// and r.y1 and r.y2 are between the subject rectangle's y1 and y2,
		// then r is wholly contained within the rectangle
		
		return this.x1 <= r.getX1() && r.getX2() <= this.x2
		&& this.y1 <= r.getY1() && r.getY2() <= this.y2;
	}
	
	//Finds if the rectangles have left/right or top/bottom adjacency, and whether
	//that adjacency is proper, sub-line, or partial. Returns false if there is 
	//no adjacency.
	
	public static boolean hasAdjacency(Rectangle r1, Rectangle r2) {

		//Check for left/right adjacency
		if (r1.getX1() == r2.getX2() || r1.getX2() == r2.getX1()) {
		
			//If both rectangle's y-coordinates are the same, they are proper.
			if (r1.getY1() == r2.getY1() && r1.getY2() == r2.getY2()) {
				System.out.println("The rectangles are properly adjacent.");
				return true;
			}
			
			//Check if only the rectangles' corners are touching (not adjacent)
			else if (r1.getY1() == r2.getY2() || r1.getY2() == r2.getY1()) {
				System.out.println("The rectangles are not adjacent, but the corners are touching.");
				return false;
			}
			
			//If the y-coordinates of one rectangle are between the y-coordinates
			//of the other, there is a sub-line adjacency.
			else if ((r1.getY1() < r2.getY1() && r1.getY2() > r2.getY2()) ||
					(r2.getY1() < r1.getY1() && r2.getY2() > r1.getY2())) {
				System.out.println("The rectangles have sub-line adjacency.");
				return true;
			}
			
			//If one y-coordinate is between the y-coordinates of the other rectangle, but the
			//other y-coordinate, is equal to or outside of the y-coordinates of the other rectangle,
			//then we have a partial adjacency
			else if (( r2.getY1() <= r1.getY1() && r2.getY2() >= r1.getY1() && r1.getY2() >= r2.getY2()) ||
					 (r2.getY1() <= r1.getY2() && r2.getY2() >= r1.getY2() && r1.getY1() <= r2.getY1())
					) {
				System.out.println("The rectangles have partial adjacency.");
				return true;
			}
			
			
			else {
				System.out.println("The rectangles are not adjacent.");
				return false;
			}
		}
		
		//Check for top/bottom adjacency
		else if (r1.getY1() == r2.getY2() || r1.getY2() == r2.getY1()) {
			
			//If both rectangle's x-coordinates are the same, they are proper.
			if (r1.getX1() == r2.getX1() && r1.getX2() == r2.getX2()) {
				System.out.println("The rectangles are properly adjacent.");
				return true;
			}
			
			//Check if only the rectangles' corners are touching (not adjacent)
			else if (r1.getX1() == r2.getX2() || r1.getX2() == r2.getX1()) {
				System.out.println("The rectangles are not adjacent, but the corners are touching.");
				return false;
			}
			
			//If the x-coordinates of one rectangle are between the x-coordinates
			//of the other, there is a sub-line adjacency.
			else if ((r1.getX1() < r2.getX1() && r1.getX2() > r2.getX2()) ||
					(r2.getX1() < r1.getX1() && r2.getX2() > r1.getX2())) {
				System.out.println("The rectangles have sub-line adjacency.");
				return true;
			}
			
			////If one x-coordinate is between the x-coordinates of the other rectangle, but the
			//other x-coordinate, is equal to or outside of the x-coordinates of the other rectangle,
			//then we have a partial adjacency.
			else if (( r2.getX1() < r1.getX1() && r2.getX2() > r1.getX1() && r1.getX2() >= r2.getX2()) ||
					 (r2.getX1() < r1.getX2() && r2.getX2() > r1.getX2() && r1.getX1() <= r2.getX1())
					) {
				System.out.println("The rectangles have partial adjacency.");
				return true;
			}
			else {
				System.out.println("The rectangles are not adjacent.");
				return false;
			}
		}
		else {
			System.out.println("The rectangles are not adjacent.");
			return false;
		}
			
	}
	
}
