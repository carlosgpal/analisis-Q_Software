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
      docker pull carlosgpal02/cpg-image:2.0
     ```

3. **Build the Container**:

   - From this directory, build the container using the following command:
     ```shell
      docker-compose up
     ```
   - This step compiles the Docker container and starts the Neo4j database along with the code translator service.

4. **Post-Build Steps**:
   - It may be necessary to restart Docker once the build is complete to ensure everything is running smoothly.

## Create Your Own CPG Image

If you wish to create your own environment with a custom CPG image, follow these steps:

### Extracting Necessary Files

1. **Extract Files from the Existing Image**:
   - Use Docker to run a container from the existing CPG image and extract the required files:
     - `server.py`
     - `Dockerfile`
     - `entrypoint.sh`
     - `docker-compose.yml`
     - The directory `kotlin-iterative-shell`
   - Use the Docker command `docker cp` to copy these files from the container to your host.

### Download the Specific CPG Software Version you need

2. **Download the `cpg-quantum-cpg` directory**:

   - Clone or download the quantum-cpg branch from this repository [Fraunhofer-AISEC CPG](https://github.com/Fraunhofer-AISEC/cpg/tree/quantum-cpg)

   Weiss, K., & Banse, C. (2022). A Language-Independent Analysis Platform for Source Code. https://doi.org/10.48550/arXiv.2203.08424

### Set Up with Docker Compose

3. **Prepare Docker Compose**:

   - Navigate to the directory containing the extracted files and the `cpg-quantum-cpg` directory.
   - Verify and modify the `docker-compose.yml` and `Dockerfile` as necessary.

4. **Build and Run the Docker Container**:
   - Execute:
     ```shell
      docker-compose up
     ```
   - This command builds the Docker container with your custom settings and starts the required services.
