import java.util.*;
/**
 * Stores all vertexes as a list of GraphNodes.  Provides necessary graph operations as
 * need by the SpyGame class. 
 * In this class, we implement DFS and BFS search methods to find the path(s) from the start 
 * node to the end node. With Spy.java, these 2 classes implements the feature of spy(s).
 * 
 * @author Yifan Mei; Yuran Liu
 */
public class SpyGraph implements Iterable<GraphNode> {

    private List<GraphNode> vlist;

    /**
     * Initializes an empty list of GraphNode objects
     */
    public SpyGraph(){
         // TODO initialize data member(s)
        vlist = new LinkedList<>();
    }

    /**
     * Add a vertex with this label to the list of vertexes.
     * No duplicate vertex names are allowed.
     * @param name The name of the new GraphNode to create and add to the list.
     */
    public void addGraphNode(String name){
         // TODO implement this method
        GraphNode new_greaphnode = new GraphNode(name);
        vlist.add(new_greaphnode);
    }

    /**
     * Adds v2 as a neighbor of v1 and adds v1 as a neighbor of v2.
     * Also sets the cost for each neighbor pair.
     *   
     * @param v1name The name of the first vertex of this edge
     * @param v2name The name of second vertex of this edge
     * @param cost The cost of traveling to this edge
     * @throws IllegalArgumentException if the names are the same
     */
    public void addEdge(String v1name, String v2name, int cost) 
                throws IllegalArgumentException {
         // TODO implement this method
        if (v1name.compareTo(v2name) == 0) {
            throw new IllegalArgumentException();
        }
        GraphNode v1 = getNodeFromName(v1name);
        GraphNode v2 = getNodeFromName(v2name);
        v1.addNeighbor(v2, cost);
        v2.addNeighbor(v1, cost);
    }

    /**
     * Return an iterator through all nodes in the SpyGraph
     * @return iterator through all nodes in alphabetical order.
     */
    public Iterator<GraphNode> iterator() {
        return vlist.iterator();
    }

    /**
     * Return Breadth First Search list of nodes on path 
     * from one Node to another.
     * @param start First node in BFS traversal
     * @param end Last node (match node) in BFS traversal
     * @return The BFS traversal from start to end node.
     */
    public List<Neighbor> BFS( String start, String end ) {
         // TODO implement this method
         // may need and create a companion method
        List<Neighbor> nq = new LinkedList<>();
        List<GraphNode> q = new LinkedList<>();
        List<GraphNode> hist = new LinkedList<>();
        List<Integer> iq = new LinkedList<>();
        GraphNode startv = getNodeFromName(start);
        GraphNode endv = getNodeFromName(end);
        int pos = 0;
        q.add(startv);
        hist.add(startv);
        iq.add(-1);
        startv.setVisited(true);
        pos = 0;
        while (pos < q.size()) {
            startv = q.get(pos);
            if (startv.compareTo(endv) == 0) {
                break;
            }
            for (int i = 0; i < startv.getNeighbors().size(); i++) {
                if (startv.getNeighbors().get(i).getNeighborNode().getVisited()) continue;
                startv.getNeighbors().get(i).getNeighborNode().setVisited(true);
                q.add(startv.getNeighbors().get(i).getNeighborNode());
                hist.add(startv.getNeighbors().get(i).getNeighborNode());
                iq.add(pos);
            }
            pos++;
        }
        endv = q.get(pos);
        while (!endv.getNodeName().equals(start)) {
            pos = iq.get(pos);
            startv = q.get(pos);
            try {
                Neighbor tmp = new Neighbor(startv.getCostTo(endv.getNodeName()), endv);
                nq.add(0, tmp);
                endv = q.get(pos);
            } catch (NotNeighborException e) {
                ;
            }
        }
        for (int i = 0; i < hist.size(); i++) {
            hist.get(i).setVisited(false);
        }
        return nq;
    }


    /**
     * @param name Name corresponding to node to be returned
     * @return GraphNode associated with name, null if no such node exists
     */
    public GraphNode getNodeFromName(String name){
        for ( GraphNode n : vlist ) {
            if (n.getNodeName().equalsIgnoreCase(name))
                return n;
        }
        return null;
    }

    /**
     * Return Depth First Search list of nodes on path 
     * from one Node to another.
     * @param start First node in DFS traversal
     * @param end Last node (match node) in DFS traversal
     * @return The DFS traversal from start to end node.
     */
    public List<Neighbor> DFS(String start, String end) {
         // TODO implement this method
         // may need and create a companion method
        List<Neighbor> nq = new LinkedList<>();
        List<GraphNode> q = new LinkedList<>();
        List<GraphNode> hist = new LinkedList<>();
        GraphNode startv = getNodeFromName(start);
        GraphNode endv = getNodeFromName(end);
        int pos = 0;
        q.add(startv);
        hist.add(startv);
        startv.setVisited(true);
        while (q.size() > 0) {
            startv = q.get(q.size() - 1);
            if (startv.compareTo(endv) == 0) {
                break;
            }
            int i = 0;
            for (i = 0; i < startv.getNeighbors().size(); i++) {
                if (!startv.getNeighbors().get(i).getNeighborNode().getVisited()) {
                    startv.getNeighbors().get(i).getNeighborNode().setVisited(true);
                    q.add(startv.getNeighbors().get(i).getNeighborNode());
                    hist.add(startv.getNeighbors().get(i).getNeighborNode());
                    break;
                }
            }
            if (i == startv.getNeighbors().size()) {
                q.remove(q.size() - 1);
            }
        }

        for (int i = 1; i < q.size(); i++) {
            startv = q.get(i - 1);
            endv = q.get(i);
            try {
                Neighbor tmp = new Neighbor(startv.getCostTo(endv.getNodeName()), endv);
                nq.add(tmp);
            } catch (NotNeighborException e) {
                ;
            }
        }
        for (int i = 0; i < hist.size(); i++) {
            hist.get(i).setVisited(false);
        }
        return nq;
    }

    /**
     * OPTIONAL: Students are not required to implement Dijkstra's ALGORITHM
     *
     * Return Dijkstra's shortest path list of nodes on path 
     * from one Node to another.
     * @param start First node in path
     * @param end Last node (match node) in path
     * @return The shortest cost path from start to end node.
     */
    public List<Neighbor> Dijkstra(String start, String end){

        // TODO: implement Dijkstra's shortest path algorithm
        // may need and create a companion method
        List<Neighbor> nq = new LinkedList<>();

        return nq;
    }


    /**
     * DO NOT EDIT THIS METHOD 
     * @return a random node from this graph
     */
    public GraphNode getRandomNode() {
        if (vlist.size() <= 0 ) {
            System.out.println("Must have nodes in the graph before randomly choosing one.");
            return null;
        }
        int randomNodeIndex = Game.RNG.nextInt(vlist.size());
        return vlist.get(randomNodeIndex);
    }


}
