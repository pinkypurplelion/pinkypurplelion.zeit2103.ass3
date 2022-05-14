import java.util.List;

/**
 * represents accounts and their relationship as a graph; 
 *
 * @author Saber Elsayed
 * @version 2, April 2022
 */
public class SocialNetwork implements SocialNetworkInterface {

    protected Graph sn;

    /**
     * constructs a social network analyser object by reading data files
     */
    public SocialNetwork() {
        sn = new Graph();

    }

    /**
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        SocialNetwork driver = new SocialNetwork();
        System.out.println(driver.sn);
    }

    /**
     * reads the data file and builds the graph accordingly; remember to call
     * this method in the constructor
     */
    @Override
    public void processFile() {

    }

    /**
     * returns a list of suggested friends. This will return a List<Node> such
     * that each Node in the list is a friend of a friend that lives in the same
     * suburb as currentPerson;
     *
     * @param currentPerson @Node
     * @return a list of no more than 5 friends
     */
    @Override
    public List<Node> suggestFriends(Node currentPerson) {
        return null;
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
        return null;
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
        return null;
    }
}
