package boersenspiel;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import exceptions.ShareNotFoundException;

public class ShareDepositAccountTest {
    Player bob = new Player("bob");
    Share bmw = new Share(5000, "bmw");
    Share vw = new Share(6000, "vw");
    ShareItem bmwItem = new ShareItem(bmw, 2);
    ShareItem vwItem = new ShareItem(vw, 5);
    Share kuka = new Share(6000, "kuka");
    ShareItem kukaItem = new ShareItem(kuka, 2);

    @Before
    public void setUp() {

        bob.getSAcc().add(bmwItem);
        bob.getSAcc().add(vwItem);
    }

    @Test
    public void testGetValue() {
        assertEquals(40000, bob.getSAcc().getValue());
    }

    @Test
    public void testGetName() {
        ShareDepositAccount sAcc = new ShareDepositAccount("Bob");
        assertEquals("Bob", sAcc.getName());
    }

    @Test
    public void testAdd() {
        bob.getSAcc().add(kukaItem);
        assertTrue(bob.getSAcc().getItems().containsKey(kukaItem.getName()));

        bob.getSAcc().add(kukaItem);
        assertEquals(bob.getSAcc().getItems().size(), 3);
    }

    @Test
    public void testRemove() {
        try {
            bob.getSAcc().remove(vwItem);
        } catch (ShareNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assertFalse(bob.getSAcc().getItems().containsKey(vwItem.getName()));
        assertTrue(bob.getSAcc().getItems().containsKey(bmwItem.getName()));
    }

}
