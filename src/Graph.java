import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



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
        if (!from.adj.containsKey(to.getId()))
        {
            // Edge created for each Node as the graph is undirected
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
     
    public void removeEdge(Node from, Node to) {
        // test line
        System.out.println("Deleting conection between: " + from + "and " + from.adj.get(to.getId()));
        
        from.adj.remove(to.getId());
        to.adj.remove(from.getId());
        
        // Test
        System.out.println("conection deleted? " + (from.adj.get(to.getId()) == null));
        System.out.println("conection deleted? " + (to.adj.get(from.getId()) == null));
        System.out.println(from.adj.get(to.getId()));
        // 
    }


    public void removeNode(Node node)
     {
    	// Test
         System.out.println("Remove Node test before removing ");
         System.out.println("NODE TO BE REMOVED: " + node);
         System.out.println("NODE FRIENDS: " + node.adj.values());
         System.out.println("NODE FRIEND FRIENDS: " + node.adj.get(6).adj.values());         
         // 
         
    	 if(nodeList.containsValue(node))
    	 {
    		 // Checks if Node has any edges associated to it
    		 if (!node.adj.isEmpty())
    		 {
    			 // If it does, find those edges
		        List<Integer> listOfFriends = new ArrayList<Integer>();
		           
		        // Reverse the HashMap so Friends of Nodes can be recorded
		        // Go through each entry within the nodeList 
		        for (HashMap.Entry<Integer, Node> entry : nodeList.entrySet()) 
		        {
		            // If 
		            if (entry.getValue().adj.containsValue(node))
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

	        // test line
        	System.out.println("node removed? "+ (nodeList.get(node.getId()) == null));
    	 }
    	 else
    	 {
    		 throw new IllegalStateException(node.getName() + " is not in graph.");
    	 }
    		
    	 // Test
         System.out.println("Remove Node test");
         System.out.println("NODE TO BE REMOVED: " + node);
         System.out.println("NODE FRIENDS: " + node.adj.values());
//         System.out.println("NODE FRIEND FRIENDS: " + node.adj.get(6).adj.values()); // should return exception as there are now no friends to see the connections associated to them
         // 
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