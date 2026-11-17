# Java Microservices Demo

A beginner-friendly Spring Boot project demonstrating five microservices concepts:

1. **API Gateway** – one entry point for requests.
2. **Service Discovery** – Eureka registers and finds services.
3. **Load Balancing** – Gateway routes `lb://PAYMENT-SERVICE` requests to available payment-service instances.
4. **Security** – Payment Service uses HTTP Basic authentication for this classroom demo.
5. **Integration** – Order Service calls Payment Service using a load-balanced REST client.

## Requirements

- JDK 17
- Maven 3.8+
- VS Code with Extension Pack for Java and Spring Boot Extension Pack

Check installation in VS Code terminal:

```bash
java -version
mvn -version
```

## Run the project

Open this root folder in VS Code. Open **four terminal windows** and run these commands from the project root, one in each terminal:

**Terminal 1 – Eureka Server**
```bash
mvn -pl eureka-server spring-boot:run
```

**Terminal 2 – Payment Service instance 1 (port 8081)**
```bash
mvn -pl payment-service spring-boot:run
```

**Terminal 3 – Payment Service instance 2 (port 8082)**
```bash
mvn -pl payment-service spring-boot:run -Dspring-boot.run.arguments=--server.port=8082
```

**Terminal 4 – Order Service**
```bash
mvn -pl order-service spring-boot:run
```

**Terminal 5 – API Gateway**
```bash
mvn -pl api-gateway spring-boot:run
```

Wait until all applications have started. The first Maven run may take a few minutes to download dependencies.

## Test in browser or terminal

Eureka dashboard:
- http://localhost:8761

Payment through API Gateway (requires login):
```bash
curl -u student:java123 http://localhost:8080/payment
```

Order Service through API Gateway:
```bash
curl http://localhost:8080/order
```

Direct payment instance 1:
```bash
curl -u student:java123 http://localhost:8081/payment
```

Direct payment instance 2:
```bash
curl -u student:java123 http://localhost:8082/payment
```

Expected payment response:
```text
Payment successful! Response from Payment Service on port 8081
```
The port may be `8081` or `8082`, depending on which instance handles the request.

Expected order response:
```text
Order created. Payment Service replied: Payment successful! Response from Payment Service on port 8081
```

## Security note

The username and password are hard-coded only for a local classroom demonstration. Do not use these credentials or this configuration in a real application. Production systems should use secure secret storage, HTTPS, stronger identity management and appropriate token-based authentication.

## Push this project to GitHub

1. Create a **new empty repository** on https://github.com/new (for example, `Java-Microservices`).
2. Extract this ZIP file.
3. In VS Code, open the extracted `Java-Microservices` folder (the folder containing this README and the root `pom.xml`).
4. Open Terminal and run:

```bash
git init
git add .
git commit -m "Add Java microservices demo"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/Java-Microservices.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username. If Git asks you to sign in, complete the GitHub sign-in prompt in your browser.

## Project structure

```text
Java-Microservices/
├── pom.xml
├── README.md
├── eureka-server/
├── payment-service/
├── order-service/
└── api-gateway/
```
