# PokeAPI Example Project

## Introduction
This repository contains a simple Java project that interacts with the Pokémon API. The project is set up without any build tools, using only the Java Development Kit (JDK) for compilation and execution.

## Setup

### Prerequisites
- Java Development Kit (JDK) installed on your system.
- Visual Studio Code (VS Code) with the necessary extensions for Java development.
- The Pokémon API jar file and the `Pokemon` class file.

### Project Structure
The project should have the following structure:

```bash
pokeapiexample/
├── lib/
│ └── json-20240205.jar
├── src/
│ └── PokemonTeamBuilderApp.java
```

### Steps to Create the Project

1. **Open VS Code and Create a New Java Project:**
   - Press `Ctrl+Shift+P` to open the Command Palette.
   - Select "Java: Create Java Project" and choose "No build tools" as the project type.
   - Name the project `pokeapiexample`.

2. **Add the Pokémon File to the `src` Folder:**
   - Place the `PokemonTeamBuilderApp.java` file in the `src` directory.

3. **Add the JAR File to the `lib` Folder:**
   - Place the `json-20240205` file in the `lib` directory.

## Running the Project

To run the project, follow these steps:

1. **Navigate to the Project Directory in the Terminal:**
   ```bash
   cd pokeapiexample
2. **Compiling the Java File:**
   ```bash
   javac -cp "lib/*" ./src/PokemonTeamBuilderApp.java
3. **Running the Java File:**
   ```bash
   java -cp "lib/*" ./src/PokemonTeamBuilderApp


