package domain.entities;
import java.time.LocalDate;

public class WildFire {
    private int id;
    private LocalDate date;
    private int severity;
    private MonitoredArea area;


    public void report() {
        System.out.println("Wildfire reported in area: " + area.getName() + " on date: " + date + " with severity: " + severity);
    }
}
