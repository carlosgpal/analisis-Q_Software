# Usage Guide

## 1. Build the container

Instructions are specified in [tutorial.md](./docker-qcpg/tutorial.md).

## 2. Backend

- **Build the backend**: Tested for Java 17. If you have a higher version it doesn't affect, but a lower version can give problems. If you want to build with a lower version, specify it in the [pom.xml](./backend-qcpg/pom.xml).

  Execute the following command:

  ```shell
  mvn clean install
  ```

- If the Neo4j user and password are changed from step 1, you must change it too in [application.yml](./backend-qcpg/src/main/resources/application.yml).

- **To run it in Visual Studio Code (The execution on the server may vary in other IDEs)**: Right-click on [BackendQcpgApplication.java](./backend-qcpg/src/main/java/com/qcpg/backendqcpg/BackendQcpgApplication.java) -> Run Java, or use the Spring Boot Dashboard extension.

## 3. Frontend

- Build

  ```shell
  npm install
  ```

- Execute

  ```shell
  npm start
  ```
