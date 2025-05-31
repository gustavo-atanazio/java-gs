package domain.entities;

import java.time.LocalDate;

public class AutomaticAlert extends Alert {

    private WheatherData generatedFrom;

    public AutomaticAlert(int id, double riskLevel, LocalDate issueDate, MonitoredArea area, WheatherData generatedFrom) {
        super(id, riskLevel, issueDate, area);
        this.generatedFrom = generatedFrom;
    }
    

    @Override
    public void generateAlert() {
        super.generateAlert();
        System.out.println("Alerta automático gerado dos dados de clima com ID: " + generatedFrom.getId());
    }

}
