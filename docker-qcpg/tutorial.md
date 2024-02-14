# Docker Container for Code to Neo4j Graph Translation

This tutorial guides you through the process of building a Docker container that facilitates the translation of code into a Neo4j graph and sets up the Neo4j database itself.

## Prerequisites

- Ensure Docker is installed on your machine. For Windows users, [Docker Hub](https://hub.docker.com/) is recommended.

## Setup Instructions

1. **(Optional) Configure Docker Compose**:

   - Open the [docker-compose.yml](./docker-compose.yml) file.
   - Set your username and password. If not set, the default credentials will be:
     - **Username**: `neo4j`
     - **Password**: `default1`
   - Note: Passwords must be at least 8 characters long.

2. **Download the Code Translator Image**:

   - Pull the image from the Docker repository using the command:
     ```shell
     docker pull carlosgpal02/cpg-image:1.0
     ```

3. **Build the Container**:

   - From this directory, build the container using the following command:
     ```shell
     docker-compose up
     ```
   - This step compiles the Docker container and starts the Neo4j database along with the code translator service.

4. **Post-Build Steps**:
   - It may be necessary to restart Docker once the build is complete to ensure everything is running smoothly.
