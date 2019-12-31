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
