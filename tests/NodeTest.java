import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    String[][] data = new String[][]{
            {"1", "Mr Potato Head", "Wanniassa", "2020-03-12"},
            {"34", "Kingo Dot Com", "Kingston", "1953-05-19"},
            {"98", "Shrektastic", "Mars", "2328-10-83"},
            {"126", "Yeeehaw", "AlphabetSoup", "2002-04-11"}
    };

    @BeforeEach
    void setUp() {
    }

    @Test
    void getId() {
        for (String[] object : data) {
            Node node = new Node(Integer.valueOf(object[0]), object[1], LocalDate.parse(object[3]), object[2]);
            assertEquals(Integer.valueOf(object[0]), node.getId());
        }
    }

    @Test
    void getName() {
        for (String[] object : data) {
            Node node = new Node(Integer.valueOf(object[0]), object[1], LocalDate.parse(object[3]), object[2]);
            assertEquals(object[1], node.getName());
        }
    }

    @Test
    void getSuburb() {
        for (String[] object : data) {
            Node node = new Node(Integer.valueOf(object[0]), object[1], LocalDate.parse(object[3]), object[2]);
            assertEquals(object[3], node.getSuburb());
        }
    }

    @Test
    void getDateOB() {
        for (String[] object : data) {
            Node node = new Node(Integer.valueOf(object[0]), object[1], LocalDate.parse(object[3]), object[2]);
            assertEquals(LocalDate.parse(object[2]), node.getDateOB());
        }
    }

    @Test
    void testToString() {
        // do we implement?
    }
}