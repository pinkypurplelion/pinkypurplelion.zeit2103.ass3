import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;



/**
 * constructs an undirected graph with some basic operations: addNode,
 * removeNode, addEdge, getNeighbors, etc.
 *
 * @author Saber Elsayed
 * @version 2.0, April 2022
 * @see Edge
 * @see Node
 */
public class Graph implements GraphInterface {

    /**
     * holds all nodes (people) in this graph
     */
    protected HashMap<Integer, Node> nodeList = new HashMap<>();
     
    int count = 0; 

    public Node addNode(Integer id, String name, LocalDate dob, String suburb)
    {
        Node newPers = new Node(id,  name,  dob,  suburb);
        if (!nodeList.containsKey(id))
        {
            count = count + 1;
            nodeList.put(count, newPers);
        } 
        return newPers;
        // LAB 
        // throw exception - if the person cannot be added to the map or regardless? Where is return
    }

    public void addEdge(Node from, Node to)
    {
        //Only checks the connection of one Node and they are made simultaneously. 
        // If there hasn't been an edge created between the two Nodes, make one.
        if (!from.connectionList.contains(to))
    	{
            // Edge created for each Node as the graph is undirected
    		from.connectionList.add(to);
    		to.connectionList.add(from);
    	}
    	else 
        {
    	    throw new RuntimeException("Edge already exists between " + from.getName() + " and " + to.getName());
        }
    	 
    	// Test
        System.out.println("CHECK");
        System.out.println(from.getName() + " is friends with " + to.getName());
        System.out.println(to.getName() + " is friends with " + from.getName());
        System.out.println(from.getName() + "'s friends:");
        for (Node nde : from.connectionList)
        {
            System.out.println(nde);
        }
        System.out.println(to.getName() + "'s friends:");
        for (Node node : to.connectionList)
        {
            System.out.println(node);
        }
        //
    }
     

    public void removeNode(Node node)
    {
        if (nodeList.containsValue(node))
        {
            // get key associated with value by use of iterator 
            // if (valueToBeRemoved.equals(entry.getValue(){}
            nodeList.remove(nodeList(node.getKey()));
        }
        else 
        {
            throw new IllegalStateException("Issue removing Node" + node.getName());
        }
    }


    /**
     * Test main that creates a graph,
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Graph g = new Graph();

        
    }
}