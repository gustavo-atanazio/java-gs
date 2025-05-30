package domain.entities;
import java.time.LocalDate;

public class WheatherData {

    private int id;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private LocalDate date;
    private MonitoredArea area;

    public int getId() {
        return id;
    }
}
