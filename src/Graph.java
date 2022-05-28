import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


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

    /**
     * Adds a Node to the graph. This create the object from the
     *  parameter values and will check if the Node already exists.
     * If it doesn't, it is added to nodeList. 
     *
     * @param id Integer, name String, dob LocalDate, suburb String
     * @return Node returns the Node created
     *
     */
    public Node addNode(Integer id, String name, LocalDate dob, String suburb)
    {
        Node newPers = new Node(id,  name,  dob,  suburb);
        for (Node person : nodeList.values())
        {
            if (person.getId() == id)
            {
                throw new RuntimeException("The id: " + id + " is already associated to a Node within the Graph");
            }
        }
        if (!nodeList.containsValue(new Node(id,name,dob,suburb)))
        {
            nodeList.put(id, newPers);
            return newPers;
        } 
        else
        {
            throw new RuntimeException(name + " is already in graph.");
        }
    }

    /**
     * Adds an edge between two Nodes. This will check if an edge already exists
     * between the two Nodes, and if it doesn't, creates one. This process
     * involves adding the relationship to both Nodes map of friends.
     *
     * @param from @Node, to @Node
     * @return none
     *
     */
    public void addEdge(Node from, Node to)
    {
        if (from != null & to != null)
    	{
            //Only checks the connection of one Node and they are made simultaneously. 
            // If there hasn't been an edge created between the two Nodes, make one.
            if (!from.adj.containsKey(to.getId()))
            {
                // Edge created for each Node as the graph is undirected
                Edge friend = new Edge(to);
                from.adj.put(to.getId(), friend);
                Edge friendOtherWay = new Edge(from);
                to.adj.put(from.getId(), friendOtherWay);
            }
            else 
            {
                throw new RuntimeException("Edge already exists between " + from.getName() + " and " + to.getName());
            }
        }
        else
        {
            throw new RuntimeException("Edge to be created has Nodes with null values.");
        }
    }
     
    /**
     * Removes an edge associated with a Node. This will check the 
     * Node's associated with'from' to see if any are 'to'. If there
     * is a match, the relationship is removed.
     *
     * @param from @Node, to @Node
     * @return none
     *
     */
    public void removeEdge(Node from, Node to) {
        if (from.adj.get(to.getId()).friend == to) {
            from.adj.remove(to.getId());
            to.adj.remove(from.getId());
        }
    }

    /**
     * Removes a Node from the graph. This will remove a Node from the graph by
     * removing the edges associated to is (if any) and then the Node itself
     * 
     *
     * @param node @Node
     * @return none
     *
     */
    public void removeNode(Node node)
    {
        if(nodeList.containsValue(node))
        {
            // Checks if Node has any edges associated to it
            if (!node.adj.values().isEmpty())
            {
                // If it does, find those edges
               List<Integer> listOfFriends = new ArrayList<Integer>();
               // Reverse the HashMap so Friends of Nodes can be recorded
               // Go through each entry within the nodeList 
               for (HashMap.Entry<Integer, Node> entry : nodeList.entrySet()) 
               {
                   if (entry.getValue().adj.containsKey(node.getId()))
                   {
                       // Store the key from entry to the list
                       listOfFriends.add(entry.getKey());
                   }
               }
               for (Integer id : listOfFriends)
               {
                   nodeList.get(id).adj.remove(node.getId());
                   // As the keys in nodeList do no correspond to the keys in the adj list of each Node, 
                   // the Node is retrieved from nodeList, with the id (key in adj) of the node being removed's friend  
                   // is used to remove the connection within the node being removed's adj map. 
                   node.adj.remove(nodeList.get(id).getId()); 
                   
               }
            }
            nodeList.remove(node.getId());
        }
        else
        {
            throw new IllegalStateException(node.getName() + " is not in graph.");
        }
           
    }

    
    /**
     * Returns a set of Edges associated to a Node. 
     *
     * Acts as retrieving the neighbors of the node in the graph 
     * 
     * @param node @Node
     * @return a set of Edge objects associated to the parameter
     *
     */
    public Set<Edge> getNeighbors(Node node) {
        HashSet <Edge> neighbors = new HashSet <Edge> ();
        for (Edge e: node.adj.values()) 
        {
            neighbors.add(e);
        }
        return neighbors;
    }

    /**
     * Returns a String to display all the Nodes and the Nodes they share an edge with.
     * 
     * This represents the network graph by displaying the String of the person's name, and their friends.
     * 
     * @param none
     * @return a String of all the Nodes within nodeList, and all the Nodes associated to it through edges
     *
     */
    @Override
    public String toString()
    {
    StringBuilder nodeString = new StringBuilder();  
    for(Node person: nodeList.values()) {
        nodeString.append(person.getName() + " --> ");
        for(Edge friend: person.adj.values()) {
            nodeString.append(friend.friend.getName() + " ");
        }
        nodeString.append("\n");
    }
    return (nodeString.toString()); 
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