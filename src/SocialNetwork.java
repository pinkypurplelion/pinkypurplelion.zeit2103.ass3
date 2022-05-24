import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 * represents accounts and their relationship as a graph; 
 *
 * @author Saber Elsayed
 * @version 2, April 2022
 */
public class SocialNetwork implements SocialNetworkInterface {

    private static final Logger logger =
            Logger.getLogger(SocialNetwork.class.getName());

    protected Graph sn;

    /**
     * constructs a social network analyser object by reading data files
     */
    public SocialNetwork() {
        sn = new Graph();
        processFile();
    }

    /**
     * reads the data file and builds the graph accordingly; remember to call
     * this method in the constructor
     */
    @Override
    public void processFile() {
        HashMap<Node, HashSet<Integer>> nodes = new HashMap<>();

        try (FileInputStream inputStream = new FileInputStream("resources/data.txt");
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                //splits lines into data segments as an array
                //needs two splits because of file formatting
                String[] split = line.split("\t");
                String[] split2 = split[2].split(",");

                // adds node to graph
                Node node = sn.addNode(Integer.parseInt(split[0]), split[1], LocalDate.parse(split2[0]), split2[1].strip());

                //creates set of nodes friends from file
                HashSet<Integer> friends = new HashSet<>();

                for (int i = 2; i < split2.length; i++) {
                    friends.add(Integer.parseInt(split2[i].strip()));
                }

                //adds to hashset to manage adding friends
                nodes.put(node, friends);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                logger.severe("IOException: Error reading file. Error: " + sc.ioException());
            }
        } catch (IOException e) {
            logger.severe("Error reading files. Error: " + e.getMessage());
        }
        for (Node node : nodes.keySet()) { // iterates over nodes added
            for (Integer friend : nodes.get(node)) { //iterates over friends
                //locates friend in hashtable, adds them as a friend
                try {
                    sn.addEdge(node, sn.nodeList.get(friend));
                } catch (RuntimeException e) {
                    System.out.println("Error when adding edge: " + e.getMessage());
                }
            }
        }
    }

    /**
     * returns a list of suggested friends. This will return a List<Node> such
     * that each Node in the list is a friend of a friend that lives in the same
     * suburb as currentPerson;
     *
     * @param currentPerson @Node
     * @return a list of no more than 5 friends
     *
     * TODO: test
     */
    @Override
    public List<Node> suggestFriends(Node currentPerson) {
        HashMap<Integer, Edge> currentFriends = currentPerson.adj;
        HashSet<Edge> friendsOfFriends = new HashSet<>();
        List<Node> suggestedFriends = new ArrayList<>();

        //adds all friends of friends to the set
        for (Edge friend : currentFriends.values()) {
            friendsOfFriends.addAll(friend.friend.adj.values());
        }
        System.out.println("current friends: " + currentFriends.values());
        System.out.println("friends of friends: " + friendsOfFriends);

        for (Edge person : friendsOfFriends) {
            if (Objects.equals(person.friend.getSuburb(), currentPerson.getSuburb()))
                suggestedFriends.add(person.friend);
        }
        return suggestedFriends;
    }

    /**
     * it returns all of friends of a given account sorted based on their
     * birthday with a string representing time until their birthday
     *
     * @param currentPerson
     * @return a string with friends of a given account sorted based on their
     * birthday with a string representing time until their birthday
     */
    @Override
    public String remindBDEvents(Node currentPerson) {
        LocalDate date = LocalDate.now();
        PriorityQueue<Node> friends = new PriorityQueue<>(
                currentPerson.adj.values().size(),
                new NodeComparator());
//        for (Node n :
//                currentPerson.adj.values()) {
//            friends.add(n);
//        }

        while (!friends.isEmpty())
            System.out.println(friends.poll());
//        friends.addAll(currentPerson.adj.values());
//        PriorityQueue<Node> friends = new PriorityQueue<>(currentPerson.adj.values());
//        System.out.println(friends);
//        StringBuilder sb = new StringBuilder();
//        sb.append("Hello " + currentPerson.getName() + ": \n");
//        for (Node n :
//                friends) {
//            sb.append(n.getName() + " has their birthday in " + n.getDateOB() +" \n");
//        }
//        return sb.toString();
        return "";
    }

    /**
     * get a list of all names of mutual friends of two friends x and y
     *
     * @param x first person
     * @param y second person
     * @return a List<String> with all mutual friends, it should contain only
     * names
     */
    @Override
    public List<String> getMutualFriends(Node x, Node y) {
        HashMap<Integer, Edge> xFriends = x.adj;
        HashMap<Integer, Edge> yFriends = y.adj;
        List<String> mutualFriends = new ArrayList<>();
        for (Edge friend :
                xFriends.values()) {
            if (yFriends.containsValue(friend))
                mutualFriends.add(friend.friend.getName());
        }
        return mutualFriends;
    }
}
