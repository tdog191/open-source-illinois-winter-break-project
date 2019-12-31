# CS425-MPs
Completing MPs from CS 425 as a personal project

## Project Structure

### Main Structure
There are three main directories for this project:

1. `src/main`
2. `src/test`
3. `logs`

All source code goes inside the `src/main` directory. All tests go inside the `src/test/` directory. The `log` directory contains sample log files for each node in the distributed system.

### Gradle Files
This project is managed with Gradle 6.0.1. The project was initialized by running `gradle init`. This initialization created the following files:

1. `gradlew`
2. `gradlew.bat`
3. `build.gradle`
4. `gradle` directory
5. `settings.gradle`

`gradlew` and `gradlew.bat` are Gradle wrapper executables. The former is a shell script, and the latter is a Windows batch script. These executables can be used to run Gradle commands in this project. They download Gradle 6.0.1 as needed, so no prior installation of Gradle is necessary.

To add dependencies on libraries from other repositories (Maven, Ivy, etc.), declare them in `build.gradle`.

`gradle.init` also generated the `src` directories and the `.gitignore` and `.gitattributes` files.

## Project Implementation Overview
The distributed system in this project uses processes as the "nodes." Each process has a UDP socket to communicate with the other processes. The justification for using processes instead of VMs is to make it easy for this project to run locally on a single machine by requiring significantly less memory and RAM.

The nodes of the distributed system are spawned by the `Wrapper` class. To provide every node with full knowledge of the number of nodes in the distributed system and every node's IP address and port number, the `Wrapper` class adds these values to the nodes' environment variables.

To implement the distributed log queries from MP1 of CS 425/ECE 428, the `Wrapper` class randomly designates one of the spawned nodes as the "Queryer," and all the other spawned nodes are considered "passive." The Queryer is provided with a list of query commands (will likely be `grep` commands) in a file. For every command in this file, the Queryer runs the command on its own log file, sends the command to every other node in the distributed system to run it on their log files, then aggregates the query results in the provided output file. The Queryer is implemented in the `Queryer` class, and the passive implementation is in the `Passive` class.

## Building, Testing, and Managing the Project
Building, testing, and management are all performed with Gradle. To run Gradle commands, use the Gradle wrapper executable corresponding to your OS (`gradlew.bat` for Windows, `gradlew` for Linux and other OS's).

Here are some of the noteworthy Gradle tasks that can be run:

Gradle Task | Description
----------- | -----------
`run` | Runs this project as a JVM application
`assemble` | Assembles the outputs of this project.
`build` | Assembles and tests this project.
`clean` | Deletes the build directory.
`javadoc` | Generates Javadoc API documentation for the main source code.
`dependencies` | Displays all dependencies declared in root project
`tasks` | Displays the tasks runnable from root project
`test` | Runs the unit tests.

For example, to run the test task on Linux, run `./gradlew test`.

## Log Dataset
The machine log files were created from http://almhuette-raith.at/apache-log/access.log.
