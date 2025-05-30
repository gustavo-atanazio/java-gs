package domain.entities;
public class MonitoredArea {
    private int id;
    private String name;
    private String location;
    private String vegetationType;
    private User user;

    public double calculateRiskLevel(WheatherData data) {
        return Math.random() * 100;
    }

    public String getName() {
        return name;
    }
}