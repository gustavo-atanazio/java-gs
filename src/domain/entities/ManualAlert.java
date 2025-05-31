package domain.entities;

import java.time.LocalDate;

public class ManualAlert extends Alert {
    private User createdBy;


    public ManualAlert(int id, double riskLevel, LocalDate issueDate, MonitoredArea area, User createdBy) {
        super(id, riskLevel, issueDate, area);
        this.createdBy = createdBy;
    }


    @Override
    public void generateAlert() {
        super.generateAlert();
        System.out.println("Alerta manual criado por: " + createdBy.getName());
    }
}
