import java.util.Objects;

/**
 *
 */
public class Edge {
    protected Node friend;
    
    public Edge(Node friend) {
    	this.friend = friend;
    }


    @Override
    public String toString() {
        return "Edge (Friend){" +
                "id=" + friend.getId() +
                ", name='" + friend.getName() + '\'' +
                ", dateOB=" + friend.getDateOB() +
                ", suburb='" + friend.getSuburb() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(friend, edge.friend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend);
    }
}