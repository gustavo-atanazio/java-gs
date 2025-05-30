package domain.entities;
public class User {
    private int id;
    private String name;
    private String email;

    public void monitor(MonitoredArea area) {
        System.out.println("Monitoring area: " + area.getName());
    }

    public String getName() {
        return name;
    }
}
