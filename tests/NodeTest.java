import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static final Logger logger =
            Logger.getLogger(NodeTest.class.getName());


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

    /*
    TODO: implement test
    TODO: test for seed < middle section being pulled out
    TODO: test for
     */
    @Test
    void testHashCode() {

    }

    // TODO: implement tests
    @Test
    void testHashCodeCollisions() {
        Set<Integer> hashes = new HashSet<>();
        Set<Node> nodes = new HashSet<>();

        try (FileInputStream inputStream = new FileInputStream("resources/test-data-raw.csv");
             Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                Node node = new Node(Integer.valueOf(data[0]), data[1], LocalDate.parse(data[3]), data[2]);
                hashes.add(node.hashCode());
                nodes.add(node);
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                logger.severe("IOException: Error reading file. Error: " + sc.ioException());
            }
        } catch (IOException e) {
            logger.severe("Error reading files. Error: " + e.getMessage());
        }
        // assert hashCode produces at least 1 in 1,000 collision
        // rate in random sampled data

        int num = nodes.size();

        logger.info("Assert collision rate < 0.001 (1 in 1000)");
        logger.info("Collision rate: " + (float) hashes.size()/num);
        logger.info("1 - Collision rate: " + (1 - (float) hashes.size()/num));
        logger.info(hashes.size() + " unique hashes out of " + num + " objects");
        assertTrue(1 - ((float) hashes.size()/num) < 0.001);

    }
}