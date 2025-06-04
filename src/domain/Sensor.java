package domain;

import domain.entities.MonitoredArea;
import domain.entities.WheatherData;

public class Sensor {
    private int id;
    private String locationDescription;
    private MonitoredArea monitoredArea;

    public Sensor(int id, String locationDescription, MonitoredArea monitoredArea) {
        this.id = id;
        this.locationDescription = locationDescription;
        this.monitoredArea = monitoredArea;
    }

    public WheatherData collectData(double temperature, double humidity, double windSpeed) {
        // Aqui você pode implementar a lógica para coletar dados meteorológicos
        // e criar um objeto WheatherData com esses dados.
        return new WheatherData(id, temperature, humidity, windSpeed, java.time.LocalDate.now(), monitoredArea);
    }
}
