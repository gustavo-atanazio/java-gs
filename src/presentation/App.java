package presentation;

import domain.entities.*;
import java.util.*;
import java.time.LocalDate;

public class App {
  private static List<MonitoredArea> areas = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static HashMap<Integer, User> users = new HashMap<>();
  private static User user = cadastrarUsuario();

  public static void main(String[] args) {
    int option;
    do {
      System.out.println("\n--- Ignira ( " + user.getName() + " ) ---");
      System.out.println("1. Alterar usuário");
      System.out.println("2. Cadastrar área monitorada");
      System.out.println("3. Inserir dados climáticos (WeatherData)");
      System.out.println("4. Monitorar área (verificar risco e gerar alerta automático)");
      System.out.println("5. Visualizar dados climáticos de uma área");
      System.out.println("6. Gerar alerta manual");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");
      option = Integer.parseInt(scanner.nextLine());

      switch (option) {
        case 1:
          user = alterarUsuario();
          break;
        case 2:
          cadastrarArea();
          break;
        case 3:
          inserirWeatherData();
          break;
        case 4:
          monitorarArea();
          break;
        case 5:
          visualizarWeatherData();
          break;
        case 6:
          gerarAlertaManual();
          break;
        case 0:
          System.out.println("Saindo...");
          break;
        default:
          System.out.println("Opção inválida!");
      }
    } while (option != 0);
  }

  private static User cadastrarUsuario() {
    int id = users.isEmpty() ? 1 : Collections.max(users.keySet()) + 1;
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    User user = new User(id, nome, email);
    users.put(id, user);
    return user;
  }

  private static User alterarUsuario() {
    if (users.isEmpty()) {
      System.out.println("Nenhum usuário cadastrado. Por favor, cadastre um usuário primeiro.");
      return cadastrarUsuario();
    }
    users.forEach((id, user) -> System.out.println("ID: " + id + ", Nome: " + user.getName()));
    System.out.print("ID do usuário: ");
    int id = Integer.parseInt(scanner.nextLine());
    User user = users.get(id);
    if (user == null) {
      return cadastrarUsuario();
    }
    System.out.println("Usuário alterado: " + user);
    return user;
  }

  private static void cadastrarArea() {
    System.out.print("ID da área: ");
    int id = Integer.parseInt(scanner.nextLine());
    System.out.print("Nome da área: ");
    String nome = scanner.nextLine();
    System.out.print("Localização: ");
    String local = scanner.nextLine();
    System.out.print("Tipo de vegetação: ");
    String vegetacao = scanner.nextLine();
    areas.add(new MonitoredArea(id, nome, local, vegetacao));
    System.out.println("Área cadastrada!");
  }

  private static void inserirWeatherData() {
    System.out.print("ID dos dados climáticos: ");
    int id = Integer.parseInt(scanner.nextLine());
    System.out.print("Temperatura: ");
    double temp = Double.parseDouble(scanner.nextLine());
    System.out.print("Umidade: ");
    double hum = Double.parseDouble(scanner.nextLine());
    System.out.print("Velocidade do vento: ");
    double vento = Double.parseDouble(scanner.nextLine());
    LocalDate data = LocalDate.now();
    System.out.println("Selecione a área monitorada (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    WheatherData wd = new WheatherData(id, temp, hum, vento, data, area);
    area.addWeatherData(wd);
    System.out.println("Dados climáticos inseridos!");
  }

  private static void monitorarArea() {
    System.out.println("Selecione a área para monitorar (ID): ");
    if (areas.isEmpty()) {
      System.out.println("Nenhuma área monitorada cadastrada!");
      return;
    }
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    user.monitor(area);
    double risco = area.calculateRiskLevel();
    if (risco == -1) {
      System.out.println("Nenhum dado climático encontrado para a área selecionada!");
      return;
    }
    System.out.println("Nível de risco calculado: " + risco);
    if (risco > 70) {
      System.out.println("ALERTA AUTOMÁTICO: Risco de incêndio alto!");
      AutomaticAlert alert = new AutomaticAlert(
          Math.toIntExact(System.currentTimeMillis() % Integer.MAX_VALUE),
          risco,
          LocalDate.now(),
          area,
          area.getLatestWeatherData());
      alert.generateAlert();
    } else {
      System.out.println("Risco dentro do normal.");
    }
  }

  private static void visualizarWeatherData() {
    System.out.println("Selecione a área (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    List<WheatherData> weatherDataList = area.getWeatherDataList();
    if (weatherDataList.isEmpty()) {
      System.out.println("Nenhum dado climático encontrado para esta área.");
      return;
    }
    System.out.println("Dados climáticos da área:");
    for (WheatherData wd : weatherDataList) {
      System.out.println("ID: " + wd.getId() + ", Data: " + wd.getDate() + ", Temperatura: " + wd.getTemperature() +
          ", Umidade: " + wd.getHumidity() + ", Vento: " + wd.getWindSpeed());
    }
  }

  private static void gerarAlertaManual() {
    System.out.println("Selecione a área (ID): ");
    List<MonitoredArea> userAreas = user.getMonitoredAreas();
    if (userAreas.isEmpty()) {
      System.out.println("Nenhuma área monitorada cadastrada!");
      return;
    }
    userAreas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    System.out.print("Informe o ID da área para gerar o alerta manual: ");
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = userAreas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    System.out.print("Informe o nível de risco para o alerta manual: ");
    double riskLevel = Double.parseDouble(scanner.nextLine());
    ManualAlert alert = new ManualAlert(
        Math.toIntExact(System.currentTimeMillis() % Integer.MAX_VALUE),
        riskLevel,
        LocalDate.now(),
        area,
        user);
    alert.generateAlert();
  }
}