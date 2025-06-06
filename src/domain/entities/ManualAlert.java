package domain.entities;

import java.time.LocalDate;

/**
 * Representa um alerta manual criado por um usuário específico.
 * <p>
 * Esta classe estende {@link Alert} e adiciona informações sobre o criador do alerta
 * e uma descrição detalhada fornecida pelo usuário. É utilizada para registrar alertas
 * que não foram gerados automaticamente pelo sistema, mas sim manualmente por um operador.
 * </p>
 *
 * <p>
 * Os principais atributos incluem:
 * <ul>
 *   <li><b>createdBy</b>: Usuário responsável pela criação do alerta.</li>
 *   <li><b>description</b>: Descrição detalhada do motivo do alerta.</li>
 * </ul>
 * </p>
 *
 * <p>
 * O método {@link #throwAlert()} exibe todas as informações relevantes do alerta manual
 * de forma formatada na saída padrão.
 * </p>
 *
 * @see Alert
 */
public class ManualAlert extends Alert {
    private User createdBy;
    private String description;

    public ManualAlert(int id, double riskLevel, LocalDate issueDate, String description,
            User createdBy) {
        super(id, riskLevel, issueDate);
        this.createdBy = createdBy;
    }

    /**
     * Gera e exibe um alerta manual com informações detalhadas.
     * <p>
     * Este método imprime os seguintes detalhes do alerta na saída padrão:
     * <ul>
     *   <li>Cabeçalho do tipo de alerta</li>
     *   <li>Nome da área</li>
     *   <li>Data de emissão do alerta</li>
     *   <li>Nível de risco (como porcentagem)</li>
     *   <li>Nome e ID do criador</li>
     *   <li>Descrição do alerta</li>
     * </ul>
     * A saída é formatada para melhor leitura.
     * 
     * <p>
     * Este método sobrescreve o método {@code throwAlert()} da superclasse implementada.
     * </p>
     * @see Alert#throwAlert()
     */
    @Override
    public void throwAlert(MonitoredArea area) {
        super.throwAlert(area);
        System.out.println("Criado por: " + createdBy.getName() + " (ID: " + createdBy.getId() + ")");
        System.out.println("Descrição: " + description);
        System.out.println("=========================\n");
    }
}
