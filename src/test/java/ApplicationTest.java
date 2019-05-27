import domain.BeerContainer;
import domain.Truck;
import enumeration.Beer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class ApplicationTest {

    @Test
    public void atLeastOneBeerWasUnloaded() throws InterruptedException {
        Truck truck = DbeerService.initializeValues();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> truckRiding = executor.submit(truck.drive());
        while (!truckRiding.isDone()) {
            assertTrue(truck.getContainers().stream()
                .map(BeerContainer::isTemperatureOutRange)
                .count() >= 1);
        }
        executor.shutdown();
    }

    @Test
    @Parameters({
        "PILSENER",
        "IPA",
        "LAGER",
        "STOUT",
        "WHEAT",
        "PALE_ALE"
    })
    public void testTemperatureOutOfRange(Beer beer) {
        BeerContainer beerContainer = BeerContainer.buildBeerContainer(beer);
        beerContainer.unloadBeer();
        assertTrue(beerContainer.isTemperatureOutRange());
        assertEquals(beerContainer.getCurrentTemperature(), Double.valueOf(beer.getMaxTemperature() + 1));
    }

    @Test
    public void testInitializer() {
        Truck truck = DbeerService.initializeValues();
        assertFalse(truck.getContainers().isEmpty());
        assertEquals(truck.getContainers().size(), Beer.values().length);
    }
}
