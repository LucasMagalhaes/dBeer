import domain.BeerContainer;
import domain.Truck;
import enumeration.Alerts;
import enumeration.Beer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static enumeration.Alerts.DOOR_OPEN;
import static enumeration.Alerts.TRUCK_LOADED;

class DbeerService {

    static Truck initializeValues() {
        List<BeerContainer> beerContainers = Arrays.stream(Beer.values())
            .map(BeerContainer::buildBeerContainer)
            .collect(Collectors.toList());

        Truck shaneTruck = Truck.builder()
            .driverName("Shane")
            .containers(beerContainers)
            .build();
        TRUCK_LOADED.alert();
        return shaneTruck;
    }

    static void startRide(Truck defaultTruck) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> truckRiding = executor.submit(defaultTruck.drive());

        while (!truckRiding.isDone()) {
            monitorTruck(defaultTruck);
        }

        System.out.println(truckRiding.get());
        executor.shutdown();
    }

    private static void monitorTruck(Truck defaultTruck) {
        defaultTruck.getContainers().forEach(container -> {
            if (container.isDoorOpen() && defaultTruck.isOnRide()) {
                DOOR_OPEN.alert();
                fix(defaultTruck);
            }

            if (container.isTemperatureOutRange()) {
                String alertMessage = String.format("%s \n for container %s",
                    Alerts.TEMPERATURE_OUT_RANGE.getMessage(),
                    container.getBeer().getNameType());
                System.out.println(alertMessage);
                fix(defaultTruck);
            }
        });

    }

    private static void fix(Truck defaultTruck) {
        defaultTruck.getContainers().forEach(beerContainer -> {
            beerContainer.setDoorOpen(false);
            beerContainer.setCurrentTemperature(beerContainer.getBeer().getMinTemperature());
        });
    }
}

