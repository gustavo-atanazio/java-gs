package domain.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa uma área monitorada para riscos ambientais, como incêndios florestais.
 * <p>
 * Cada instância de {@code MonitoredArea} contém informações sobre a área, incluindo
 * identificador, nome, localização, tipo de vegetação, dados climáticos históricos e
 * registros de incêndios ocorridos.
 * <p>
 * Fornece métodos para calcular o nível de risco de incêndio com base nos dados climáticos
 * mais recentes ou fornecidos, além de gerenciar os dados associados à área.
 *
 * <ul>
 *   <li><b>id</b>: Identificador único da área monitorada.</li>
 *   <li><b>name</b>: Nome da área monitorada.</li>
 *   <li><b>location</b>: Localização geográfica da área.</li>
 *   <li><b>vegetationType</b>: Tipo de vegetação predominante na área.</li>
 *   <li><b>weatherDataList</b>: Lista de dados climáticos históricos associados à área.</li>
 *   <li><b>wildFires</b>: Lista de incêndios registrados na área.</li>
 * </ul>
 *
 * <h2>Exemplo de uso:</h2>
 * <pre>
 *     MonitoredArea area = new MonitoredArea(1, "Parque Nacional", "Latitude X, Longitude Y", "Cerrado");
 *     area.addWeatherData(new WheatherData(...));
 *     double risk = area.calculateRiskLevel();
 * </pre>
 *
 */
public class MonitoredArea {
    private int id;
    private String name;
    private String location;
    private String vegetationType;
    private List<WheatherData> weatherDataList = new ArrayList<>();
    private List<WildFire> wildFires = new ArrayList<>();

    public MonitoredArea(int id, String name, String location, String vegetationType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vegetationType = vegetationType;
    }


    /**
     * Calcula o nível de risco da área monitorada com base nos dados climáticos mais recentes.
     * 
     * @return o nível de risco calculado (0 a 100), ou -1 se não houver dados climáticos.
     */
    public double calculateRiskLevel() {
        WheatherData wd = this.getLatestWeatherData();
        if (wd == null) {
            System.out.println("Nenhum dado climático encontrado para a área selecionada!");
            return -1;
        }

        return calculateRiskLevel(wd.getTemperature(), wd.getHumidity(), wd.getWindSpeed());
    }

    /**
     * Calcula o nível de risco da área monitorada com base em um objeto WheatherData fornecido.
     * 
     * @param data os dados climáticos a serem utilizados no cálculo.
     * @return o nível de risco calculado (0 a 100), ou -1 se os dados forem inválidos.
     */
    public double calculateRiskLevel(WheatherData data) {
        if (data == null) {
            System.out.println("Dados climáticos inválidos.");
            return -1;
        }
        return calculateRiskLevel(data.getTemperature(), data.getHumidity(), data.getWindSpeed());
    }

        /**
     * Calcula o nível de risco da área monitorada com base nos valores de temperatura, umidade e velocidade do vento.
     * 
     * @param temperature temperatura em graus Celsius.
     * @param humidity umidade relativa do ar em porcentagem.
     * @param windSpeed velocidade do vento em km/h.
     * @return o nível de risco calculado (0 a 100), ou -1 se algum valor for inválido.
     */
    public double calculateRiskLevel(double temperature, double humidity, double windSpeed) {
        if (temperature < 0 || humidity < 0 || windSpeed < 0) {
            System.out.println("Valores inválidos para temperatura, umidade ou velocidade do vento.");
            return -1;
        }

        double riskLevel = (temperature * 0.4 + windSpeed * 0.3) - (humidity * 0.5);
        riskLevel = Math.max(0, Math.min(100, riskLevel));
        return riskLevel;
    }

    /**
     * Recupera a entrada mais recente de {@link WheatherData} da lista {@code weatherDataList}.
     * <p>
     * Este método busca na lista de dados climáticos associados a esta área monitorada
     * e retorna a entrada com a data mais recente. Se a lista estiver vazia, retorna {@code null}.
     *
     * @return o objeto {@link WheatherData} mais recente, ou {@code null} se não houver dados disponíveis
     */
    public WheatherData getLatestWeatherData() {
        return this.weatherDataList.stream()
                .max(Comparator.comparing(WheatherData::getDate))
                .orElse(null);
    }

    public void addWildFire(WildFire wildFire) {
        this.wildFires.add(wildFire);
        System.out.println("Wildfire reportado na área " + this.getName() + " na data " + wildFire.getDate() + " com severidade " + wildFire.getSeverity());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WheatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public void addWeatherData(WheatherData data) {
        weatherDataList.add(data);
    }

    public List<WildFire> getWildFires() {
        return wildFires;
    }

    public String getLocation() {
        return location;
    }

    public String getVegetationType() {
        return vegetationType;
    }

}