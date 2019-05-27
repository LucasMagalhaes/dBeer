package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static enumeration.Alerts.FINAL_DESTINATION_ARRIVED;
import static enumeration.Alerts.NEW_JOURNEY_STARTED;

@Builder
public class Truck {
    @Getter
    private List<BeerContainer> containers;
    @Getter
    private boolean isOnRide;
    @Setter
    private String driverName;

    public Callable<String> drive() {
        return () -> {
            boolean shouldRide = true;
            while (shouldRide) {
                NEW_JOURNEY_STARTED.alert();
                isOnRide = true;
                TimeUnit.SECONDS.sleep(3);
                isOnRide = false;

                this.containers.stream()
                    .skip(new Random().nextInt((this.containers).size() - 1))
                    .findFirst()
                    .get()
                    .unloadBeer();
                shouldRide = !shouldArrive();
            }

            isOnRide = false;
            return FINAL_DESTINATION_ARRIVED.getMessage();
        };
    }

    private boolean shouldArrive() {
        return (new Random().nextDouble() < 0.2);
    }
}
