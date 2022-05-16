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
        if (!from.adj.containsKey(to.getId()))
        {
            from.adj.put(to.getId(), to);
            to.adj.put(from.getId(), from);
        }
        else 
        {
            throw new RuntimeException("Edge already exists between " + from.getName() + " and " + to.getName());
        }
        
       // Test
        System.out.println(from.getName() + " is friends with " + to.getName());
        System.out.println(to.getName() + " is friends with " + from.getName());
        System.out.println("CHECK");
        System.out.println(from.getName() + "'s friends:");
        System.out.println(from.adj);
        System.out.println(to.adj.get(from.getId()));
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