package domain.entities;

import java.util.List;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private List<MonitoredArea> monitoredAreas = new ArrayList<>();

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void monitor(MonitoredArea area) {
        if (monitoredAreas.contains(area)) {
            System.out.println("Área já está sendo monitorada: " + area.getName());
            return;
        }
        monitoredAreas.add(area);
        System.out.println("Monitorando área: " + area.getName());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }

    public List<MonitoredArea> getMonitoredAreas() {
        return monitoredAreas;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", monitoredAreas=" + monitoredAreas +
                '}';
    }
}
