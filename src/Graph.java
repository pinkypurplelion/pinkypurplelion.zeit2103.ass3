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

    // hasmap of nodes and list of nodes its atached to 
    // if it does exist yet, make it
    // if it does exist, get key of node and add the key of the other node to the list within the map
    // public void addEdge(Node from, Node to)
    // {
    //     // if the hashmap doesn't contain the from node,
    //     if (!nodeList.containsValue(from))
    //     {
    //         // add it to the map
    //         addNode(from.getId(), from.getName(), from.getDateOB(), from.getSuburb());
    //     } else 
    //     if (!nodeList.containsValue(to))
    //     {
    //         addNode(to.getId(), to.getName(), to.getDateOB(), to.getSuburb());
    //         nodeList.get()
    //     }
    // }

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