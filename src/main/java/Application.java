import java.util.concurrent.ExecutionException;

public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DbeerService.startRide(DbeerService.initializeValues());
    }
}
