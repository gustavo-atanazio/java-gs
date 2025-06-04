package domain.entities;

import java.time.LocalDate;

/**
 * Representa um alerta automático gerado com base em dados climáticos específicos.
 * 
 * <p>
 * Esta classe estende {@link Alert} e adiciona a referência aos dados climáticos
 * ({@link WheatherData}) que originaram o alerta. O método {@code generateAlert()}
 * exibe no console informações detalhadas sobre a área monitorada, data de emissão,
 * nível de risco e os dados climáticos utilizados para a geração do alerta.
 * </p>
 * 
 * @see Alert
 * @see WheatherData
 */
public class AutomaticAlert extends Alert {

    private WheatherData generatedFrom;

    public AutomaticAlert(int id, double riskLevel, LocalDate issueDate,
            WheatherData generatedFrom) {
        super(id, riskLevel, issueDate);
        this.generatedFrom = generatedFrom;
    }

    /**
     * Exibe um alerta automático no console com informações detalhadas sobre a área,
     * data de emissão, nível de risco e dados climáticos utilizados para a geração do alerta.
     * Os dados exibidos incluem temperatura, umidade, velocidade do vento e o ID dos dados climáticos.
     * 
     * <p>
     * Este método sobrescreve o método {@code throwAlert()} da superclasse Alert.
     * </p>
     * 
     * @see Alert#throwAlert()
     */
    @Override
    public void throwAlert(MonitoredArea area) {
        super.throwAlert(area);
        System.out.println("Dados Climáticos Utilizados:");
        System.out.printf("  Temperatura: %.2f°C\n", generatedFrom.getTemperature());
        System.out.printf("  Umidade: %.2f%%\n", generatedFrom.getHumidity());
        System.out.printf("  Vento: %.2f km/h\n", generatedFrom.getWindSpeed());
        System.out.println("ID dos Dados Climáticos: " + generatedFrom.getId());
        System.out.println("=============================\n");
    }

}
