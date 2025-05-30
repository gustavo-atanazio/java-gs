package domain.entities;
import java.time.LocalDate;

public class Alert {
    private int id;
    private double riskLevel;
    private LocalDate issueDate;
    private MonitoredArea area;


    public void generateAlert() {
        System.out.println("Alert generated for area: " + area.getName() + " with risk level: " + riskLevel);
    }
}
