# Calculadora de IP

Uma aplicação Spring Boot que fornece funcionalidades de cálculo de endereços IP, incluindo endereço de rede, endereço de broadcast, número de hosts, notação CIDR e determinação da classe de rede.

## Funcionalidades

- Cálculo do endereço de rede a partir do IP e máscara de sub-rede
- Cálculo do endereço de broadcast
- Determinação do número de hosts disponíveis
- Cálculo da notação CIDR
- Identificação da classe de rede (A, B, C, D ou E)

## Stack Tecnológica

- Java
- Spring Boot
- Maven

## Estrutura do Projeto

```
src/main/java/com/example/ipcalculator/
├── IpCalculatorApplication.java    # Classe principal da aplicação
├── controller/
│   └── IpCalculatorController.java # Controlador REST para cálculos de IP
└── service/
    └── IpCalculatorService.java    # Lógica de negócio para cálculos de IP
```

## Endpoints da API

### Calcular Informações de IP
- **URL**: `/calculate`
- **Método**: `POST`
- **Parâmetros**:
  - `ipAddress`: Endereço IP (ex: "192.168.1.1")
  - `subnetMask`: Máscara de sub-rede (ex: "255.255.255.0")
- **Resposta**: Objeto JSON contendo:
  - `networkAddress`: Endereço de rede calculado
  - `broadcastAddress`: Endereço de broadcast calculado
  - `numberOfHosts`: Número de hosts disponíveis
  - `cidr`: Notação CIDR
  - `networkClass`: Classe da rede (A, B, C, D ou E)

## Exemplo de Uso

1. Inicie a aplicação:
```bash
mvn spring-boot:run
```

2. Envie uma requisição POST para calcular as informações do IP:
```bash
curl -X POST "http://localhost:8080/calculate" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "ipAddress=192.168.1.1&subnetMask=255.255.255.0"
```

3. Exemplo de Resposta:
```json
{
    "success": true,
    "networkAddress": "192.168.1.0",
    "broadcastAddress": "192.168.1.255",
    "numberOfHosts": 254,
    "cidr": 24,
    "networkClass": "Class C"
}
```

## Tratamento de Erros

A aplicação retorna mensagens de erro apropriadas em caso de entrada inválida:
```json
{
    "success": false,
    "error": "Formato de endereço IP ou máscara de sub-rede inválido"
}
```

## Compilando o Projeto

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Compile usando Maven:
```bash
mvn clean install
```

## Executando a Aplicação

Execute a aplicação usando:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Licença

Este projeto é open source e está disponível sob a Licença MIT.
