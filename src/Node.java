
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
 * Represents a vertex in the graph with its adjacency list of edges.
 *
 * @version 2.0, April 2022
 * @author Saber Elsayed
 */
class Node implements NodeInteface {

    //id
    private Integer id;
    //person name
    private String name;

    //date of birth
    private LocalDate dateOB;
    //suburb a person lives at
    private String suburb;

    //contains a list of all firends of a person object
    protected HashMap<Integer, Edge> adj;

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
     *
     * @return
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
        return id.equals(node.id) && name.equals(node.name) && dateOB.equals(node.dateOB) && suburb.equals(node.suburb);
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
        StringBuilder nameChar = new StringBuilder();
        for (char c :
                this.name.toCharArray()) {
            nameChar.append((int) c);
        }
        int nameSeed;
        if (nameChar.length() > 2)
            nameSeed = Integer.parseInt(nameChar.substring(nameChar.length()/2-1, nameChar.length()/2+2));
        else
            nameSeed = Integer.parseInt(nameChar.toString());


        StringBuilder suburbChar = new StringBuilder();
        for (char c :
                this.suburb.toCharArray()) {
            suburbChar.append((int) c);
        }
        int suburbSeed;
        if (suburbChar.length() > 2)
            suburbSeed = Integer.parseInt(suburbChar.substring(suburbChar.length()/2-1, suburbChar.length()/2+2));
        else
            suburbSeed = Integer.parseInt(suburbChar.toString());

        int dateSeed = this.dateOB.getDayOfYear();

        int seed = Integer.parseInt(nameSeed + "" + dateSeed + "" + suburbSeed);

        long seedSqrd = (long) seed * seed;

        String seedS = Long.toString(seedSqrd);
        System.out.println("seed: " + seedS);
        int hash = Integer.parseInt(seedS.substring(seedS.length()/2-3, seedS.length()/2+3));

        return hash;
    }
}
