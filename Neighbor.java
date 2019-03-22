///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Neighbor.java;
// Semester:         (course) Summer 2017
//
// Author:           Yifan Mei; ymei8@wisc.edu
// CS Login:         yifanmei
// Lecturer's Name:  Meena
// Lab Section:      (your lab section number)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Yuran Liu
// Email:            yuran.liu@wisc.edu
// CS Login:         yuran
// Lecturer's Name:  Meena
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Neighbor class represents an edge between two nodes and the associated cost.
 * By having this Neighbor class, we can add a list of neighbor named neighbors in the GraphNode.java
 * 
 * @author Yifan Mei; Yuran Liu
 */

public class Neighbor implements Comparable<Neighbor> {
    private int cost;
    private GraphNode neighbor;

    /** 
     * A neighbor is added to an existing GraphNode by creating an instance 
     * of Neighbor that stores the neighbor and the cost to reach that 
     * neighbor.
     * 
     * @param cost of the edge
     * @param neighbor GraphNode
     */
    public Neighbor(int cost, GraphNode neighbor) {
        this.cost = cost;
        this.neighbor = neighbor;
    }

    /** 
     * Returns the cost of the edge. 
     *
     * @return the cost of this edge.
     */
    public int getCost() {
        return cost;
    }

    /** 
     * Returns the neigbor on the other end of the edge. 
     *
     * @return the neighbor
     */
    public GraphNode getNeighborNode() {
        return neighbor;
    }

    /** 
     * Return the results of comparing this node's neighbor name to the other 
     * node's neighbor name. Allows List of Neighbors to be sorted. 
     * Hint: Read the java docs for String class carefully 
     *
     * @param otherNode Neighbor instance whose Graphnode needs to be compared.
     * @return negative value or 0 or positive value
     */
    public int compareTo(Neighbor otherNode) {
        return neighbor.getNodeName().compareTo(otherNode.getNeighborNode().getNodeName());
    }

    /**
     * Returns a String representation of this Neighbor.
     * The String that is returned shows an arrow (with the cost in the middle) 
     * and then the Neighbor node's name. 
     *
     * Example:  
     * " --1--> b"
     * indicates a cost of 1 to get to node b
     * Note: Quotes are given here for clarification, do not print the quotes.
     *
     * @return String representation
     */
    public String toString() {
        String s = "--";
        s += cost;
        s += "--> ";
        s += neighbor.getNodeName();
        return s;
    }
}
