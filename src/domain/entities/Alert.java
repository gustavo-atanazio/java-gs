package domain.entities;

import java.time.LocalDate;

/**
 * Representa um alerta emitido para uma determinada área monitorada,
 * contendo informações sobre o nível de risco, data de emissão e a área associada.
 * 
 * <p>
 * Um alerta é criado com um identificador único, um nível de risco (de 0 a 100),
 * a data de emissão e a referência à área monitorada correspondente.
 * </p>
 * 
 * <p>
 * Fornece métodos para acessar os dados do alerta e para gerar uma mensagem
 * de alerta formatada na saída padrão.
 * </p>
 * 
 */
public class Alert {
    private int id;
    private double riskLevel;
    private LocalDate issueDate;

    public Alert(int id, double riskLevel, LocalDate issueDate) {
        this.id = id;
        if (riskLevel < 0 || riskLevel > 100) {
            throw new IllegalArgumentException("Nível de risco deve estar entre 0 e 100.");
        }
        this.riskLevel = riskLevel;
        this.issueDate = issueDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public double getRiskLevel() {
        return riskLevel;
    }

    /**
     * Gera e imprime uma mensagem de alerta na saída padrão.
     * A mensagem inclui o nome da área, nível de risco e data de emissão.
     */
    public void throwAlert(MonitoredArea area) {
        if (area == null) {
            throw new IllegalArgumentException("Área monitorada não pode ser nula.");
        }
        System.out.println("\n===== ALERTA =====");
        System.out.println("Área: " + area.getName());
        System.out.println("Data do Alerta: " + issueDate);
        System.out.printf("Nível de Risco: %.2f%%\n", riskLevel);
        System.out.println("===================");
    }
}
