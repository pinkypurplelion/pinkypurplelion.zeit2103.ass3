import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
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

                // splits lines into data segments as an array
                // needs two splits because of file formatting
                String[] split = line.split("\t|,");

                // adds node to graph
                Node node = sn.addNode(
                        Integer.parseInt(split[0]),
                        split[1],
                        LocalDate.parse(split[2]),
                        split[3].strip());

                // creates set of nodes friends from file
                HashSet<Integer> friends = new HashSet<>();

                for (int i = 4; i < split.length; i++) {
                    friends.add(Integer.parseInt(split[i].strip()));
                }

                // adds to hashset to manage adding friends
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
     */
    @Override
    public List<Node> suggestFriends(Node currentPerson) {
        HashMap<Integer, Edge> currentFriends = currentPerson.adj;
        HashSet<Edge> friendsOfFriends = new HashSet<>();
        List<Node> suggestedFriends = new ArrayList<>();

        //adds all friends of friends to the set
        for (Edge friend : currentFriends.values()) { // On wrt num friends
            friendsOfFriends.addAll(friend.friend.adj.values());
        }

        // iterates over set of friends and adds their friends to the set
        for (Edge person : friendsOfFriends) { // On wrt num friends of friends
            if (Objects.equals(person.friend.getSuburb(), currentPerson.getSuburb()))
                suggestedFriends.add(person.friend);
        }
        return suggestedFriends;
    }

    /**
     * Converts a given sorted ArrayList of nodes & a person into
     * a string of birthday reminders
     * @param currentPerson The person receiving reminders
     * @param friends A sorted list in order of days till birthday
     * @return A string containing birthday reminders
     * @author Majority Alimah, slight modification by Liam
     */
    private String createBDString(Node currentPerson, List<Node> friends) {
        LocalDate now = LocalDate.now();
        StringBuilder sb = new StringBuilder();

        sb.append("Hello ").append(currentPerson.getName()).append(":-> \n");

        for (Node personsFriend : friends) {
            int year = now.getYear();
            if (personsFriend.getDateOB().getDayOfYear() < now.getDayOfYear())
                year += 1;

            LocalDate nextBirthdate = LocalDate.of(
                    year,
                    personsFriend.getDateOB().getMonthValue(),
                    personsFriend.getDateOB().getDayOfMonth());
            Period timeUntilBday = now.until(nextBirthdate);
            sb.append(personsFriend.getName())
                    .append(" has their birthday in ")
                    .append(timeUntilBday.getYears()).append(" Year(s), ")
                    .append(timeUntilBday.getMonths()).append(" Month(s), ")
                    .append(timeUntilBday.getDays()).append(" Day(s) \n");
        }
        return sb.toString();
    }

    /**
     * it returns all friends of a given account sorted based on their
     * birthday with a string representing time until their birthday
     *
     * @param currentPerson The person receiving reminders
     * @return a string with friends of a given account sorted based on their
     * @author Majority Alimah, slight modification by Liam
     */
    @Override
    public String remindBDEvents(Node currentPerson) {
        LocalDate currentDate = LocalDate.now();

        // Converts edge collection into friend collection
        ArrayList<Node> relations = new ArrayList<>();
        for (Edge relation : currentPerson.adj.values()) {
            relations.add(relation.friend);
        }
        // Throw error if relations no friends exists
        if (relations.isEmpty())
            throw new RuntimeException(currentPerson.getName() + " has no friends.");

        // Sorted into month-date order
        Collections.sort(relations);

        // Finds the next birthday after the current date
        int x = 0;
        for (int i = x; i < relations.size(); i++) {
            int day = relations.get(i).getDateOB().getDayOfYear();
            if (day > currentDate.getDayOfYear()) {
                x = i;
                break;
            }
        }

        // Reorders list to be in terms of days until birthday
        ArrayList<Node> sortedBirthdays =
                new ArrayList<>(relations.subList(x, relations.size()));
        sortedBirthdays.addAll(relations.subList(0, x));

        return createBDString(currentPerson, sortedBirthdays);
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
