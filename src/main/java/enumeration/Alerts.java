package enumeration;

import lombok.Getter;

@Getter
public enum Alerts {
    DOOR_OPEN(
        "********** ALERT **********\n" +
                "*   TRUCK DOOR IS OPEN    *\n" +
                "***************************"),
    TEMPERATURE_OUT_RANGE(
        "********** ALERT **********\n" +
                "* TEMPERATURE OF OUT RANGE*\n" +
                "***************************"),

    NEW_JOURNEY_STARTED (
        "Truck started new journey!"
    ),

    FINAL_DESTINATION_ARRIVED(
        "Truck just arrived at final destination!"
    ),

    TRUCK_LOADED(
        "Truck loaded and ready to ride!"
    );

    private String message;
    public void alert() {System.out.println(this.getMessage());}
    Alerts(String message) {
        this.message = message;
    }
}
