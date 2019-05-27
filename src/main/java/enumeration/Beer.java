package enumeration;

import lombok.Getter;

@Getter
public enum Beer {
    PILSENER("Pilsner", 4.0, 6.0),
    IPA("IPA", 5.0, 6.0),
    LAGER("Lager", 4.0, 7.0),
    STOUT("Stout", -8.0, -6.0),
    WHEAT("Wheat beer", -5.0, -3.0),
    PALE_ALE("Pale Ale", 4.0, 6.0);

    String nameType;
    Double minTemperature;
    Double maxTemperature;

    Beer(String nameType, Double minTemperature, Double maxTemperature) {
        this.nameType = nameType;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }
}
