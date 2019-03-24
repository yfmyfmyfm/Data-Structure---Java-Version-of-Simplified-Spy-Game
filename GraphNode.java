import java.util.*;

/**
 * GraphNode class maintains a vertex name and a list of adjacent vertices which
 * stores the neighbors in a sorted list. Thus, in the GraphNode class, we not only need to 
 * implement the features of a node, but also the ways to get this node's neighbor(s)' cost 
 * and name
 * 
 * @author Yifan Mei; Yuran Liu
 */

public class GraphNode implements Comparable<GraphNode> {
    private String nodeName;
    private boolean spycam;
    private List<Neighbor> neighbors;
    private boolean visited;
    
    /** 
     * Constructs a GraphNode with the vertex name and empty neighbors list.
     * 
     * @param name of the vertex, which needs to be unique
     */
    public GraphNode(String name) {
        nodeName = name;
        spycam = false;
        neighbors = new LinkedList<>();
        visited = false;
    }

    /** 
     * Returns the name of the vertex. 
     *
     * @return the name of this GraphNode
     */
    public String getNodeName() {
        return nodeName;
    }

    /** 
     * Returns the neigbors of the vertex. 
     *
     * @return the neighbors of this GraphNode
     */
    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    /**
     * Sets the visited flag of this vertex.
     *
     * @param flagVal boolean value used to set the flag
     */
    public void setVisited(boolean flagVal) {
        visited = flagVal;
    }
    
    /**
     * Gets the visited flag of this vertex.
     *
     * @return visited boolean value 
     */
    public boolean getVisited() {
        return visited;
    }

    /** 
     * Return the results of comparing this node's name to the other node's 
     * name. 
     * 
     * @param otherNode GraphNode instance whose vertex name is required for 
     * comparison
     * @return negative value or 0 or positive value
     */
    public int compareTo(GraphNode otherNode) {
        return nodeName.compareTo(otherNode.getNodeName());
    }


    /** 
     * Adds a new neighbor and maintains sorted order of neighbors by neighbor 
     * name.
     *
     * @param neighbor an adjacent node 
     * @param cost to move to that node (from this node)
     */
    public void addNeighbor(GraphNode neighbor, int cost) {
        Neighbor new_neighbor = new Neighbor(cost, neighbor);
        neighbors.add(new_neighbor);
        neighbors.sort(null);
    }

    /** 
     * Prints a list of neighbors of this GraphNode and the cost of the edge to 
     * them. 
     * Example:
     * "1 b"
     * "4 c"
     * Note: Quotes are given here for clarification, do not print the quotes.
     */
    public void displayCostToEachNeighbor() {
        for (int i = 0; i < neighbors.size(); i++) {
            System.out.println(neighbors.get(i).getCost() + " " + neighbors.get(i).getNeighborNode().getNodeName());
        }
    }

    /** 
     * Returns cost to reach the neighbor.
     *
     * @param neighborName name of neighbor
     * @return cost to neighborName
     * @throws NotNeighborException if neighborName is not a neighbor
     */
    public int getCostTo(String neighborName) throws NotNeighborException {
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getNeighborNode().getNodeName().compareTo(neighborName) == 0) {
                return neighbors.get(i).getCost();
            }
        }
        throw new NotNeighborException();
    }

    /** 
     * Returns the GraphNode associated with name that is a neighbor of the 
     * current node.
     *
     * @param neighborName name of neighbor
     * @return the GraphNode associated with name that is neighbor of this node
     * @throws NotNeighborException if neighborName is not a neighbor
     */
    public GraphNode getNeighbor(String neighborName) throws NotNeighborException {
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getNeighborNode().getNodeName().compareTo(neighborName) == 0) {
                return neighbors.get(i).getNeighborNode();
            }
        }
        throw new NotNeighborException();
    }

    /** 
     * Returns an iterator that can be used to find neighbor names
     * of this GraphNode.
     *
     * @return iterator of String node labels
     */
    public Iterator<String> getNeighborNames() {
        List<String> names = new LinkedList<>();
        for (int i = 0; i < neighbors.size(); i++) {
            names.add(neighbors.get(i).getNeighborNode().getNodeName());
        }
        return names.iterator();
    }

    /** 
     * Sets/unsets spycam at this node.
     *
     * @param cam indicates whether the node now has a spycam
     */
    public void setSpycam(boolean cam) {
        spycam = cam;
    }

    /** 
     * Returns information about spycam presense in this node.
     *
     * @return true if the GraphNode has a spycam
     */
    public boolean getSpycam() {
        return spycam;
    }

    /** 
     * Returns true if this node name is a neighbor of current node.
     *
     * @param neighborName name of neighbor
     * @return true if the node is an adjacent neighbor
     */
    public boolean isNeighbor(String neighborName) {
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).getNeighborNode().getNodeName().compareTo(neighborName) == 0) {
                return true;
            }
        }
        return false;
    }

    /** 
     * Returns the name of this node.
     *
     * @return name of node
     */
    public String toString() {
        return nodeName;
    }

}

