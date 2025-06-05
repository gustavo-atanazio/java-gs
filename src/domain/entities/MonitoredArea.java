package domain.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa uma área monitorada para riscos ambientais, como incêndios
 * florestais.
 * <p>
 * Cada instância de {@code MonitoredArea} contém informações sobre a área,
 * incluindo
 * identificador, nome, localização, tipo de vegetação, dados climáticos
 * históricos e
 * registros de incêndios ocorridos.
 * <p>
 * Fornece métodos para calcular o nível de risco de incêndio com base nos dados
 * climáticos
 * mais recentes ou fornecidos, além de gerenciar os dados associados à área.
 *
 * <ul>
 * <li><b>id</b>: Identificador único da área monitorada.</li>
 * <li><b>name</b>: Nome da área monitorada.</li>
 * <li><b>location</b>: Localização geográfica da área.</li>
 * <li><b>vegetationType</b>: Tipo de vegetação predominante na área.</li>
 * <li><b>weatherDatas</b>: Lista de dados climáticos históricos associados à
 * área.</li>
 * <li><b>wildFires</b>: Lista de incêndios registrados na área.</li>
 * <li><b>alerts</b>: Lista de alertas gerados para a área monitorada.</li>
 * <li><b>sensor</b>: Sensor utilizado para coletar dados climáticos.</li>
 * </ul>
 *
 * <h2>Exemplo de uso:</h2>
 * 
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
    private List<WheatherData> weatherDatas = new ArrayList<>();
    private List<WildFire> wildFires = new ArrayList<>();
    private List<Alert> alerts = new ArrayList<>();
    private Sensor sensor;

    public MonitoredArea(int id, String name, String location, String vegetationType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vegetationType = vegetationType;
        this.sensor = new Sensor(id, "Sensor na área monitorada: " + name);
    }

    /**
     * Calcula o nível de risco da área monitorada com base nos dados climáticos
     * mais recentes.
     * 
     * @return o nível de risco calculado (0 a 100), ou -1 se não houver dados
     *         climáticos.
     */
    public double calculateRiskLevel() {
        WheatherData wd = this.getLatestWeatherData();
        if (wd == null) {
            System.out.println("Nenhum dado climático encontrado para a área selecionada!");
            return -1;
        }
        System.out.println(wd.getDate() + " - Dados climáticos mais recentes coletados: "
                + wd.getTemperature() + "°C, " + wd.getHumidity() + "%, " + wd.getWindSpeed() + " km/h");
        return calculateRiskLevel(wd.getTemperature(), wd.getHumidity(), wd.getWindSpeed());
    }

    /**
     * Calcula o nível de risco da área monitorada com base em um objeto
     * WheatherData fornecido.
     * 
     * @param data os dados climáticos a serem utilizados no cálculo.
     * @return o nível de risco calculado (0 a 100), ou -1 se os dados forem
     *         inválidos.
     */
    public double calculateRiskLevel(WheatherData data) {
        if (data == null) {
            System.out.println("Dados climáticos inválidos.");
            return -1;
        }
        return calculateRiskLevel(data.getTemperature(), data.getHumidity(), data.getWindSpeed());
    }

    /**
     * Calcula o nível de risco da área monitorada com base nos valores de
     * temperatura, umidade e velocidade do vento.
     * 
     * @param temperature temperatura em graus Celsius.
     * @param humidity    umidade relativa do ar em porcentagem.
     * @param windSpeed   velocidade do vento em km/h.
     * @return o nível de risco calculado (0 a 100), ou -1 se algum valor for
     *         inválido.
     */
    public double calculateRiskLevel(double temperature, double humidity, double windSpeed) {
        if (temperature < 0 || humidity < 0 || windSpeed < 0) {
            System.out.println("Valores inválidos para temperatura, umidade ou velocidade do vento.");
            return -1;
        }

        double riskLevel = (temperature * 2) + (windSpeed * 2) - (humidity * 1.5);
        riskLevel = Math.max(0, Math.min(100, riskLevel));
        return riskLevel;
    }

    /**
     * Recupera a entrada mais recente de {@link WheatherData} da lista
     * {@code weatherDataList}.
     * <p>
     * Este método busca na lista de dados climáticos associados a esta área
     * monitorada
     * e retorna a entrada com a data mais recente. Se a lista estiver vazia,
     * retorna {@code null}.
     *
     * @return o objeto {@link WheatherData} mais recente, ou {@code null} se não
     *         houver dados disponíveis
     */
    public WheatherData getLatestWeatherData() {
        return this.weatherDatas.stream()
                .max(Comparator.comparing(WheatherData::getId))
                .orElse(null);
    }

    /**
     * Gera um alerta automático com base no nível de risco e nos dados climáticos fornecidos.
     * <p>
     * Se os dados climáticos forem {@code null}, o método exibe uma mensagem e retorna {@code null}.
     * Caso contrário, cria um novo {@link AutomaticAlert}, adiciona à lista de alertas e o retorna.
     * </p>
     *
     * @param riskLevel o nível de risco calculado para o alerta
     * @param data os dados climáticos associados ao alerta; não pode ser {@code null}
     * @return o {@link AutomaticAlert} gerado, ou {@code null} se os dados climáticos forem inválidos
     */
    public AutomaticAlert generateAlert(double riskLevel, WheatherData data) {
        if (data == null) {
            System.out.println("Dados climáticos inválidos para gerar alerta.");
            return null;
        }
        AutomaticAlert alert = new AutomaticAlert(alerts.size() + 1, riskLevel, data.getDate(), data);
        this.addAlert(alert);
        return alert;
    }

    /**
     * Gera um alerta manual para esta área monitorada com o nível de risco, usuário e descrição especificados.
     * <p>
     * Se o usuário fornecido for {@code null}, o método imprime uma mensagem de erro e retorna {@code null}.
     * Caso contrário, cria um novo {@link ManualAlert}, adiciona à lista de alertas e retorna o alerta criado.
     * </p>
     *
     * @param riskLevel   o nível de risco associado ao alerta
     * @param user        o usuário responsável por gerar o alerta; não pode ser {@code null}
     * @param description uma descrição do alerta
     * @return o {@link ManualAlert} gerado, ou {@code null} se o usuário for inválido
     */
    public ManualAlert generateAlert(double riskLevel, User user, String description) {
        if (user == null) {
            System.out.println("Usuário inválido para gerar alerta.");
            return null;
        }
        ManualAlert alert = new ManualAlert(alerts.size() + 1, riskLevel, java.time.LocalDate.now(), description, user);
        this.addAlert(alert);
        return alert;
    }

    /**
     * Coleta dados climáticos do sensor um número especificado de vezes.
     * Para cada coleta bem-sucedida, os dados são adicionados tanto à lista local
     * quanto ao registro de dados climáticos da área monitorada.
     *
     * @param quantidade o número de vezes que os dados serão coletados do sensor
     * @return uma lista de objetos {@link WheatherData} coletados nesta operação
     */
    public List<WheatherData> useSensor(int quantity){
        List<WheatherData> collectedData = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            int weatherId = weatherDatas.size() + 1;
            WheatherData data = sensor.collectData(weatherId);
            if (data != null) {
                collectedData.add(data);
                this.addWeatherData(data);
            }
        }
        System.out.println(quantity + " dados climáticos inseridos com sucesso!");
        return collectedData;
    }

    private void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public void addWildFire(WildFire wildFire) {
        this.wildFires.add(wildFire);
        System.out.println("Wildfire reportado na área " + this.getName() + " na data " + wildFire.getDate()
                + " com severidade " + wildFire.getSeverity());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<WheatherData> getWeatherDataList() {
        return weatherDatas;
    }

    public void addWeatherData(WheatherData data) {
        weatherDatas.add(data);
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