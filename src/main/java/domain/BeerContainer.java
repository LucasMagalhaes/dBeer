package domain;

import enumeration.Beer;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Data
@Builder
public class BeerContainer {
    private Beer beer;
    private Double currentTemperature;
    private boolean isDoorOpen;

    public static BeerContainer buildBeerContainer(Beer beer) {
        return BeerContainer.builder()
            .beer(beer)
            .currentTemperature(beer.getMaxTemperature())
            .build();
    }

    public void unloadBeer() {
        isDoorOpen = true;
        currentTemperature = currentTemperature + 1;
        System.out.println("Beer unloaded! " + this.beer.getNameType());
        isDoorOpen = new Random().nextBoolean();
    }

    public boolean isTemperatureOutRange() {
        return currentTemperature > beer.getMaxTemperature()
            || currentTemperature < beer.getMinTemperature();
    }
}
