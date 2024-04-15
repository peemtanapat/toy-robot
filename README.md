# Toy Robot Application

Toy Robot is a console Java application. The robot can be placed, moved, rotate and report the current position on the Table 5x5 dimensions. Thank you & please enjoy.

## Requirements

- Java 8 or higher is required for usage;
- Maven 3
- IntelliJ or Java IDE
- Operating system: no specific requirements (tested on macOS).

## How to run

### Option 1. Run with IDE

Open the project with IntelliJ or Java IDE and run the program with Main class (src/main/java/com/toyrobot/Main.java) without any parameter.

### Option 2. Run with Jar file

At the project root path run these commands:

```bash
cd out/artifacts/toy_robot_jar/
java -jar toy-robot.jar
```

After running application.
The console will be shown up with manual then you can type robot commands.
For example:

```bash
---Application Manual---
...
...
Enter Command: PLACE 1,2,NORTH
Enter Command: MOVE
Enter Command: LEFT
Enter Command: RIGHT
Enter Command: REPORT
```

## Run tests with Maven

```bash
mvn test
```

## Developer Profile

[LinkedIn peemtanapat](https://www.linkedin.com/in/peemtanapat/)
