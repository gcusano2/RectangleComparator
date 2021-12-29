# Rectangle Comparator

The Rectangle Comparator is a program for verifying whether 2 Rectangles have points of intersection, are contained within one another, are adjacent to each other, or a wholly separate from each other.

## Running the Service

Make sure you have Java 8 installed and added to your system PATH. To start the program, run the startup.bat script from the command line:

```bash
startup.sh
```

## Usage

The application will prompt the user to enter the x1, x2, y1, and y2 coordinates for 2 Rectangles:

```
Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1:
1 5 7 9
Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2:
-1 -5 -7 -9
```

Then, the application will check whether the rectangles are equal, have points of intersection, are contained within one another, or are adjacent to one another. In the example above, both rectangles are wholly separate from one another, so we can expect the following to be returned:

```
The rectangles have no intersections.
The rectangles are not adjacent.
Rectangles 1 and 2 do not contain one another.
```

If the rectangles are identical, the following is returned:

```
Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1:
1 5 7 9
Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2:
1 5 7 9
The rectangles are identical
```

If the rectangles have intersecting points, the points of intersection are returned:

```
Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1:
10 1 17 6
Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2:
5 4 15 10
The rectangles intersect at the following points:
(10.0,4.0)
(15.0,6.0)
```

If the rectangles are adjacent to one another, the program returns what type
of adjacency it is (proper, sub-line, or partial):

```
Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1:
6 4 10 10
Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2:
10 4 18 10
The rectangles have no intersections.
The rectangles are properly adjacent.
```

If one rectangle contains the other, the following message is returned:

```
Please enter x1 y1 x2 y2 (space separated) coordinates for Rectangle 1:
3 1 13 7
Please enter x1 y1 x2 y2  (space separated) coordinates for Rectangle 2:
6 2 8 6
The rectangles have no intersections.
The rectangles are not adjacent.
Rectangle 1 contains Rectangle 2.
````



# Notes for Nuvalence

## Design of Application and Algorithms

### Assumption

The only assumption made in this application is that the rectangles are axis-aligned (not turned on an angle).

### Rectangle Implementation

The Java implementation of the Rectangle class takes an x and y coordinate (top left of rectangle) as well as length and width in it's constructor. Since we only care about the positioning of the rectangles in relation to each other, my implementation of the Rectangle included both x and y coordinates (x1/x2/y1/y2) that make up a rectangle. The length and width were not necessary for this assignment.

The Rectangle class's constructor will "normalize" the rectangle, making sure that the x1/y1 value is always the lesser x/y coordinate. The class also contains and overridden equals method for comparing Rectangle equality.

The hasIntersections and hasAdjacency methods are static methods taking 2 rectangles as arguments. The contains method takes 1 rectangle as an argument, and checks whether that rectangle is contained within the rectangle calling the method.

### Intersections

To determine whether 2 rectangles intersect each other, the method finds the maximum x1 coordinate of the 2 rectangles and the minimum x2 coordinate of the 2 rectangles (same for y1/y2). If the max x1 (or y1) coordinate is greater than or equal to the min x2 (or y2) coordinate, then the rectangles do NOT intersect.

If they do intersect, then the max and min x and y coordinates give us the 4 corners of the resultant rectangle (the rectangle formed by the intersection of the 2 original rectangles).

In cases where there are only 2 points of intersection between the rectangles, 2 of the resultant rectangles corners will be the same coordinate as one of the original rectangle's corners. The method checks whether each corner of the resultant rectangle is the same as the corresponding corner of the original rectangles (i.e. resultant bottom-left corner = bottom-left corner of R1 or R2). If they are the same, it is NOT a point of intersection and is not included in the list of intersecting coordinates.

### Adjacency

To determine whether 2 rectangles are adjacent to each other, the method first determines whether they have left/right adjacency or top/bottom adjacency. The rectangles are left/right adjacent if the x1 coordinate of one rectangle is equal to the x2 coordinate of the other.

Then, if the y1 and y2 coordinates of both rectangles are the same, it is determined that the rectangles are properly adjacent.

If the y1 coordinate of one rectangle is equal to the y2 coordinate of the other rectangle (and vice-versa), then the rectangle's corners are touching and they are NOT adjacent.

If the y1 and y2 coordinates of 1 rectangle are between the y1 and y2 coordinates of the other, then there is a sub-line adjacency.

If one y-coordinate is between the y-coordinates of the other rectangle, but the other y-coordinate, is equal to or outside of the y-coordinates of the other rectangle, then we have a partial adjacency.

The rectangles are top/bottom adjacent if y1 coordinate of one rectangle is equal to the y2 coordinate of the other.

The same condition checks for left/right adjacent rectangles are also run (except with x-coordinates).

### Containment

To determine whether one rectangle contains another, the method checks that the x-coordinates and y-coordinates of the tested rectangle are between the x/y-coordinates of the rectangle calling the method. If not, it returns false.

## Testing

I included 34 JUnit tests comparing different pairs rectangles (see RectangleTest.java). These tests call the methods of Rectangle to see whether they are equal, intersect, adjacent to, or contain one another, as well as testing that wholly separate rectangles do not return true for any of the methods.

Many of the rectangles have differing types or intersections and adjacent edges (with positive and negative coordinates). With more time, I would have liked to include more combinations to test, since there are dozens of different scenarios to consider. However, I believe I've included the most important combinations to test.

## Ideas for Improvement

As described above, with more time I would've liked to implement more robust unit tests.

I would also have liked to rework the interface to return the prompt for rectangle inputs until the user decides to exit. Other improvement would be to allow for more than 2 rectangles to be compared or to handle rectangles that are not axis-aligned.
