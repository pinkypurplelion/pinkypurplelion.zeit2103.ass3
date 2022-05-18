/**
 *
 */
public class Edge {
	protected Node source;
    protected Node friend;
    
    public Edge(Node friend, Node otherFriend) {
    	this.source = friend;
        this.friend = otherFriend;
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