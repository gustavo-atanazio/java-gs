package domain.entities;
import java.time.LocalDate;

public class WheatherData {

    private int id;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private LocalDate date;
    private MonitoredArea area;

    public WheatherData(int id, double temperature, double humidity, double windSpeed, LocalDate date, MonitoredArea area) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.date = date;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public LocalDate getDate() {
        return date;
    }

    public MonitoredArea getArea() {
        return area;
    }
}
