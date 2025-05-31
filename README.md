# IGNIRA

Este projeto é um sistema em Java para monitoramento de áreas ambientais, focado na avaliação de risco de incêndios florestais com base em dados climáticos. O sistema permite o cadastro de usuários, áreas monitoradas, inserção de dados meteorológicos, registro de incêndios e geração de alertas automáticos ou manuais.

## Funcionalidades

- **Cadastro de Usuários:** Permite criar e alterar entre usuários do sistema.
- **Cadastro de Áreas Monitoradas:** Registre áreas ambientais com informações de localização e vegetação.
- **Inserção de Dados Climáticos:** Adicione registros de temperatura, umidade e vento para cada área.
- **Registro de Incêndios:** Registre eventos de incêndio com data e severidade.
- **Monitoramento de Risco:** Calcule automaticamente o risco de incêndio com base nos dados mais recentes.
- **Geração de Alertas:**
  - **Automático:** Quando o risco ultrapassa um limite, um alerta automático é gerado.
  - **Manual:** Usuários podem gerar alertas manuais com descrição personalizada.
- **Visualização de Dados:** Consulte o histórico climático e de alertas de cada área.

## Estrutura das Classes Principais

- **User:** Representa um usuário do sistema.
- **MonitoredArea:** Representa uma área monitorada, contendo listas de dados climáticos e incêndios.
- **WheatherData:** Dados meteorológicos associados a uma área e data.
- **WildFire:** Evento de incêndio registrado em uma área.
- **Alert (abstract):** Alerta de risco, com subclasses:
  - **AutomaticAlert:** Gerado automaticamente a partir de dados climáticos.
  - **ManualAlert:** Criado manualmente por um usuário.

## Diagrama UML



## Como Executar

1. **Compilação:**  
   Compile todos os arquivos `.java` do projeto:
   ```sh
   javac -d bin src/domain/entities/*.java src/presentation/App.java

2. **Execução:**  
    Execute o programa principal:
    ```sh
    java -cp bin presentation.App

3. **Uso:**  
    Siga o menu interativo no terminal para cadastrar usuários, áreas, inserir dados e monitorar riscos.
    ## Exemplo de Uso
    ```sh
    --- MENU ---
    1. Alterar usuário
    2. Cadastrar área monitorada
    3. Inserir dados climáticos (WeatherData)
    4. Inserir reporte de incêndio (WildFire)
    5. Monitorar área (verificar risco e gerar alerta automático)
    6. Visualizar dados de uma área
    7. Gerar alerta manual
    0. Sair
    Escolha uma opção:
    ```
    ## Observações
    - Os dados são mantidos em memória (não há persistência em banco de dados).
    - O cálculo de risco pode ser ajustado conforme a necessidade.
    - O sistema é modular e pode ser expandido para integração com bancos de dados ou interfaces gráficas.

## Integrantes do grupo
- Gustavo - RM559098
- Matheus A. - RM555177
- Matheus Q. - RM558801