package domain.entities;

/**
 * Representa um sensor meteorológico responsável por coletar dados ambientais como
 * temperatura, umidade e velocidade do vento em uma determinada localização.
 * Cada sensor possui um identificador único e uma descrição de localização.
 *
 * Métodos principais:
 * <ul>
 *   <li>{@link #collectData(int)}: Simula a coleta de dados meteorológicos, gerando valores aleatórios para temperatura, umidade e velocidade do vento, além de registrar a data atual.</li>
 * </ul>
 *
 * Exemplo de uso:
 * <pre>
 *     Sensor sensor = new Sensor(1, "Campus Principal");
 *     WheatherData dados = sensor.collectData(1001);
 * </pre>
 */
public class Sensor {
    private int id;
    private String locationDescription;

    public Sensor(int id, String locationDescription) {
        this.id = id;
        this.locationDescription = locationDescription;
    }

    /**
     * Simula a coleta de dados meteorológicos gerando valores aleatórios para temperatura,
     * umidade e velocidade do vento. Também gera um ID único aleatório para os dados coletados
     * e utiliza a data atual como timestamp.
     *
     * @return um novo objeto {@link WheatherData} contendo temperatura (0-40°C),
     *         umidade (0-100%), velocidade do vento (0-20 m/s) geradas aleatoriamente,
     *         um ID aleatório e a data atual.
     */
    public WheatherData collectData(int wheatherId) {
        double temperature = Math.random() * 40;
        double humidity = Math.random() * 100;
        double windSpeed = Math.random() * 20;
        return new WheatherData(wheatherId, temperature, humidity, windSpeed, java.time.LocalDate.now());
    }
}
