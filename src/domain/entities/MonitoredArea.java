package domain.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MonitoredArea {
    private int id;
    private String name;
    private String location;
    private String vegetationType;
    private List<WheatherData> weatherDataList = new ArrayList<>();

    public MonitoredArea(int id, String name, String location, String vegetationType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vegetationType = vegetationType;
    }

    public double calculateRiskLevel() {
        WheatherData wd = this.getLatestWeatherData();
        if (wd == null) {
            System.out.println("Nenhum dado climático encontrado para a área selecionada!");
            return -1;
        }

        return calculateRiskLevel(wd.getTemperature(), wd.getHumidity(), wd.getWindSpeed());
    }

    public double calculateRiskLevel(WheatherData data) {
        if (data == null) {
            System.out.println("Dados climáticos inválidos.");
            return -1;
        }
        return calculateRiskLevel(data.getTemperature(), data.getHumidity(), data.getWindSpeed());
    }

    public double calculateRiskLevel(double temperature, double humidity, double windSpeed) {
        if (temperature < 0 || humidity < 0 || windSpeed < 0) {
            System.out.println("Valores inválidos para temperatura, umidade ou velocidade do vento.");
            return -1;
        }

        double riskLevel = (temperature * 0.4 + windSpeed * 0.3) - (humidity * 0.5);
        riskLevel = Math.max(0, Math.min(100, riskLevel));
        return riskLevel;
    }

    public WheatherData getLatestWeatherData() {
        return this.weatherDataList.stream()
                .max(Comparator.comparing(WheatherData::getDate))
                .orElse(null);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WheatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void addWeatherData(WheatherData data) {
        weatherDataList.add(data);
    }

    public String getLocation() {
        return location;
    }

    public String getVegetationType() {
        return vegetationType;
    }

    @Override
    public String toString() {
        return "MonitoredArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", vegetationType='" + vegetationType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MonitoredArea))
            return false;
        MonitoredArea that = (MonitoredArea) o;
        return id == that.id &&
                name.equals(that.name) &&
                location.equals(that.location) &&
                vegetationType.equals(that.vegetationType);
    }

}