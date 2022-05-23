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
}