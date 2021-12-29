package com.nuvalence.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nuvalence.demo.Rectangle;



class RectangleTest {
	
	Rectangle rect1 = new Rectangle(1,1,4,7);
	Rectangle rect2 = new Rectangle(1,1,4,7);
	private Rectangle rect3;
	
	//Intersecting Rectangles
	private Rectangle rect4 = new Rectangle(5,4,15,10);
	private Rectangle rect5 = new Rectangle(10,1,17,6);
	private Rectangle rect6 = new Rectangle(3,3,7,6);
	private Rectangle rect7 = new Rectangle(23,0,27,6);
	private Rectangle rect8 = new Rectangle(7,-8,16,-2);
	private Rectangle rect9 = new Rectangle(9,2,13,12);
	private Rectangle rect10 = new Rectangle(7,2,13,6);
	
	//Containing Rectangles
	private Rectangle rect11 = new Rectangle(3,1,13,7);
	private Rectangle rect12 = new Rectangle(6,2,8,6);
	private Rectangle rect13 = new Rectangle(6,2,8,6);
	private Rectangle rect14 = new Rectangle(-3,-3,5,4);
	private Rectangle rect15 = new Rectangle(-1,-1,3,2);
	private Rectangle rect16 = new Rectangle(-1,-5,2,-2);
	
	//Adjacent Rectangles
	private Rectangle rect17 = new Rectangle(6,4,10,10);
	private Rectangle rect18 = new Rectangle(10,4,18,10);
	private Rectangle rect19 = new Rectangle(10,4,18,12);
	private Rectangle rect20 = new Rectangle(1,5,6,9);
	private Rectangle rect21 = new Rectangle(1,5,3,9);
	private Rectangle rect22 = new Rectangle(12,1,16,4);
	private Rectangle rect23 = new Rectangle(7,10,13,13);
	private Rectangle rect24 = new Rectangle(5,4,10,8);
	private Rectangle rect25 = new Rectangle(10,6,14,8);
	private Rectangle rect26 = new Rectangle(10,8,14,12);
	
	//Separate Rectangles
	private Rectangle rect27 = new Rectangle(1,5,7,9);
	private Rectangle rect28 = new Rectangle(-1,-5,-7,-9);
	

	@Test
	void testEqualsObject() {
		Assertions.assertTrue(rect1.equals(rect1));
		Assertions.assertTrue(rect1.equals(rect2));
		Assertions.assertTrue(rect2.equals(rect1));
		
	}

	@Test
	void testHasIntersections() {
		Assertions.assertFalse(Rectangle.hasIntersections(rect1, rect2)); //Identical, no intersection
		Assertions.assertFalse(Rectangle.hasIntersections(rect1, rect3)); //Null rectangle
		Assertions.assertTrue(Rectangle.hasIntersections(rect4, rect5)); //2 intersections
		Assertions.assertTrue(Rectangle.hasIntersections(rect4, rect6)); //2 intersections
		Assertions.assertFalse(Rectangle.hasIntersections(rect4, rect7)); //No intersections
		Assertions.assertFalse(Rectangle.hasIntersections(rect4, rect8)); //Negative axis, no intersections
		Assertions.assertTrue(Rectangle.hasIntersections(rect4, rect9)); //4 intersections 
		Assertions.assertTrue(Rectangle.hasIntersections(rect10, rect4)); //2 intersections
		Assertions.assertTrue(Rectangle.hasIntersections(rect14, rect16)); //Negative axis, 2 intersections
		Assertions.assertFalse(Rectangle.hasIntersections(rect11, rect12)); //No intersections, 11 contains 12
		Assertions.assertFalse(Rectangle.hasIntersections(rect17, rect18)); //No intersections, proper adjacency
	}
	
	@Test
	void testContainsRectangle() {
		Assertions.assertTrue(rect11.contains(rect12)); //11 contains 12
		Assertions.assertFalse(rect12.contains(rect11)); //12 does not contain 11
		Assertions.assertFalse(rect12.contains(rect13)); //Identical, no containment
		Assertions.assertTrue(rect14.contains(rect15)); //Negative axis, 14 contains 15
		Assertions.assertFalse(rect14.contains(rect16)); //Negative axis, 14 does not contain 16
	}
	
	@Test
	void testAdjacency() {
		Assertions.assertTrue(Rectangle.hasAdjacency(rect17, rect18)); //Proper adjacency
		Assertions.assertTrue(Rectangle.hasAdjacency(rect17, rect19)); //Partial adjacency on side
		Assertions.assertTrue(Rectangle.hasAdjacency(rect17, rect20)); //Sub-line adjacency on side
		Assertions.assertFalse(Rectangle.hasAdjacency(rect17, rect21)); //No adjacency
		Assertions.assertTrue(Rectangle.hasAdjacency(rect18, rect22)); //Sub-line adjacency on top/bottom
		Assertions.assertTrue(Rectangle.hasAdjacency(rect18, rect23)); //Partial adjacency on top/bottom
		Assertions.assertFalse(Rectangle.hasAdjacency(rect4, rect5)); //Intersecting, no adjacency
		Assertions.assertFalse(Rectangle.hasAdjacency(rect11, rect12)); //Containing, no adjacency
		Assertions.assertTrue(Rectangle.hasAdjacency(rect24, rect25)); //Partial adjacency on top/bottom
		Assertions.assertFalse(Rectangle.hasAdjacency(rect24, rect26)); //Corners touching, not adjacent
		Assertions.assertFalse(Rectangle.hasAdjacency(rect26, rect24)); //Corners touching, not adjacent
	}
	
	@Test
	void testSeparateRectangles() {
		Assertions.assertFalse(Rectangle.hasIntersections(rect27, rect28));
		Assertions.assertFalse(Rectangle.hasAdjacency(rect27, rect28));
		Assertions.assertFalse(rect27.contains(rect28));
		Assertions.assertFalse(rect28.contains(rect27));
	}

}
