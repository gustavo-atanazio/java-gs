package presentation;

import domain.entities.*;
import java.util.*;
import java.time.LocalDate;

public class App {
  private static List<MonitoredArea> areas = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);
  private static HashMap<Integer, User> users = new HashMap<>();
  private static User user = cadastrarUsuario();

  /**
   * Ponto de entrada da aplicação. Exibe um menu para o usuário interagir com o
   * sistema,
   * permitindo operações como alterar usuário, cadastrar áreas monitoradas,
   * inserir dados
   * climáticos e de incêndio, monitorar áreas, visualizar dados e gerar alertas.
   * O menu continua sendo exibido até que a opção de sair seja selecionada.
   *
   * @param args Argumentos da linha de comando (não utilizados).
   */
  public static void main(String[] args) {
    int option;
    do {
      System.out.println("\n--- Ignira ( " + user.getName() + " ) ---");
      System.out.println("1. Alterar usuário");
      System.out.println("2. Cadastrar área monitorada");
      System.out.println("3. Inserir dados climáticos do sensor");
      System.out.println("4. Inserir dados climáticos manualmente (WeatherData)");
      System.out.println("5. Inserir reporte de incêndio (WildFire)");
      System.out.println("6. Monitorar área (verificar risco e gerar alerta automático)");
      System.out.println("7. Visualizar dados de uma área");
      System.out.println("8. Gerar alerta manual");
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
          inserirSensorData();
          break;
        case 4:
          inserirWeatherData();
          break;
        case 5:
          inserirWildFire();
          break;
        case 6:
          monitorarArea();
          break;
        case 7:
          visualizarArea();
          break;
        case 8:
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

  /**
   * Cadastra um novo usuário com o ID fornecido.
   * Solicita ao usuário o nome e o email via entrada padrão,
   * cria uma nova instância de {@link User} e a adiciona ao mapa de usuários.
   *
   * @param id o identificador único para o novo usuário
   * @return o objeto {@link User} criado e cadastrado
   */
  private static User cadastrarUsuario(int id) {
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    User user = new User(id, nome, email);
    users.put(id, user);
    return user;
  }

  /**
   * Cria e cadastra um novo usuário atribuindo um ID único.
   * O ID é definido como 1 se não houver usuários cadastrados,
   * caso contrário, é definido como o maior ID existente mais 1.
   *
   * @return o novo objeto User cadastrado.
   */
  private static User cadastrarUsuario() {
    int id = users.isEmpty() ? 1 : Collections.max(users.keySet()) + 1;
    return cadastrarUsuario(id);
  }

  /**
   * Permite alterar o usuário ativo, escolhendo um existente ou cadastrando um
   * novo usuário caso não haja usuários cadastrados
   * ou o ID informado não exista.
   *
   * <p>
   * Se não houver usuários cadastrados, solicita o cadastro de um novo usuário.
   * Caso contrário, exibe a lista de usuários cadastrados, solicita o ID do
   * usuário a ser alterado,
   * e retorna o usuário correspondente. Se o ID informado não existir, solicita o
   * cadastro de um novo usuário com o ID informado.
   * </p>
   *
   * @return o usuário alterado ou recém-cadastrado
   */
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
      return cadastrarUsuario(id);
    }
    System.out.println("Usuário alterado: " + user);
    return user;
  }

  /**
   * Solicita ao usuário as informações de uma nova área monitorada (ID, nome,
   * localização e tipo de vegetação),
   * cria uma instância de {@link MonitoredArea} com esses dados e a adiciona à
   * lista de áreas monitoradas.
   * Exibe uma mensagem de confirmação ao final do cadastro.
   */
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

  /**
   * Solicita ao usuário que selecione uma área monitorada pelo seu ID e insira uma quantidade de dados de sensor (weather data) para essa área.
   * Exibe a lista de áreas disponíveis, valida a seleção do usuário e, caso a área seja encontrada, solicita a quantidade de dados a ser inserida.
   * Em seguida, utiliza o método {@code useSensor} da área selecionada para registrar a quantidade informada.
   * Caso o ID informado não corresponda a nenhuma área, exibe uma mensagem de erro.
   */
  private static void inserirSensorData() {
    System.out.println("Selecione a área monitorada (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    System.out.print("Digite a quantidade de weather data: ");
    int quantity = Integer.parseInt(scanner.nextLine());
    area.useSensor(quantity);

  }

  /**
   * Solicita ao usuário os dados climáticos (ID, temperatura, umidade, velocidade
   * do vento)
   * e a seleção de uma área monitorada pelo seu ID. Cria um novo objeto
   * {@link WheatherData}
   * com as informações fornecidas e o adiciona à área monitorada selecionada.
   * Exibe mensagens apropriadas em caso de sucesso ou se a área não for
   * encontrada.
   *
   * Pré-condição: A lista 'areas' deve estar inicializada e conter objetos
   * {@link MonitoredArea}.
   * Utiliza o scanner para entrada de dados do usuário.
   */
  private static void inserirWeatherData() {
    System.out.println("Selecione a área monitorada (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    System.out.print("Temperatura: ");
    double temp = Double.parseDouble(scanner.nextLine());
    System.out.print("Umidade: ");
    double hum = Double.parseDouble(scanner.nextLine());
    System.out.print("Velocidade do vento: ");
    double vento = Double.parseDouble(scanner.nextLine());
    LocalDate data = LocalDate.now();
    WheatherData wd = new WheatherData(area.getWeatherDataList().size() + 1, temp, hum, vento, data);
    area.addWeatherData(wd);
    System.out.println("Dados climáticos inseridos!");
  }

  /**
   * Solicita ao usuário as informações necessárias para criar um novo incêndio
   * (WildFire),
   * incluindo data, gravidade e área monitorada. Em seguida, adiciona o
   * incêndio à área
   * selecionada, caso ela exista. Caso a área não seja encontrada, exibe uma
   * mensagem de erro.
   *
   * Pré-condição: A lista de áreas monitoradas ('areas') deve estar inicializada
   * e não nula.
   * Pós-condição: Um novo objeto WildFire é criado e associado à área monitorada
   * selecionada,
   * caso o ID da área seja válido.
   */
  private static void inserirWildFire() {
    System.out.println("Selecione a área monitorada (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    System.out.print("Data do incêndio (YYYY-MM-DD): ");
    LocalDate data = LocalDate.parse(scanner.nextLine());
    System.out.print("Gravidade do incêndio (0-100): ");
    int severity = Integer.parseInt(scanner.nextLine());
    WildFire wildFire = new WildFire(area.getWildFires().size() + 1, data, severity);
    area.addWildFire(wildFire);
  }

  /**
   * Monitora uma área selecionada calculando seu nível de risco e gerando um
   * alerta automático se o risco exceder 70%.
   * <p>
   * O método executa os seguintes passos:
   * <ul>
   * <li>Verifica se há áreas cadastradas. Se não houver, notifica o usuário e
   * retorna.</li>
   * <li>Pede ao usuário para selecionar uma área pelo ID.</li>
   * <li>Busca a área correspondente ao ID informado. Se não encontrar, notifica o
   * usuário e retorna.</li>
   * <li>Calcula o nível de risco da área e exibe o valor.</li>
   * <li>Se o risco for maior que 70%, gera e dispara um alerta automático para a
   * área.</li>
   * <li>Se o risco estiver dentro dos limites normais, notifica o usuário.</li>
   * </ul>
   */
  private static void monitorarArea() {
    if (areas.isEmpty()) {
      System.out.println("Nenhuma área cadastrada!");
      return;
    }
    System.out.println("Selecione a área para monitorar (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    user.monitor(area);
    double risco = area.calculateRiskLevel();
    System.out.println("Nível de risco calculado: " + risco + "%");
    if (risco > 70) {
      AutomaticAlert alert = area.generateAlert(
          risco,
          area.getLatestWeatherData());
      alert.throwAlert(area);
    } else {
      System.out.println("Risco dentro do normal.");
    }
  }

  /**
   * Exibe informações detalhadas sobre uma área monitorada selecionada pelo
   * usuário.
   * 
   * O método solicita ao usuário que selecione uma área pelo ID, exibe os dados
   * climáticos
   * e os relatórios de incêndio associados à área escolhida. Caso não haja dados
   * registrados,
   * mensagens apropriadas são exibidas.
   * 
   * Fluxo:
   * <ul>
   * <li>Lista todas as áreas disponíveis e solicita a seleção por ID.</li>
   * <li>Verifica se a área existe; caso contrário, informa que não foi
   * encontrada.</li>
   * <li>Exibe dados climáticos (se houver) da área selecionada.</li>
   * <li>Exibe relatórios de incêndio (se houver) da área selecionada.</li>
   * <li>Informa caso não existam dados climáticos ou incêndios registrados.</li>
   * </ul>
   */
  private static void visualizarArea() {
    System.out.println("Selecione a área (ID): ");
    areas.forEach(a -> System.out.println(a.getName() + " (ID: " + a.getId() + ")"));
    int areaId = Integer.parseInt(scanner.nextLine());
    MonitoredArea area = areas.stream().filter(a -> a.getId() == areaId).findFirst().orElse(null);
    if (area == null) {
      System.out.println("Área não encontrada!");
      return;
    }
    List<WheatherData> weatherDataList = area.getWeatherDataList();
    List<WildFire> wildFires = area.getWildFires();
    if (weatherDataList.isEmpty() && wildFires.isEmpty()) {
      System.out.println("Nenhum dado climático ou incêndio registrado para a área selecionada.");
      return;
    }

    if (!weatherDataList.isEmpty()) {
      System.out.println("Dados climáticos da área " + area.getName() + ":");
      for (WheatherData wd : weatherDataList) {
        System.out.println("ID: " + wd.getId() + ", Data: " + wd.getDate() + ", Temperatura: " + wd.getTemperature() +
            ", Umidade: " + wd.getHumidity() + ", Velocidade do Vento: " + wd.getWindSpeed());
      }
    } else {
      System.out.println("Nenhum dado climático registrado.");
    }

    if (wildFires.isEmpty()) {
      System.out.println("Nenhum incêndio reportado.");
      return;
    }
    System.out.println("Relatórios de incêndio na área " + area.getName() + ":");
    for (WildFire wf : wildFires) {
      System.out.println("ID: " + wf.getId() + ", Data: " + wf.getDate() + ", Gravidade: " + wf.getSeverity());
    }
  }

  /**
   * Gera um alerta manual para uma área monitorada pelo usuário.
   * <p>
   * O método exibe as áreas monitoradas cadastradas pelo usuário, solicita a
   * seleção de uma área pelo ID,
   * o nível de risco (%) e uma descrição para o alerta. Em seguida, cria e gera
   * um alerta manual para a área selecionada.
   * </p>
   * <ul>
   * <li>Se não houver áreas monitoradas cadastradas, exibe uma mensagem e
   * retorna.</li>
   * <li>Se o ID informado não corresponder a nenhuma área, exibe uma mensagem e
   * retorna.</li>
   * </ul>
   * 
   * Pré-condições:
   * <ul>
   * <li>O usuário deve possuir pelo menos uma área monitorada cadastrada.</li>
   * <li>O scanner deve estar inicializado.</li>
   * </ul>
   * 
   * Pós-condições:
   * <ul>
   * <li>Um alerta manual é gerado para a área selecionada, caso todas as
   * informações sejam válidas.</li>
   * </ul>
   */
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
    System.out.print("Informe o nível de risco para o alerta manual (%): ");
    double riskLevel = Double.parseDouble(scanner.nextLine());
    System.out.print("Descrição do alerta: ");
    String description = scanner.nextLine();
    ManualAlert alert = area.generateAlert(
        riskLevel,
        user,
        description);
    alert.throwAlert(area);
  }
}