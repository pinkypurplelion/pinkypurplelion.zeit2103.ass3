import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    String[][] data = new String[][]{
            {"1", "Mr Potato Head", "Wanniassa", "2020-03-12"},
            {"34", "Kingo Dot Com", "Kingston", "1953-05-19"},
            {"98", "Shrektastic", "Mars", "2328-10-29"},
            {"126", "M", "AlphabetSoup", "2006-02-11"},
            {"126", "Ki", "M", "2012-10-19"},
            {"126", "Yodadoooledo", "Do", "1009-11-11"},
            {"126", "Heyoooo", "Dadada Kinga", "2222-02-22"},
            {"126", "Heyoooo", "Dadada Kinga", "2001-01-01"},
            {"126", "Heyoooo", "Dadada Kinga", "2003-12-30"},
            {"126", "Heyoooo", "Dadada Kinga", "2003-12-30"},
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
        // TODO: do we implement?
    }

    // TODO: implement
    @Test
    void testEquals() {
    }

    // TODO: implement tests
    @Test
    void testHashCode() {
        for (String[] object : data) {
            Node node = new Node(Integer.valueOf(object[0]), object[1], LocalDate.parse(object[3]), object[2]);
            System.out.println(node.hashCode());
        }
    }
}