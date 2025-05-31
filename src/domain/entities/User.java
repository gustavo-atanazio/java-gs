package domain.entities;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa um usuário do sistema, contendo informações pessoais e as áreas monitoradas por ele.
 * Um usuário possui um identificador único, nome, e-mail e pode monitorar múltiplas áreas.
 *
 * <p>
 * Métodos principais permitem adicionar novas áreas monitoradas, acessar informações do usuário
 * e obter a lista de áreas atualmente monitoradas.
 * </p>
 *
 */
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

    /**
     * Adiciona a {@code MonitoredArea} especificada à lista de áreas monitoradas se ainda não estiver sendo monitorada.
     * Se a área já estiver sendo monitorada, uma mensagem será exibida indicando isso.
     * Caso contrário, a área é adicionada e uma mensagem de confirmação é exibida.
     *
     * @param area a {@code MonitoredArea} a ser monitorada
     */
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
