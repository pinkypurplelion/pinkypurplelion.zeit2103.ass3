import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SocialNetworkTest {
    static SocialNetwork socialNetwork;
    @BeforeAll
    static void setUp() {
        socialNetwork = new SocialNetwork();
    }

    @Test
    void processFile() {
        System.out.println("Testing correct number of nodes present");
        assertEquals(1002, socialNetwork.sn.nodeList.size());
        System.out.println("Tested Passed. \nTesting correct information stored for nodes");
        assertEquals("Minna Whittaker", socialNetwork.sn.nodeList.get(1).getName());
        assertNotEquals("Minna Whittaker", socialNetwork.sn.nodeList.get(3).getName());
        assertEquals("Gungahlin", socialNetwork.sn.nodeList.get(9001).getSuburb());
        System.out.println("Tested Passed. \nTesting friends correctly added");
        assertEquals(19 ,socialNetwork.sn.nodeList.get(990).adj.size());
        System.out.println("Tested Passed. \n");
    }

    @Test
    void suggestFriends() {
        assertEquals(6, socialNetwork.suggestFriends(socialNetwork.sn.nodeList.get(9001)).size());
    }

    @Test
    void remindBDEvents() {
        fail();
    }

    @Test
    void getMutualFriends() {
        Graph sn = socialNetwork.sn;
        assertEquals(2, socialNetwork.getMutualFriends(sn.nodeList.get(5001), sn.nodeList.get(9001)).size());
//        fail();
    }
}