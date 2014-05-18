package boersenspiel;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.junit.Test;
import org.junit.Test;

import exceptions.NotEnoughMoneyException;
import exceptions.NotEnoughSharesException;
import exceptions.PlayerNotFoundException;
import exceptions.ShareNotFoundException;

public class AccountManagerImplTest {
    StockPriceProvider provider = new RandomStockPriceProvider();
    AccountManagerImpl accMan = new AccountManagerImpl(provider);

    @Test
    public void testNewPlayer() throws PlayerNotFoundException {
        accMan.newPlayer("Bob");
        accMan.newPlayer("Lisa");
        assertEquals(2, accMan.getGambler().size());
        accMan.newPlayer("Horst");
        assertEquals(3, accMan.getGambler().size());
        assertTrue(accMan.getGambler().containsKey("Lisa"));
    }

    @Test
    public void testBuy() throws PlayerNotFoundException, NotEnoughMoneyException {
        accMan.newPlayer("Bob");
        accMan.buy("Bob", "bmw", 20);
        assertTrue(accMan.getGambler().containsKey("Bob"));
        
    }

    @Test
    public void testSell() throws PlayerNotFoundException, NotEnoughMoneyException, ShareNotFoundException, NotEnoughSharesException {
        accMan.newPlayer("Bob");
        accMan.buy("Bob", "bmw", 20);
        accMan.sell("Bob", "bmw", 10);
        assertEquals(10, accMan.getGambler().values().toArray()[0]);
    }

//    @Test
//    public void testSell2() throws PlayerNotFoundException, NotEnoughMoneyException, ShareNotFoundException, NotEnoughSharesException {
//        accMan.newPlayer("Bob");
//        accMan.buy("Bob", "bmw", 20);
//        accMan.sell("Bob", "bmw", 20);
//        assertEquals(0, accMan.getGambler()[0].getSAcc().getCollection().length);
//    }
    
    @Test
    public void testGetCashValueOf() throws PlayerNotFoundException, NotEnoughMoneyException {
        accMan.newPlayer("Bob");
        accMan.buy("Bob", "bmw", 20);
        assertEquals(2000000 - (provider.getShareValue("bmw")*20), accMan.getCashValueOf("Bob"));
    }

    @Test
    public void testGetSharesValueOf() throws PlayerNotFoundException, NotEnoughMoneyException {
        accMan.newPlayer("Bob");
        accMan.buy("Bob", "bmw", 20);
        assertEquals(provider.getShareValue("bmw")*20, accMan.getSharesValueOf("Bob"));
    }

    @Test
    public void testGetAllAssetsOf() throws PlayerNotFoundException, NotEnoughMoneyException {
        accMan.newPlayer("Bob");
        accMan.buy("Bob", "bmw", 20);
        assertEquals(provider.getShareValue("bmw")*20, accMan.getSharesValueOf("Bob"));
    }

}
