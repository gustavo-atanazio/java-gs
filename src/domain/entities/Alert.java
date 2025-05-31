package domain.entities;
import java.time.LocalDate;

public class Alert {
    private int id;
    private double riskLevel;
    private LocalDate issueDate;
    private MonitoredArea area;

    public Alert(int id, double riskLevel, LocalDate issueDate, MonitoredArea area) {
        this.id = id;
        this.riskLevel = riskLevel;
        this.issueDate = issueDate;
        this.area = area;
    }


    public void generateAlert() {
        System.out.println("Alerta gerado na área: " + area.getName() + " com risco de nível: " + riskLevel + " na data: " + issueDate);
    }
}
