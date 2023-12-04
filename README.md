# POC-Microservices

## This is a proof of concept for microservices architecture using Docker, React, Spring Boot and MySql.

### The project is divided into 3 services:
- Frontend (React + Spring Boot)
- Backend (Spring Boot)
- Backend (Micronaut - wip)

### Other services that are used:
- Database (MySql)
- Reverse Proxy (Nginx)
- Service Discovery (Eureka)
- Tracing (Zipkin or Grafana Tempo)
- Monitoring (Prometheus, Spring Admin, Grafana)
- Logging (FileBeat, LogStash, ELK or Grafana Loki)
- Messaging (Kafka)
- API Monitoring (Spring Boot Actuator)
- API Documentation (Swagger - wip)
- Docker Compose
- Kubernetes (wip)

### Arhitecture Diagram
![Architecture Diagram](diagrama.png)

### Mermaid Diagram (wip)
[Mermaid Diagram](mermaid.md)

### How to run the project
- Clone the repository
- Install Gradle if you don't have it already
- Run ./buildProject.sh in the root folder. This will build the frontend and backend projects.
- Run `docker-compose up --build` in the root folder
- Open `http://localhost:18080` in your browser to see the frontend
- Open `http://localhost:8080/admin` in your browser to see the Spring Admin dashboard
- Open `http://localhost:9411/zipkin/` in your browser to see the tracing in Zipkin
- Open `http://localhost:9090/prometheus` in your browser to see the monitoring data in Prometheus
- Open `http://localhost:3000/grafana` in your browser to see the dashboards in Grafana
- Open `http://localhost:5601` in your browser to see the logs in Kibana
- Open 'http://localhost:29092/' in a Kafka client to see the messages in Kafka 

