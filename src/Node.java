
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version 2.0, April 2022
 * @author Saber Elsayed
 */
class Node implements NodeInteface, Comparable<Node> {

    //id
    private Integer id;
    //person name
    private String name;

    //date of birth
    private LocalDate dateOB;
    //suburb a person lives at
    private String suburb;

    //contains a list of all friends of a person object
    protected HashMap<Integer, Edge> adj;
    //contains a list of all firends of a person object
    // protected HashMap<Integer, Node> adj = new HashMap<Integer, Node>();

    // contains list of friends of a node (edge)
    protected List<Node> connectionList = new ArrayList<>();

    /**
     * Construct a new vertex in the graph with the supplied id, name, DOB and
     * suburb.
     *
     */
    public Node(Integer id, String name, LocalDate dob, String suburb) {
        this.id = id;
        this.name = name;
        this.dateOB = dob;
        this.suburb = suburb;
    }

    /**
     * get account id
     *
     * @return account id
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     * get account name
     *
     * @return account name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * get suburb
     *
     * @return suburb
     */
    @Override
    public String getSuburb() {
        return this.suburb;
    }

    /**
     * get date of birth
     *
     * @return date of birth
     */
    @Override
    public LocalDate getDateOB() {
        return this.dateOB;
    }

    /**
     * Converts the Node object into a String
     *
     * @return A string representation of the node
     */
    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOB=" + dateOB +
                ", suburb='" + suburb + '\'' +
                '}';
    }

    /**
     * @param o Object to compare against
     * @return true if equal, false if not
     * TODO: verify equals words/implement
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id.equals(node.id)
                && name.equals(node.name)
                && dateOB.equals(node.dateOB)
                && suburb.equals(node.suburb);
    }

    /**
     * @return The hashcode of the object
     *
     * TODO: test different hash lengths
     * TODO: test number of squares required
     * TODO: handle null input values?
     */
    @Override
    public int hashCode() {
        // multiples of 2 because references index
        int subSeedLen = 6;
        int hashLength = 8;

        StringBuilder nameChar = new StringBuilder();
        for (char c :
                this.name.toCharArray()) {
            nameChar.append((int) c);
        }
        int nameSeed;
        if (nameChar.length() > subSeedLen)
            nameSeed = Integer.parseInt(
                    nameChar.substring(
                            nameChar.length()/2-(subSeedLen/2),
                            nameChar.length()/2+(subSeedLen/2)));
        else
            nameSeed = Integer.parseInt(nameChar.toString());

        StringBuilder suburbChar = new StringBuilder();
        for (char c :
                this.suburb.toCharArray()) {
            suburbChar.append((int) c);
        }
        int suburbSeed;
        if (suburbChar.length() > subSeedLen)
            suburbSeed = Integer.parseInt(
                    suburbChar.substring(
                            suburbChar.length()/2-(subSeedLen/2),
                            suburbChar.length()/2+(subSeedLen/2)));
        else
            suburbSeed = Integer.parseInt(suburbChar.toString());

        int dateSeed = this.dateOB.getDayOfYear();

        long seed = Long.parseLong(nameSeed + "" + dateSeed + "" + suburbSeed);

        long seedSqrd = seed * seed;
        if (seedSqrd < 0) seedSqrd *= -1;

        String seedS = Long.toString(seedSqrd);

        if (seedS.length() < hashLength)
            return (int) seedSqrd;
        return Integer.parseInt(seedS.substring(seedS.length()/2-(hashLength/2), seedS.length()/2+(hashLength/2)));
    }

    @Override
    public int compareTo(Node o) {
        int month = o.getDateOB().getMonthValue();
        int day = o.getDateOB().getDayOfMonth();
        if (month < this.dateOB.getMonthValue()) return 1;
        else if (month > this.dateOB.getMonthValue()) return -1;
//        else if (day < this.dateOB.getDayOfMonth()) return 1;
//        else if (day > this.dateOB.getDayOfMonth()) return -1;
        return 0;
    }
}

class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        int month = a.getDateOB().getMonthValue();
        int day = a.getDateOB().getDayOfMonth();
        if (month < b.getDateOB().getMonthValue()) return 1;
        else if (month > b.getDateOB().getMonthValue()) return -1;
        return 0;
    }
}
