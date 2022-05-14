
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
     */
    @Override
    public int hashCode() {
        StringBuilder nameSeed = new StringBuilder();
        for (char c :
                this.name.substring(0,3).toCharArray()) {
            nameSeed.append(c);
        }
        System.out.println(nameSeed);
        return Objects.hash(id, name, dateOB, suburb);
    }
}
