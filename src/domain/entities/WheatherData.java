package domain.entities;
import java.time.LocalDate;

/**
 * Representa os dados meteorológicos coletados para uma área monitorada em uma data específica.
 * Armazena informações como temperatura, umidade, velocidade do vento e a área associada.
 *
 * <p>Esta classe é usada para encapsular um único registro de medições meteorológicas.</p>
 *
 */
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
